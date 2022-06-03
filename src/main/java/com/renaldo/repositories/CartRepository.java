package com.renaldo.repositories;

import com.renaldo.pojo.*;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends PagingAndSortingRepository<Cart, Long>
        , JpaSpecificationExecutor<Cart> {

    Optional<Cart> findByCustomerAndCombo(Customer customer, Combo combo);

    Optional<Cart> findByCustomerAndDish(Customer customer, Dish dish);

    void deleteAllByCustomer(Optional<Customer> currentCustomer);

    List<Cart> findAllByCustomer(Customer customer);
}
