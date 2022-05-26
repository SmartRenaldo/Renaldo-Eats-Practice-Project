package com.renaldo.dto;

import com.renaldo.pojo.Dish;
import com.renaldo.pojo.DishOption;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishOption> options = new ArrayList<>();

    private Long categoryId;

    private Integer copies;
}
