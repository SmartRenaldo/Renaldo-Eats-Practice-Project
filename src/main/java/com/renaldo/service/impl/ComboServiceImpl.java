package com.renaldo.service.impl;

import com.renaldo.common.CustomException;
import com.renaldo.dto.ComboDto;
import com.renaldo.pojo.Combo;
import com.renaldo.pojo.ComboDish;
import com.renaldo.repositories.ComboRepository;
import com.renaldo.service.CategoryService;
import com.renaldo.service.ComboDishService;
import com.renaldo.service.ComboService;
import com.renaldo.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
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

    @Autowired
    private DishService dishService;

    @Override
    @Transactional
    public void save(ComboDto comboDto) {
        Combo combo = new Combo();
        BeanUtils.copyProperties(comboDto, combo);
        combo.setCategory(categoryService.findById(comboDto.getCategoryId()).get());
        comboRepository.save(combo);

        List<ComboDish> comboDishes = comboDto.getComboDishes().stream().peek(i ->
                i.setCombo(combo)).collect(Collectors.toList());

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

    @Override
    public ComboDto getComboDtoById(Long id) {
        ComboDto comboDto = new ComboDto();
        Combo combo = comboRepository.getComboById(id);
        BeanUtils.copyProperties(combo, comboDto);
        comboDto.setComboDishes(comboDishService.getAllByComboId(id));

        return comboDto;
    }

    @Override
    @Transactional
    public void updateStatusById(Integer status, Long id) {
        Combo combo = new Combo();
        combo.setStatus(status);
        combo.setId(id);
        if (comboRepository.findById(combo.getId()).isPresent()) {
            Combo comboPer = comboRepository.findById(combo.getId()).get();
            updateCombo(combo, comboPer);
        } else {
            throw new CustomException("Combo with id " + combo.getId() + " not found!");
        }
    }

    @Override
    @Transactional
    public void update(ComboDto comboDto) {
        Combo combo = new Combo();
        BeanUtils.copyProperties(comboDto, combo);
        combo.setCategory(categoryService.findById(comboDto.getCategoryId()).get());

        if (comboRepository.findById(combo.getId()).isPresent()) {
            Combo comboPer = comboRepository.findById(combo.getId()).get();
            updateCombo(combo, comboPer);

            /*
                dishOptionService.deleteAllByDish(dish);

                List<DishOption> dishOptions = dishDto.getOptions().stream().peek((option) ->
                        option.setDish(dish)).collect(Collectors.toList());
                dishOptionService.saveAll(dishOptions);
             */
            comboDto.setComboDishes(comboDto.getComboDishes().stream().peek(i ->
                    i.setCombo(comboPer)).collect(Collectors.toList()));

            comboDishService.deleteAllByComboId(combo.getId());
            comboDishService.saveAll(comboDto.getComboDishes());
        } else {
            throw new CustomException("Combo with id " + combo.getId() + " not found!");
        }
    }

    @Override
    @Transactional
    public void updateCombo(Combo combo, Combo comboPer) {
        if (combo.getCategory() != null) {
            comboPer.setCategory(combo.getCategory());
        }

        if (StringUtils.hasText(combo.getCode())) {
            comboPer.setCode(combo.getCode());
        }

        if (StringUtils.hasText(combo.getDescription())) {
            comboPer.setDescription(combo.getDescription());
        }

        if (StringUtils.hasText(combo.getImage())) {
            comboPer.setImage(combo.getImage());
        }

        if (StringUtils.hasText(combo.getName())) {
            comboPer.setName(combo.getName());
        }

        if (combo.getPrice() != null && combo.getPrice().compareTo(BigDecimal.ZERO) >= 0) {
            comboPer.setPrice(combo.getPrice());
        }

        if (combo.getStatus() != null && combo.getStatus() >= 0) {
            comboPer.setStatus(combo.getStatus());
        }
    }
}
