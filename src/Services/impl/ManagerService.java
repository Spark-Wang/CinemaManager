package Services.impl;

import DAO.MovieDao;
import DAO.ScenceDao;
import DAO.impl.MovieDaoImpl;
import DAO.impl.ScenceDaoImpl;
import Entity.Movie;
import Entity.Scence;
import Services.MovieHandler;
import Services.ScenceHandler;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class ManagerService extends Service implements MovieHandler, ScenceHandler{
    public ManagerService(int C_id, String C_type) {
        super(C_id, C_type);
    }

    public void mgrSearch(){
        while(true){
            System.out.println("=============管理系统查询=============");
            System.out.println("1.查询所有电影 2.查询所有场次 3.查询所有票据 4.查询所有用户 5.返回");
            int choice = inputCheck();
            switch (choice){
                case 1:
                    printAllMovies();
                    break;
                case 2:
                    getScence();
                    break;
                case 3:
                    getOrderRecord();
                    break;
                case 4:
                    printAllUsers();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("请输入正确的数字：");
            }
        }
    }

    @Override
    public void addMovie() {
        printAllMovies();
        System.out.println("请输入电影ID：（与当前所有ID不同）");
        int movieId = inputCheck();
        System.out.println("请输入电影名：");
        String movie_name = scanner.next();
        System.out.println("请输入电影票价：");
        double movie_price = scanner.nextDouble();
        System.out.println("请输入影片时长：");
        int movie_dur = inputCheck();
        try {
            System.out.println("请输入上映时间：yyyy-MM-dd");
            String movie_sta = scanner.next();
            Date movieStart = formatDay.parse(movie_sta);
            Timestamp m_start = new Timestamp(movieStart.getTime());
            System.out.println("请输入下架时间：");
            String movie_en = scanner.next();
            Date movieEnd = formatDay.parse(movie_en);
            Timestamp m_end = new Timestamp(movieEnd.getTime());
            Movie m = new Movie(movieId,movie_name,movie_price,movie_dur,m_start,m_end);
            MovieDao movieDao = new MovieDaoImpl();
            String sql = "insert into movie(m_id, m_name, m_price, m_durTime, m_startTime, m_endTime) values (?,?,?,?,?,?)";
            Object[] param = {m.getM_id(), m.getM_name(), m.getM_price(), m.getM_durTime(), m.getM_startTime(),m.getM_endTime()};
            if(movieDao.changeMovies(sql, param) > 0){
                System.out.println("添加"+m.getM_name()+"电影成功！");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delMovie() {
        printAllMovies();
        System.out.println("请输入要删除的电影id:");
        int movie_id = inputCheck();
        MovieDao movieDao = new MovieDaoImpl();
        List<Movie> movieList = movieDao.getMovies("select * from movie where m_id = " + movie_id,null);
        if(movieList.size() > 0){
            Movie movie = movieList.get(0);
            String sql = "delete from movie where m_id = " + movie_id;
            if(movieDao.changeMovies(sql,null) > 0){
                System.out.println("删除电影《"+movie.getM_name()+"》成功！现在的影片列表：");
                printAllMovies();
            }
        }else{
            System.out.println("请输入正确的影片编号！");
        }
    }

    @Override
    public void addScence() {
        System.out.println("电影信息：");
        printAllMovies();
        System.out.println("影厅信息：");
        printAllHall();
        System.out.println("请输入电影id");
        int movie_id = inputCheck();
        System.out.println("请输入影厅id");
        int hall_id = inputCheck();
        try{
            scanner.nextLine();//清空上一次的输入，输入光标从新一行的头开始
            System.out.println("请输入放映时间：yyyy-MM-dd HH:mm:ss");
            String s_time = scanner.nextLine();//防止空格作为分隔符导致录入的时间信息不全
            Date s_date = formatTime.parse(s_time);
            Timestamp scenceTime = new Timestamp(s_date.getTime());
            Scence scence = new Scence(movie_id,hall_id,scenceTime);
            String sql = "insert into scence (m_id, h_id, s_time) values (?,?,?)";
            Object[] param = {scence.getM_id(), scence.getH_id(), scence.getS_time()};
            ScenceDao scenceDao = new ScenceDaoImpl();
            if(scenceDao.changeScence(sql,param) > 0){
                System.out.println("成功添加："+scence.getH_id()+"号厅"+scence.getS_time()+"播放的电影");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delScence() {
        System.out.println("电影信息：");
        printAllMovies();
        System.out.println("场次信息：");
        getScence();
        System.out.println("请输入要删除原电影id；");
        int movie_id = inputCheck();
        System.out.println("请输入要删除原影厅id");
        int hall_id = inputCheck();
        try {
            scanner.nextLine();
            System.out.println("请输入要删除的原放映时间：");
            String scence_Time = scanner.nextLine();
            Date scence_t = formatTime.parse(scence_Time);
            Timestamp scence_time = new Timestamp(scence_t.getTime());
            Scence scence = new Scence(movie_id, hall_id, scence_time);
            String sql = "delete from scence where m_id = ? and h_id = ? and s_time = ?";
            String[] param = {movie_id+"",hall_id+"",scence_Time+""};
            ScenceDao scenceDao = new ScenceDaoImpl();
            if(scenceDao.changeScence(sql, param) > 0){
                System.out.println("成功删除：" + scence.getH_id() + "号大厅" + scence.getS_time() + "场次电影");
            }else{
                System.out.println("删除失败");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        ManagerService ms = new ManagerService(1,"Manager");
//        //ms.delMovie();
//        //ms.addScence();
//        ms.delScence();
//
//    }

}
