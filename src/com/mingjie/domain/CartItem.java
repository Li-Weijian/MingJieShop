package com.mingjie.domain;

/**
 * @Author:Liweijian
 * @Description: 购物项，包含在购物车内
 */
public class CartItem {

    private Product product;
    private int num;        //商品购买数量
    private double subTotal;    //商品小计

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Product getProduct() {
        return product;
    }

    public int getNum() {
        return num;
    }

    public double getSubTotal() {
        return subTotal;
    }
}
