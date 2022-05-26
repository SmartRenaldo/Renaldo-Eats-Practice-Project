package com.renaldo.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.renaldo.pojo.Category;
import com.renaldo.pojo.QCategory;
import com.renaldo.repositories.CategoryRepository;
import com.renaldo.service.CategoryService;
import com.renaldo.service.ComboService;
import com.renaldo.service.DishService;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    DishService dishService;

    @Autowired
    ComboService comboService;

    @Override
    public boolean save(Category category) {
        Category save = categoryRepository.save(category);

        if (save == null) {
            return false;
        }

        return true;
    }

    /**
     * combine paging and sorting together, sorting by sort field asc
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<Category> findAll(int page, int pageSize) {
        Sort.TypedSort<Category> sort = Sort.sort(Category.class);
        Sort ascending = sort.by(Category::getSort).ascending();
        return categoryRepository.findAll(PageRequest.of(page - 1, pageSize, ascending));
    }

    /**
     * delete by id
     * before deleting, check if there are associated dishes and combos in this category
     * If the current category is associated to a combo or dish, throw an error
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        categoryRepository.deleteById(id);

        return true;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    @Transactional
    public void updateById(Category category) {
        Category categoryById = categoryRepository.getCategoryById(category.getId());

        if (StringUtils.hasText(category.getName())) {
            categoryById.setName(category.getName());
        }

        if (category.getSort() != null) {
            categoryById.setSort(category.getSort());
        }

        if (category.getType() != null) {
            categoryById.setType(category.getType());
        }
    }

    /**
     * use commons-collections4 converting iterable to list
     * @param category
     * @return
     */
    @Override
    public List<Category> list(Category category) {
        QCategory qCategory = QCategory.category;
        BooleanExpression expression = qCategory.isNotNull().or(qCategory.isNull());
        
        expression = (category.getId() != null && category.getId() > -1) ?
                expression.and(qCategory.id.eq(category.getId())) : expression;
        expression = (category.getType() != null && category.getType() > -1) ?
                expression.and(qCategory.type.eq(category.getType())) : expression;
        expression = (category.getSort() != null && category.getSort() > -1) ?
                expression.and(qCategory.sort.eq(category.getSort())) : expression;
        expression = StringUtils.hasText(category.getName()) ?
                expression.and(qCategory.name.eq(category.getName())) : expression;
        expression = StringUtils.hasText(category.getCreatedBy()) ?
                expression.and(qCategory.createdBy.eq(category.getCreatedBy())) : expression;
        expression = StringUtils.hasText(category.getLastModifiedBy()) ?
                expression.and(qCategory.lastModifiedBy.eq(category.getLastModifiedBy())) : expression;

        return IterableUtils.toList(categoryRepository.findAll(expression
                , new QSort(qCategory.sort.asc()).and(new QSort(qCategory.dateModified.desc()))));
    }
}
