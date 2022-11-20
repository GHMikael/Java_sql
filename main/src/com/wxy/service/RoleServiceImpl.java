package com.wxy.service;

import com.wxy.bean.Product;
import com.wxy.bean.Role;
import com.wxy.dao.RoleDao;
import com.wxy.dao.RoleDaoImpl;

import java.util.List;

public class RoleServiceImpl implements RoleService{

    public RoleDao roleDao;
    //通过roleDao调用实现类的返回值
    public RoleServiceImpl(){
        roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> selectAllRole(){

        return roleDao.selectRoleByAll();
    }
    @Override
    public Role loginRole(String userName,String password){

        return roleDao.selectRoleByUsernameAndPassword(userName,password);
    }

    @Override
    public List<Product> selectAllProduct(){
        return roleDao.showAllProduct();
    }

    @Override
    public int  insertCart(int productId, int r_id) {
        return roleDao.insertCartById(productId,r_id);
    }


}
