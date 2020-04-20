#### 最近想搭一个练手的后台单体管理项目，在用户登录和安全认证方面遇到点问题
##### 希望学习使用shiro，进行练习
##### 4.16 笔记
1.shiro三大核心对象

1.1 subject(用户)：应用代码直接交互对象就是subject，也就是说shiro的对外API核心就是subject。它代表了了当前的用户，可以看作是一个门面

1.2 securityManager(管理所有用户)：安全管理器，所有与安全相关的操作都会与securityManager进行交互，管理所有的subject，是shiro的核心，类似于springMVC中的dispatcherServlet

1.3 realm(连接数据)，用于获取安全实体，可以用JDBC实现，也可以是内存实现(redis)，由用户提供，所以一般在应用中都需要实现自己的realm

##### 4.20 笔记
1.加密的必要性：用户密码不可能明文存储，涉及隐私，所以需要一套加密和解密系统。加密又分为可逆和不可逆加密，不可逆是最常见的，比如md5加密

2.shiro的加密

2.1 基本加密：md5得到明文的密文，但明文可能比较简单导致密文会被破解

2.2 加盐加密：这是比较常见的加密，系统生成一个随机盐(salt)，md5(salt+明文)这样可以得到一个较为复杂的密文

2.2.1 盐(salt)：就是一个字符串，这个字符串是随机的，在用户生成的时候生成一个salt

2.3 加盐多次迭代加密：就是多次加盐，比如第一次md5(salt+明文)得到密文A，第二次md5(salt+密文A)得到密文B，像这样多次加密，得到更加复杂的密文

3.密码比对器
就是将登录时的明文转成密文，再和数据库中的数据比对。需要告知加密的一些细节信息。需要配置，然后注册进userRealm中。
##### 总结
基本上shrio的使用还是比较方便的，但是学习视频中不是前后端分离，需要配合thymeleaf使用

需要注意的就是userRealm，要去继承AuthorizingRealm，重写它的认证二号授权方法，在里面写逻辑

然后就是shrioconfig的配置，如果是springMVC的话，需要3个xml文件去分别配置ShiroFilterFactoryBean，DefaultWebSecurityManager，UserRealm；如果是boot的话直接一个配置类就搞定。这三个bean的耦合程度很高，基本上是一起使用的，配置的话从useRealm开始配，最后是ShiroFilterFactoryBean。

关于过滤器的配置，有很多的配置，一般都是用过滤器链去做。然后mapper注入，编写认证和授权逻辑
