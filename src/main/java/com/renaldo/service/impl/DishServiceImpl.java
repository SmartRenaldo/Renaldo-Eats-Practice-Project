package com.renaldo.service.impl;

import com.renaldo.common.CustomException;
import com.renaldo.dto.DishDto;
import com.renaldo.pojo.Category;
import com.renaldo.pojo.Dish;
import com.renaldo.pojo.DishOption;
import com.renaldo.repositories.DishRepository;
import com.renaldo.service.CategoryService;
import com.renaldo.service.ComboDishService;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishOptionService dishOptionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ComboDishService comboDishService;

    @Override
    @Transactional
    public void save(DishDto dishDto) {
        Category category = new Category();
        category.setId(dishDto.getCategoryId());
        dishDto.setCategory(categoryService.findById(dishDto.getCategoryId()).get());
        log.info(dishDto.getOptions().toString());

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDto, dish, "options", "categoryId", "copies");

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
        if (nameContains == null) {
            return dishRepository.findAll(PageRequest.of(page - 1, pageSize));
        } else {
            return dishRepository.findAllByNameContains(nameContains, PageRequest.of(page - 1, pageSize));
        }
    }

    @Override
    @Transactional
    public DishDto getDishDtoById(Long id) {
        Optional<Dish> byId = dishRepository.findById(id);

        if (byId.isPresent()) {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(byId.get(), dishDto);
            List<DishOption> dishOptions = dishOptionService.findByDish(byId.get());
            dishDto.setOptions(dishOptions);

            return dishDto;
        }

        return null;
    }

    /**
     * the method in DishOptionRepository deleteAllByDish(Dish dish) should be override.
     * This means two annotations need to be added:
     *      `@Query("DELETE FROM DishOption where dish=:dish")`
     *      `@Modifying`
     * If this method is not be overrode, this delete method will delete the dependant data (normally will fail),
     * because of foreign key constraints
     * Error message: Cannot delete or update a parent row: a foreign key constraint fails
     *      (`renaldo_eats`.`tb_dish`, CONSTRAINT `FKq4jvrf5j7puei9vv2otm4e92s` FOREIGN KEY (`category_id`)
     *      REFERENCES `tb_category` (`id`))
     * @param dishDto
     */
    @Override
    @Transactional
    public void update(DishDto dishDto) {
        dishRepository.updateDishNameCategoryPriceImageAndDescriptionById(dishDto.getName(), dishDto.getCategory()
                , dishDto.getPrice(), dishDto.getImage(), dishDto.getDescription(), dishDto.getId());
        Optional<Dish> byId = dishRepository.findById(dishDto.getId());
        Dish dish = byId.get();
        dishOptionService.deleteAllByDish(dish);

        List<DishOption> dishOptions = dishDto.getOptions().stream().peek((option) ->
                option.setDish(dish)).collect(Collectors.toList());
        dishOptionService.saveAll(dishOptions);
    }

    /**
     * the method in DishRepository deleteById(@Param("id") Long id) should be override.
     * This means this method should be specified like this:
     *      `@Query("DELETE FROM Dish where id=:id")`
     *      `@Modifying`
     *      `void deleteById(@Param("id") Long id);`
     * If this method is not be overrode, this delete method will delete the dependant data (normally will fail),
     * because of foreign key constraints
     * Error message: Cannot delete or update a parent row: a foreign key constraint fails
     *      (`renaldo_eats`.`tb_dish`, CONSTRAINT `FKq4jvrf5j7puei9vv2otm4e92s` FOREIGN KEY (`category_id`)
     *      REFERENCES `tb_category` (`id`))
     */
    @Override
    @Transactional
    public void deleteDishById(Long id) {
        Optional<Dish> byId = dishRepository.findById(id);
        Dish dish = byId.get();

        int count = comboDishService.getCountByDishId(id);

        if (count > 0) {
            throw new CustomException("Dish '" + dish.getName() + "' is associated with combo. Delete failed!");
        }

        if (dish.getStatus() == 1) {
            throw new CustomException("Dish is selling. Cannot delete!");
        }

        dishOptionService.deleteAllByDish(dish);
        dishRepository.deleteById(dish.getId());
    }

    @Override
    @Transactional
    public void updateStatusById(Integer statusCode, Long id) {
        dishRepository.updateStatusById(statusCode, id);
    }

    @Override
    @Transactional
    public List<Dish> getDishByCategory(DishDto dishDto) {
        Sort.TypedSort<Dish> sort = Sort.sort(Dish.class);
        Sort and = sort.by(Dish::getCategory).ascending().and(sort.by(Dish::getDateModified).descending());

        return dishRepository.getDishByCategoryId(dishDto.getCategoryId(), and);
    }

    @Override
    public List<Dish> getDishByName(DishDto dishDto) {
        Sort.TypedSort<Dish> sort = Sort.sort(Dish.class);
        Sort and = sort.by(Dish::getCategory).ascending().and(sort.by(Dish::getDateModified).descending());

        return dishRepository.getDishByNameContains(dishDto.getName(), sort);
    }
}
