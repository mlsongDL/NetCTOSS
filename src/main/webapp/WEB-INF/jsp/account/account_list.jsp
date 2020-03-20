<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html>
    <head>
        <title>宏晶信息－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="${ctx}/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="${ctx}/styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            //删除
            function deleteAccount() {
                var r = window.confirm("确定要删除此账务账号吗？\r\n删除后将不能恢复，且会删除其下属的所有业务账号。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //开通或暂停
            function setState() {
                var r = window.confirm("确定要开通此账务账号吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="${ctx}/images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
                <li><a href="../account/account_list.html" class="account_on"></a></li>
                <li><a href="../service/service_list.html" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="${ctx}/list.acc" method="get">
                <!--查询-->
                <div class="search_add">                        
                    <div>身份证：<input type="text" name="idcardNo" class="text_search" /></div>
                    <div>姓名：<input type="text" name="realName" class="width70 text_search" /></div>
                    <div>登录名：<input type="text" name="loginName"  class="text_search" /></div>
                    <div>
                        状态：
                        <select class="select_search" name="status">
                            <option value="-1">全部</option>
                            <option value="0">开通</option>
                            <option value="1">暂停</option>
                            <option value="2">删除</option>
                        </select>
                    </div>
                    <div><input type="submit" value="搜索" class="btn_search" /></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='account_add.html';" />
                </div>
                <!--删除等的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="${ctx}/images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功，且已删除其下属的业务账号！
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th>账号ID</th>
                        <th>姓名</th>
                        <th class="width150">身份证</th>
                        <th>登录名</th>
                        <th>状态</th>
                        <th class="width100">创建日期</th>
                        <th class="width150">上次登录时间</th>                                                        
                        <th class="width200"></th>
                    </tr>
                        <%--
                            pageContext -> request -> seesion -> application(servletContext)

                            pageContext.setAttribute("account", 集合元素);

                        --%>
                    <c:forEach var="account" items="${accounts}">
                        <tr>
                            <td>${account.id}</td>
                            <td><a href="account_detail.html">${account.realName}</a></td>
                            <td>${account.idcardNo}</td>
                            <td>${account.loginName}</td>
                            <c:choose>
                                <c:when test="${account.status == 1}">
                                    <td>暂停</td>
                                </c:when>
                                <c:when test="${account.status == 0}">
                                    <td>开通</td>
                                </c:when>
                                <c:when test="${account.status == 2}">
                                    <td>删除</td>
                                </c:when>
                            </c:choose>
                            <td>${account.createDate}</td>
                            <td>${account.lastLoginTime}</td>
                            <td class="td_modi">
                                <input type="button" value="暂停" class="btn_pause" onclick="setState();" />
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='account_modi.html';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteAccount();" />
                            </td>
                        </tr>
                    </c:forEach>


                </table>
                <p>业务说明：<br />
                1、创建则开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、暂停账务账号，同时暂停下属的所有业务账号；<br />                
                6、暂停后重新开通账务账号，并不同时开启下属的所有业务账号，需要在业务账号管理中单独开启；<br />
                7、删除账务账号，同时删除下属的所有业务账号。</p>
                </div>                   
                <!--分页-->
                <%--<div id="pages">
                    <a href="${ctx}/list.acc">首页${totalPage}</a>
        	        <a href="#">上一页</a>
                    <a href="javascript:void(0);" onclick="goPage(1);" class="current_page">1</a>
                    <a href="javascript:void(0);" onclick="goPage(2);">2</a>
                    <a href="javascript:void(0);" onclick="goPage(3);">3</a>
                    <a href="javascript:void(0);" onclick="goPage(4);">4</a>
                    <a href="javascript:void(0);" onclick="goPage(5);">5</a>
                    <a href="#">下一页</a>
                    <a href="#">末页</a>
                </div>     --%>

                <div id="pages">
                    <a href="javascript:void(0);" onclick="goPage(1);">首页${curPage}</a>
                    <a href="javascript:void(0);" onclick="goPage(${curPage - 1})">上一页</a>

                    <%-- 总页数，小于等于5页， 有多少页，显示多少也 --%>
                    <c:if test="${totalPage <= 5}">
                        <c:forEach begin="1" end="${totalPage}" step="1" var="count">
                            <c:choose>
                                <c:when test="${count == curPage}">
                                    <a href="javascript:void(0);" class="current_page" onclick="goPage(${count});">${count}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:void(0);" onclick="goPage(${count});">${count}</a>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </c:if>

                    <%-- 总页数，大于5页， 有多少页，显示多少也 --%>
                    <c:if test="${totalPage > 5}">

                    </c:if>

                    <a href="javascript:void(0);" onclick="goPage(${curPage + 1});">下一页</a>
                    <a href="javascript:void(0);" onclick="goPage(${totalPage});">末页</a>
                </div>



            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[地址：合肥市高新区云飞路与文曲路交口创新产业园二期F4栋12层]</p>
            <p>版权所有&copy;合肥宏晶信息科技有限公司 </p>
        </div>


    <script>
        /**
         * 跳转页面
         * @param pageNo
         */
        function goPage(pageNo) {
            if (pageNo <= 0) {
                pageNo = 1;
            }

            if (pageNo > '${totalPage}') {
                pageNo = '${totalPage}';
            }


            location.href = '${ctx}' + '/list.acc?pageNo=' + pageNo;
        }
    </script>
    </body>
</html>
