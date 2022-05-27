package com.renaldo.controller;

import com.renaldo.common.BaseContextUtils;
import com.renaldo.common.R;
import com.renaldo.dto.DishDto;
import com.renaldo.pojo.Dish;
import com.renaldo.pojo.DishOption;
import com.renaldo.service.DishOptionService;
import com.renaldo.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishOptionService dishOptionService;

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody DishDto dishDto) {
        String empUsername = (String) request.getSession().getAttribute("employee");
        BaseContextUtils.setCurrentUsername(empUsername);

        dishService.save(dishDto);

        return R.success("Save successfully!");
    }

    /**
     * paging by page, pageSize, querying by name (%name%), and sorting by `dateModified` desc
     * @param page
     * @param pageSize
     * @param name
     * @return page object
     */
    @GetMapping("/page")
    public R<Page<Dish>> page(int page, int pageSize, String name) {
        return R.success(dishService.findAllByNameContains(page, pageSize, name));
    }

    @GetMapping("/{id}")
    public R<DishDto> getDishDtoById(@PathVariable Long id) {
        DishDto dishDto = dishService.getDishDtoById(id);

        if (dishDto == null) {
            return R.error("No data!");
        }

        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody DishDto dishDto) {
        String empUsername = (String) request.getSession().getAttribute("employee");
        BaseContextUtils.setCurrentUsername(empUsername);

        dishService.update(dishDto);

        return R.success("Modify successfully!");
    }

    @DeleteMapping
    public R<String> delete(Long[] id) {
        for (Long i : id) {
            dishService.deleteDishById(i);
        }

        return R.success("Delete successfully!");
    }

    @PostMapping("/status/{statusCode}")
    public R<String> updateStatus(@PathVariable Integer statusCode, Long[] ids) {
        for (Long id : ids) {
            dishService.updateStatusById(statusCode, id);
        }

        return R.success("Modify successfully!");
    }
}
