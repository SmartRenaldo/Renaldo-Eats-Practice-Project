package com.renaldo.repositories;

import com.renaldo.pojo.DishOption;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DishOptionRepository extends PagingAndSortingRepository<DishOption, Long>
        , QuerydslPredicateExecutor<DishOption> {
}
