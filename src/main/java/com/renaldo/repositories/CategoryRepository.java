package com.renaldo.repositories;

import com.renaldo.pojo.Category;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>
        , QuerydslPredicateExecutor<Category> {
    Category getCategoryById(Long id);
}
