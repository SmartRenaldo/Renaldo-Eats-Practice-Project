package com.renaldo.service;

import com.renaldo.pojo.ComboDish;

import java.util.List;

public interface ComboDishService {
    void saveAll(List<ComboDish> comboDishes);

    void deleteAllByComboId(Long[] ids);

    int getCountByDishId(Long id);

    List<ComboDish> getAllByComboId(Long comboId);

    void deleteAllByComboId(Long comboId);
}
