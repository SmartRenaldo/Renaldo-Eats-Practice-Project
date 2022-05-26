package com.renaldo.service.impl;

import com.renaldo.repositories.DishOptionRepository;
import com.renaldo.service.DishOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishOptionServiceImpl implements DishOptionService {
    @Autowired
    private DishOptionRepository dishOptionRepository;
}
