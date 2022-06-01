package com.renaldo.controller;

import com.renaldo.common.R;
import com.renaldo.dto.ComboDto;
import com.renaldo.dto.DishDto;
import com.renaldo.pojo.Combo;
import com.renaldo.pojo.Dish;
import com.renaldo.service.ComboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/combos")
public class ComboController {

    @Autowired
    private ComboService comboService;

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody ComboDto comboDto) {
        comboService.save(comboDto);

        return R.success("Save successfully!");
    }

    /**
     * paging by page and pageSize, querying by name (%name%) (priority), and sorting by `dateModified` desc
     * @param page
     * @param pageSize
     * @param name
     * @return page object
     */
    @GetMapping("/page")
    public R<Page<Combo>> page(int page, int pageSize, String name) {
        return R.success(comboService.findAllByNameContains(page, pageSize, name));
    }

    @DeleteMapping
    public R<String> delete(Long ...ids) {
        comboService.delete(ids);
        return R.success("Delete successfully!");
    }

    @GetMapping("/{id}")
    public R<ComboDto> getComboById(@PathVariable Long id) {
        return R.success(comboService.getComboDtoById(id));
    }

    @PostMapping("/status/{statusCode}")
    public R<String> updateStatus(HttpServletRequest request, @PathVariable Integer statusCode, Long[] ids) {
        for (Long id : ids) {
            comboService.updateStatusById(statusCode, id);
        }

        return R.success("Modify successfully!");
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody ComboDto comboDto) {
        comboService.update(comboDto);

        return R.success("Save successfully!");
    }


    /**
     * getting dish by category, sorting by category id asc (priority) and date modified desc
     * @return
     */
    @GetMapping("/list")
    public R<List<Combo>> list(ComboDto comboDto) {
        if (comboDto.getCategoryId() != null && comboDto.getCategoryId() > -1) {
            List<Combo> comboByCategory = comboService.getComboByCategory(comboDto);

            return R.success(comboByCategory);
        } else if (StringUtils.hasText(comboDto.getName())){
            List<Combo> comboByName = comboService.getComboByName(comboDto);

            return R.success(comboByName);
        }

        return null;
    }

}
