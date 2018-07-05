### Spring Controller应用
Controller配置:(masterspringmvc.controller.HelloController)
- @RequestMapping("/hello")
> requestMapping 是请求地址,注意是/hello,不是/hello/,结尾处没有/
- @ResponseBody

### Debug模式
application.properties 配置debug=true


## 1.6 Spring Boot 代码解析
### 1.6.2 视图解析器：WebMvcAutoConfigurationAdapter
> 配置视图解析器，在application.properties 中配置spring.view.prefix,spring.view,suffix即可
### 静态资源：　
- webjar前缀的资源访问会在类路径中解析 (maven 打包的javascript库)
- 静态资源放在类路径中，并且位于一下４个目录中的任意一个 <br>
     /META-INF/resources/, /resources/,/static/,/public/<br>
- 地域解析器:<br>
     spring.mvc.locale配置地域

问题：1.什么是类路径??<br>
答　：

## 1.7 错误与转码配置: ErrorMvcAutoConfiguration
- 定义了一个bean(DefaultErrorAttributes),通过特定的属性暴露了有用的错误信息，这些属性包括状态，
错误码和相关的栈跟踪信息
- 定义了一个BaseErrorController bean ,Mvc控制器,展现我们看到的错误页面
- 允许我们将Spring Boot 的错误页面whitelabel 错误页面设置为无效
error.whitelabel.enabled设置为false
- 借助模板引擎提供自己的错误页面

## 1.8 嵌入式Servlet容器（Tomcat）的配置
### 1.8.1 Http端口设置
server.port


# 第2章 精通MVC架构
### 领域驱动设计(DDD)
规则: 实体(entity),值类型(value type),通用语言(Ubiquitous Language),限界上下文(Bounded Context),洋葱架构(Onion Architecture)
防腐化层(anti corruption layer)

## 2.6 Spring表达式语言
> SpEl<br>
> '${}'


## 2.7 搭建Spring Social Twitter
问题: 一开始配置gradle的时候好像书上的地址不好用,最后直接谷歌的然后加上了版本号就可以用了,学会了gradle添加依赖的compile的写法
 
## 2.8 lambda表达式
lambda是函数表达式的便捷语法，它可以用到单个的抽象方法(Single Abstract Method)之中,也就是只包含一个函数的接口
- 获取单条twitter信息
```
   //　显示单条twitter信息
   String text = searchResults.getTweets().get(0).getText();
   model.addAttribute("message", text);
```
- 获取twitter信息列表
```
   List<String> tweets = searchResults.getTweets().stream().map(Tweet::getText).collect(Collectors.toList());
   model.addAttribute("tweets", tweets);
   
   <ul>
    <!--/*@thymesVar id="tweets" type="java.util.List"*/-->
    <li th:each="tweet:${tweets}">Some tweet</li>
   </ul>
```
使用布局的时候书上数不用引入thymelaf-layout-dialect 依赖,结果发现没有,然后又加上了,果然不能尽信书啊－－

## 2.9 导航
错误: 
> 使用布局的时候因为application.prop 中加了spring.groovy.template.cache=false属性,导致从searchPage到resultPage后结果页面引入css错误
错误原因待查.


# 第三章　处理表单和复杂的URL映射
















































