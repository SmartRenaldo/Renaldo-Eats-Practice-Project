package com.renaldo.service.impl;

import com.renaldo.common.CustomException;
import com.renaldo.pojo.Customer;
import com.renaldo.repositories.CustomerRepository;
import com.renaldo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Optional<Customer> find(Customer customer) {
        Optional<Customer> one = customerRepository.findOne((Specification<Customer>) (root, query, criteriaBuilder) -> {
            Path<Long> id = root.get("id");
            Path<String> name = root.get("name");
            Path<String> email = root.get("email");
            Path<String> gender = root.get("gender");
            Path<String> photo = root.get("photo");
            Path<Integer> status = root.get("status");

            ArrayList<Predicate> predicates = new ArrayList<>();

            if (customer.getId() != null && customer.getId() > -1) {
                predicates.add(criteriaBuilder.equal(id, customer.getId()));
            }

            if (StringUtils.hasText(customer.getName())) {
                predicates.add(criteriaBuilder.equal(name, customer.getName()));
            }

            if (StringUtils.hasText(customer.getEmail())) {
                predicates.add(criteriaBuilder.equal(email, customer.getEmail()));
            }

            if (StringUtils.hasText(customer.getGender())) {
                predicates.add(criteriaBuilder.equal(gender, customer.getGender()));
            }

            if (StringUtils.hasText(customer.getPhoto())) {
                predicates.add(criteriaBuilder.equal(photo, customer.getPhoto()));
            }

            if (customer.getStatus() != null && customer.getStatus() > -1) {
                predicates.add(criteriaBuilder.equal(status, customer.getStatus()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

        return one;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
