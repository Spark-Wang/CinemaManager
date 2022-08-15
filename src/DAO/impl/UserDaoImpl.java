package DAO.impl;

import DAO.BaseDao;
import DAO.UserDao;
import Entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Peiqi Wang
 * @Date 2022-08-15
 **/

public class UserDaoImpl extends BaseDao implements UserDao {
    private Connection conn = null; //建立的连接

    private PreparedStatement pstmt = null; //将要预编的SQL

    private ResultSet rs = null;//结果集


    @Override
    public List<Customer> getAllUser() {
        return getUser("select * from customer",null);
    }

    @Override
    public List<Customer> getUser(String sql, String []param) {
        List<Customer> customerList = new ArrayList<>();
        try {
            connectSql(sql, param);
            while(rs.next()){
                Customer customer = new Customer();
                customer.setC_id(rs.getInt(1));
                customer.setC_name(rs.getString(2));
                customer.setC_password(rs.getString(3));
                customer.setC_type(rs.getString(4));
                customerList.add(customer);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            super.closeAll(conn, pstmt, rs);
        }
        return customerList;
    }

    @Override
    public int addUser(String sql, String[] param) {
        return super.executeSQL(sql, param);
    }

    @Override
    public int updateUser(String sql, String[] param) {
        return super.executeSQL(sql, param);
    }
}
