package DAO.impl;

import DAO.BaseDao;
import DAO.ScenceDao;
import Entity.Scence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Peiqi Wang
 * @Date 2022-08-15
 **/

public class ScenceDaoImpl extends BaseDao implements ScenceDao {
    @Override
    public int changeScence(String sql, String[] param) {
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
}
