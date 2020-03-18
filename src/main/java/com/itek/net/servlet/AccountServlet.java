/**
 * @Author mlsong
 * @Date 2020/3/18
 * @Description
 */
package com.itek.net.servlet;

import com.itek.net.domain.Account;
import com.itek.net.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: net
 * @description: 账务账号模块servlet控制器 入口
 * @author: mlsong
 * @create: 2020-03-18 16:13
 **/
@WebServlet(urlPatterns = "*.acc")
public class AccountServlet extends HttpServlet {

    public AccountServlet() {
        accountService = new AccountService();
    }

    /**
     * 账务账号模块业务处理器
     */
    private AccountService accountService;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 所有和登录相关的业务都在此servlet中进行处理
        String uri = req.getRequestURI();
        // 解析请求的路径
        String path = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));

        switch (path) {
            case "list":
                // 从account表中查询表中的记录
//                List<Account> accounts = accountService.findAccounts();
                List<Account> accounts = new ArrayList<>();
                Account account = new Account();

                account.setStatus("1");
                account.setRealName("zhangsan");
                account.setLoginName("szhang");
                account.setIdcardNo("12321313221312");

                Account account2 = new Account();

                account2.setStatus("2");
                account2.setRealName("lisi");
                account2.setLoginName("sli");
                account2.setIdcardNo("23456789");

                accounts.add(account);
                accounts.add(account2);



                // TODO 将查询数据绑定到jsp页面
                // request / session / servletContext  / pageContext
                req.setAttribute("accounts", accounts);

                // 转发到账务账号模块页面jsp
                req.getRequestDispatcher("/WEB-INF/jsp/account/account_list.jsp")
                        .forward(req, resp);
                break;
        }

    }
}
