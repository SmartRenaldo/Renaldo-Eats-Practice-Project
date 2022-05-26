package com.renaldo.repositories;

import com.renaldo.pojo.Dish;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DishRepository extends PagingAndSortingRepository<Dish, Long>
        , QuerydslPredicateExecutor<Dish> {
}
