package com.renaldo.controller;

import com.renaldo.common.R;
import com.renaldo.pojo.Category;
import com.renaldo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Category category) {
        boolean save = categoryService.save(category);

        if (save) {
            return R.success("Save successfully!");
        }

        return R.error("Save failed!");
    }

    /**
     * paging query
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        return R.success(categoryService.findAll(page, pageSize));
    }

    @DeleteMapping
    public R<String> deleteCategoryById(Long id) {
        categoryService.deleteById(id);
        return R.success("Delete successfully!");
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("Update successfully!");
    }

    /**
     * query by conditions (conditions store in object category)
     * sort by sort field asc, and upload_time field desc
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        return R.success(categoryService.list(category));
    }
}
