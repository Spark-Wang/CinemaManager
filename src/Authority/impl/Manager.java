package Authority.impl;

import Authority.CinemaMgr;
import Services.impl.ManagerService;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class Manager implements CinemaMgr {
    private int C_id;
    private String C_type = "Manager";
    public ManagerService managerService = new ManagerService(C_id,C_type);

    public Manager(int C_id){
        this.C_id = C_id;
        managerService.setC_id(C_id);
    }
    @Override
    public void search() {
        managerService.mgrSearch();
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
