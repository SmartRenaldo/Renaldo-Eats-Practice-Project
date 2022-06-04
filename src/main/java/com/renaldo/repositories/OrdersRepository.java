package com.renaldo.repositories;

import com.renaldo.pojo.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long>
        , JpaSpecificationExecutor<Orders> {

    Page<Orders> findAllByCustomerId(Pageable pageable, Long customerId);

    Page<Orders> findAllByCustomerIdAndNameContains(Pageable pageable, Long customerId, String name);

}
