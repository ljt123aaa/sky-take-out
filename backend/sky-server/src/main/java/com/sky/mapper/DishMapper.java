package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    // 根据分类id查询菜品数量
    @Select("select count(*) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @AutoFill(value = OperationType.INSERT)
    void addDishWithFlavor(Dish dish);

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteDish(List<Long> ids);

    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    @Select("select * from dish where id = #{id}")
    DishVO getByIdWithFlavor(Long id);

    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    // List<Dish> getDishlistById(Dish dish);

    /**
     * 动态条件查询菜品
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);
}
