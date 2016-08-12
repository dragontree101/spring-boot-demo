# spring-boot-demo

主要包含了spring-boot常用的简单方法，方便以后copy代码， 主要包含了常用的模块：

- mvc

这个模块主要是基于spring-mvc所实现的业务逻辑接口类，里面用到了jdbc、redis、security三个常用的组件

- no mvc

这个模块主要是简单介绍如何通过springboot来完成非mvc的代码逻辑

- jersey

这个模块主要是基于jersey的api来完成业务逻辑， 里面用了hibernate来作为数据库访问的介质

- jdbc

这个模块主要是提供了一些jdbc的配置，并且封装一些数据逻辑的操作, 提供的了多数据源的动态配置类

- hibernate

这个模块主要是提供了jpa + hibernate的操作数据库

- redis

这个模块主要是提供了redis缓存的配置

- security

这个模块主要是简单使用spring-boot-starter-security来完成简单的认证逻辑, 其中是简单的通过数据库进行了用户名、密码和权限角色的管理， 这个模块暂时只能用于spring-mvc的模块，不能使用于jersey的模块


等等
