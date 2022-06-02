package com.renaldo.service;

import com.renaldo.dto.ComboDto;
import com.renaldo.pojo.Combo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComboService {
    void save(ComboDto comboDto);

    Page<Combo> findAllByNameContains(int page, int pageSize, String name);

    void delete(Long[] ids);

    ComboDto getComboDtoById(Long id);

    void updateStatusById(Integer status, Long id);

    void update(ComboDto comboDto);

    void updateCombo(Combo combo, Combo comboPer);

    List<Combo> getComboByCategory(ComboDto comboDto);

    List<Combo> getComboByName(ComboDto comboDto);

    Combo findById(Long comboId);
}
