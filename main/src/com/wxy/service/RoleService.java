package com.wxy.service;

import com.wxy.bean.Product;
import com.wxy.bean.Role;

import java.util.List;

public interface RoleService {
    List<Role> selectAllRole();
    Role loginRole(String userName,String password);
    List<Product> selectAllProduct();
    int insertCart(int productId, int r_id);
}
