package com.renaldo.service.impl;

import com.renaldo.pojo.DishOption;
import com.renaldo.repositories.DishOptionRepository;
import com.renaldo.service.DishOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishOptionServiceImpl implements DishOptionService {
    @Autowired
    private DishOptionRepository dishOptionRepository;

    @Override
    public void saveAll(List<DishOption> dishOptions) {
        dishOptionRepository.saveAll(dishOptions);
    }
}
