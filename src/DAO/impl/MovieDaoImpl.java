package DAO.impl;

import DAO.BaseDao;
import DAO.MovieDao;
import Entity.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Peiqi Wang
 * @Date 2022-08-15
 **/

public class MovieDaoImpl extends BaseDao implements MovieDao {

    @Override
    public List<Movie> getMovies(String sql, String[] param) {
        List<Movie> movieList = new ArrayList<>();
        try {
            connectSql(sql, param);
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setM_id(rs.getInt(1));
                movie.setM_name(rs.getString(2));
                movie.setM_price(rs.getDouble(3));
                movie.setM_durTime(rs.getInt(4));
                movie.setM_startTime(rs.getTimestamp(5));
                movie.setM_endTime(rs.getTimestamp(6));
                movieList.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return movieList;
    }

    @Override
    public int changeMovies(String sql, String[] params) {
        return super.executeSQL(sql, params);
    }
}
