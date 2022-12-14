package Role;

import Authority.CinemaMgr;
import Authority.Normal;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class Role {

    private String description;//设置manager或user
    //普通 管理 一个role只能有一种被赋值
    private Normal normal = null;//普通的权限
    private CinemaMgr cinemaMgr = null;//管理的权限

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
