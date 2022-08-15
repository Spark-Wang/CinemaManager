package DAO;

import Entity.Scence;
import Entity.Ticket;

import java.util.List;

public interface TicketDao {
    //获取影票信息
    List<Ticket> getTicket(String sql, String[] param);
    //根据用户返回订票信息
    List<Ticket> findTicketByUser(int C_id);
    //根据电影名返回订票信息
    List<Ticket> findTicketByMovie(int M_id);
    //根据影票返回场次
    List<Scence> findScenceFromTicket(String sql, String[] param);

    //添加订票
    int insertTicket(String sql, String[] param);
    //更新订票
    int updateTicket(String sql, String[] param);
    //退票
    int delTicket(String sql, String[] param);
}
