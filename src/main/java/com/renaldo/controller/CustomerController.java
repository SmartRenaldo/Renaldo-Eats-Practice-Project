package com.renaldo.controller;

import com.renaldo.common.CustomException;
import com.renaldo.common.R;
import com.renaldo.pojo.Customer;
import com.renaldo.service.CustomerService;
import com.renaldo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MailService mailService;

    /**
     * send verification code (4 digits) by email
     * if send successfully, store code into session
     * @param customer
     * @return
     */
    @PostMapping("/sendEmail")
    public R<String> sendEmail(HttpSession session, @RequestBody Customer customer) {
        if (customer.getEmail().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            String code = mailService.sendVerificationCode4Digits(customer.getEmail());
            session.setAttribute(customer.getEmail(), code);
            return R.success("Send successfully!");
        } else {
            throw new CustomException("Send failed!");
        }
    }

    /**
     * login function
     * compare the received code with session code
     * if compares successfully, 1. login successfully
     *                         , and do not have this user in database, 2. register automatically
     * @param session
     * @param map
     * @return
     */
    @PostMapping("/login")
    public R<Customer> login(HttpSession session, @RequestBody Map<String, Object> map) {
        String email = map.get("email").toString();
        String code = map.get("code").toString();
        String sessionCode = (String) session.getAttribute(email);

        if (sessionCode != null && sessionCode.equals(code)) {
            Customer customer = new Customer();
            customer.setEmail(email);
            Optional<Customer> optional = customerService.find(customer);

            if (!optional.isPresent()) {
                customer.setStatus(1);
                customerService.save(customer);
            }

            session.setAttribute("customer", customer.getEmail());
            return R.success(customer);
        }

        return R.error("Send failed!");
    }
}
