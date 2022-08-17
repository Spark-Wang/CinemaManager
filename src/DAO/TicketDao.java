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
    //返回票据中的完整信息 有 电影名 等其他表中的项
    List<List<String>> getFullTicket(int C_id, String C_type);
    //返回所有的当前结果
    List<List<String>> findAllScence();

    //添加订票
    int insertTicket(String sql, String[] param);
    //更新订票
    int updateTicket(String sql, String[] param);
    //退票
    int delTicket(String sql, String[] param);
}
