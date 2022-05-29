package com.renaldo.repositories;

import com.renaldo.pojo.Customer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>
        , QuerydslPredicateExecutor<Customer> {
}
