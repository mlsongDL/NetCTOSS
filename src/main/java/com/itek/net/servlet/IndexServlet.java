/**
 * @Author mlsong
 * @Date 2020/3/17
 * @Description
 */
package com.itek.net.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: net
 * @description: 首页servlet处理器
 * @author: mlsong
 * @create: 2020-03-17 15:06
 **/
@WebServlet(urlPatterns = "/index.index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
    }
}
