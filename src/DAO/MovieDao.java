package DAO;

import Entity.Movie;

import java.util.List;

/**
 * @author Peiqi Wang
 * @date 2022-08-15
 */
public interface MovieDao {
    //获取电影对象
    List<Movie> getMovies(String sql, String[] params);
    //更改电影对象
    int changeMovies(String sql, String[] params);
}
