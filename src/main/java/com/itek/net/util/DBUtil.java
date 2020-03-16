/**
 * @Author mlsong
 * @Date 2020/3/16
 * @Description
 */
package com.itek.net.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @program: net
 * @description:
 * @author: mlsong
 * @create: 2020-03-16 16:23
 **/
public class DBUtil {

    private static String url;
    private static String username;
    private static String pwd;
    private static String driverName;

    private static BasicDataSource dataSource;

    static {
        Properties prop = new Properties();
        try {
            prop.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            System.out.println("加载配置文件失败！");
            e.printStackTrace();
        }

        url = prop.getProperty("jdbc.url");
        driverName = prop.getProperty("jdbc.driver");
        username = prop.getProperty("jdbc.username");
        pwd = prop.getProperty("jdbc.password");

        // 初始化连接池
        dataSource = new BasicDataSource();
        dataSource.setUsername(username);
        dataSource.setDriverClassName(driverName);
        dataSource.setPassword(pwd);
        dataSource.setUrl(url);
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("获取连接对象失败！！");
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭连接错误！");
                e.printStackTrace();
            }
        }
    }




}
