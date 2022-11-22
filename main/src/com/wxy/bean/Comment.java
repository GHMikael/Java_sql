package com.wxy.bean;

public class Comment {
    private int id;
    private int role_id;

    private String product_name;
    private String content;
    private String states;

    public Comment(int id, int role_id, String product_name, String content, String states) {
        this.id = id;
        this.role_id = role_id;
        this.product_name = product_name;
        this.content = content;
        this.states = states;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return id +
                "\t\t\t" + role_id +
                "\t\t\t" + product_name +
                "\t\t\t" + content  +
                "\t\t\t" + states ;
    }
}
