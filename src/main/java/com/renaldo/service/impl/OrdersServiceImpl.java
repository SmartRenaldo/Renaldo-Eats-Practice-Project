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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    @Transactional
    public Page<OrderDto> findAllByNameContains(int page, int pageSize, String name) {
        Sort.TypedSort<Orders> sort = Sort.sort(Orders.class);
        Sort descending = sort.by(Orders::getOrderTime).descending();
        Long currentId = BaseContextUtils.getCurrentId();

        Page<OrderDto> orderDtos;

        if (name == null) {
            orderDtos = ordersRepository.findAllByCustomerId(PageRequest.of(page - 1, pageSize, descending)
                    , currentId).map(o -> {
                OrderDto orderDto = new OrderDto(o);
                orderDto.setOrderDetails(orderDetailService.findAllByOrderId(o.getId()));

                return orderDto;
            });
            orderDtos.getContent().stream().peek(i ->
                    i.setOrderDetails(orderDetailService.findAllByOrderId(i.getId())));
        } else {
            orderDtos = ordersRepository.findAllByCustomerIdAndNameContains(
                    PageRequest.of(page - 1, pageSize, descending), currentId, name)
                    .map(o -> {
                        OrderDto orderDto = new OrderDto(o);
                        orderDto.setOrderDetails(orderDetailService.findAllByOrderId(o.getId()));

                        return orderDto;
                    });
        }

        log.info("orderDtos: {}", orderDtos);

        return orderDtos;
    }
}
