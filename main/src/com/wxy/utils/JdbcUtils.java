package com.wxy.utils;

import java.sql.*;

public class JdbcUtils {
    //连接数据库的工具类
    //MySQL作用是为了提供Java连接MySQL的能力--数据库加载驱动

    //创建数据库连接对象
    public  static Connection conn;
    public  static Connection getConnection(){
        //加载数据库驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/shopping_manager?serverTimezone=UTC&UserSSL=false";
            //获取数据库地址，访问数据库的信息，包含url,username,password
            conn = DriverManager.getConnection(url,"root","root");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    //关闭数据库  (连接数据库的时候返回结果Result（查询的结果，更新-增加-改变）0或者n)
    //数据声明PrepareStatement    where id = 1 或者条件后面的赋值
    //连接对象Conn

    public static void closeConnection(ResultSet rs, PreparedStatement ps,Connection conn){
            try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        System.out.print(JdbcUtils.getConnection());
    }
}
