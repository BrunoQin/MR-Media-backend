# MR-Media 接口文档 v0.02 （For微信）
## 0.微信验证
### 0.1 微信签名验证
```
url: /WeChat/check_signature
method: get
path variable: signature, timestamp, nonce, echostr
```
```
response body: {
	echostr, //请求中的随记字符串
}
```
## 1.用户
### 1.1 用户查询个人薪资（需进行微信验证）
```
url: /user/salary
method: get
path variable: token
```
```
response body: {
	"errCode", //integer
	"salary": {
		"baseSalary", //integer
		"AAgentCount", //integer
		"BAgentCount", //integer
		"CAgentCount", //integer
		"actorCount", //integer
		"originSalary", //integer
		"totalSalary", //integer
	}
}
```
## 2.艺人
### 2.1 艺人上传个人信息（需进行微信验证）
```
url: /actor/upload_information
method: post
path variable: none
request body: {
	"username", //string
	"gender", //integer
	"age", //integer
	"dateOfBirth", //date
}
```
```
response body: {
	"errCode", //integer
}
```
### 2.3 艺人上传个人照片（需进行微信验证）
```
url: /actor/upload_picture
method: post, multipart
path variable: none
request body: {
	"picture", //file
}
```
```
response body: {
	"errCode", //integer
	"location":{
		"filename", //String
	}
}
```
### 2.3 艺人上传才艺视频（需进行微信验证）
```
url: /actor/upload_video
method: post, multipart
path variable: none
request body: {
	"video", //file
}
```
```
response body: {
	"errCode", //integer
}
```
