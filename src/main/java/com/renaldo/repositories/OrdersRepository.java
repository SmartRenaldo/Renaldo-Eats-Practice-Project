package com.renaldo.repositories;

import com.renaldo.pojo.Orders;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long>
        , JpaSpecificationExecutor<Orders> {
}
