package com.renaldo.service;

import com.renaldo.pojo.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    boolean save(Category category);

    Page<Category> findAll(int page, int pageSize);

    boolean deleteById(Long id);

    void remove(Long id);

    void updateById(Category category);

    List<Category> list(Category category);

    Optional<Category> findById(Long id);
}
