package com.mingjie.domain;

/**
 * @Author:Liweijian
 * @Description:
 * @Date:Create in 15:50 2017/12/30 0030
 */
public class OrderItem {
 /*   `itemid` varchar(32) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
            `pid` varchar(32) DEFAULT NULL,
  `oid` varchar(32) DEFAULT NULL,*/

     private String itemid;
     private int count;
     private double subtotal;
     private Product product;
     private Order order;

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getItemid() {
        return itemid;
    }

    public int getCount() {
        return count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }
}
