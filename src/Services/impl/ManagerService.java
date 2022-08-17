package Services.impl;

import Services.MovieHandler;
import Services.ScenceHandler;
import Services.UserHandler;


/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class ManagerService extends Service implements MovieHandler, ScenceHandler, UserHandler {
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

    }

    @Override
    public void delMovie() {

    }

    @Override
    public void addScence() {

    }

    @Override
    public void delScence() {

    }

    @Override
    public void addUser() {

    }

    @Override
    public void updateUser() {

    }
}
