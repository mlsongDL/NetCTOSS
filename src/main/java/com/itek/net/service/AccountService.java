/**
 * @Author mlsong
 * @Date 2020/3/18
 * @Description
 */
package com.itek.net.service;

import com.itek.net.dao.AccountDAO;

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

}
