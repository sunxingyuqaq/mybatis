# mybatis boot test


<a name="overview"></a>
## 概览
mybatis-plus swagger demo


### 版本信息
*版本* : 2.0


### 联系方式
*名字* : XingYu Sun  
*邮箱* : 12870621552@qq.com


### 许可信息
*许可证* : apache license  
*许可网址* : http://www.apache.org/  
*服务条款* : https://www.baidu.com


### URI scheme
*域名* : localhost:9000  
*基础路径* : /


### 标签

* test : Test Controller


### 生成

* `application/json;charset=utf-8`




<a name="paths"></a>
## 资源

<a name="test_resource"></a>
### Test
Test Controller


<a name="delusingget"></a>
#### del
```
GET /api/del
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### HTTP请求示例

###### 请求 path
```
/api/del
```


##### HTTP响应示例

###### 响应 200
```json
"object"
```


<a name="getallusingpost"></a>
#### getAll
```
POST /api/post
```


##### 参数

|类型|名称|类型|
|---|---|---|
|**FormData**|**age**  <br>*必填*|integer (int32)|
|**FormData**|**id**  <br>*必填*|integer (int64)|
|**FormData**|**name**  <br>*必填*|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/x-www-form-urlencoded`


##### HTTP请求示例

###### 请求 path
```
/api/post
```


###### 请求 formData
```json
"test"
```


##### HTTP响应示例

###### 响应 200
```json
"object"
```


<a name="getusingget"></a>
#### 获取用户信息
```
GET /api/users
```


##### 说明
获取用户信息


##### 参数

|类型|名称|类型|
|---|---|---|
|**Query**|**id**  <br>*必填*|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### HTTP请求示例

###### 请求 path
```
/api/users?id=string
```


##### HTTP响应示例

###### 响应 200
```json
"object"
```







