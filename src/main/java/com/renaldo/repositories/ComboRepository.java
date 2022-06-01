package com.renaldo.repositories;

import com.renaldo.pojo.Combo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComboRepository extends PagingAndSortingRepository<Combo, Long>
        , QuerydslPredicateExecutor<Combo>
        , JpaSpecificationExecutor<Combo> {

    @Override
    @Query("SELECT c FROM Combo c ORDER BY c.category.sort, c.dateModified desc ")
    Page<Combo> findAll(Pageable pageable);

    @Query("SELECT c FROM Combo c WHERE c.name LIKE %:name% ORDER BY c.category.sort, c.dateModified desc ")
    Page<Combo> findAllByNameContains(Pageable pageable, String name);

    @Query("SELECT COUNT(c) FROM Combo c where c.id IN (:ids) AND c.status=1")
    int getSellingStatusCount(@Param("ids") Long[] ids);

    @Query("DELETE FROM Combo c WHERE c.id IN (:ids)")
    @Modifying
    void deleteAllById(@Param("ids") Long[] ids);

    Combo getComboById(Long id);

    @Query("UPDATE Combo c set c.status=:status where c.id=:id")
    @Modifying
    void updateStatusById(@Param("status") Integer status, @Param("id") Long id);

    @Query("SELECT c FROM Combo c where c.category.id=:categoryId")
    List<Combo> getComboByCategoryId(@Param("categoryId") Long categoryId, Sort and);

    List<Combo> getComboByNameContains(String name, Sort and);
}
