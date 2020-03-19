/**
 * @Author mlsong
 * @Date 2020/3/17
 * @Description
 */
package com.itek.net.domain;

import java.util.Date;
import java.util.Objects;

/**
 * @program: net
 * @description: Account实体类，用来存储account表中的记录
 * @author: mlsong
 * @create: 2020-03-17 16:27
 **/
public class Account {

    // 使用包装类，接收数据库中的字段
    private Integer id;         // account表中id字段
    private String loginName;   // account表中login_name字段
    private String loginPwd;    // account表中login_passwd字段
    private String status;      // status字段
    private Date createDate;    // create_date字段
    private String realName;    // real_name
    private String idcardNo;    // idcard_no字段
    private Date lastLoginTime;   // last_login_time

    // lombok组件


    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(loginName, account.loginName) &&
                Objects.equals(loginPwd, account.loginPwd) &&
                Objects.equals(status, account.status) &&
                Objects.equals(createDate, account.createDate) &&
                Objects.equals(realName, account.realName) &&
                Objects.equals(idcardNo, account.idcardNo) &&
                Objects.equals(lastLoginTime, account.lastLoginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginName, loginPwd, status, createDate, realName, idcardNo, lastLoginTime);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", realName='" + realName + '\'' +
                ", idcardNo='" + idcardNo + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
