package DAO;

import Entity.Customer;

import java.util.List;

/**
 * @author Peiqi Wang
 * @date 2022-08-15
 */
public interface UserDao {
    //返回所有用户列表
    List<Customer> getAllUser();
    //新增用户
    int addUser(String sql, String[] param);
    //更新用户信息
    int updateUser(String sql, String[] param);
}
