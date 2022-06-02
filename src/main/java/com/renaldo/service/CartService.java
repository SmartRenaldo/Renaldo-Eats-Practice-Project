package com.renaldo.service;

import com.renaldo.dto.CartDto;

import java.util.List;

public interface CartService {
    CartDto add(CartDto cartDto);

    CartDto sub(CartDto cartDto);

    List<CartDto> list();

    void clean();
}
