package DAO.impl;

import DAO.BaseDao;
import DAO.ScenceDao;
import Entity.Scence;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Peiqi Wang
 * @Date 2022-08-15
 **/

public class ScenceDaoImpl extends BaseDao implements ScenceDao {
    @Override
    public int changeScence(String sql, Object[] param) {
        return executeSQL(sql,param);
    }

    @Override
    public List<Scence> getScence(String sql, String[] param) {
        List<Scence> scenceList = new ArrayList<>();
        try {
            connectSql(sql,param);
            while(rs.next()){
                Scence scence = new Scence();
                scence.setM_id(rs.getInt(1));
                scence.setH_id(rs.getInt(2));
                scence.setS_time(rs.getTimestamp(3));
                scenceList.add(scence);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            super.closeAll(conn,pstmt,rs);
        }
        return scenceList;
    }


    /**
     *
     * @return 电影名 影厅号 影厅名 时间 电影时长 票价
     */
    @Override
    public List<List<String>> findAllScence() {
        List<List<String>> scenceList = new ArrayList<>();
        try{
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            String sql = "select movie.M_name, hall.H_id, hall.H_name, scence.S_time, movie.M_durTime, movie.M_price "
                    + "from scence join movie on scence.M_id = movie.M_id "
                    + "join hall on scence.H_id = hall.H_id "
                    + "where scence.S_time > '" + timestamp +"'";
            connectSql(sql, null);
            while(rs.next()){
                List<String> scence = new ArrayList<>();
                scence.add(rs.getString(1));
                scence.add(rs.getInt(2)+"");
                scence.add(rs.getString(3));
                scence.add(rs.getTimestamp(4) + "");
                scence.add(rs.getInt(5) + "");
                scence.add(rs.getDouble(6) + "");
                scenceList.add(scence);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            super.closeAll(conn, pstmt, rs);
        }
        return scenceList;
    }

//    public static void main(String[] args) {
//        Date date = new Date();
//        Timestamp timestamp = new Timestamp(date.getTime());
//        String time = timestamp.toString();
//        System.out.println(time);
//        ScenceDao scenceDao = new ScenceDaoImpl();
//    }
}
