# Develop Note

**Gloss Api** is a Markdown Document Api Management Tool
## Project
### glossapi-core

GlossApi core library, md to html and html md to pdf, Online Post Test.

### glossapi-spring-boot-starter

GlossApi integration with Spring Boot

### glossapi-store

GlossApi store manager

## Core
flexmark-java
openhtmltopdf

## Task
### glossapi-core
|ID|Task Name|Content|Complete|
|---|---|---|---|
|GA-DEV-1|Markdown Parse||[√]
|GA-DEV-2|Markdown to HTML|support `GFM Table` and `TOC`|[√]
|GA-DEV-3|HTML pretty style||[×]
|GA-DEV-4|Markdown to PDF|support `Bookmarks`|[×]
|GA-DEV-5|PDF pretty style|same as HTML style|[×]
|GA-DEV-6|HTML inset Http Post| JekyllTag `{% post host %}` |[×]

### glossapi-spring-boot-starter

|GA-DEV-6|Web||[×]

### glossapi-store



## Idea
### Gateway
1. http://apistore.{foo}.com/ [commend]
2. http://www.{foo}.com/apistore
3. http://www.{foo}.com/{bar}

### Web Api
1. http://apistore.{foo}.com/ `用户登录`
2. http://apistore.{foo}.com/main/  `首页`
2. http://apistore.{foo}.com/public/  `开放的无需认证`
3. http://apistore.{foo}.com/two-way/ `开放的需要认证`

## Version
0.0.1.RC1
0.0.1.RC*
0.0.1

### Api Link
1. 接口提供方提供GlossApi接口地址：
http://apistore.foo.com/public/aGVsbG93b3JsZA==  `开放的无需认证`
http://apistore.foo.com/two-way/aGVsbG93b3JsZA== `开放的需要认证`

http://apistore.foo.com/public/aGVsbG93b3JsZA== `默认HTML格式内容`
http://apistore.foo.com/public/aGVsbG93b3JsZA==.md `MD格式内容`
http://apistore.foo.com/public/aGVsbG93b3JsZA==.html `HTML格式内容`
http://apistore.foo.com/public/aGVsbG93b3JsZA==.pdf `PDF格式内容`

提供者：更新主动推送
提供者：更新主动推送
消费者：手动更新

@张三
在接口更新描述中用上述标识会通知相关人查看变更。。因为相关变更可能影响使用者。
