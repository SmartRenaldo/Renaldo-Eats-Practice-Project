package com.renaldo.repositories;

import com.renaldo.pojo.Cart;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CartRepository extends PagingAndSortingRepository<Cart, Long>
        , JpaSpecificationExecutor<Cart> {
}
