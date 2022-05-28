package com.renaldo.service;

import com.renaldo.dto.ComboDto;
import com.renaldo.pojo.Combo;
import org.springframework.data.domain.Page;

public interface ComboService {
    void save(ComboDto comboDto);

    Page<Combo> findAllByNameContains(int page, int pageSize, String name);

    void delete(Long[] ids);

    ComboDto getComboDtoById(Long id);

    void updateStatusById(Integer statusCode, Long id);
}
