/**
 * @Author mlsong
 * @Date 2020/3/17
 * @Description
 */
package com.itek.net.service;

import com.itek.net.dao.AccountDAO;
import com.itek.net.dao.MenuDao;
import com.itek.net.domain.Account;
import com.itek.net.domain.Menu;
import com.itek.net.servlet.LoginServlet;
import com.itek.net.util.DBUtil;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: net
 * @description: 登录相关的业务处理类
 * @author: mlsong
 * @create: 2020-03-17 13:49
 **/
public class LoginService {

    public LoginService() {
        accountDAO = new AccountDAO();
        menuDao = new MenuDao();
    }
    // 操作account表的DAO组件，封装了所有对account表的sql操作
    private AccountDAO accountDAO;

    // menu菜单表sql执行类
    private MenuDao menuDao;


    /**
     * 跳转登录页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void forwardLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    /**
     * 校验用户名或密码是否正确
     * @param username
     * @param pwd
     * @return
     *      null: 表示用户名或密码错误
     *      不为null：表示当前用户信息的对象
     */
    public Account checkUserAndPwd(String username, String pwd) {
        return accountDAO.selectByUserAndPwd(username, pwd);
    }

    /**
     * 根据当前登录用户的id，查询用户所能访问的菜单列表
     * @param userId
     * @return
     */
    public List<Menu> findLoginUserMenus(Integer userId) {
        return menuDao.selectCorrelativeMenusByUserId(userId);
    }
}
