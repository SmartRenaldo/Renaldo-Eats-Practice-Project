package com.renaldo.repositories;

import com.renaldo.pojo.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DishRepository extends PagingAndSortingRepository<Dish, Long>
        , QuerydslPredicateExecutor<Dish> {
    Page<Dish> findAllByNameContains(Pageable pageable, String nameContains);
}
