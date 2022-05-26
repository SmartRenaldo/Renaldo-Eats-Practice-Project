package com.renaldo.service;

import com.renaldo.pojo.DishOption;

import java.util.List;

public interface DishOptionService {
    /**
     * save all dish options
     * @param dishOptions
     */
    void saveAll(List<DishOption> dishOptions);
}
