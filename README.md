# 网易作业——内容销售系统
[Homework of NetEase](https://github.com/Xavierqwb/sales)

具体需求见：[Requirement](Requirement.md)

## 技术选型
- 本系统使用java 1.8.0_131编写
- 本系统选用Spring-boot作后端开发
- 本系统使用MySQL5.6作为数据库，使用Druid作数据库连接池，使用Mybatis作为持久化框架
- 本系统使用FreeMarker模板引擎渲染前端页面
- 本系统前端html、css、js代码，大部分内容复制自demo：http://59.111.100.242:8080

## 启动应用前的准备

在本地的MySQL中添加指定的表，参考[DDL](ddl.sql)

在[application.properties](sales-web/src/main/resources/application.properties)中修改MySQL的配置

## 运行方式
> 使用IDE，直接运行sales-web模块下的SalesApp中的main方法，该方法唯一缺陷即不能在运行中加载新添加的资源，即上传的图片无法读取，重启应用后才可以看到

> 使用mvn clean package命令将项目打包，然后在sales-web/target/目录下，执行java -jar sales-web-1.0-SNAPSHOT.jar执行应用，同上，此方法无法热加载资源文件

> 在命令行中使用mvn spring-boot:run指令启动，该启动方式运行后不存在上述启动方法中存在的问题

应用启动后，在浏览器中访问[127.0.0.1:8080/sales](127.0.0.1:8080/sales)，请使用Chrome、Safari等webkit内核的浏览器！

## docker容器运行

安装了docker的话，可以使用docker运行该项目

> docker build . -t sales:1.0.0

在项目路径下执行上述指令，会生成名为sales，tag为1.0.0的image并加入到docker中，接下来在命令行执行`docker run -p 8080:8080 --name sales -d sales:1.0.0`即可运行

> tips: 在docker中运行应用的话，注意mysql的配置哦！