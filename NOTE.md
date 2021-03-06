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

## 2.9 使用WEbJars实现质感设计

### 2.9.1 使用布局
错误: 
> 使用布局的时候因为application.prop 中加了spring.groovy.template.cache=false属性,导致从searchPage到resultPage后结果页面引入css错误
错误原因待查.


# 第三章　处理表单和复杂的URL映射
** 操作: ctrl + shift + alt + / 自动构建运行项目，可以在运行状态中加载修改的页面
## 3.3 国际化
> 国际化出现乱码，修改项目文件编码为UTF-8即可
### 3.3.1 修改地域
> 国际化的时候出现了问题，找不到带后缀的资源文件，只能识别messages.prop
>原来是WebConfiguration 中localeResolver 单词写错了，locale少个e local
### 3.3.2 翻译应用的文本
### 3.3.3 表单中的列表

# 第四章 文件上传与错误处理
## 4.1 上传文件
> 上传个人信息头像是，照书本书写后遇到了问题，PictureUploadController.getUploadedPicture中利用
> @ModelAttribute("picturePath")注解将上传的头像显示到页面中，但是报了个类型转换错误的类型，研究后
> 发现这个注解的类返回的是Resource类型，而方法参数是Path,所以报错，开始很烦躁，慢慢的静下心来解决了问题，
> 参数改成了一致的Resource,然后重新生成Path对象
> 果然尽信书不要如无书啊，遇到问题还是要多动脑，很开心。
### 4.1.3 prop配置文件
> 参考PicturesUploadProperties ,会根据prop文件中的名字自动对应。
### 4.1.4 处理上传文件的错误
1.IOException
> 控制器中添加 @ExceptionHandle 注解 类，该控制器下的所有异常都会调用此方法

2.MartFileException
> 配置tomcat 异常，因为书上spring-boot版本是1.2.5而我用的是2.0所以这个类在2.0中废弃了，导致我查了下文档，
> 参考WebConfiguration 中的ConfigurableServletWebServerFactory，这是2.0中对tomcat的配置K

## 4.2 转换错误信息
>将错误提示信息转换成通俗文字,从资源文件中取

## 4.3 将基本信息放到会话中
把信息放到会话中,类似struts中的session
创建UserProfileSession类
> Scope注解 proxyMode参数

1.TARGET_CLASS: 这会使用CGLIB代理
2.INTERFACES: 这会创建JDK代理
3.NO: 不会创建任何代理

## 4.4 自定义错误页面
>定义自己的错误视图

添加error.html(#spring boot 配置的,名称必须是error#)如果访问无法处理的URL会转到此页面

## 4.5 使用矩阵变量进行URL映射
添加了SearchController 和　SearchVice
实现了通过url传入多个参数搜索结果
> 问题: 自动注入twitter的时候出现问题,现在猜测应该是代理问题,和TweetController中的是一样的,因为我的代理
> 是手动改的,所以估计是自动注入的时候无法连接到twitter服务器,所以报错了

## 4.6 将其组合起来
组合功能
1. 将图片上传功能整合到个人表单
2. 在个人表单提交信息直接跳转到搜索结果页
* 组合完之后 searchPage.html 和TweetController可以删除,本项目中不删除,在此说明.
添加 HomeController

## 4.7检查点
1. 新加两个控制器 PictureUploadController,SearchController
    SearchController 将搜索功能委托给SearchService,删除旧的TweetController

2. 创建了一个新的会话bean,UserProfileSession 存储用户信息

3. WebConfiguration 添加两项内容 
    Servlet 容器级别的错误页面以及对矩阵变量的支持功能

4. 资源方面
    添加了一张图片表示匿名用户,以及一个静态页面处理错误,将上传功能转移到profilePage,移除searchPage

## 4.8 小结


# 第五章 创建RESTful 应用



























