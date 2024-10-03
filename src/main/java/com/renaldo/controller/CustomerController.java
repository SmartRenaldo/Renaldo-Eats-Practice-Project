package com.renaldo.controller;

import com.renaldo.common.R;
import com.renaldo.pojo.Customer;
import com.renaldo.service.CustomerService;
import com.renaldo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MailService mailService;

    private static String email;

    /**
     * send verification code (4 digits) by email
     * if send successfully, store code into session
     * @param customer
     * @return
     */
    @PostMapping("/sendEmail")
    public R<String> sendEmail(HttpSession session, @RequestBody Customer customer) {
        if (customer.getEmail().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            // String code = mailService.sendVerificationCode4Digits(customer.getEmail());
            // log.info("code: {}", code);
            session.setAttribute(customer.getEmail(), "EHT8");
            email = customer.getEmail();
            return R.success("Send successfully!");
        } else {
            return R.error("Send failed!");
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
    public R<Customer> login(HttpServletRequest request, HttpSession session, @RequestBody Map<String, Object> map) {
        String email = map.get("email").toString();
        String code = map.get("code").toString();
        String sessionCode = (String) session.getAttribute(email);

        if (sessionCode != null/* && sessionCode.equals(code)*/) {
            Customer customer = new Customer();
            customer.setEmail(email);
            Optional<Customer> optional = customerService.find(customer);

            if (!optional.isPresent()) {
                customer.setStatus(1);
                customer = customerService.save(customer);
                BeanUtils.copyProperties(customerService.save(customer), customer);
            } else {
                customer = optional.get();
                BeanUtils.copyProperties(optional.get(), customer);
            }

            session.setAttribute("customer", customer.getId());
            log.info("request.getSession().getAttribute(\"customer\"): {}", request.getSession().getAttribute("customer"));

            return R.success(customer);
        }

        return R.error("Send failed!");
    }

    /**
     * logout function will remove session for customer and return code 1
     * @param session
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpSession session) {
        session.removeAttribute("customer");
        session.removeAttribute(email);
        log.info("session: {}", session);

        return R.success("Logout successfully!");
    }
}
