<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>宏晶信息－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" /> 
    </head>
    <body class="index">
        <!--导航区域开始-->
        <div id="index_navi">
            <ul id="menu">
                <%--
                    pageContext -> request -> session -> application
                --%>
                <c:forEach var="menu" items="${menus}">
                    <li>
                        <c:choose>
                            <c:when test="${menu.menuName == '主页'}">
                                <a href="${ctx}/${menu.menuHref}" class="${menu.menuCss}_on"></a>
                            </c:when>
                            <c:otherwise>
                                <a href="${ctx}/${menu.menuHref}" class="${menu.menuCss}_off"></a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
