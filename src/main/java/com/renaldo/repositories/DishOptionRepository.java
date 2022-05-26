package com.renaldo.repositories;

import com.renaldo.pojo.Dish;
import com.renaldo.pojo.DishOption;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DishOptionRepository extends PagingAndSortingRepository<DishOption, Long>
        , QuerydslPredicateExecutor<DishOption> {
    List<DishOption> findAllByDish(Dish dish);

    void deleteAllByDish(Dish dish);
}
