package DAO;

import Entity.Hall;

import java.util.List;

/**
 * @author Peiqi Wang
 * @date 2022-08-15
 */
public interface HallDao {
    //根据条件寻找影厅
    List<Hall> getHall(String sql, String[] param);
    //修改影厅设置
    int changeHall(String sql, String[] param);
}
