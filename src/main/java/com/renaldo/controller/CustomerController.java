package com.renaldo.controller;

import com.renaldo.common.R;
import com.renaldo.pojo.Customer;
import com.renaldo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * send verification code by email
     * @param customer
     * @return
     */
    @PostMapping("/sendEmail")
    public R<String> sendEmail(Customer customer) {
        return null;
    }
}
