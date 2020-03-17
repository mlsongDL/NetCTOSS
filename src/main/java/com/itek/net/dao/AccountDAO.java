/**
 * @Author mlsong
 * @Date 2020/3/17
 * @Description
 */
package com.itek.net.dao;

import com.itek.net.domain.Account;
import com.itek.net.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: net
 * @description: account表的DAO操作层
 * @author: mlsong
 * @create: 2020-03-17 16:03
 **/
public class AccountDAO {
    /**
     * 根据用户名和密码统计匹配的用户记录数
     * @param username
     *      用户名
     * @param pwd
     *      密码
     * @return
     */
    public int countByUserAndPwd(String username, String pwd) {
        // 1. 获取连接
        Connection conn = DBUtil.getConnection();
        // 2. 构造sql
        String sql = "select count(1) from account where login_name = ? and login_passwd = ?";
        try {
            // 3. 设置sql动态参数
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pwd);

            // 执行sql语句
            ResultSet rs = ps.executeQuery();
            rs.next();
           return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }
        return 0;
    }

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     *      用户名
     * @param pwd
     *      用户密码
     * @return
     *      查询到的用户记录
     */
    public Account selectByUserAndPwd(String username, String pwd) {
        // 1. 获取连接
        Connection conn = DBUtil.getConnection();
        // 2. 构造sql
        String sql = "select * from account where login_name = ? and login_passwd = ?";
        try {
            // 3. 设置sql动态参数
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pwd);

            // 执行sql语句
            ResultSet rs = ps.executeQuery();
            // 返回结果集可能为空，也可能有数据
            if (rs.next()) {
                // 结果集不为空，并且，我们执行的sql语句，入股哦有结果，肯定只有一条
                Account account = new Account(); // 用实体类对象，接收，查询结果的记录
                account.setId(rs.getInt("id"));
                account.setCreateDate(rs.getDate("create_date"));
                account.setIdcardNo(rs.getString("idcard_no"));
                account.setLoginName(rs.getString("login_name"));
                account.setLoginPwd(rs.getString("login_passwd"));
                account.setRealName(rs.getString("real_name"));
                account.setStatus(rs.getString("status"));
                return account;

            } else {
                // 结果集为空，没有查到任何记录
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }
        return null;



    }
}
