package com.wxy.bean;

public class Role {
    private int r_id;
    private String r_name;
    private String r_username;
    private String r_password;
    private int power_id;

    public String getR_password() {
        return r_password;
    }

    public void setR_password(String r_password) {
        this.r_password = r_password;
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_username() {
        return r_username;
    }

    public void setR_username(String r_username) {
        this.r_username = r_username;
    }

    public int getPower_id() {
        return power_id;
    }

    public void setPower_id(int power_id) {
        this.power_id = power_id;
    }


    @Override
    public java.lang.String toString() {
        return r_id +
                "\t\t\t" + r_name +
                "\t\t\t" + r_username +
                "\t\t\t" + r_password +
                "\t\t\t" + power_id;
    }
}
