package DAO.impl;

import DAO.BaseDao;
import DAO.TicketDao;
import Entity.Scence;
import Entity.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Peiqi Wang
 * @Date 2022-08-15
 **/

public class TicketDaoImpl extends BaseDao implements TicketDao {
    /**
     * 返回要查找的电影票的裸数据
     * @param sql
     * @param param
     * @return
     */
    @Override
    public List<Ticket> getTicket(String sql, String[] param) {
        List<Ticket> ticketList = new ArrayList<>();
        try {
            connectSql(sql, param);
            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setC_id(rs.getInt(1));
                ticket.setM_id(rs.getInt(2));
                ticket.setH_id(rs.getInt(3));
                ticket.setH_Line(rs.getInt(4));
                ticket.setH_Row(rs.getInt(5));
                ticket.setT_time(rs.getTimestamp(6));
                ticketList.add(ticket);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            super.closeAll(conn, pstmt, rs);
        }
        return ticketList;
    }

    @Override
    public List<Ticket> findTicketByUser(int C_id) {
        String[] params = {(C_id + "")};
        List<Ticket> ticketList = getTicket("select * from ticket where C_id = ?",params);
        return ticketList;
    }

    @Override
    public List<Ticket> findTicketByMovie(int M_id) {
        String[] params = {(M_id + "")};
        List<Ticket> ticketList = getTicket("select * from ticket where M_id = ?",params);
        return ticketList;
    }

    /**
     * 根据电影票返回对应的场次
     * @param sql
     * @param param
     * @return
     */
    @Override
    public List<Scence> findScenceFromTicket(String sql, String[] param) {
        List<Scence> scenceList = new ArrayList<>();
        try {
            connectSql(sql, param);
            while(rs.next()){
                Scence scence = new Scence();
                scence.setH_id(rs.getInt(3));
                scence.setM_id(rs.getInt(2));
                scence.setS_time(rs.getTimestamp(6));
                scenceList.add(scence);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            super.closeAll(conn, pstmt, rs);
        }
        return scenceList;
    }

    /**
     * 为上层执行SQL的接口
     * @param sql
     * @param param
     * @return
     */
    @Override
    public int insertTicket(String sql, String[] param) {
        return super.executeSQL(sql, param);
    }

    @Override
    public int updateTicket(String sql, String[] param) {
        return super.executeSQL(sql, param);
    }

    @Override
    public int delTicket(String sql, String[] param) {
        return super.executeSQL(sql, param);
    }

//    public static void main(String[] args) {
//        TicketDaoImpl ticketDao = new TicketDaoImpl();
//        List<Ticket> ticketList = ticketDao.getTicket("select * from ticket",null);
//        System.out.println(ticketList.get(0).getH_id()+" "+ticketList.get(0).getT_time());
//        ticketList = ticketDao.findTicketByMovie(1);
//        System.out.println(ticketList.get(0).getH_id()+" "+ticketList.get(0).getT_time());
//        ticketList = ticketDao.findTicketByUser(1001);
//        System.out.println(ticketList.get(0).getH_id()+" "+ticketList.get(0).getT_time());
//        List<Scence> scenceList = ticketDao.findScenceFromTicket("select * from ticket",null);
//        System.out.println(scenceList.get(0).getH_id()+" "+scenceList.get(0).getS_time());
//    }
}
