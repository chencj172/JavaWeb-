## 简介

一个适合JavaWeb新手的项目，没有使用任何框架，但是尽可能利用了MVC模式。实现用户对数据库中的图书进行借阅的功能，管理员能够对用户借书的这个请求作出相应的回答。

* 涉及到的知识点html/css/js/jq+servlet+jsp+mysql
* 数据库连接主要使用了jdbc+c3p0,你可以在c3p0.xml中配置你本地的连接信息

## 如何运行
* 首先把c3p0.xml中的配置信息改成自己本地的连接信息
* 在mysql中建立项目中所用到的数据库
* 配置你的tomcat信息
* 然后运行项目
* 最后在浏览器中输入localhost:8080/login.jsp进行登录操作

## 数据库
* book表存储当前可以借阅的图书
* bookadmin表存储管理员的用户名和密码
* bookcase表存储图书类型
* borrow表存储借书列表
* reader表存储用户用户名和密码
* returnbook表没有可忽略

## 处理后台的java package
* controller 与前端直接交互数据的java文件
* entity 表示各种实例对象
* Filter 过滤器
* service controller层拿到数据交给service进行逻辑处理
* repository 响应service所需要的的数据
* utils 封装数据库连接池
## 前端展示
* login.jsp 登录页面
* index.jsp 展示可借阅的书籍
* borrow.jsp 展示用户的借书情况
* admin.jsp 管理员待处理的业务
* return.jsp 图书归还，管理员进行管理
