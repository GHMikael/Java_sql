package com.wxy.dao;

import com.wxy.bean.Cart;
import com.wxy.bean.Product;
import com.wxy.bean.Role;
import com.wxy.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RoleDaoImpl implements RoleDao {
    private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;


    @Override
    public List<Role> selectRoleByAll() {
        conn = JdbcUtils.getConnection();
        //创建集合存放数据
        List roles = new ArrayList<>();
        //编写数据库语句
        String sql = "select * from role";

        //执行sql语句
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Role role = new Role();
                role.setR_id(rs.getInt(1));
                role.setR_name(rs.getString(2));
                role.setR_username(rs.getString(3));
                role.setR_password(rs.getString(4));
                role.setPower_id(rs.getInt(5));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    @Override
    public Role selectRoleByUsernameAndPassword(String username, String password) {
        conn = JdbcUtils.getConnection();
        Role role = new Role();
        String sql = "select *from role where r_username = ? and r_password = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("用户名密码错误");
            } else {
                role.setR_id(rs.getInt(1));
                role.setR_name(rs.getString(2));
                role.setR_username(rs.getString(3));
                role.setR_password(rs.getString(4));
                role.setPower_id(rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public List showAllProduct() {
        conn = JdbcUtils.getConnection();
        //创建集合存放数据
        List products = new ArrayList<>();
        //编写数据库语句
        String sql = "select * from product";

        //执行sql语句
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product();
                product.setPro_id(rs.getInt(1));
                product.setPro_name(rs.getString(2));
                product.setPro_price(rs.getDouble(3));
                product.setPro_num(rs.getInt(4));
                product.setPro_info(rs.getString(5));
                product.setPro_good(rs.getInt(6));
                products.add(product);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int insertCartById(int productId, int user_id) {
        conn = JdbcUtils.getConnection();
        int price = 10;
        //根据商品ID得到商品的具体价格
        //改写成字符串连接，完成SQL语句的查询
        String sql0 = "select pro_price from product where pro_id = "+productId;
        //执行sql语句
        try {
            ps = conn.prepareStatement(sql0);
            //问题：此处使用ps传参，报SQL语句出错，很奇怪
            //ps.setInt(1,productId);
            rs = ps.executeQuery(sql0);
            while (rs.next()) {
                price = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int res = 0;
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sql = "insert into cart(user_id,pro_id,cart_time,cart_money) VALUES(?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, productId);
            ps.setString(3, df.format(date));
            ps.setInt(4, price);

            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    //    1、生成账单
//    2、清空购物车
    @Override
    public int dealCartAndIndent(int user_id, int productId) {
        conn = JdbcUtils.getConnection();
//        存在的因素，是否有多个商品结算，存在多条记录
//                  1个商家 1几个地址，只需要一条记录

//        订单编号
        String ind_id = "";
//        1、统计购买的数量和金额
        int cartNum = 0;
        double cartMoney = 0;
        String sql = "select sum(cart_money),count(cart_id) from cart WHERE user_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("你尚未添加任何商品，请先添加至购物车或者直接购买");
            } else {
                cartNum = rs.getInt(2);
                cartMoney = rs.getDouble(1);
//                根据不同的商品编号找到不同的商家，进行统计分类(忽略)

//                通过user_id获取一下地址

//                生成订单，然后清除购物车
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
                ind_id = df.format(date);
                String sql1 = "insert into indent(ind_id,user_id,ind_money,ind_state) values(?,?,?,?)";
                ps = conn.prepareStatement(sql1);

                ps.setString(1, ind_id);
                ps.setInt(2, user_id);
                ps.setDouble(3, cartMoney);
                ps.setInt(4, 1);

                int intIndent = ps.executeUpdate();
                if (intIndent > 0) {
//                    清除购物车
                    sql = "DELETE FROM cart WHERE user_id = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, user_id);
                    int cartRes = ps.executeUpdate();
                    if (cartRes != cartNum) {
                        return 0;
                    } else {
                        return cartNum;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Cart> selectAllCarts() {
        conn = JdbcUtils.getConnection();
        //创建集合存放数据
        List carts = new ArrayList<>();
        //编写数据库语句
        String sql = "select * from cart";

        //执行sql语句
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCart_id(rs.getInt(1));
                cart.setUser_id(rs.getInt(2));
                cart.setPro_id(rs.getInt(3));
                cart.setCart_money(rs.getDouble(4));
                cart.setCart_time(rs.getTimestamp(5));
                carts.add(cart);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

}
