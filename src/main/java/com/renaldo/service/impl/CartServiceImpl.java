package com.renaldo.service.impl;

import com.renaldo.common.BaseContextUtils;
import com.renaldo.dto.CartDto;
import com.renaldo.pojo.Cart;
import com.renaldo.pojo.Combo;
import com.renaldo.pojo.Customer;
import com.renaldo.pojo.Dish;
import com.renaldo.repositories.CartRepository;
import com.renaldo.service.CartService;
import com.renaldo.service.ComboService;
import com.renaldo.service.CustomerService;
import com.renaldo.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DishService dishService;

    @Autowired
    private ComboService comboService;

    @Override
    @Transactional
    public CartDto add(CartDto cartDto) {
        // get customerId
        Optional<Customer> currentCustomer = customerService.getCurrentCustomer();

        if (currentCustomer.isPresent()) {
            Customer customer = currentCustomer.get();
            Cart cart = new Cart();
            cart.setCustomer(customer);
            Optional<Cart> option = Optional.empty();

            // judge dish or combo
            if (cartDto.getDishId() != null) {
                Dish dish = dishService.findById(cartDto.getDishId());
                cart.setDish(dish);
                option = cartRepository.findByCustomerAndDish(cart.getCustomer(), cart.getDish());

            } else if (cartDto.getComboId() != null) {
                Combo combo = comboService.findById(cartDto.getComboId());
                cart.setCombo(combo);
                option = cartRepository.findByCustomerAndCombo(cart.getCustomer(), cart.getCombo());
            }

            CartDto dto = new CartDto();

            // if exists, add number and set DataModified
            if (option.isPresent()) {
                Cart cartPre = option.get();
                cartPre.setNumber(cartPre.getNumber() + 1);
                cartPre.setDateModified(new Date());
                BeanUtils.copyProperties(cartPre, dto);
            } else {
                BeanUtils.copyProperties(cartDto, cart, "customer", "dish", "combo");
                cart.setNumber(1);
                Cart save = cartRepository.save(cart);
                BeanUtils.copyProperties(save, dto);
            }

            if (dto.getCombo() != null) {
                dto.setComboId(dto.getCombo().getId());
            }

            if (dto.getCustomer() != null) {
                dto.setCustomerId(dto.getCustomer().getId());
            }

            if (dto.getDish() != null) {
                dto.setDishId(dto.getDish().getId());
            }

            return dto;
        }

        return null;
    }

    @Override
    @Transactional
    public CartDto sub(CartDto cartDto) {
        // get customerId
        Optional<Customer> currentCustomer = customerService.getCurrentCustomer();

        if (currentCustomer.isPresent()) {
            Customer customer = currentCustomer.get();
            Cart cart = new Cart();
            cart.setCustomer(customer);
            Optional<Cart> option = Optional.empty();

            // judge dish or combo
            if (cartDto.getDishId() != null) {
                Dish dish = dishService.findById(cartDto.getDishId());
                cart.setDish(dish);
                option = cartRepository.findByCustomerAndDish(cart.getCustomer(), cart.getDish());

            } else if (cartDto.getComboId() != null) {
                Combo combo = comboService.findById(cartDto.getComboId());
                cart.setCombo(combo);
                option = cartRepository.findByCustomerAndCombo(cart.getCustomer(), cart.getCombo());
            }

            CartDto dto = new CartDto();

            // if exists, add number and set DataModified
            if (option.isPresent()) {
                Cart cartPre = option.get();

                cartPre.setNumber(cartPre.getNumber() - 1);
                BeanUtils.copyProperties(cartPre, dto);

                if (cartPre.getNumber() == 0){
                    cartRepository.deleteById(cartPre.getId());
                }

                if (dto.getCombo() != null) {
                    dto.setComboId(dto.getCombo().getId());
                }

                if (dto.getCustomer() != null) {
                    dto.setCustomerId(dto.getCustomer().getId());
                }

                if (dto.getDish() != null) {
                    dto.setDishId(dto.getDish().getId());
                }

                return dto;
            }

        }

        return null;
    }

    @Override
    public List<CartDto> list() {
        Sort.TypedSort<Cart> sort = Sort.sort(Cart.class);
        Sort descending = sort.by(Cart::getDateModified).descending();

        Optional<Customer> currentCustomer = customerService.getCurrentCustomer();

        if (currentCustomer.isPresent()) {
            Customer curCustomer = currentCustomer.get();

            return cartRepository.findAll((Specification<Cart>) (root, query, criteriaBuilder) -> {
                ArrayList<Predicate> predicates = new ArrayList<>();
                Path<Customer> customer = root.get("customer");
                predicates.add(criteriaBuilder.equal(customer, curCustomer));

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }, descending).stream().map(i -> {
                CartDto cartDto = new CartDto();
                BeanUtils.copyProperties(i, cartDto);

                if (cartDto.getCombo() != null) {
                    cartDto.setComboId(cartDto.getCombo().getId());
                }

                if (cartDto.getCustomer() != null) {
                    cartDto.setCustomerId(cartDto.getCustomer().getId());
                }

                if (cartDto.getDish() != null) {
                    cartDto.setDishId(cartDto.getDish().getId());
                }

                return cartDto;
            }).collect(Collectors.toList());

        }

        return null;
    }

    @Override
    public void clean() {
        Optional<Customer> currentCustomer = customerService.getCurrentCustomer();
        cartRepository.deleteAllByCustomer(currentCustomer);
    }
}
