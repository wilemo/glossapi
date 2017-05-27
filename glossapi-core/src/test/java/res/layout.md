<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="{ val:generatorInfo =glossapi 0.0.1}">
<title>{ val:apiName =Swagger Petstore }</title>
<style>
{% include style.css aaa bbb 111 %}
{% include1 style1.css %}
</style>
</head>
<body>
<div id="header">
<h1>Swagger Petstore</h1>
<div id="toc">

[TOC html]: #

</div>
<div id="content">

Swagger Petstore
================

## 1. Overview
{% include ## %}
Petstore API Description

### 1.1.Version information
*Version*: 1.0.0

### 1.2. Contact information
*Contact*: TestName
*Contact Email*: [test@test.de](mailto:test@test.de)

### 1.3. License information
*License*: Apache 2.0
*License URL*: [http://www.apache.org/licenses/LICENSE-2.0.html](http://www.apache.org/licenses/LICENSE-2.0.html)

### 1.4. URI scheme
*Host*: localhost:8080
*BasePath*: /

### 1.5. Tags
* Stores: Operations about store
* Users: Operations about user
* Pets: Operations about pets

***

## 2. Chapter of manual content 1
This is some dummy text

### 2.1. Sub chapter
Dummy text of sub chapter

***

## 3. Chapter of manual content 2
This is some dummy text

***

## 4. Resources
### 4.1. Pets
Operations about pets

## 4.1.1. Add a new pet to the store
```
POST /pets
```

### Parameters
|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**pet**<br/>required|Pet object that needs to be added to the store|[Pet](http://swagger2markup.github.io/spring-swagger2markup-demo/1.1.0/#_pet)|

### Responses
|HTTP<br/>Code|Description|Schema|
|---|:---:|:---:|
|**200**|OK|[ ]|
|**201**|Created|[X]|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|
|**405**|Invalid input|No Content|

### Consumes
* ```application/json```

### Produces
* application/xml
* application/x-smile
* application/json

### Example HTTP request
```http
POST /pets/ HTTP/1.1
Content-Type: application/json;charset=UTF-8
Host: localhost:8080
Content-Length: 96

{"id":1,"category":{"id":1,"name":"Hund"},"name":"Wuffy","photoUrls":[],"tags":[],"status":null}
```

### Example HTTP response
```http
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Content-Length: 7

SUCCESS
```

### Example Curl request
```bash
$ curl 'http://localhost:8080/pets/' -i -X POST -H 'Content-Type: 
application/json;charset=UTF-8' -d '{"id":1,"category":
{"id":1,"name":"Hund"},"name":"Wuffy","photoUrls":[],"tags":[],"status":null}'
```

## 4.1.2. Update an existing pet
```
PUT /pets
```

### Parameters
|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**pet**<br/>required|Pet object that needs to be added to the store|[Pet](http://swagger2markup.github.io/spring-swagger2markup-demo/1.1.0/#_pet)|

### Responses
|HTTP<br/>Code|Description|Schema|
|---|:---:|:---:|
|**200**|OK|string|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|
|**405**|Invalid input|No Content|

### Consumes
* ```application/json```

### Produces
* application/xml
* application/x-smile
* application/json

### Security
|Type|Name|Scopes|
|---|:---:|:---:|
|**oauth2**|[petstore_auth](http://swagger2markup.github.io/spring-swagger2markup-demo/1.1.0/#_petstore_auth)|string|


## 4.1.3. Update an existing pet
```
PUT /pets
```

### Parameters
|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**pet**<br/>required|Pet object that needs to be added to the store|[Pet](http://swagger2markup.github.io/spring-swagger2markup-demo/1.1.0/#_pet)|

### Responses
|HTTP<br/>Code|Description|Schema|
|---|:---:|:---:|
|**200**|OK|string|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|
|**405**|Invalid input|No Content|

### Consumes
* ```application/json```

### Produces
* application/xml
* application/x-smile
* application/json

### Security
|Type|Name|Scopes|
|---|:---:|:---:|
|**oauth2**|[petstore_auth](http://swagger2markup.github.io/spring-swagger2markup-demo/1.1.0/#_petstore_auth)|string|

## 4.1.4. Update an existing pet
```
PUT /pets
```

### Parameters
|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**pet**<br/>required|Pet object that needs to be added to the store|[Pet](http://swagger2markup.github.io/spring-swagger2markup-demo/1.1.0/#_pet)|

### Responses
|HTTP<br/>Code|Description|Schema|
|---|:---:|:---:|
|**200**|OK|string|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|
|**405**|Invalid input|No Content|

### Consumes
* ```application/json```

### Produces
* application/xml
* application/x-smile
* application/json

### Security
|Type|Name|Scopes|
|---|:---:|:---:|
|**oauth2**|[petstore_auth](http://swagger2markup.github.io/spring-swagger2markup-demo/1.1.0/#_petstore_auth)|string|

</div>
</div>
<div id="footer">

{ val:updated = Last updated 2016-07-21 16:40:21 BST }

</div>
</body>
</html>