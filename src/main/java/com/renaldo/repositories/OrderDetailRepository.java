package com.renaldo.repositories;

import com.renaldo.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Long>
        , JpaSpecificationExecutor<OrderDetail> {
}
