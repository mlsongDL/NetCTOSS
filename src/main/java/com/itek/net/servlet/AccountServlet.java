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

    public static final int PAGE_SIZE = 3;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 所有和登录相关的业务都在此servlet中进行处理
        String uri = req.getRequestURI();
        // 解析请求的路径
        String path = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));

        switch (path) {
            case "list":
                // 从account表中查询表中的记录
                // V1.0从account表中查询全部记录
//                List<Account> accounts = accountService.findAllAccounts();
                // V2.0可以接收分页数据，进行分页数据查询
                String pageNoStr = req.getParameter("pageNo");
                String pageSizeStr = req.getParameter("pageSize");
                int pageNo = 1;
                try {
                    pageNo = Integer.parseInt(pageNoStr);
                } catch (Exception e) {
                    System.out.println("pageNo格式输入有误！pageNo默认设置为1");
                    pageNo = 1;
                }
                int pageSize = PAGE_SIZE;
                try {
                    pageSize = Integer.parseInt(pageSizeStr);
                    if (pageSize <= 0) {
                        pageSize = PAGE_SIZE;
                    }
                } catch (Exception e) {
                    System.out.println("pageSize格式输入有误！pageSize默认设置为常量值");
                    pageSize = PAGE_SIZE;
                }
                // V2.0根据页码和每页显式的记录条数查询数据
//                List<Account> accounts = accountService.findAccountsByPage(pageNo, pageSize);
                // V3.0 再页面上点击搜索按钮，向list.acc发送身份证号，姓名，登录名以及状态等信息
                // 根据以上提交过来的信息进行数据的分页+模糊查询
                // like '% _'
                // 接收模糊查询的四个条件
                Account acc = new Account();
                // 如果页面没有填写该表单控件，传过来的值不是null值，而是空白字符串
                acc.setIdcardNo(req.getParameter("idcardNo") == null ? "" : req.getParameter("idcardNo"));
                acc.setLoginName(req.getParameter("loginName") == null ? "" : req.getParameter("loginName"));
                acc.setRealName(req.getParameter("realName") == null ? "" : req.getParameter("realName"));
                acc.setStatus(req.getParameter("status") == null ? "-1" : req.getParameter("status"));
                acc.setPageNo(pageNo);
                acc.setPageSize(pageSize);

                // TODO 使用业务处理，-》DAO进行sql构造，数据查询
                List<Account> accounts = accountService.findAccountsByConditions(acc);




                // 查询数据总记录条数
                double totalCount = accountService.findAccountsCount();

                // 计算总页数
                int totalPage = (int)Math.ceil(totalCount/pageSize);




                // 将查询数据绑定到jsp页面
                req.setAttribute("accounts", accounts);
                req.setAttribute("totalPage", totalPage);
                // 当前页面显示的页码
                req.setAttribute("curPage", pageNo);


                // 转发到账务账号模块页面jsp
                req.getRequestDispatcher("/WEB-INF/jsp/account/account_list.jsp")
                        .forward(req, resp);
                break;
        }

    }
}
