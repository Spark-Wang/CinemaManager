package Menu;

import User.User;

import java.util.Scanner;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class Mainmenu {
    public User user = new User();
    Scanner scanner = new Scanner(System.in);

    public void mainMenu(){
        if(!user.init()){
            System.out.println("未能成功获取数据，无法进入系统！");
            return;
        }
        int input;
        while(true){
            System.out.println("===========欢迎进入影院管理系统============");
            System.out.println("1.用户登陆\t2.用户注册\t3.退出");
            System.out.print("请输入数字选择要进行的操作：");
            input = inputCheck();
            switch (input){
                case 1:
                    LoginMenu();
                    break;
                case 2:
                    RegistMenu();
                    break;
                case 3:
                    System.out.println("欢迎下次再来");
                    return;
                default:
                    System.out.println("输入格式有误，请重新再试:");
                    input = scanner.nextInt();
            }
        }
    }

    public void LoginMenu(){
        System.out.println("===============用户登陆==============");
        if(user.login()) systemIn();
    }

    public void RegistMenu(){
        System.out.println("===============用户注册==============");
        if(user.regist()){
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败，请重新注册！");
        }
    }

    public void systemIn(){
        System.out.println("==============WELCOME==============");
        while(true){
            if(user.getRole().getDescription().equals("Manager")){
                System.out.println("===============管理系统=============");
                System.out.println("欢迎您，"+user.getUserName()+",请选择您的操作");
                System.out.println("1.查询信息\t2.影片管理\t3.放映管理\t4.用户管理\t5.退出");
                int input = inputCheck();
                switch (input){
                    case 1:
                        user.search();
                        break;
                    case 2:
                        manageMovieMenu();
                        break;
                    case 3:
                        manageScenceMenu();
                        break;
                    case 4:
                        manageUserMenu();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.print("请输入正确的数字：");
                }
            }else if(user.getRole().getDescription().equals("User")){
                System.out.println("==============用户系统==============");
                System.out.println("欢迎用户" + user.getUserName() + "登陆，请选择需要的服务：");
                System.out.println("1.订票\t2.退票\t3.改签\t4.查询\t5.退出");
                int input = inputCheck();
                switch (input){
                    case 1:
                        user.order();
                        break;
                    case 2:
                        user.refund();
                        break;
                    case 3:
                        user.changeTicket();
                        break;
                    case 4:
                        user.search();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.print("请输入正确的数字：");
                }
            }else{
                System.out.println("输入错误，请再试一次");
                return;
            }
        }
    }

    public void manageMovieMenu(){
        System.out.println("=============影片管理============");
        System.out.println("1.上架影片\t2.下架影片\t3.退出");
        int input = inputCheck();
        switch (input){
            case 1:
                user.addMovie();
                break;
            case 2:
                user.delMovie();
                break;
            default:
                return;
        }
    }

    public void manageScenceMenu(){
        System.out.println("=============放映管理============");
        System.out.println("1.添加放映\t2.结束放映\t3.退出");
        int input = inputCheck();
        switch (input){
            case 1:
                user.addScence();
                break;
            case 2:
                user.delScence();
                break;
            default:
                return;
        }
    }

    public void manageUserMenu(){
        System.out.println("=============用户管理============");
        System.out.println("1.添加用户\t2.修改用户\t3.退出");
        int input = inputCheck();
        switch (input){
            case 1:
                user.addUser();
                break;
            case 2:
                user.updateUser();
                break;
            default:
                return;
        }
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
}
