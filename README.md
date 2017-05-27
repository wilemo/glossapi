1. md to html [OK]
2. gfm table supper [OK]
3. style supper



















## 文件格式
api.markdown[contain API命令表达式]
to:
markdown
html
pdf

可导出的:
md[ex]|md|html|pdf

## Api Type
Web-restful[GET|POST|PUT|DELETE]
JMS-ActiveMQ
RPC-Dubbo(订阅服务后远程调用，或telnet)
Socket(上传客户端命令实现，在页面动态调用发送命令)
上传签名实现

## 模版
支持API模版，预置和自定义几套类型的

模版类型：
header=头部
footer=页脚

## 主题
导出文件支持多种主题

## 技术
spring boot
hibernate validator
thymeleaf


* 数据库
Mysql
Mongdb
文件

## Project
1. glossapi-cli 命令行工具
可以通过java -jar glossapi-cli.jar source=d://api.md target=d://api/ format=html,pdf
依赖glossapi-core、glossapi-util

2. glossapi-core 核心包
用于解析和转换api[ex].md 到 md|html|pdf

3. glossapi-util 工具包

4. glossapi-example 示例包

5. glossapi-spring-boot-starter Spring Boot 快速集成包，引入后可在application.yaml 配置，启动可访问页面http://{}/api

6. glossapi-store API集市，汇集团队多个服务Api管理和动态更新和发布。(推荐工程域名：http://apistore.{}.com | http://{}.com/apistore)

使用方式：
1. 不依赖项目，单独命令行使用 glossapi-cli
2. spring 单独使用，依赖 glossapi-core
3. spring boot 单独使用，glossapi-spring-boot-starter 
4. 集中管理和查阅，Api商店

{ include "header.md" }

{ include "appoint.md" }}

请求示例

{{ call }}

响应示例

{{ include("footer.md") }}

## 

原生 markdown 解析库
API表达式 解析库

[Gloss{Api}]

## Task
1. md to html
2. html to pdf
3. md[ex] to html [include|post]
4. release glossapi-core [GlossApi.mdToHtml(),GlossApi.mdExToHtml(),GlossApi.htmlToPDF()]
5. release glossapi-spring-boot-starter[http://{:}/api, open or close download PDF]
7. release glossapi-store


不使用adoc而坚持md的原因：
adoc还不够流行，虽然易于扩展功能比md多，但md说实话写个api文档足够用了。
至于md无法满足的语法，可以通过自定义扩展实现。

功能点：全局|工程文本引用
文档里可以限定标签环境如：

{ include "ips" }
out:
192.168.1.1

根据不同标签输出内容：
{if label is "dev" }
ip:192.168.1.1
{end}

{if label not is "prep" }
ip:10.25.223.25
{end}

功能点：不止是接口文档，而是和接口有关的一切
录入dubbo依赖关系，提交给商店后，自动匹配依赖关系，让你知道谁依赖了你


可选功能点：
信息收集：Information gather
成员、成员分组
管理员设置字段、字段可选值、值是否唯一（不允许重复，相对已提交）、示例内容
选择分组，发布
成员填写
查看网页模式，导出xls或pdf

核心处理器使用：flexmark https://github.com/vsch/flexmark-java


-----------------------------------
v0.0.1 glossapi-core
v0.0.1 glossapi-spring-boot-starter
v0.0.1 glossapi-store
v1.0.0 glossapi

级别：
public  公有域 eg.公司|企业|团队
default 默认域 eg.部门|小组
private 私有域 eg.个人

内建标签：
# 引入文件 include
{% include public foo.{md|html} %}    [ 引入 public Markdown 模版 ]
{% include private foo.{md|html} %}   [ 引入局部 Markdown 模版 ]
{% include default foo.{md|html} %}   [ 引入局部 Markdown 模版 ]
{% include foo.{md|html} %}           [ 引入 Markdown 模版(依次查找域顺序：私有>默认>公有)]

{% post host:public:post_ip %}
