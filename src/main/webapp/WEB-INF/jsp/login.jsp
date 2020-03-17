<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>宏晶信息－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" /> 
    </head>
    <body class="index">
        <div class="login_box">
            <form action="/net/check.login" method="post">
                <table>
                    <tr>
                        <td class="login_info">账号：</td>
                        <td colspan="2"><input name="username" type="text" class="width150" /></td>
                        <td class="login_error_info"><span class="required"></span></td>
                    </tr>
                    <tr>
                        <td class="login_info">密码：</td>
                        <td colspan="2"><input name="password" type="password" class="width150" /></td>
                        <td><span class="required"></span></td>
                    </tr>
                    <tr>
                        <td class="login_info">验证码：</td>
                        <td class="width70"><input name="captcha" type="text" class="width70" /></td>
                        <td><img src="images/valicode.jpg" alt="验证码" title="点击更换" /></td>
                        <td><span class="required"></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td class="login_button" colspan="2">
                            <input type="submit" value="提交">
                            <%--<a href="index.html"><img src="images/login_btn.png" /></a>--%>
                        </td>
                        <td><span class="required">${param.msg}</span></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
