package com.wxy.bean;

import java.util.Date;

public class Cart {
    private int cart_id;
    private int user_id;
    private int pro_id;
    private Date cart_time;

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {

        this.user_id = user_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public Date getCart_time() {
        return cart_time;
    }

    public void setCart_time(Date cart_time) {
        this.cart_time = cart_time;
    }

}
