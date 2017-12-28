package com.mingjie.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Liweijian
 * @Description: 购物车
 * @Date:Create in 21:54 2017/12/28 0028
 */
public class Cart {

    //商品集合
    private Map<String,CartItem> cartItem = new HashMap<>();

    //商品价格总计
    private double total;

    public void setCartItem(Map<String, CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public Map<String, CartItem> getCartItem() {
        return cartItem;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public double getTotal() {
        return total;
    }
}
