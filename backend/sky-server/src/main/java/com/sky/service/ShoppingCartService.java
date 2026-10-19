package com.sky.service;

import java.util.List;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

public interface ShoppingCartService {
    
    /**
     * 添加购物车
     * @param shoppingCartDTO 购物车商品信息
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);
    
    /**
     * 查询购物车列表
     * @return 购物车列表
     */
    List<ShoppingCart> getShoppingCartList();

    /**
     * 清空购物车
     */
    void cleanShoppingCart();

    /**
     * 删除购物车商品
     * 
     * @param shoppingCartDTO 购物车商品信息
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
