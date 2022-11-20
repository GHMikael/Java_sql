package com.wxy;

import com.wxy.bean.Product;
import com.wxy.bean.Role;
import com.wxy.service.RoleService;
import com.wxy.service.RoleServiceImpl;
import org.junit.Before;


import java.util.List;
import java.util.Scanner;

public class main {

//    public static HttpServletResquest resquest;

    public static void main(String[] args) {

        RoleService roleService = new RoleServiceImpl();
        Scanner sc = new Scanner(System.in);
        //先进行登录
        System.out.println("请输入用户名和密码");
        System.out.println("用户名：");
        String name = sc.next();
        System.out.println("密码：");
        String password = sc.next();
        Role role  = roleService.loginRole(name,password);

        switch (role.getPower_id()){
            case 1:
                jumpToAdmin(role.getR_name());
                break;
            case 2:
                jumpToBusiness(role.getR_name());
                break;
            case 3:
                jumpToUser(role);
                break;
        }

        //分用户、管理员和商家  判断登录信息是否正确，如果正确，再判断power_id值
        System.out.println("以下是所有人员信息：");
        List<Role> roles = roleService.selectAllRole();
        System.out.println(roles.toString());
    }

    private static void jumpToAdmin(String r_name){
        System.out.println("欢迎回来，尊贵的用户"+ r_name);

    }
    private  static void jumpToBusiness(String r_name){
        System.out.println("欢迎回来："+ r_name);
    }
    private static void jumpToUser(Role role){


        RoleService roleService = new RoleServiceImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎回来，尊贵的用户：" + role.getR_name());
        System.out.println("--------------在线商城------------------");
        List<Product> products = roleService.selectAllProduct();
        System.out.println("商品ID\t\t\t" + "商品名称\t\t\t" + "商品价格\t\t\t" + "剩余数量\t\t\t"
                + "商品描述\t\t\t" + "最新热度");
        for (Product product : products) {
            System.out.println(product.toString());
        }

        //加入购物车
        addCart(role);


    }

    //单独封装

    public static void addCart(Role role){
        //加入购物车
        RoleService roleService = new RoleServiceImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------请选择心仪的商品---------------");
        System.out.print("请输入商品ID：");
        int productId = sc.nextInt();
        int res = roleService.insertCart(productId,role.getR_id());
        if (res >= 1) {
            System.out.println("添加成功");
            System.out.println("1、继续添加商品  2、前往下单");
        }
        else {
            System.out.println("添加失败,请重新选购商品");
        }
    }
    @Before
    public void Test(){
        System.out.println("前置方法");
    }


}