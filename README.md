#### 最近想搭一个练手的后台单体管理项目，在用户登录和安全认证方面遇到点问题
##### 希望学习使用shiro，进行练习
##### 4.16 笔记
1.shiro三大核心对象

1.1 subject(用户)：应用代码直接交互对象就是subject，也就是说shiro的对外API核心就是subject。它代表了了当前的用户，可以看作是一个门面

1.2 securityManager(管理所有用户)：安全管理器，所有与安全相关的操作都会与securityManager进行交互，管理所有的subject，是shiro的核心，类似于springMVC中的dispatcherServlet
1.3 realm(连接数据)，用于获取安全实体，可以用JDBC实现，也可以是内存实现(redis)，由用户提供，所以一般在应用中都需要实现自己的realm