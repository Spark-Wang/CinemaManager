package DAO.impl;

import DAO.BaseDao;
import DAO.TicketDao;
import Entity.Scence;
import Entity.Ticket;
import com.sun.media.sound.RealTimeSequencerProvider;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
     * 返回电影票完整的信息，通过用户权限的不同，显示不同的返回值
     * @param C_id
     * @param C_type
     * @return 用户名 电影名 影厅ID 影厅名 座位行列 时间 票价
     */
    @Override
    public List<List<String>> getFullTicket(int C_id, String C_type) {
        List<List<String>> ticketList = new ArrayList<>();
        try{
            String sql;
            if(C_type.equals("Manager")){
                sql = "select customer.C_name, movie.M_name, hall.H_id, hall.H_name, ticket.H_line, ticket.H_row, ticket.T_time, movie.M_price\n"
                        + "from ticket join customer on ticket.C_id = customer.C_id "
                        + "join movie on ticket.M_id = movie.M_id "
                        + "join hall on ticket.H_id = hall.H_id";
            }else{
                sql = "select customer.C_name, movie.M_name, hall.H_id, hall.H_name, ticket.H_line, ticket.H_row, ticket.T_time, movie.M_price\n"
                        + "from ticket join customer on ticket.C_id = customer.C_id "
                        + "join movie on ticket.M_id = movie.M_id "
                        + "join hall on ticket.H_id = hall.H_id"
                        + "where ticket.C_id = " + C_id;
            }
            connectSql(sql,null);
            while(rs.next()){
                List<String> ticket = new ArrayList<>();
                ticket.add(rs.getString(1));
                ticket.add(rs.getString(2));
                ticket.add(rs.getInt(3) + "");
                ticket.add(rs.getString(4));
                ticket.add(rs.getInt(5) + "");
                ticket.add(rs.getInt(6) + "");
                ticket.add(rs.getTimestamp(7) + "");
                ticket.add(rs.getDouble(8) + "");
                ticketList.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            super.closeAll(conn, pstmt, rs);
        }
        return ticketList;
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
            String sql = "select movie.M_name, hall.H_id, hall.H_name, scence.T_time, movie.M_durTime, movie.M_price "
                    + "from scence join movie on scence.M_id = movie.M_id "
                    + "join hall on scence.H_id = hall.H_id "
                    + "where scence.S_time > " + timestamp;
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
//        List<List<String>> res = ticketDao.getFullTicket(1001,"Manager");
//        for(int i = 0;i < res.size();i ++){
//            List<String> t = res.get(i);
//            //注意List的get方法是从0开始计数的，而ResultSet是从1开始get
//            System.out.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",(i + 1),t.get(0),t.get(1),t.get(2),t.get(3),t.get(4),t.get(5),t.get(6),t.get(7));
//        }
//    }
}
