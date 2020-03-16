/**
 * @Author mlsong
 * @Date 2020/3/16
 * @Description
 */
package com.itek.net.servlet;

import com.itek.net.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: net
 * @description: 登录控制Servlet
 * @author: mlsong
 * @create: 2020-03-16 15:45
 **/
@WebServlet(urlPatterns = "*.login")
public class LoginServlet extends HttpServlet {

    /**
     * 重写父类service方法，自定义访问/login 路径的业务处理逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 所有和登录相关的业务都在此servlet中进行处理
        String uri = req.getRequestURI();

        // 解析请求的路径
        String path = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));

        // path路径的匹配
        switch (path) {

            case "page":    // page.login=> 转发到登录页面
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
                break;

            case "check":   // check.login=>用户的用户名和密码校验
                // 1. 接收用户提交过来的数据
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String captcha = req.getParameter("captcha");

                // 2. TODO 用户提交数据的校验, 验证码校验

                // 3. 校验通过
                Connection conn = DBUtil.getConnection();
                String sql = "select count(1) from account where login_name = ? and login_passwd = ?";
                try {
                    // sql构造
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, username);
                    ps.setString(2, password);

                    // 执行sql语句
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);


                    resp.setContentType("text/html;charset=UTF-8");

                    if (count >= 1) {
                        // TODO 跳转到首页
//                        resp.getWriter().println("用户名密码输入正确");
                    } else {
//                        resp.getWriter().println("用户名密码输入错误");
                        // TODO  跳转到登录页面，并给出提示
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;


        }






    }

}
