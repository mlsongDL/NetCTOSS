# NetCTOSS电信计费系统说明

### 1. 创建NetCTOSS项目（MAVEN项目）

```
maven: 自动化项目构建管理工具
	1. 项目构建：编译构建
	2. 可以自动导入包的依赖（数据库连接池：commons-dbcp.jar ）
	3. 管理项目和项目之间的依赖关系
```

#### 1.1 如何创建Maven项目

![1584339734405](.\img\1584339734405.png)

![1584339855396](.\img\1584339855396.png)

![1584340014059](.\img\1584340014059.png)

![1584340126732](.\img\1584340126732.png)

修改settings.xml(apache-maven-3.3.9/conf), 修改maven的镜像地址

```xml
<mirrors>
	<mirror>
			<id>alimaven</id>
			<name>aliyun maven</name>
			<url>http://maven.aliyun.com/nexus/content/groups/public/</url>
			<mirrorOf>central</mirrorOf>
		</mirror>
		<mirror>      
	      <id>repo</id>      
	      <mirrorOf>central</mirrorOf>      
	      <name>Human Readable Name for this Mirror.</name>      
	      <url>http://repo2.maven.org/maven2/</url>      
	    </mirror>
</mirrors>
```

检查是否存在.m2的文件夹

![1584340397734](.\img\1584340397734.png)

![1584340789972](.\img\1584340789972.png)

```xml
<!-- 引入数据库连接池  -->
<!-- 使用dependency标签引入本地仓库jar包 -->
<dependency>
    <groupId>commons-dbcp</groupId>
    <artifactId>commons-dbcp</artifactId>
    <version>1.2.2</version>
</dependency>
```

创建maven-arttype-webapp:

![1584343094967](.\img\1584343094967.png) 

```
1. 在main文件夹下创建java文件夹，标注-> sources root
2. 在main文件夹下创建resources文件夹，标注-> resources root
```

![1584343251201](.\img\1584343251201.png) 







### 2. 完成登录模块

![1584338886170](.\img\1584338886170.png)

#### 1.1 设计前端页面（login.html）

```java
1. 改造html为jsp页面

1.1 将html后缀改成jsp


1.2 在jsp文件首行中添加一行代码
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    
    
1.3 将文件放置到WEB-INF/目录下， 用于无法直接通过地址栏访问该文件，提高网站系统的安全性

1.4 WEB-INF下的内容，必须要使用转发机制才能访问到
```

```
转发和重定向
	转发：
		1. 地址栏不会变
		2. 浏览器发送一次请求
		3. 共享数据
	重定向：
		1. 地址栏会发生变化
		2. 浏览器发送两次请求
		3. 不共享数据
		
check.login  如果用户校验不通过，转发到login.jsp
url : check.login    页面：login.jsp    url和页面不匹配

check.login  如果用户校验不通过，需要重定向到page.login中

```

#### 1.2 登录拦截校验，验证用户是否已经登录，是否有权限访问系统的其他功能

```java
添加过滤器，完成用户的登录校验
 // 验证用户是否已经登录，验证session中是否已经存在登录成功的标识
 HttpSession session = request.getSession();
 Boolean isLogin = (Boolean)session.getAttribute("isLogin");

if (isLogin == null || isLogin != true) {
// 用户尚未登录，跳转到登录页
// 转发到login.jsp  还是重定向到page.login
response.sendRedirect("/net/page.login");
return;
    
// 请求登录页面的时候，让过滤器放行，不需要进行登录验证
if (!uri.endsWith("page.login")) {
    // 验证用户是否已经登录，验证session中是否已经存在登录成功的标识
    HttpSession session = request.getSession();
    Boolean isLogin = (Boolean)session.getAttribute("isLogin");

    if (isLogin == null || isLogin != true) {
        // 用户尚未登录，跳转到登录页
        // 转发到login.jsp  还是重定向到page.login
        response.sendRedirect("/net/page.login");
        return;
    }
}
    
// 还需要过滤掉登录验证的check.login请求 
```

#### 1.3 项目结构中的DAO

```
DAO: Data Access Object 数据访问对象（数据访问层）
直接和数据库打交道，将所有数据库的操作封装到DAO
```

#### 1.4 实体类的设计

```
用实体类属性接收数据库表中的数据记录

account表  ->  java  Account实体类

实体中的属性，和表中的字段一一对应，用来存储数据库字段的值

account表中的每一条记录，都可以用Account实体类对象进行存储


实体类中的属性如果是基本数据类型，最号使用其包装类型接收字段数据
```

![1584433381786](.\img\1584433381786.png)

![1584433509174](.\img\1584433509174.png)

![1584434160742](.\img\1584434160742.png) 









### Java Web项目目录说明

![1584511416629](.\img\1584511416629.png) 

java: 蓝色文件夹

resources：灰色+圆柱体文件夹

classpath路径

Tomcat目录下webapps文件夹下

 - 项目名
    - /WEB-INF
      	- classes:  classpath路径下的文件都会部署到classes文件夹下
      	- lib

