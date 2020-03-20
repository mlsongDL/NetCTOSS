/**
 * @Author mlsong
 * @Date 2020/3/18
 * @Description
 */
package com.itek.net.service;

import com.itek.net.dao.AccountDAO;
import com.itek.net.domain.Account;

import java.util.List;

/**
 * @program: net
 * @description: 用来处理Account账务账号模块所有的业务
 * @author: mlsong
 * @create: 2020-03-18 16:21
 **/
public class AccountService {

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    /**
     * account表的dao操作类
     */
    private AccountDAO accountDAO;

    /**
     * 查询用户记录  account
     * @return
     */
    public List<Account> findAllAccounts() {
        return accountDAO.selectAll();
    }

    /**
     * 根据页码和每页显式的记录条数查询数据
     * @param pageNo
     *      页码
     * @param pageSize
     *      页面大小
     * @return
     */
    public List<Account> findAccountsByPage(int pageNo, int pageSize) {
        return accountDAO.selectByPage(pageNo, pageSize);
    }

    public int findAccountsCount() {
        return accountDAO.count();
    }

    /**
     * 根据条件进行账号信息查询
     * @param acc
     *      查询条件
     * @return
     */
    public List<Account> findAccountsByConditions(Account acc) {
        return accountDAO.selectByConditions(acc);
    }
}
