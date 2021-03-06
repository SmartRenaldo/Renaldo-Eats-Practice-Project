package com.renaldo.repositories;

import com.renaldo.pojo.ComboDish;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComboDishRepository extends PagingAndSortingRepository<ComboDish, Long>
        , QuerydslPredicateExecutor<ComboDish> {

    @Query("DELETE FROM ComboDish cd WHERE cd.combo.id IN (:ids) ")
    @Modifying
    void deleteAllByComboId(@Param("ids") Long[] ids);

    @Query("SELECT COUNT(cd) FROM ComboDish cd WHERE cd.dishId=:dishId")
    int getCountByDishId(@Param("dishId") Long dishId);

    @Query("SELECT cd FROM ComboDish cd WHERE cd.combo.id=:comboId")
    List<ComboDish> getAllByComboId(@Param("comboId") Long comboId);

    @Query("DELETE FROM ComboDish cd WHERE cd.combo.id=:comboId ")
    @Modifying
    void deleteAllByComboId(@Param("comboId") Long comboId);
}
