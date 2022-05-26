package com.renaldo.service;

import com.renaldo.dto.DishDto;
import com.renaldo.pojo.Dish;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface DishService {

    void save(DishDto dishDto);

    Page<Dish> findAllByNameContains(int page, int pageSize, String nameContains);

    DishDto getDishDtoById(Long id);

    void update(DishDto dishDto);
}
