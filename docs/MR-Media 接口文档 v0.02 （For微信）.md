# MR-Media 接口文档 v0.02 （For微信）
## 1.用户
### 1.1 用户查询个人薪资
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
### 2.1 艺人上传个人信息
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
### 2.3 艺人上传个人照片
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
}
```
### 2.3 艺人上传才艺视频
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
