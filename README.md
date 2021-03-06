# 项目简介：
-----------------
##这个项目主要的目的是为了能够帮助新人快速使用springboot来构建项目, 目前项目中使用的springboot的版本是1.4.1.RELEASE

# 项目目前由几个模块组成：

## mvc模块

这个模块主要是基于spring-mvc所实现的后端业务逻辑接口类，里面用到了jdbc(用于访问数据库)、redis(用于缓存一些数据)、security(用于简单的权限控制)三个常用的组件

## jdbc模块
这个模块主要是提供了一些jdbc的配置，并且封装一些配置数据源逻辑的操作, 并且提供的了多数据源的动态配置类，这个模块主要是供给mvc模块使用

## redis模块
这个模块主要是提供了redis缓存的配置， 并且结合了spring-cache来使用redis，目前这个模块是提供给spring-mvc模块使用

## security模块
这个模块主要是简单使用spring-boot-starter-security来完成简单的认证逻辑, 其中是简单的通过数据库进行了用户名、密码和权限角色的管理， 这个模块暂时只能用于spring-mvc的模块，不能使用于jersey的模块

## no-mvc模块
这个模块主要是简单介绍如何通过springboot来完成非mvc的代码逻辑，比如处理一些脚本任务、统计任务和定时任务

## jersey模块
这个模块主要是基于jersey框架来来完成后端业务逻辑接口类， 里面基于jpa+hibernate来作为数据库访问的框架，然后打算使用keycloak来做一些简单的权限控制功能(因为jersey和security模块貌似不能很好的集成)，但是由于spring-boot从1.4.0.RELEASE版本以后，打包的插件打出来的jar包目录形式发生了改变，导致jersey不兼容，从而直接通过java -jar的方式启动会失败

## hibernate模块
这个模块主要是提供了jpa + hibernate的操作数据库，由于springboot升级到了1.4.0.RELEASE，所以默认的hibernate也使用了hibernate 5（可能会导致一些与hibernate 4不兼容的地方），目前这个模块是提供给jersey模块使用

## web模块
这个模块主要是提供了简单的前端界面，主要是为了配合keycloak来进行oauth2的认证和测试。目前jersey模块的认证准备基于keycloak来进行
启动之后在http://localhost:8087就可以访问到web页面了

## dockerfile模块
这个模块准备把依赖的基础服务mysql、redis、nginx、keycloak部署到docker里面，方便部署，顺便简单学习一下docker的使用
