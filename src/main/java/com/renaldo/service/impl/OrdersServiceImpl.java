package com.renaldo.service.impl;

import com.renaldo.common.BaseContextUtils;
import com.renaldo.common.CustomException;
import com.renaldo.common.R;
import com.renaldo.dto.OrderDto;
import com.renaldo.pojo.*;
import com.renaldo.repositories.OrdersRepository;
import com.renaldo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    @Transactional
    public R<String> submit(Orders orders) {
        //Query user data
        Optional<Customer> currentCustomer = customerService.getCurrentCustomer();

        if (!currentCustomer.isPresent()) {
            throw new CustomException("Customer error! Cannot submit order!");
        }

        //Query the current user's shopping cart data
        List<Cart> carts = cartService.findAllByCustomer(currentCustomer);

        //isEmpty() in ArrayList: @return {@code true} if this list contains no elements (size == 0)
        if (carts == null || carts.isEmpty()) {
            throw new CustomException("Cart is empty. Cannot submit order!");
        }

        //Insert data into the order details table (multiple pieces of data)
        List<OrderDetail> orderDetails = carts.stream().map(i -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setNumber(i.getNumber());
            if (i.getDishOption() != null) {
                orderDetail.setDishOption(i.getDishOption());
            }

            if (i.getDish() != null) {
                orderDetail.setDishId(i.getDish().getId());
            }

            if (i.getCombo() != null) {
                orderDetail.setComboId(i.getCombo().getId());
            }

            orderDetail.setName(i.getName());
            orderDetail.setImage(i.getImage());
            orderDetail.setAmount(i.getAmount().multiply(BigDecimal.valueOf(i.getNumber())));

            return orderDetail;
        }).collect(Collectors.toList());

        BigDecimal amount = carts.stream().map(i ->
                i.getAmount().multiply(BigDecimal.valueOf(i.getNumber())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //Query address data
        Optional<Address> byId = addressService.findById(orders.getAddressId());

        if (!byId.isPresent()) {
            throw new CustomException("Address error! Cannot submit order!");
        }

        Address address = byId.get();

        //Insert data into the tb_order table (one piece of data)
        orders.setStatus(2);
        orders.setCustomerId(currentCustomer.get().getId());
        orders.setCustomerName(currentCustomer.get().getName());
        orders.setName(address.getName());
        orders.setPhone(address.getPhone());
        orders.setAddress(address.getDetail() == null ? "" : address.getDetail());
        orders.setAmount(amount);

        ordersRepository.save(orders);

        orderDetails = orderDetails.stream().peek(i ->
                i.setOrderId(orders.getId())).collect(Collectors.toList());

        orderDetailService.savaAll(orderDetails);

        //Clear shopping cart data
        cartService.clean();

        return null;
    }

    /**
     * only search this customer's order, sorting by orderTime desc
     *
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public Page<OrderDto> findAllByOrderDto(OrderDto orderDto) {
        Sort.TypedSort<Orders> sort = Sort.sort(Orders.class);
        Sort descending = sort.by(Orders::getOrderTime).descending();
        Long currentId = BaseContextUtils.getCurrentId();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer page = orderDto.getPage();
        Integer pageSize = orderDto.getPageSize();

        Page<OrderDto> orderDtos;

        orderDtos = ordersRepository
                .findAll((Specification<Orders>) (root, query, criteriaBuilder) -> {
                    ArrayList<Predicate> predicates = new ArrayList<>();
                    Path<Long> id = root.get("id");
                    Path<Long> customerId = root.get("customerId");
                    Path<String> name = root.get("name");
                    Path<Date> orderTime = root.get("orderTime");

                    if (orderDto.getName() != null) {
                        predicates.add(criteriaBuilder.like(name, "%" + orderDto.getName() + "%"));
                    }

                    if (currentId != null) {
                        predicates.add(criteriaBuilder.equal(customerId, currentId));
                    } else if (orderDto.getId() != null) {
                        predicates.add(criteriaBuilder.equal(id, orderDto.getId()));
                    }

                    if (orderDto.getBeginTime() != null) {
                        try {
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(orderTime, simpleDateFormat.parse(orderDto.getBeginTime())));
                        } catch (ParseException e) {
                            throw new CustomException("Date format error!");
                        }
                    }

                    if (orderDto.getEndTime() != null) {
                        try {
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(orderTime, simpleDateFormat.parse(orderDto.getEndTime())));
                        } catch (ParseException e) {
                            throw new CustomException("Date format error!");
                        }
                    }

                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }, PageRequest.of(page - 1, pageSize, descending))
                .map(o -> {
                    OrderDto orderDtoRes = new OrderDto(o);
                    orderDtoRes.setOrderDetails(orderDetailService.findAllByOrderId(o.getId()));

                    return orderDtoRes;
                });

        return orderDtos;
    }

    @Override
    @Transactional
    public void update(OrderDto orderDto) {
        Optional<Orders> byId = ordersRepository.findById(orderDto.getId());

        if (byId.isPresent()) {
            if (orderDto.getStatus() != null) {
                byId.get().setStatus(orderDto.getStatus());
            }

            return;
        }

        throw new CustomException("No such order!");
    }
}
