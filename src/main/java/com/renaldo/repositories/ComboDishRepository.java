package com.renaldo.repositories;

import com.renaldo.pojo.ComboDish;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComboDishRepository extends PagingAndSortingRepository<ComboDish, Long>
        , QuerydslPredicateExecutor<ComboDish> {
}
