package com.wxy.service;

import com.wxy.bean.Cart;
import com.wxy.bean.Comment;
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
    public int insertCart(int productId, int user_id) {
        return roleDao.insertCartById(productId,user_id);
    }

    @Override
    public int clearCart(int r_id, int productId) {
        return roleDao.dealCartAndIndent(r_id,productId);
    }

    @Override
    public List<Cart> selectAllCarts() {

        return roleDao.selectAllCarts();
    }

    @Override
    public List<Comment> selectAllComment() {
        return roleDao.selectAllComments();
    }

    @Override
    public int insertComment(int commentId, int r_id,String content) {
        return roleDao.insertComment(commentId,r_id,content);
    }


}
