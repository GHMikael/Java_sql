package com.wxy.bean;

public class Product {
    private int pro_id;
    private String pro_name;
    private double pro_price;
    private int pro_num;
    private String pro_info;
    private int pro_good;

    private int role_id;
    public Product() {
    }

    public Product(int pro_id, String pro_name, double pro_price, int pro_num, String pro_info, int pro_good) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.pro_price = pro_price;
        this.pro_num = pro_num;
        this.pro_info = pro_info;
        this.pro_good = pro_good;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public double getPro_price() {
        return pro_price;
    }

    public void setPro_price(double pro_price) {
        this.pro_price = pro_price;
    }

    public int getPro_num() {
        return pro_num;
    }

    public void setPro_num(int pro_num) {
        this.pro_num = pro_num;
    }

    public String getPro_info() {
        return pro_info;
    }

    public void setPro_info(String pro_info) {
        this.pro_info = pro_info;
    }

    public int getPro_good() {
        return pro_good;
    }

    public void setPro_good(int pro_good) {
        this.pro_good = pro_good;
    }

    @Override
    public String toString() {
        return  pro_id +
                "\t\t\t" + pro_name +
                "\t\t\t" + pro_price +
                "\t\t\t" + pro_num +
                "\t\t\t" + pro_info +
                "\t\t\t" + pro_good;
    }
}
