package com.wxy;

import com.wxy.bean.Cart;
import com.wxy.bean.Comment;
import com.wxy.bean.Product;
import com.wxy.bean.Role;
import com.wxy.service.RoleService;
import com.wxy.service.RoleServiceImpl;



import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {

    public static HttpServletRequest request;
    public static void main(String[] args) {

        RoleService roleService = new RoleServiceImpl();
        System.out.println("以下是所有人员信息：（只是为了方便你使用，实际不会提供）");
        System.out.println("用户ID\t\t\t" + "姓名\t\t\t" + "用户名\t\t\t" + "用户密码\t\t\t"
                + "权限\t\t\t");
        List<Role> roles = roleService.selectAllRole();
        for(Role role1: roles)
        {
            System.out.println(role1.toString());
        }

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

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        int productId = Integer.parseInt(df.format(date));
        //加入购物车
        System.out.println("1、添加购物车  2、清空购物车  3、进行订单的评论  4、退出账号");
        int i = sc.nextInt();
        if (i == 1){
            //加入购物车
            addCart(role);
        }
        else if(i == 2) {
            goToCheckOut(role,productId);
        }
        else if (i == 3) {
            goToComment(role);
        }

    }

    //单独封装
    public static void addCart(Role role){
        //加入购物车
        RoleService roleService = new RoleServiceImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------请选择喜欢的商品，并加入购物车---------------");

        System.out.print("请输入商品ID：");
        int productId = sc.nextInt();

        //判断商品在商城中是否存在
        boolean isTrue = false;
        List<Product> products = roleService.selectAllProduct();
        for (Product product : products) {
            if(product.getPro_id() == productId){
                isTrue = true;
            }
        }
        int res = 0;
        if(isTrue){
             res = roleService.insertCart(productId, role.getR_id());
        }
        if (res >= 1 ){
            System.out.println("添加成功");
            //显示购物车的商品：
            System.out.println("购物车已有的商品：");
            List<Cart> carts = roleService.selectAllCarts();
            System.out.println("购物车ID\t\t\t" + "用户ID\t\t\t" + "商品ID\t\t\t" + "购物车价格\t\t\t"
                    +  "购物时间");
            for (Cart cart : carts) {
                System.out.println(cart.toString());
            }

            System.out.println("1、继续选择商品  2、清空购物车  3、返回用户界面");
            int i = sc.nextInt();
            switch (i){
                case 1:
                    addCart(role);
                    break;
                case 2:
//                    下单(用户编号，地址，付款金额，商家发货地址)
                    goToCheckOut(role,productId);
                    break;
                case 3:
                    jumpToUser(role);
            }
        }else{
            System.out.println("商城中不存在商品，请重新选择商品号！");
            addCart(role);
        }
    }

    private static void goToCheckOut(Role role, int productId) {
        RoleService roleService = new RoleServiceImpl();
        Scanner sc = new Scanner(System.in);

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String first = df.format(date);
        int clearRes = roleService.clearCart(role.getR_id(),productId);
        if (clearRes>0){
            System.out.println("购买成功，订单号为：" + first + productId + "\t合计" + clearRes + "个订单");
//            如果需要打印订单号 ，根据user_id查询订单表，获取订单信息
        }else {
            System.out.println("购物车为空，请重新进行操作");
        }
        jumpToUser(role);
    }

    private static void goToComment(Role role){
        RoleService roleService = new RoleServiceImpl();


        Scanner sc = new Scanner(System.in);
        System.out.println("-----------请选择需要评论的商品---------------");
        System.out.println("可评论的商品：");
        List<Product> products = roleService.selectAllProduct();
        System.out.println("商品ID\t\t\t" + "商品名称\t\t\t" + "商品价格\t\t\t" + "剩余数量\t\t\t"
                + "商品描述\t\t\t" + "最新热度");
        for (Product product : products) {
            System.out.println(product.toString());
        }

        System.out.println("请输入需要评论的商品号：");
        int commentId = sc.nextInt();
        System.out.println("请输入需要评论的内容：");
        String content = sc.next();
        int res = roleService.insertComment(commentId,role.getR_id(),content);
        List<Comment> comments = roleService.selectAllComment();
        if (res >= 1 ){
            System.out.println("评论成功");
            //显示购物车的商品：
            System.out.println("已有的评论：");
            System.out.println("评论ID\t\t\t" + "用户ID\t\t\t" + "商品名称\t\t\t" + "评论内容\t\t\t"
                    + "订单状态");
            for (Comment comment : comments) {
                System.out.println(comment.toString());
            }

            System.out.println("1、继续选择评论商品  2、返回用户界面");
            int i = sc.nextInt();
            switch (i){
                case 1:
                    goToComment(role);
                    break;
                case 2:
                   jumpToUser(role);
            }
        }else{
            System.out.println("评论失败，请重新选择");
            addCart(role);
        }




    }

}