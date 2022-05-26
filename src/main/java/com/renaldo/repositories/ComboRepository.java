package com.renaldo.repositories;

import com.renaldo.pojo.Combo;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComboRepository extends PagingAndSortingRepository<Combo, Long>
        , QuerydslPredicateExecutor<Combo> {
}
