package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class BaseDao {
    public static String DRIVER; // 数据库驱动

    public static String URL ; // url

    public static String DBNAME; // 数据库用户名

    public static String DBPASS; // 数据库密码

    protected Connection conn = null; // 保存数据库连接

    protected PreparedStatement pstmt = null; // 用于执行SQL语句

    protected ResultSet rs = null; // 用户保存查询到的结果集

    protected void connectSql(String sql, String[] param) throws ClassNotFoundException, SQLException {
        conn = getConn();
        pstmt = conn.prepareStatement(sql);
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                pstmt.setString(i + 1, param[i]);
            }
        }
        rs = pstmt.executeQuery();
    }

    static{
        init();
    }

    public static void init(){
        Properties params=new Properties();
        String configFile = "database.properties";//配置文件路径
        //加载配置文件到输入流中
        InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);

        try {
            //从输入流中读取属性列表
            params.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //根据指定的获取对应的值
        DRIVER=params.getProperty("driver");
        URL=params.getProperty("url");
        DBNAME=params.getProperty("user");
        DBPASS=params.getProperty("password");
    }

    public Connection getConn() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try {
            Class.forName(DRIVER); // 注册驱动
            conn = DriverManager.getConnection(URL, DBNAME, DBPASS); // 获得数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn; // 返回连接
    }

    public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {

        /* 如果rs不空，关闭rs */
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /* 如果pstmt不空，关闭pstmt */
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /* 如果conn不空，关闭conn */
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int executeSQL(String preparedSql, Object[] param) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int num = 0;

        /* 处理SQL,执行SQL */
        try {
            conn = getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
                }
            }
            num = pstmt.executeUpdate(); // 执行SQL语句
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // 处理ClassNotFoundException异常
        } catch (SQLException e) {
            e.printStackTrace(); // 处理SQLException异常
        } finally {
            this.closeAll(conn, pstmt, null);
        }
        return num;
    }

//    public static void main(String[] args) {
//        BaseDao baseDao = new BaseDao();
//        Connection conn = null;
//        try{
//            conn = baseDao.getConn();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }finally {
//            baseDao.closeAll(conn, null, null);
//        }
//    }
}
