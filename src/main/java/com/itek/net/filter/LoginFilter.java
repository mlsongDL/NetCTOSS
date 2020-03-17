/**
 * @Author mlsong
 * @Date 2020/3/17
 * @Description
 */
package com.itek.net.filter;

import com.itek.net.domain.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: net
 * @description: 用户登录拦截器，校验用户是否已经登录
 *  如果登录，可以访问系统的servlet
 *  如果没有登录，则无法访问系统的servlet
 * @author: mlsong
 * @create: 2020-03-17 15:18
 **/
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    /**
     * 过滤器的拦截逻辑
     * @param req
     * @param resp
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // 进入到servlet之前代码
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        // 对登录页面的请求，进行放行
        String uri = request.getRequestURI();

        // 如果不是登录页面进行用户的登录验证
        // 还需要过滤掉登录验证的check.login请求
        if (!uri.endsWith("page.login") && !uri.endsWith("check.login")) {
            // 验证用户是否已经登录，验证session中是否已经存在登录成功的标识
            HttpSession session = request.getSession();
            Account loginUser = (Account)session.getAttribute("loginUser");

            if (loginUser == null) {
                // 用户尚未登录，跳转到登录页
                // 转发到login.jsp  还是重定向到page.login
                response.sendRedirect("/net/page.login");
                return;
            }
        }

        chain.doFilter(req, resp); // 将请求转交给下一个组件进行处理
        // servlet执行之后的代码
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
