package com.renaldo.service;

import com.renaldo.pojo.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> find(Customer customer);

    void save(Customer customer);
}
