package com.wxy.dao;

import com.wxy.bean.Role;

import java.util.List;

public interface RoleDao {
    List<Role> selectRoleByAll();
    Role selectRoleByUsernameAndPassword(String username,String password);
    public List showAllProduct();

    int  insertCartById(int productId, int r_id);
}
