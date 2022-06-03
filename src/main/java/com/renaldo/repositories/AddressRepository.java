package com.renaldo.repositories;

import com.renaldo.pojo.Address;
import com.renaldo.pojo.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long>
        , QuerydslPredicateExecutor<Address>
        , JpaSpecificationExecutor<Address> {

    List<Address> findAllByCustomer(Customer customer);

    @Query("SELECT a FROM Address a WHERE a.isDefault=1 AND a.customer=:customer")
    Optional<Address> findDefault(@Param("customer") Customer customer);
}
