package com.wxy.dao;

import com.wxy.bean.Cart;
import com.wxy.bean.Comment;
import com.wxy.bean.Role;

import java.util.List;

public interface RoleDao {
    List<Role> selectRoleByAll();
    Role selectRoleByUsernameAndPassword(String username,String password);
    public List showAllProduct();

    int insertCartById(int productId, int user_id);

    int dealCartAndIndent(int user_id, int productId);

    List<Cart> selectAllCarts();

    List<Comment> selectAllComments();
}
