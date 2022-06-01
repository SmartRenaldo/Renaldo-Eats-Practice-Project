package com.renaldo.service.impl;

import com.renaldo.repositories.CartRepository;
import com.renaldo.service.CartSertvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartSertvice {

    @Autowired
    private CartRepository cartRepository;
}
