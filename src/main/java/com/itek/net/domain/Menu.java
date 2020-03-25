/**
 * @Author mlsong
 * @Date 2020/3/25
 * @Description
 */
package com.itek.net.domain;

import java.util.Objects;

/**
 * @program: net
 * @description: menu表实体类
 * @author: mlsong
 * @create: 2020-03-25 16:13
 **/
public class Menu {
    public Menu() {
    }

    public Menu(Integer id, String menuName, String menuHref, String menuCss, Integer state) {
        this.id = id;
        this.menuName = menuName;
        this.menuHref = menuHref;
        this.menuCss = menuCss;
        this.state = state;
    }

    private Integer id;
    private String menuName;
    private String menuHref;
    private String menuCss;
    private Integer state;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", menuHref='" + menuHref + '\'' +
                ", menuCss='" + menuCss + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) &&
                Objects.equals(menuName, menu.menuName) &&
                Objects.equals(menuHref, menu.menuHref) &&
                Objects.equals(menuCss, menu.menuCss) &&
                Objects.equals(state, menu.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuName, menuHref, menuCss, state);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuCss() {
        return menuCss;
    }

    public void setMenuCss(String menuCss) {
        this.menuCss = menuCss;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
