package Role;

import Authority.CinemaMgr;
import Authority.Normal;
import com.sun.javaws.IconUtil;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class Role {

    private String description;
    private Normal normal = null;
    private CinemaMgr cinemaMgr = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Normal getNormal() {
        return normal;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }

    public CinemaMgr getCinemaMgr() {
        return cinemaMgr;
    }

    public void setCinemaMgr(CinemaMgr cinemaMgr) {
        this.cinemaMgr = cinemaMgr;
    }

    public void search(){
        if(normal == null && cinemaMgr == null){
            System.out.println("请先登陆后再进行操作");
        }else if(cinemaMgr != null){
            cinemaMgr.search();
        }else{
            normal.search();
        }
    }

    public void addMovie(){
        if(cinemaMgr == null){
            System.out.println("请按管理员登陆");
            return;
        }
        cinemaMgr.addMovie();
    }

    public void delMovie(){
        if(cinemaMgr == null){
            System.out.println("请按管理员登陆");
            return;
        }
        cinemaMgr.delMovie();
    }

    public void addScence(){
        if(cinemaMgr == null){
            System.out.println("请按管理员登陆");
            return;
        }
        cinemaMgr.addScence();
    }

    public void delScence(){
        if(cinemaMgr == null){
            System.out.println("请按管理员登陆");
            return;
        }
        cinemaMgr.delScence();
    }

    public void addUser(){
        if(cinemaMgr == null){
            System.out.println("请按管理员登陆");
            return;
        }
        cinemaMgr.addUser();
    }

    public void updateUser(){
        if(cinemaMgr == null){
            System.out.println("请按管理员登陆");
            return;
        }
        cinemaMgr.updateUser();
    }

    public void order(){
        if(normal == null){
            System.out.println("请先登陆");
            return;
        }
        normal.order();
    }

    public void refund(){
        if(normal == null){
            System.out.println("请先登陆");
            return;
        }
        normal.refund();
    }

    public void changeTicket(){
        if(normal == null){
            System.out.println("请先登陆");
            return;
        }
        normal.changeTicket();
    }

}
