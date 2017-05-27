[![logo](http://images.tingjiandan.com/resource/logo/logo-title-04.png "logo")](ttp://images.tingjiandan.com/resource/logo/logo-title-04.png " logo ")

[北京停简单信息技术有限公司](http://www.tingjiandan.com)

#  停简单-三方停车业务-接口文档
### 约定
```
接口如无特殊说明，遵照以下规范:
金额格式统一为：(最大9位).(最多2位)。需要通过此正则表达式^((([1-9]{1}\\d{0,9}))|([0]{1}))((\\.(\\d){2}))?$。
code结果说明：0=成功，1=业务失败，2=系统失败，其他标志=自定义失败。
本业务接口中，时间格式默认为YYYYMMDDHHMISS
车牌颜色仅识别blue|yellow|green|white|black
车牌号规则：6-9位
考虑到接口扩展情况，停简单提供的接口可能会扩展返回值，但是不会删除key和修改返回值类型，即会保证向下兼容。请掉用方给予兼容操作
```

### 测试环境信息
```
商户ID：b7470b140f9d46a38943371547ce3c0b
商户秘钥：ab13b21324004117b99a5dc07345d5b0
开放平台接口地址：http://test.tingjiandan.com/openapi/gateway
```

## 停简单提供接口
### 注册车牌
* 描述：添加相应的车牌到停简单系统，停简单会将此车牌进出场信息推送至对方平台
* service：parkhub.car.register
* 请求参数列表

|参数|类型|是否必须|名称|备注 |
|:-----|:-----|:-----|:-----|:-----|
|carNum|String| Y|车牌号||
|carNumColor|String| Y|车牌颜色||
|phone|String| N|用户手机号||
|outCarId|String|N|第三方系统对应id，不超过32位|如果有此信息，进场参数会将此id推送过去|

* 请求参数示例：
```
{
    "service": "parkhub.car.register",
    "carNum": "京A45781",
    "carNumColor": "blue",
    "phone": "15210501514",
    "outCarId": "45454545454",
    "version":"1.0",
    "sign":"3347b109a1e44f3fd5baa78b74a84948",
    "partner":"5836b8b52ada463ebc6199579f029566",
    "timestamp":"2016-05-26 11:30:10",
    "charset":"utf-8",
    "signType":"md5"
}
```

* 响应参数列表

| 参数     | 类型  | 名称 | 备注|   
|:----------|:-------- |:---------|:-----|:-----    |
| timestamp | string |响应时间 ||
| returnCode | string | 响应码 ||
| returnMSG | string | 响应描述 ||
| isSuccess | string | 请求是否成功 ||
| errorMsg | string |错误信息||
| carId |   string  |   停简单此车牌唯一标识    |最大长度32位|

* 响应参数示例：
```
{
	"timestamp": "1464235239561",
	"returnCode": "T",
	"errorMsg": "",
	"returnMsg": "OK",
	"isSuccess": "0",
	"carId": "5836b8b52ada463ebc6199579f029561"
}
```
### 注销车牌
* 描述：注销之后，此车辆进出场消息不再推送至对方平台
* service：parkhub.car.writeOff
* 请求参数列表

|参数|类型|是否必须|名称|备注 |
|:-----|:-----|:-----|:-----|:-----|
|carId|String| Y|注册车牌返回的停简单唯一标识||

* 请求参数示例：
```
{
    "service": "parkhub.car.writeOff",
    "version":"1.0",
    "sign":"3347b109a1e44f3fd5baa78b74a84948",
    "partner":"5836b8b52ada463ebc6199579f029566",
    "timestamp":"2016-05-26 11:30:10",
    "charset":"utf-8",
    "signType":"md5"
}
```

* 响应参数列表

| 参数     | 类型  | 名称 | 备注
|:----------|:-------- |:---------|:-----|:-----
| timestamp | string |响应时间 ||
| returnCode | string | 响应码 ||
| returnMSG | string | 响应描述 ||
| isSuccess | string | 请求是否成功 ||
| errorMsg | string |错误信息||

* 响应参数示例：
```
{
	"timestamp": "1464235239561",
	"returnCode": "T",
	"errorMsg": "",
	"returnMsg": "OK",
	"isSuccess": "0"
}
```

### 在场订单查询
* 描述：根据订单号、车牌号、outCarId查询在场订单
* service：parkhub.order.info
* 请求参数列表

|参数|类型|是否必须|名称|备注 |
|:-----|:-----|-----|:-----|:-----|
|tradeId|String| N|订单号|如果有tradeId，建议使用tradeId查询。tradeId、carNum和carId不能同时为空|
|carNum|String| N|车牌号|tradeId、carNum和carId不能同时为空,如果使用车牌号查询，必须保证此车牌号调用注册接口成功|
|carId|String| N|注册车牌，停简单返回的车牌号唯一id|tradeId、carNum和carId不能同时为空|

* 请求参数示例：
```
{
	"service": "parkhub.order.info",
	"tradeId": "5836b8b52ada463ebc6199579f029565",
	"carNum": "京A45414",
	"carId": "5836b8b52ada463ebc6199579f029565",
	"version": "1.0",
	"sign": "3347b109a1e44f3fd5baa78b74a84948",
	"partner": "5836b8b52ada463ebc6199579f029566",
	"timestamp": "2016-05-26 11:30:10",
	"charset": "utf-8",
	"signType": "md5"
}
```

* 响应参数列表

| 参数     | 类型  | 名称 | 备注
|:----------|:-------- |:---------|:-----|:-----|
| timestamp | string | 应时间 ||
| returnCode | string | 响应码 ||
| returnMSG | string | 响应描述 ||
| isSuccess | string | 请求是否成功 ||
| inDt| string | 进场时间 |格式：yyyyMMddhhmmss|
| tradeId | string | 订单号 ||
| carNum| string | 车牌号 ||
| parkTime | string | 停车时长 |单位(秒)|
| totalAmount | string | 总金额 ||
| unPayAmount | string | 还需支付金额 ||
| paidAmount | string | 已经支付费用||
| freeThroughTime|String|免费出场时间|单位为分钟|
| outDt| string |  计费截止时间|格式：yyyyMMddhhmmss|
| payUrl|String |使用停简单页面进行支付的url|页面可调起微信和支付宝支付，后续可能会扩展|
| accountId|String|每次查费返回的核算id| 调用抵扣接口，需要将此核算id给停简单系统 |
```
打开payUrl页面进行支付，用户支付的金额会到停简单对应的支付平台的商户号，无需调用抵扣、结算通知、核销接口。
payUrl根据支付类型需要拼接相应参数，默认（或type=1）展示订详情页面，会给用户展示进出场时间、停车时长等基本订单信息，type=2则直接展示支付页面，没有订单基本信息,直接到支付页面。
type=1暂时为示例，随后给出标准拼接方式
```
* 响应参数示例：
```
{
	"inDt": "20160526112000",
	"timestamp": "1464235239561",
	"returnCode": "T",
	"errorMsg": "",
	"tradeId": "d49aeaedcf144f1faf569846d6b1667e",
	"carNum": "京A45414",
	"parkTime": "2439593",
	"returnMsg": "OK",
	"totalAmount": "0.03",
	"isSuccess": "0",
	"unPayAmount": "0.03",
	"paidAmount": "0.00",
	"freeThroughTime": "15",
	"outDt": "20170321121212",
	"payUrl": "http://domaon/tcweixin/……",
	"accountId": "d49aeaedcf144f1faf569846d6b16671"
}
```

### 在场订单查询
* 描述：根据车牌号/订单号查询在场订单（支持优惠时间）
* service：parkhub.order.infoForFreeMins
* 请求参数列表

|参数|类型|是否必须|名称|备注 |
|:-----|:-----|-----|:-----|:-----|
|carNum|String| N|车牌号|和tradeId不能同时为空|
|pmParkId|String| N|停车场id||
|freeMins|Integer| Y|免费分钟数|必传,大于等于0|
|tradeId|String| N|停车订单id|和carNum不能同时为空|

* 请求参数示例：
```
{
	"service": "parkhub.order.infoForFreeMins",
	"parkId": "5836b8b52ada463ebc6199579f029565",
	"freeMins": 0,
	"tradeId": "5836b8b52ada463ebc6199579f029565",
	"carNum": "京A45781",
	"version": "1.0",
	"sign": "3347b109a1e44f3fd5baa78b74a84948",
	"partner": "5836b8b52ada463ebc6199579f029566",
	"timestamp": "2016-05-26 11:30:10",
	"charset": "utf-8",
	"signType": "md5"
}
```

* 响应参数列表

| 参数     | 类型  | 名称 | 备注
|:----------|:-------- |:---------|:-----|:-----|
| timestamp | string | 应时间 ||
| returnCode | string | 响应码 ||
| returnMSG | string | 响应描述 ||
| isSuccess | string | 请求是否成功 ||
| inDt| string | 进场时间 |格式：yyyyMMddhhmmss|
| tradeId | string | 订单号 ||
| parkTime | string | 停车时长 |单位(秒)|
| totalAmount | string | 总金额 ||
| unPayAmount | string | 还需支付金额 ||
| paidAmount | string | 已经支付费用||
| freeThroughTime|String|免费出场时间|单位为分钟|
| outDt| string |  计费截止时间|格式：yyyyMMddhhmmss|
| payUrl|String |使用停简单页面进行支付的url|页面可调起微信和支付宝支付，后续可能会扩展|
| accountId|String|每次查费返回的核算id| 调用抵扣接口，需要将此核算id给停简单系统 |
| freeMinsAmount|String|优惠分钟数freeMins对应的金额|对于停简单系统来说，freeMins并未支付，所以此时unPayAmount没有减去此金额，需要掉用方注意|
| pmParkId|String|停车场id||
```
打开payUrl页面进行支付，用户支付的金额会到停简单对应的支付平台的商户号，无需调用抵扣、结算通知、核销接口。
payUrl根据支付类型需要拼接相应参数，默认（或type=1）展示订详情页面，会给用户展示进出场时间、停车时长等基本订单信息，type=2则直接展示支付页面，没有订单基本信息,直接到支付页面。
type=1暂时为示例，随后给出标准拼接方式
```
* 响应参数示例：
```
{
	"inDt": "20160526112000",
	"timestamp": "1464235239561",
	"returnCode": "T",
	"errorMsg": "",
	"tradeId": "d49aeaedcf144f1faf569846d6b1667e",
	"parkTime": "2439593",
	"returnMsg": "OK",
	"totalAmount": "0.03",
	"isSuccess": "0",
	"unPayAmount": "0.03",
	"paidAmount": "0.00",
	"freeThroughTime": "15",
	"outDt": "20170321121212",
	"payUrl": "http://domaon/tcweixin/……",
	"accountId": "d49aeaedcf144f1faf569846d6b16671",
	"freeMinsAmount": "0.00",
	"pmParkId": "d49aeaedcf144f1faf569846d6b16670"
}
```

### 停车费抵扣接口
* 描述：为指定订单抵扣停车费，如果此时车辆已经出场，返回isSuccess错误码为3，停简单系统不再接收此笔抵扣信息，对方系统需要给用户发起退款
* service：parkhub.order.deduction
* 请求参数列表

| 参数 | 类型 | 是否必须 | 名称 | 备注
|:------|:-----|:------|:---------|:-----|:-----
| tradeId | string | Y |   停车订单号||
| deductionAmount | string | Y |   支付金额|单位为元，保留小数点后两位|
| outTradeNo| string | Y |   掉用方交易订单号|在对方平台唯一，不超过32位|
| accountId| string | Y |   核算id|在场订单查询接口返回的核算id|

* 请求参数示例：
```
{
    "service": "parkhub.order.deduction",
    "version":"1.0",
    "sign":"3347b109a1e44f3fd5baa78b74a84948",
    "partner":"5836b8b52ada463ebc6199579f029566",
    "timestamp":"2016-05-26 11:30:10",
    "charset":"utf-8",
    "signType":"md5",
    "tradeId": "224478781859452794f2a68a756fe461",
    "deductionAmount": "0.1",
    "outTradeNo":"4738473847834378",
    "accountId":"3347b109a1e44f3fd5baa78b74a84941",
}
```

* 响应参数列表

| 参数     | 类型  | 名称 | 备注
|:----------|:-------- |:------|:---------|:-----|:-----
| timestamp | string |响应时间 ||
| returnCode | string| 响应码 ||
| returnMSG | string | 响应描述 ||
| isSuccess | string | 请求是否成功 |返回值为3情况下，说明车辆已经出场，需要第三方系统给用户发起退费操作|
| prePayId| string | 支付id||

* 响应参数示例：
```
{
    "timestamp": "1464247094636",
    "returnCode": "T",
    "errorMsg": "",
    "returnMsg": "OK",
    "isSuccess": "0",
    "prePayId":"224478781859452794f2a68a756fe441"
}
```

### 停车费抵扣接口（无结算）
* 描述：为指定订单抵扣停车费，如果此时车辆已经出场，返回isSuccess错误码为3，停简单系统不再接收此笔抵扣信息，对方系统需要给用户发起退款（后续业务中无结算）
* service：parkhub.order.deductionNotSettle
* 请求参数列表

与停车费抵扣接口相同

* 请求参数示例：

与停车费抵扣接口相同

* 响应参数列表

与停车费抵扣接口相同

* 响应参数示例：

与停车费抵扣接口相同

### 车辆位置查询
* 描述：根据订单编号查询车辆停放位置。只有在进场时候推送数据中canFindCar=0时，调用此接口才有数据返回
* service：tcsearch.findCar
* 请求参数列表

| 参数 | 类型 | 是否必须 | 名称 | 备注
|:------|:-----|:------|:---------|:-----|:-----
| tradeId | string | Y |   停简单停车订单号|进场接口发送的tradeId|

* 请求参数示例：
```
{
	"service": "tcsearch.findCar",
	"version": "1.0",
	"sign": "3347b109a1e44f3fd5baa78b74a84948",
	"partner": "5836b8b52ada463ebc6199579f029566",
	"timestamp": "2016-05-26 11:30:10",
	"charset": "utf-8",
	"signType": "md5",
	"tradeId": "224478781859452794f2a68a756fe442"
}
```

* 响应参数列表

| 参数     | 类型  | 名称 | 备注
|:----------|:-------- |:------|:---------|:-----|:-----|
| timestamp | string |响应时间 ||
| returnCode | string| 响应码 ||
| returnMSG | string | 响应描述 ||
| isSuccess | string | 请求是否成功 ||
| data | Array | 停放位置数组 ||
| -- parkName| String | 停车场名称 ||
| -- parkId| String | 停车场id ||
| -- carNum| String | 车牌号 ||
| -- floor| String | 楼层 |-1 表示地下1层  2地上2层|
| -- area| String | 区域 ||
| -- carPortCode| String | 车位编号 ||
| -- carImg| String | 停车图片地址 ||
| -- inTime| String | 进场时间 ||
| -- parkTime| String | 停车时长 |单位为秒|

* 响应参数示例：
```
{
	"timestamp": "1464247094636",
	"returnCode": "T",
	"errorMsg": "",
	"returnMsg": "OK",
	"isSuccess": "0",
	"data": [{
		"parkName": "151测试停车场",
		"parkId": "f3f11a46f3a54056953aed97944242d4",
		"carNum": "青QUQU22",
		"floor": "-2",
		"area": "C",
		"carPortCode": "021",
		"carImg": "http://img.tingjiandan.com/resource/parking_test/20161211/0c98ecdb59e342e48c5528348eb1ea41.jpg",
		"inTime": "20161205100000",
		"parkTime": "537410"
	}]
}
```
### 结算通知（仅当第三方系统作为第一收款方时适用）
* 描述：第三方系统在给停简单结算之后，需要调用此接口告知停简单系统结算明细。
* service :待定
* 请求参数列表

| 参数 | 类型 | 是否必须 | 名称 | 备注
|:------|:-----|:------|:---------|:-----|:-----
| settlAccountsId | string | Y |   结算订单号|保证每次结算唯一，不超过32位|
| settlAccountsAmount | string | Y |   结算金额|单位为元，保留小数点后两位|
| tripartitePaymentId | string | Y |   三方支付唯一标识|例如通过块钱转账，最好为快钱单号，方便两边对账使用|
| channel   |   string  |   Y   | 结算渠道  |渠道标识待完善|
| remark | string | N |   备注|单长度不超过64个字符|
| writeOffDetail  |   Object  |   N|核销对象    |   如果结算只有一条核销记录，可传输此对象，无需再次调用核销接口|
| --prePayId | string | Y |   预支付订单号||
| --settlAccountsAmount | string | Y |   结算金额|单位为元，保留小数点后两位|
| --remark | string | N |   备注|单长度不超过64个字符|


* 请求参数示例：
```
{
	"service": "待定",
	"version": "1.0",
	"sign": "3347b109a1e44f3fd5baa78b74a84948",
	"partner": "5836b8b52ada463ebc6199579f029566",
	"timestamp": "2016-05-26 11:30:10",
	"charset": "utf-8",
	"signType": "md5",
	"settlAccountsId": "224478781859452794f2a68a756fe463",
	"settlAccountsAmount": "1.02",
	"tripartitePaymentId": "8439849384938493493",
	"channel": "3010",
	"remark": "支付宝分润核销",
	"writeOffDetail": {
		"prePayId": "224478781859452794f2a68a756fe461",
		"settlAccountsAmount": "1.02",
		"remark": "支付停车费，支付宝分润核销"
	}
}
```
* 响应参数列表

| 参数     | 类型  | 名称 | 备注
|:----------|:-------- |:------|:---------|:-----|:-----
| timestamp | string |响应时间 ||
| returnCode | string| 响应码 ||
| returnMSG | string | 响应描述 ||
| isSuccess | string | 请求是否成功 ||

* 响应参数示例：
```
{
    "timestamp": "1464247094636",
    "returnCode": "T",
    "errorMsg": "",
    "returnMsg": "OK",
    "isSuccess": "0"
}
```

### 核销接口（仅当第三方系统作为第一收款方时适用）
* 描述： 抵扣接口产生的费用需要在结算之后(调用结算通知之后)，调用此接口进行核销。停简单系统会根据此接口prePayId和settlAccountsId做幂等性判断，多次调用以第一次调用为准。如果为退费核销，此金额为负值
* service：待定
* 请求参数列表

| 参数 | 类型 | 是否必须 | 名称 | 备注
|:------|:-----|:------|:---------|:-----|:-----
| prePayId | string | Y |   预支付订单号||
| settlAccountsId | string | Y |  结算id|不超过32位|
| settlAccountsAmount | string | Y |   结算金额|单位为元，保留小数点后两位|
| remark | string | N |   备注|单长度不超过64个字符|

* 请求参数示例：
```
{
	"service": "待定",
	"version": "1.0",
	"sign": "3347b109a1e44f3fd5baa78b74a84948",
	"partner": "5836b8b52ada463ebc6199579f029566",
	"timestamp": "2016-05-26 11:30:10",
	"charset": "utf-8",
	"signType": "md5",
	"prePayId": "224478781859452794f2a68a756fe461",
	"settlAccountsId": "224478781859452794f2a68a756fe463",
	"settlAccountsAmount": "1.02",
	"remark": "支付停车费，支付宝分润核销"
}
```
* 响应参数列表

| 参数     | 类型  | 名称 | 备注
|:----------|:-------- |:------|:---------|:-----|:-----
| timestamp | string |响应时间 ||
| returnCode | string| 响应码 ||
| returnMSG | string | 响应描述 ||
| isSuccess | string | 请求是否成功 ||

* 响应参数示例：
```
{
    "timestamp": "1464247094636",
    "returnCode": "T",
    "errorMsg": "",
    "returnMsg": "OK",
    "isSuccess": "0"
}
```

## 第三方系统提供接口
#### 返回数据说明
* 所有第三方系统提供接口，返回数据格式如下：
```json
{
	"errorMsg": "",
	"isSuccess": "0"
}
```
* 返回说明

| 参数 | 类型 | 名称 | 备注
|:------|:-----|:-----|:-----|
| isSuccess | string | 返回code | 0成功，1业务级失败（不进行重发），2系统级失败（有重发机制）|
| errorMsg | string | Y |   错误描述|

### 进场
* 描述：车辆进场，将车辆信息发送至对方平台。如果进场识别为黑牌车，并且对方录入没有黑色车牌，但是有蓝色车牌的情况下，依然会将黑色车牌的进场信息通过此接口推送过去。
* 请求参数列表

| 参数 | 类型 |  是否必须 | 名称 | 备注
|:------|:-----|:------|:-----|:-----
| tradeId | string | Y |   订单号||
| carNum | string | Y |   车牌号||
| carNumColor | string | Y |   车牌颜色||
| inDt | string | Y |   进场时间||
| parkId | string | Y |   停简单停车场id||
| parkName | string | Y |   停车场名称||
| outCarId | string | N |   addCar第三方系统对应的车牌id||
| lon | string | N |   停车场位置经度|百度坐标系|
| lat | string | N |   停车场位置纬度|百度坐标系|
| wLon | string | N |   停车场位置经度|wgs84坐标系|
| wLat | string | N |   停车场位置纬度|wgs84坐标系|
| payUrl|string|Y|停简单支付页面url|payUrl说明参见在场订单查询接口|
| canFindCar|string|Y|是否支持查询车辆位置|0支持，1不支持|

* 请求参数示例：
```
{
	"tradeId": "5836b8b52ada463ebc6199579f029565",
	"outCarId": "45454545454",
	"carNum": "京A45413",
	"carNumColor ": "blue",
	"inDt": "20170319202020",
	"parkName": "测试停车场",
	"parkId": "5836b8b52ada463ebc6199579f029561",
	"lon": "74.000272",
	"lat": "159.768703",
	"wLon": "123.523032",
	"wLat": "35.430735",
	"payUrl": "http://domaon/tcweixin/……",
	"canFindCar": "0"
}
```

### 出场
* 描述：车辆进场，将车辆信息发送至对方平台
* 请求参数列表

| 参数 | 类型 | 长度 | 名称 | 备注
|:------|:-----|:-------|:-----|:-----
| tradeId | string | Y |   订单号||
| outDt | string | Y |   出场时间||
| parkAmount | string | Y |  停车费金额，单位为元||

* 请求参数示例：
```
{
	"tradeId": "5836b8b52ada463ebc6199579f029565",
	"outDt": "20170319232020",
	"parkAmount": "5.20"
}
```
### 岗亭缴费
* 描述：车辆到达岗亭之后，仍存在待缴费用时，将缴费信息发送至对方平台
* 请求参数列表

| 参数 | 类型 |  是否必须 | 名称 | 备注
|:------|:-----|:------|:-----|:-----
| tradeId  | string | Y |   订单号||
| parkTime| string | Y |   停车时长|单位为秒|
| totalAmount| string | Y |   总停车费||
| unPayAmount| string | Y |   待支付费用||
| paidAmount| string | Y |   已支付费用||
| outDt| string | Y |   计费截止时间||
| accountId| string | Y |   核算id|抵扣接口需要的accountId|

* 请求参数示例：
```
{
	"tradeId": "d49aeaedcf144f1faf569846d6b1667e",
	"parkTime": "2439593",
	"totalAmount": "0.03",
	"unPayAmount": "0.03",
	"paidAmount": "0.0",
	"outDt": "20170321121212",
	"accountId":"d49aeaedcf144f1faf569846d6b16671"
}
```
### 退款（仅当第三方系统作为第一收款方时适用）
* 描述：在特定场景下，用户在场内支付的停车费可能会发生退费现象，通过此接口进行退款业务。
* 请求参数列表

| 参数 | 类型 | 是否必须 | 名称 | 备注
|:------|:-----|:------|:-----|:-----
| outTradeNo | string | Y |   支付时候对方交易系统的唯一标识||
| refundAmount | string | Y |   退款金额||

* 请求参数示例：
```
{
	"outTradeNo": "5894584958948594343",
	"refundAmount ": "5.20"
}
```
### 支付通知
* 描述：用户在payUrl页面中进行支付之后，停简单系统会通知相应第三方系统，便于第三方系统UI交互进行相应的调整。
* 请求参数列表

| 参数 | 类型 | 是否必须 | 名称 | 备注
|:------|:-----|:------|:-----|:-----
| tradeId | string | Y |   停车订单号||
| payAmount | string | Y |   支付金额|保留小数点两位|
| payDt | string | Y |   支付时间||

* 请求参数示例：
```
{
	"tradeId": "d49aeaedcf144f1faf569846d6b1667e",
	"payAmount": "12.12",
	"payDt": "20170425033033"
}
```