package com.renaldo.repositories;

import com.renaldo.pojo.Combo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComboRepository extends PagingAndSortingRepository<Combo, Long>
        , QuerydslPredicateExecutor<Combo> {

    @Query("SELECT c FROM Combo c ORDER BY c.category.sort, c.dateModified desc ")
    Page<Combo> findAll(Pageable pageable);

    @Query("SELECT c FROM Combo c WHERE c.name LIKE %:name% ORDER BY c.category.sort, c.dateModified desc ")
    Page<Combo> findAllByNameContains(Pageable pageable, String name);

}
