package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "菜品分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    @ApiOperation("分页查询菜品分类")
    public Result<PageResult> getCategoryPage(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("员工分页查询，参数为{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    @ApiOperation("根据分类查询菜品")
    public Result<List<Category>> getCategoryList(Integer type){
        List<Category> categoryList = categoryService.list(type);
        return Result.success(categoryList);
    }

    @PostMapping
    @ApiOperation("新增菜品分类")
    public Result saveCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("删除菜品分类")
    public Result deleteCategory(@RequestParam Long id){
        log.info("删除菜品分类，参数为{}", id);
        categoryService.deleteCategory(id);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用菜品分类")
    public Result updateStatus(Long id, @PathVariable Integer status){
        categoryService.update(id, status);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改菜品分类")
    public Result updateCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }
}
