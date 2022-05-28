package com.renaldo.service.impl;

import com.renaldo.common.CustomException;
import com.renaldo.dto.ComboDto;
import com.renaldo.pojo.Combo;
import com.renaldo.pojo.ComboDish;
import com.renaldo.repositories.ComboRepository;
import com.renaldo.service.CategoryService;
import com.renaldo.service.ComboDishService;
import com.renaldo.service.ComboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    private ComboRepository comboRepository;

    @Autowired
    private ComboDishService comboDishService;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public void save(ComboDto comboDto) {
        Combo combo = new Combo();
        BeanUtils.copyProperties(comboDto, combo);
        combo.setCategory(categoryService.findById(comboDto.getCategoryId()).get());
        comboRepository.save(combo);

        List<ComboDish> comboDishes = comboDto.getComboDishes().stream().peek(i ->
                i.setComboId(combo.getId())).collect(Collectors.toList());

        comboDishService.saveAll(comboDishes);
    }

    @Override
    public Page<Combo> findAllByNameContains(int page, int pageSize, String name) {
        if (name == null) {
            return comboRepository.findAll(PageRequest.of(page - 1, pageSize));
        }

        return comboRepository.findAllByNameContains(PageRequest.of(page - 1, pageSize), name);
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        int count = comboRepository.getSellingStatusCount(ids);

        if (count > 0) {
            throw new CustomException("Combo is selling. Cannot delete!");
        }

        comboDishService.deleteAllByComboId(ids);
        comboRepository.deleteAllById(ids);
    }
}
