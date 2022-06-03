package com.renaldo.service;

import com.renaldo.dto.CartDto;
import com.renaldo.pojo.Cart;
import com.renaldo.pojo.Customer;

import java.util.List;
import java.util.Optional;

public interface CartService {
    CartDto add(CartDto cartDto);

    CartDto sub(CartDto cartDto);

    List<CartDto> list();

    void clean();

    List<Cart> findAllByCustomer(Optional<Customer> currentCustomer);
}
