package com.renaldo.service.impl;

import com.renaldo.pojo.ComboDish;
import com.renaldo.repositories.ComboDishRepository;
import com.renaldo.service.ComboDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ComboDishServiceImpl implements ComboDishService {

    @Autowired
    private ComboDishRepository comboDishRepository;

    @Override
    public void saveAll(List<ComboDish> comboDishes) {
        comboDishRepository.saveAll(comboDishes);
    }

    @Override
    public void deleteAllByComboId(Long[] ids) {
        comboDishRepository.deleteAllByComboId(ids);
    }

    @Override
    public int getCountByDishId(Long dishId) {
        return comboDishRepository.getCountByDishId(dishId);
    }

    @Override
    public List<ComboDish> getAllByComboId(Long comboId) {
        return comboDishRepository.getAllByComboId(comboId);
    }

    @Override
    public void deleteAllByComboId(Long comboId) {
        comboDishRepository.deleteAllByComboId(comboId);
    }
}
