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

