package User;

import Authority.CinemaMgr;
import Authority.Normal;
import Authority.impl.Manager;
import Authority.impl.NormalUser;
import DAO.UserDao;
import DAO.impl.UserDaoImpl;
import Entity.Customer;
import Role.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class User {
    private Role role = null;//用户的角色
    public UserDao userDao = new UserDaoImpl();//对用户表的操作
    public HashMap<String,Customer> map = new HashMap<>();//暂存取出的用户数据，便于查询
    public String userName;//当前登陆用户的用户名
    Scanner scanner = new Scanner(System.in);//便捷获取输入

    public Role getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public boolean init(){
        List<Customer> customerList = userDao.getAllUser();
        for(Customer customer:customerList){
            Customer temp = customer;
            map.put(temp.getC_name(), temp);
        }
        return map.size() > 0;
    }

    public boolean login(){
        role = new Role();
        System.out.println("请输入用户名：");
        userName = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();
        if(!map.containsKey(userName)){
            System.out.println("用户不存在，请先注册！");
            return false;
        }else {
            Customer curUser = map.get(userName);
            if(curUser.getC_password().equals(password)){
                if(curUser.getC_type().equals("Manager")){
                    role.setDescription("Manager");
                    CinemaMgr mgr = new Manager(curUser.getC_id());
                    role.setCinemaMgr(mgr);
                    return true;
                }else if(curUser.getC_type().equals("User")){
                    role.setDescription("User");
                    Normal normal = new NormalUser(curUser.getC_id());
                    role.setNormal(normal);
                    return true;
                }
            }else{
                System.out.println("密码错误，请重新输入");
                return false;
            }
        }
        return false;
    }

    public boolean regist(){
        System.out.println("请输入需要注册的用户名：(长度为3-10)");
        Scanner input = new Scanner(System.in);
        String name = input.next();
        if(name.length() >= 3 && name.length() <= 10){
            if(map.containsKey(name)){
                System.out.println("用户名已存在，请重新取名");
            }else{
                System.out.println("请输入密码：");
                String password = input.next();
                System.out.println("请输入密码确认：");
                String password2 = input.next();
                if(password2.equals(password)){
                    Customer customer = new Customer(map.size() + 1, name, password, "User");
                    if(userDao.addUser(customer) > 0){
                        System.out.println("用户信息添加成功");
                        map.put(name, customer);
                        return true;
                    }else {
                        System.out.println("用户信息添加失败");
                    }
                }else{
                    System.out.println("两次密码输入必须相同");
                }
            }
        }else{
            System.out.println("请输入正确长度的用户名");
        }
        return false;
    }

    public void addUser(){
        regist();
    }

    public void updateUser(){
        System.out.println("请输入原用户名：");
        String c_name = scanner.next();
        System.out.println("请输入原密码：");
        String c_password = scanner.next();
        if(!map.containsKey(c_name) || !map.get(c_name).getC_password().equals(c_password)){
            System.out.println("输入错误！");
            return;
        }
        int c_id = map.get(c_name).getC_id();
        System.out.println("请输入新用户名：");
        String new_name = scanner.next();
        System.out.println("请输入新密码：");
        String new_password = scanner.next();
        System.out.println("请输入新权限：Manager/User");
        String new_type = scanner.next();
        Customer customer = new Customer(c_id, new_name, new_password, new_type);
        String sql = "update customer set C_name = ?,C_password = ?, C_type = ? where C_id = ? and C_name = ?";
        Object[] param = {customer.getC_name(),customer.getC_password(),customer.getC_type(),customer.getC_id(),c_name};
        UserDao userDao = new UserDaoImpl();
        if(userDao.updateUser(sql, param) > 0){
            System.out.println("修改成功："+customer.getC_id()+"号用户为"+customer.getC_name()+",密码："+customer.getC_password()+"类型："+customer.getC_type());
        }
        init();
    }

    public void search(){
        role.search();
    }

    public void order(){
        role.order();
    }

    public void refund(){
        role.refund();
    }

    public void changeTicket(){
        role.changeTicket();
    }

    public void addMovie(){
        role.addMovie();
    }

    public void delMovie(){
        role.delMovie();
    }

    public void addScence(){
        role.addScence();
    }

    public void delScence(){
        role.delScence();
    }

//    public static void main(String[] args) {
//        User user = new User();
//        user.updateUser();
//    }
}
