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
    //返回要求的用户列表
    List<Customer> getUser(String sql, String[] param);
    //新增用户
    int addUser(Customer customer);
    //更新用户信息
    int updateUser(String sql, Object[] param);
}
