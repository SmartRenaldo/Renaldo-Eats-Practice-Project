package com.renaldo.service.impl;

import com.renaldo.repositories.CustomerRepository;
import com.renaldo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
}
