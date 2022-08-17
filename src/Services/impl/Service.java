package Services.impl;

import DAO.MovieDao;
import DAO.UserDao;
import DAO.impl.MovieDaoImpl;
import DAO.impl.TicketDaoImpl;
import DAO.impl.UserDaoImpl;
import Entity.Customer;
import Entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public abstract class Service {
    private int C_id;
    private String C_type;
    Scanner scanner = new Scanner(System.in);

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

    public int inputCheck(){
        int t;
        try{
            t = scanner.nextInt();
        }catch (Exception e){
            System.out.println("请输入数字类型!");
            scanner.nextLine();
            return -1;
        }
        return t;
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
        System.out.println("序号\t\t\t电影名\t\t\t\t影厅号\t\t影厅名\t\t\t\t放映时间\t\t\t\t\t\t电影时长\t\t票价");
        for(int i = 0;i < scenceList.size();i ++){
            List<String> temp = scenceList.get(i);
            System.out.format("%-8d\t%-16s\t%-8s\t%-16s\t%-26s\t%-8s\t%-8s\n",(i + 1),temp.get(0),temp.get(1)+"号厅",temp.get(2),temp.get(3),temp.get(4)+"分钟",temp.get(5)+"元");
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
        System.out.println("序号\t\t\t用户名\t\t\t\t电影名\t\t\t\t影厅ID\t\t影厅名\t\t\t\t座位排\t\t座位列\t\t时间\t\t\t\t\t\t\t票价");
        for(int i = 0;i < ticketlist.size();i ++){
            List<String> ticket = ticketlist.get(i);
            System.out.format("%-8d\t%-16s\t%-16s\t%-10s\t%-16s\t%-8s\t%-10s\t%-26s\t%-8s\n",(i + 1),ticket.get(0),ticket.get(1),ticket.get(2)+"号厅",ticket.get(3),ticket.get(4),ticket.get(5),ticket.get(6),ticket.get(7)+"元");
        }
    }

    /**
     * 打印所有的电影信息
     */
    public void printAllMovies(){
        MovieDao movieDao = new MovieDaoImpl();
        List<Movie> movieList = movieDao.getMovies("select * from movie",null);
        System.out.println("序号\t\t\t电影名\t\t\t\t电影票价\t\t上架时间\t\t\t\t\t\t下映时间\t\t\t\t\t\t电影时长");
        for(Movie value : movieList){
            Movie movie = new Movie();
            movie = value;
            System.out.format("%-8d\t%-16s\t%-8s\t%-26s\t%-26s\t%-8s\n",movie.getM_id(),movie.getM_name(),movie.getM_price()+"元",movie.getM_startTime(),movie.getM_endTime(),movie.getM_durTime()+"分钟");
        }
    }

    public void printAllUsers(){
        UserDao userDao = new UserDaoImpl();
        List<Customer> customerList = new ArrayList<>();
        customerList = userDao.getAllUser();
        System.out.println("序号\t\t\t用户名\t\t\t\t\t密码\t\t\t\t\t\t用户类型");
        for(Customer customer : customerList){
            Customer cur = new Customer();
            cur = customer;
            System.out.format("%-8d\t%-20s\t%-20s\t%-10s\n",cur.getC_id(), cur.getC_name(),cur.getC_password(),cur.getC_type());
        }
    }

//    public static void main(String[] args) {
//        Service service = new ManagerService(1,"Manager");
//        service.printAllUsers();
//        service.printAllMovies();
//        service.getOrderRecord();
//        service.getScence();
//    }
}
