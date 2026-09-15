package com.sky.service;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import java.util.List;


public interface CategoryService {

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void addCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    void update(Long id, Integer status);

    List<Category> list(Integer type);

    void updateCategory(CategoryDTO categoryDTO);
}
