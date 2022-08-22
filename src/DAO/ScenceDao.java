package DAO;

import Entity.Scence;

import java.util.List;

public interface ScenceDao {
    //更改场次
    int changeScence(String sql, Object[] param);
    //返回场次
    List<Scence> getScence(String sql, String[] param);

    /**
     * 返回所有的当前结果
     * 因为需要并表查询，所以返回的不是Scence对象，而是二维字符串列表
     * @return
     */
    List<List<String>> findAllScence();

}
