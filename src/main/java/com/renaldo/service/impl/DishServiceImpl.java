package com.renaldo.service.impl;

import com.renaldo.dto.DishDto;
import com.renaldo.pojo.Category;
import com.renaldo.pojo.Dish;
import com.renaldo.pojo.DishOption;
import com.renaldo.repositories.DishRepository;
import com.renaldo.service.CategoryService;
import com.renaldo.service.DishOptionService;
import com.renaldo.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishOptionService dishOptionService;

    @Override
    @Transactional
    public void save(DishDto dishDto) {
        Category category = new Category();
        category.setId(dishDto.getCategoryId());
        dishDto.setCategory(categoryService.findById(dishDto.getCategoryId()).get());
        log.info(dishDto.getOptions().toString());

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDto, dish, "options", "categoryId", "copies");
        /*dish.setCategory(dishDto.getCategory());
        dish.setCode(dishDto.getCode());
        dish.setName(dishDto.getName());
        dish.setSort(dishDto.getSort());
        dish.setDateCreated(dishDto.getDateCreated());
        dish.setDateModified(dishDto.getDateModified());
        dish.setDescription(dishDto.getDescription());
        dish.setId(dishDto.getId());
        dish.setImage(dishDto.getImage());
        dish.setPrice(dishDto.getPrice());
        dish.setStatus(dishDto.getStatus());*/

        dishRepository.save(dish);

        if (dishDto.getOptions() != null && dishDto.getOptions().size() > 0) {
            //set dish as dish for all options
            dishDto.setOptions(dishDto.getOptions().stream().peek((option) ->
                    option.setDish(dish)).collect(Collectors.toList()));

            dishOptionService.saveAll(dishDto.getOptions());
        }
    }

    @Override
    public Page<Dish> findAllByNameContains(int page, int pageSize, String nameContains) {
        Sort.TypedSort<Dish> sort = Sort.sort(Dish.class);
        Sort and = sort.by(Dish::getDateModified).descending();

        if (nameContains == null) {
            return dishRepository.findAll(PageRequest.of(page - 1, pageSize, and));
        } else {
            return dishRepository.findAllByNameContains(PageRequest.of(page - 1, pageSize, and), nameContains);
        }
    }

    @Autowired
    private CategoryService categoryService;
}
