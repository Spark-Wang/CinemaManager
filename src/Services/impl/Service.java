package Services.impl;

import DAO.MovieDao;
import DAO.TicketDao;
import DAO.impl.MovieDaoImpl;
import DAO.impl.TicketDaoImpl;
import Entity.Movie;
import Entity.Scence;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public abstract class Service {
    private int C_id;
    private String C_type;

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int c_id) {
        C_id = c_id;
    }

    public String getC_type() {
        return C_type;
    }

    public void setC_type(String c_type) {
        C_type = c_type;
    }

    public Service(int C_id, String C_type){
        this.C_id = C_id;
        this.C_type = C_type;
        System.out.println("当前登陆的用户类型为："+C_type);
    }

    /**
     * 获取放映场次列表
     * @return 放映场次列表
     */
    public List<String> getScence(){
        TicketDaoImpl ticketDao = new TicketDaoImpl();
        //电影名 影厅号 影厅名 时间 电影时长 票价
        List<List<String>> scenceList = ticketDao.findAllScence();
        List<String> scences = new ArrayList<>();
        System.out.println("序号\t\t电影名\t\t影厅号\t\t影厅名\t\t放映时间\t\t电影时长\t\t票价");
        for(int i = 0;i < scenceList.size();i ++){
            List<String> temp = scenceList.get(i);
            System.out.format("%d\t\t%s\t%s\t%s\t%s\t%s\t%s\t",(1 + 1),temp.get(0),temp.get(1),temp.get(2),temp.get(2),temp.get(3),temp.get(4),temp.get(5));
            scences.add(temp.get(0));
        }
        return scences;
    }

    /**
     * 获取用户的订票记录
     */
    public void getOrderRecord(){
        TicketDaoImpl ticketDao = new TicketDaoImpl();
        List<List<String>> ticketlist = ticketDao.getFullTicket(C_id,C_type);
        //用户名 电影名 影厅ID 影厅名 座位行列 时间 票价
        System.out.println("序号\t\t用户名\t\t\t电影名\t\t\t影厅ID\t\t\t影厅名\t\t\t座位排\t\t座位列\t\t\t时间\t\t\t票价");
        for(int i = 0;i < ticketlist.size();i ++){
            List<String> ticket = ticketlist.get(i);
            System.out.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t",(i + 1),ticket.get(0),ticket.get(1),ticket.get(2),ticket.get(3),ticket.get(4),ticket.get(5),ticket.get(6));
        }
    }

    /**
     * 打印所有的电影信息
     */
    public void printAllMovies(){
        MovieDao movieDao = new MovieDaoImpl();
        List<Movie> movieList = movieDao.getMovies("select * from movie",null);
        System.out.println("序号\t\t\t电影名\t\t\t\t\t电影票价\t\t上架时间\t\t\t\t\t\t下映时间\t\t\t\t\t\t电影时长");
        for(Movie value : movieList){
            Movie movie = new Movie();
            movie = value;
            System.out.format("%d\t%s\t%s\t%s\t%s\t%s\t",movie.getM_id(),movie.getM_name(),movie.getM_price(),movie.getM_startTime(),movie.getM_endTime(),movie.getM_durTime());
        }
    }
}
