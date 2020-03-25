/**
 * @Author mlsong
 * @Date 2020/3/16
 * @Description
 */
package com.itek.net.servlet;

import com.itek.net.domain.Account;
import com.itek.net.domain.Menu;
import com.itek.net.service.LoginService;
import com.itek.net.util.DBUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: net
 * @description: 登录控制Servlet
 * @author: mlsong
 * @create: 2020-03-16 15:45
 **/
@WebServlet(urlPatterns = "*.login")
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        loginService = new LoginService();
    }

    /**
     * 登录业务处理器
     * servlet声明周期
     * 1. 实例化
     * 2. 初始化
     * 3. 准备就绪（service处理）
     * 4. 销毁
     */
    private LoginService loginService;

    /**
     * 重写父类service方法，自定义访问/login 路径的业务处理逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取项目的名称
        System.out.println(req.getContextPath());


        // 所有和登录相关的业务都在此servlet中进行处理
        String uri = req.getRequestURI();
        // 解析请求的路径
        String path = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));

        // path路径的匹配
        switch (path) {
            // page.login=> 转发到登录页面
            case "page":
                loginService.forwardLoginPage(req, resp);
                break;
            case "check":   // check.login=>用户的用户名和密码校验
                // 1. 接收用户提交过来的数据
                String username = req.getParameter("username");
                String password = req.getParameter("password");

                // 2. TODO 用户提交数据的校验, 验证码校验
                Account loginUser = loginService.checkUserAndPwd(username, password);
                if (loginUser != null) {
                    // 校验成功，进入首页
                    // 用户登录成功标识
                    HttpSession session = req.getSession();
                    session.setAttribute("loginUser", loginUser);

                    // 查询当前登录成功的用户所拥有的访问权限
                    List<Menu> menus = loginService.findLoginUserMenus(loginUser.getId());
                    session.setAttribute("menus", menus);

                    resp.sendRedirect("/net/index.index");
                } else {
                    // 校验失败，进入到登录页，并进行提示
                    resp.sendRedirect("/net/page.login?msg=用户名或密码错误");
                }

                break;


        }






    }

}
