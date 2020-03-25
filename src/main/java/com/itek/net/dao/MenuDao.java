/**
 * @Author mlsong
 * @Date 2020/3/25
 * @Description
 */
package com.itek.net.dao;

import com.itek.net.domain.Account;
import com.itek.net.domain.Menu;
import com.itek.net.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: net
 * @description: Menu表DAO操作类
 * @author: mlsong
 * @create: 2020-03-25 16:22
 **/
public class MenuDao {

    /**
     * 根据用户id查询用户关联的菜单列表
     * @param userId
     *          用户id
     * @return
     *          关联用户的菜单列表
     */
    public List<Menu> selectCorrelativeMenusByUserId(Integer userId) {
        // 1. 获取连接
        Connection conn = DBUtil.getConnection();
        // 2. 构造sql
        String sql = "select DISTINCT me.* from t_account_role ar " +
                "left join t_role_menu rm on ar.role_id = rm.role_id " +
                "left join menu me on me.id = rm.menu_id " +
                "where ar.account_id = ? and state = 1";
        List<Menu> menus = new ArrayList<>();
        try {
            // 3. 设置sql动态参数
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            // 执行sql语句
            ResultSet rs = ps.executeQuery();
            // 返回结果集可能为空，也可能有数据
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String menuName = rs.getString("menu_name");
                String menuHref = rs.getString("menu_href");
                String menuCss = rs.getString("menu_css");
                int state = rs.getInt("state");
                menus.add(new Menu(id, menuName, menuHref, menuCss, state));
            }
            return menus;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }
        return null;
    }
}
