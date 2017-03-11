# MR-Media 接口文档 v0.03
## 1.网站首页相关
### 1.1* 经纪人自行注册
```
url: /agent/register
method: post
path variable: none
request body: {
	"uid", //String
	"realName", //String
	"phoneNumber", //String
	"weChatNumber", //String
	"email", //String
	"settleType", //integer
	"settleAccount", //String
}
```
```
response body: {
	"errCode", //integer
}
```
### 1.2 经纪人上传手持身份证照片
```
url: /agent/register/upload_picture
method: post, multipart
path variable: token
request body: {
	"frontPicture", //file
	"backPicture", //file
}
```
```
response body: {
	"errCode", //integer
}
```
### 1.3* 艺人自行注册
```
url: /actor/register
method: post
path variable: none
request body: {
	"uid", //String
	"realName", //String
	"talentType", //Integer
	"phoneNumber", //String
	"weChatNumber", //String
	"email", //String
	"location", //String
	"settleType", //integer
	"settleAccount", //String
}
```
```
response body: {
	"errCode", //integer
}
```
### 1.4 艺人上传照片
```
url: /agent/register/upload_picture
method: post, multipart
path variable: token
request body: {
	"picture1", //file
	"picture2", //file
	"picture3", //file
}
```
```
response body: {
	"errCode", //integer
}
```
## 2.后台管理网站相关
### 2.1* 员工获取所有通知（分页，已读/未读）
```
url: /notification/get_related_notifications
method: get
path variable: token, pageId, pageSize, status
```
```
response body: {
	"errCode", //integer
	"pageId", //integer
	"pageSize", //integer
	"totalPage", //integer
	"notifications": [{
		"content", //String
		"time", //date
		...
	}, ...]
}
```
### 2.2* 员工将通知标为已读/未读
```
url: /notification/mark_notification
method: post
path variable: token
request body: {
	"notificationId", //String
	"status", //integer
}
```
```
response body: {
	"errCode", //integer
}
```
### 2.3 `管理员`权限更换员工上级
```
url: /admin/change_employee
method: post
path variable: token
request body: {
	"uid", //String
	"superId", //String
}
```
```
response body: {
	"errCode", //integer
}
```
### 2.4 `管理员`权限删除员工
```
url: /admin/delete_employee
method: post
path variable: token
request body: {
	"uid", //String
}
```
```
response body: {
	"errCode", //integer
}
```
### 2.5* `管理员`权限查看所有审核（分页，已处理/未处理）
```
url: /review/get_reviews
method: get
path variable: token, pageId, pageSize, *status
```
```
response body: {
	"errCode", //integer
	"pageId", //integer
	"pageSize", //integer
	"totalPage", //integer
	"reviews": [{
		"reviewId": //integer
		"content": //string
		...
	}, ...],
}
```
### 2.6* `管理员`权限处理审核（通过/拒绝/删除）
```
url: /admin/mark_review
method: post
path variable: token
request body: {
	"reviewId", //integer
	"action", //integer
}
```
```
response body: {
	"errCode", //integer
}
```
### 2.7 `管理员`权限结算`艺人`
```
url: /admin/settle/actor
method: post
path variable: token
request body: {
	"uid", //String
	"basicSalary", //integer
}
```
```
response body: {
	"errCode", //integer
	"finalSalary", //integer
}
```
### 2.8 `管理员`权限结算`经纪人`
```
url: /admin/settle/agent
method: post
path variable: token
request body: {
	"uid", //String
	"basicSalary", //integer
	"brokerageFees": [{
		"fee", //integer
	}, ...],
}
```
```
response body: {
	"errCode", //integer
	"finalSalary", //integer
}
```
### 2.9 用户查看下属员工（分页，经纪人/艺人）
```
url: /user/sub_employees
method: get
path variable: token, pageId, pageSize, *authority
```
```
response body: {
	"errCode", //integer
	"pageId", //integer
	"pageSize", //integer
	"totalPage", //integer
	"subEmployees": [{
		"uid": //string
		...
	}, ...],
}
```
### 2.10 用户查看下属员工详细情况（经纪人/艺人）
```
url: /user/sub_employee/detail
method: post
path variable: token
request body: {
	"uid", //String
}
```
```
response body: {
	"errCode", //integer
	"info": {
		"uid", //String
		"name", //String
	}
}
```
### 2.11 `经纪人`权限查看提交审核情况列表（分页，经纪人/艺人，已处理/未处理）
```
url: /admin/reviews
method: get
path variable: token, pageId, pageSize, *authority, *status
```
```
response body: {
	"errCode", //integer
	"pageId", //integer
	"pageSize", //integer
	"totalPage", //integer
	"reviews": [{
		"reviewId": //integer
		"content": //string
		...
	}, ...],
}
```
