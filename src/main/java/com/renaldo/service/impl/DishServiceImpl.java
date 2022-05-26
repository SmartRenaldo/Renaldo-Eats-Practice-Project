package com.renaldo.service.impl;

import com.renaldo.pojo.Dish;
import com.renaldo.repositories.DishRepository;
import com.renaldo.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public boolean save(Dish dish) {
        Dish save = dishRepository.save(dish);

        if (dish != null) {
            return true;
        }

        return false;
    }
}
