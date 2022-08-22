package Services.impl;

import DAO.HallDao;
import DAO.ScenceDao;
import DAO.TicketDao;
import DAO.impl.HallDaoImpl;
import DAO.impl.ScenceDaoImpl;
import DAO.impl.TicketDaoImpl;
import Entity.Hall;
import Entity.Scence;
import Entity.Ticket;
import Services.TicketHandler;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class CustomerService extends Service implements TicketHandler {

    public CustomerService(int C_id, String C_type) {
        super(C_id, C_type);
    }

    @Override
    public void search() {
        while(true){
            System.out.println("=============用户查询界面============");
            System.out.println("请输入查询的信息：1.查询当前场次 2.查询订票记录 3.退出");
            int choice = inputCheck();
            switch (choice){
                case 1:
                    getScence();
                    break;
                case 2:
                    getOrderRecord();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("输入有误,请重新再试！");
            }
        }
    }

    @Override
    public void order() {
        //获取上映场次列表
        List<String> movieNameList = getScence();
        HashSet<String> movieSet = new HashSet<>();
        for(String s:movieNameList){
            movieSet.add(s);
        }
        System.out.println("请输入电影名称");
        String movieName = scanner.next();
        if(!movieSet.contains(movieName)){
            System.out.println("未查询到该电影");
            return;
        }
        //查询输入名称的电影 的排片
        ScenceDao scenceDao = new ScenceDaoImpl();
        String sql = "select scence.m_id, scence.h_id, scence.s_time from scence join movie on movie.m_id = scence.m_id where movie.m_name like ? ";
        String[] param = new String[]{movieName};
        List<Scence> scenceList = scenceDao.getScence(sql, param);
        System.out.println("序号\t\t\t电影名\t\t\t\t影厅号\t\t放映时间");
        for(int i = 0;i < scenceList.size();i ++){
            Scence scence = scenceList.get(i);
            System.out.format("%-8d\t%-16s\t%-10s\t%-28s\n",(i + 1),movieName,scence.getH_id(),scence.getS_time());
        }
        //选择相应的场次
        int choice;
        while(true){
            System.out.println("选择场次：");
            choice = inputCheck();
            if(choice <= scenceList.size() && choice > 0)
                break;
        }
        //对应场次的影厅信息
        HallDao hallDao = new HallDaoImpl();
        param = new String[]{scenceList.get(choice - 1).getH_id() + ""};
        List<Hall> hallList = hallDao.getHall("select * from hall where h_id = ?",param);
        int scence_mid = scenceList.get(choice - 1).getM_id();
        int scence_hid = scenceList.get(choice - 1).getH_id();
        Timestamp s_timestamp = scenceList.get(choice - 1).getS_time();
        TicketDao ticketDao = new TicketDaoImpl();
        sql = "select * from ticket where m_id = ? and h_id = ? and t_time = ?";
        param = new String[]{scence_mid + "", scence_hid + "", s_timestamp + ""};
        List<Ticket> ticketList = ticketDao.getTicket(sql, param);
        //订票数量
        System.out.println("现已订购：" + ticketList.size() + "，剩余：" + (hallList.get(0).getH_capacity()-ticketList.size()));
        System.out.println("请输入需要预订的电影票数：");
        int count = inputCheck();
        if(count > (hallList.get(0).getH_capacity() - ticketList.size())){
            System.out.println("超出可定的最大票数，订票失败！");
            return;
        }else{
            while(true){
                System.out.println("请选择选座方式：1.手工选座 2.自动选座");
                int c = inputCheck();
                if(c == 1){
                    selfChoose(scenceList.get(choice - 1), hallList.get(0), count);
                    break;
                }else if(c == 2){
                    autoChoose(scenceList.get(choice - 1), hallList.get(0), count);
                    break;
                }else{
                    System.out.println("请重新输入正确的数字！");
                }
            }
        }
    }

    public char[][] showScence(Scence scence){
        HallDao hallDao = new HallDaoImpl();
        String []param = new String[]{scence.getH_id() + ""};
        List<Hall> hallList = hallDao.getHall("select * from hall where h_id = ?",param);
        int hallLine = hallList.get(0).getH_line();
        int hallRow = hallList.get(0).getH_row();
        char[][] seat = new char[hallLine][hallRow];
        for(int l = 0;l < hallLine;l ++){
            for(int r = 0;r < hallRow;r ++){
                seat[l][r] = '_';
            }
        }
        int scence_mid = scence.getM_id();
        int scence_hid = scence.getH_id();
        Timestamp s_timestamp = scence.getS_time();
        TicketDao ticketDao = new TicketDaoImpl();
        String sql = "select * from ticket where m_id = ? and h_id = ? and t_time = ?";
        param = new String[]{scence_mid + "", scence_hid + "", s_timestamp + ""};
        List<Ticket> ticketList = ticketDao.getTicket(sql, param);
        for(int i = 0;i < ticketList.size();i ++){
            seat[ticketList.get(i).getH_Line() - 1][ticketList.get(i).getH_Row() - 1] = '#';
        }
        printSeat(seat, hallLine, hallRow);
        return seat;
    }
    public void selfChoose(Scence scence, Hall hall, int count){
        char[][] seat = showScence(scence);
        for(int num = 0;num <count;num ++){
            System.out.println("请输入排：");
            int i = inputCheck();
            System.out.println("请输入列：");
            int j = inputCheck();
            while(i > hall.getH_line() || i < 0 || j > hall.getH_row() || j < 0){
                System.out.println("输入的位置有误");
                i = inputCheck();
                j = inputCheck();
            }
            while(seat[i - 1][j - 1] == '#'){
                System.out.println("位置已被选，请重新选择");
                i = inputCheck();
                j = inputCheck();
            }
            String sql = "insert into ticket (C_id, M_id, H_id, H_line, H_row, T_time) values (?,?,?,?,?,?)";
            Object[] params = new Object[]{super.getC_id(),scence.getM_id(),scence.getH_id(),i,j,scence.getS_time()};
            TicketDao ticketDao = new TicketDaoImpl();
            if(ticketDao.insertTicket(sql,params) > 0){
                System.out.println("购票成功！在"+i+"排"+j+"列");
            }else{
                System.out.println("购票失败，请重试！");
            }
            if(num > 0){
                System.out.println("是否要继续购票：1.是 2.否");
                int buyChoice = inputCheck();
                if(buyChoice != 1)
                    break;
            }
        }
    }

    public void autoChoose(Scence scence,Hall hall, int count){
        char[][] seat = showScence(scence);
        int hallLine = hall.getH_line();
        int hallRow = hall.getH_row();
        int left = hallRow / 4;
        int right = hallRow * 3 / 4;
        int front = hallLine / 4;
        int back = hallLine * 3 / 4;
        int templ = front;
        int tempr = left - 1;
        int autoline = 0;
        int autorow = 0;
        boolean flag = false;

        for(int l = front;l < back;l ++){
            for(int r = left;r < right;r ++){
                if(seat[l][r] == '#' && !flag){
                    if(r - left >= count){
                        autoline = l;
                        autorow = left;
                        break;
                    }
                    templ = l;
                    tempr = r;
                    flag = true;
                }else if(seat[l][r] == '#' && flag){
                    if(r - tempr - 1 >= count){
                        autoline = templ;
                        autorow = tempr;
                        break;
                    }else{
                        templ = l;
                        tempr = r;
                    }
                }
            }
            if(right - tempr - 1 >= count){
                autoline = templ;
                autorow = tempr + 1;
                break;
            }else{
                templ = l + 1;
                tempr = left - 1;
                flag = false;
            }
        }
        int num = 0;
        autoline++;
        autorow++;
        TicketDao ticketDao = new TicketDaoImpl();
        if(hallRow < count || (autorow + count) > hallRow || autoline > hallLine){
            System.out.println("暂时无法自动优化，进入顺序选座！");
            for(int i = 0;i < hallLine;i ++){
                for(int j = 0;j < hallRow && num < count;j ++){
                    if(seat[i][j] != '#'){
                        String sql = "insert into ticket(C_id, M_id, H_id, H_line, H_row, T_time) values (?,?,?,?,?,?)";
                        Object[] params = {super.getC_id(),scence.getM_id(),scence.getH_id(),(i+1),(j+1),scence.getS_time()};
                        if(ticketDao.insertTicket(sql, params) > 0){
                            System.out.println("购票成功！在"+(i+1)+"排，"+(j+1)+"列");
                        }else System.out.println("购票失败！");
                        num++;
                    }
                }
            }
            return;
        }else{
            String sql = "insert into ticket(C_id, M_id, H_id, H_line, H_row, T_time) values (?,?,?,?,?,?)";
            while(num < count) {
                Object[] params = {super.getC_id(), scence.getM_id(), scence.getH_id(), autoline, (autorow + num), scence.getS_time()};
                if(ticketDao.insertTicket(sql, params) > 0){
                    System.out.println("自动选座成功！在"+autoline+"排，"+(autorow+num)+"列");
                }else System.out.println("购票失败！");
                num++;
            }
        }
    }

    public void printSeat(char[][] seat, int line, int row){
        System.out.print("\t\t");
        for(int i = 0;i < row;i ++){
            System.out.print("\t"+ (i + 1));
        }
        System.out.println();
        for(int i = 0;i < line;i ++){
            System.out.format("\t%-2d |",(i + 1));
            for(int j = 0;j < row;j ++){
                System.out.print("\t"+seat[i][j]);
            }
            System.out.print("\t|\n");
        }
        System.out.println("\t\t\t可选：_ 不可选：#");
    }

    @Override
    public void refund() {
        System.out.println("以下为订票记录：");
        TicketDao ticketDao = new TicketDaoImpl();
        List<Ticket> ticketList = ticketDao.findTicketByUser(super.getC_id());
        if(ticketList.size() == 0){
            System.out.println("没有可以退的票，请确认后再试！");
            return;
        }
        getOrderRecord();
        System.out.println("请输入要退票的记录号：");
        int no = inputCheck();
        while(no > ticketList.size() || no < 1){
            System.out.println("输入错误，请重新输入");
            no = inputCheck();
        }
        Ticket ticket = ticketList.get(no - 1);
        String sql = "delete from ticket where c_id = ? and m_id = ? and h_id = ? and h_line = ? and h_row = ? and t_time = ?";
        Object[] param = {ticket.getC_id(), ticket.getM_id(), ticket.getH_id(), ticket.getH_Line(), ticket.getH_Row(), ticket.getT_time()};
        if(ticketDao.delTicket(sql, param) > 0){
            System.out.println("退票成功");
        }else{
            System.out.println("退票失败");
        }
    }

    @Override
    public void changeTicket() {
        System.out.println("以下为订票记录：");
        TicketDao ticketDao = new TicketDaoImpl();
        List<Ticket> ticketList = ticketDao.findTicketByUser(super.getC_id());
        if(ticketList.size() == 0){
            System.out.println("没有可以改签的票，请确认后再试！");
            return;
        }
        getOrderRecord();
        System.out.println("请输入要改签的记录号：");
        int no = inputCheck();
        while(no > ticketList.size() || no < 1){
            System.out.println("输入错误，请重新输入");
            no = inputCheck();
        }
        Ticket ticket = ticketList.get(no - 1);
        int m_id = ticket.getM_id();
        ScenceDao scenceDao = new ScenceDaoImpl();
        List<Scence> scenceList = scenceDao.getScence("select * from scence where m_id = " + m_id,null);
        if(scenceList.size() == 1){
            System.out.println("没有可以改签的其他场次！");
            return;
        }else{
            String sql = "delete from ticket where c_id = ? and m_id = ? and h_id = ? and h_line = ? and h_row = ? and t_time = ?";
            Object[] param = {ticket.getC_id(), ticket.getM_id(), ticket.getH_id(), ticket.getH_Line(), ticket.getH_Row(), ticket.getT_time()};
            ticketDao.delTicket(sql,param);
            System.out.println("序号\t\t影厅号\t\t放映时间");
            for(int i = 0;i < scenceList.size();i ++){
                Scence scence = scenceList.get(i);
                System.out.format("%-8d\t%-8d\t%-20s\n",(i + 1),scence.getH_id(),scence.getS_time());
            }
            System.out.println("请选择场次；");
            int choice = inputCheck();
            System.out.println("请您选择座位：");
            sql = "select * from hall where h_id = " + scenceList.get(choice - 1).getH_id();
            HallDao hallDao = new HallDaoImpl();
            List<Hall> hallList = hallDao.getHall(sql,null);
            selfChoose(scenceList.get(choice - 1),hallList.get(0),1);
        }
    }

//    public static void main(String[] args) {
//        CustomerService customerService = new CustomerService(5, "User");
//        customerService.order();
//        //customerService.printSeat(new char[10][10],10,10);
//        customerService.refund();
//        customerService.changeTicket();
//        customerService.search();
//    }
}
