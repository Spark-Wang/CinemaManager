package DAO.impl;

import DAO.BaseDao;
import DAO.HallDao;
import Entity.Hall;

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

public class HallDaoImpl extends BaseDao implements HallDao {

    @Override
    public List<Hall> getHall(String sql, String[] param) {
        List<Hall> hallList = new ArrayList<>();
        try{
//            conn = getConn();
//            pstmt = conn.prepareStatement(sql);
//            if(param != null){
//                for(int i = 0;i < param.length;i ++){
//                    pstmt.setString(i + 1, param[i]);
//                }
//            }
//            rs = pstmt.executeQuery();
            connectSql(sql,param);
            Hall hall = null;
            while(rs.next()){
                hall = new Hall();
                hall.setH_id(rs.getInt(1));
                hall.setH_name(rs.getString(2));
                hall.setH_capacity(rs.getInt(3));
                hall.setH_line(rs.getInt(4));
                hall.setH_row(rs.getInt(5));
                hallList.add(hall);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            super.closeAll(conn, pstmt, rs);
        }
        return hallList;
    }

    @Override
    public int changeHall(String sql, String[] param) {
        return super.executeSQL(sql, param);
    }

//    public static void main(String[] args) {
//        HallDaoImpl hallDao = new HallDaoImpl();
//        List<Hall> hall = hallDao.getHall("select * from hall",null);
//        for(Hall h :hall)
//            System.out.println(h.getH_id()+h.getH_name());
//    }
}
