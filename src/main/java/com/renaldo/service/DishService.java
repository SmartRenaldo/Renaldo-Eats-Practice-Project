package com.renaldo.service;

import com.renaldo.dto.DishDto;
import com.renaldo.pojo.Category;
import com.renaldo.pojo.Dish;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DishService {

    void save(DishDto dishDto);

    Page<Dish> findAllByNameContains(int page, int pageSize, String nameContains);

    DishDto getDishDtoById(Long id);

    void update(DishDto dishDto);

    void deleteDishById(Long id);

    void updateStatusById(Integer statusCode, Long id);

    List<Dish> getDishByCategory(DishDto dishDto);

    List<Dish> getDishByName(DishDto dishDto);

    Dish findById(Long dishId);
}
