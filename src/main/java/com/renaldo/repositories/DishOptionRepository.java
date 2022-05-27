package com.renaldo.repositories;

import com.renaldo.pojo.Dish;
import com.renaldo.pojo.DishOption;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DishOptionRepository extends PagingAndSortingRepository<DishOption, Long>
        , QuerydslPredicateExecutor<DishOption> {
    List<DishOption> findAllByDish(Dish dish);

    @Query("DELETE FROM DishOption where dish=:dish")
    @Modifying
    void deleteAllByDish(Dish dish);
}
