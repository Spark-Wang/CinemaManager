package DAO;

import Entity.Scence;

import java.util.List;

public interface ScenceDao {
    //更改场次
    int changeScence(String sql, Object[] param);
    //返回场次
    List<Scence> getScence(String sql, String[] param);
    //返回所有的当前结果
    List<List<String>> findAllScence();

}
