package com.wxy.service;

import com.wxy.bean.Cart;
import com.wxy.bean.Product;
import com.wxy.bean.Role;

import java.util.List;

public interface RoleService {
    List<Role> selectAllRole();
    Role loginRole(String userName,String password);
    List<Product> selectAllProduct();
    int insertCart(int productId,int user_id);

    int clearCart(int r_id, int productId);

    List<Cart> selectAllCarts();
}
