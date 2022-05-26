package com.renaldo.repositories;

import com.renaldo.pojo.Category;
import com.renaldo.pojo.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DishRepository extends PagingAndSortingRepository<Dish, Long>
        , QuerydslPredicateExecutor<Dish> {
    Page<Dish> findAllByNameContains(Pageable pageable, String nameContains);

    /**
     * @Query("UPDATE Customer set name=:name where id=:id")
     *     @Modifying  //modify spring data this is CUD not R
     *     @Transactional
     *     int updateCustomerNameById(@Param("name") String name, @Param("id")Long id);
     */

    @Query("UPDATE Dish set name=:name, category=:category, price=:price, image=:image, description=:description where id=:id")
    @Modifying
    int updateDishNameCategoryPriceImageAndDescriptionById(@Param("name") String name
            , @Param("category") Category category, @Param("price") BigDecimal price
            , @Param("image") String image, @Param("description") String description, @Param("id") Long id);
}
