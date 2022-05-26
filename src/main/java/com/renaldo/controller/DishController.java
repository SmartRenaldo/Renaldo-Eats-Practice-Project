package com.renaldo.controller;

import com.renaldo.common.BaseContextUtils;
import com.renaldo.common.R;
import com.renaldo.pojo.Dish;
import com.renaldo.service.DishOptionService;
import com.renaldo.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishOptionService dishOptionService;

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Dish dish) {
        String empUsername = (String) request.getSession().getAttribute("employee");
        BaseContextUtils.setCurrentUsername(empUsername);

        boolean save = dishService.save(dish);

        if (save) {
            return R.success("Save successfully!");
        }

        return R.error("Save failed!");
    }
}
