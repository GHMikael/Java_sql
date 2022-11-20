package com.wxy.dao;

import com.wxy.bean.Product;
import com.wxy.bean.Role;
import com.wxy.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{
    private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;


    @Override
    public List<Role> selectRoleByAll(){
        conn = JdbcUtils.getConnection();
        //创建集合存放数据
        List roles = new ArrayList<>();
        //编写数据库语句
        String sql = "select * from role";

        //执行sql语句
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while(rs.next()){
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
    public Role selectRoleByUsernameAndPassword(String username,String password){
        conn = JdbcUtils.getConnection();
        Role role = new Role();
        String sql = "select *from role where r_username = ? and r_password = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(!rs.next()){
                System.out.println("用户名密码错误");
            }
            else{
                role.setR_name(rs.getString(2));
                role.setPower_id(rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public List showAllProduct(){
        conn = JdbcUtils.getConnection();
        //创建集合存放数据
        List products = new ArrayList<>();
        //编写数据库语句
        String sql = "select * from product";

        //执行sql语句
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while(rs.next()){
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
    public int insertCartById(int productId, int r_id) {
        return 0;
    }

}
