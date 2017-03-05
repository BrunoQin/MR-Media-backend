# MR-Media 接口文档 v0.01
## 1.用户
### 1.1 用户登录
```
url: /user/login
method: post
path variable: none
request body: {
	"uid", //string
	"password", //string
}
```
```
response body: {
	"errCode", //integer
	"token", //string
	"authority", //integer
}
```
### 1.2 用户查看个人信息
```
url: /user/profile
method: get
path variable: token
```
```
response body: {
	"errCode", //integer
	"profile": {
		"uid", //string
		"username", //string
		...
	}
}
```
### 1.3 用户更改密码
```
url: /user/password/edit
method: post
path variable: token
request body: {
	"oldPassword", //string
	"newPassword", //string
	"confirmPassword", //string
}
```
```
response body: {
	"errCode", //integer
}
```
## 2.权限组相关
### 2.1 `管理员`权限查看所有经纪人（分页）
```
url: /admin/employee/agents
method: get
path variable: token, pageId, pageSize, *disable, *username
```
```
response body: {
	"errCode", //integer
	"pageId", //integer
	"pageSize", //integer
	"totalPage", //integer
	"agents": [{
		"uid": //string
		"username", //string
	}, ...],
}
```
### 2.2 `管理员`权限查看所有艺人（分页）
```
url: /admin/employee/actors
method: post
path variable: token, pageId, pageSize, *disable, *username
```
```
response body: {
	"errCode", //integer
	"pageId", //integer
	"pageSize", //integer
	"totalPage", //integer
	"actors": [{
		"uid": //string
		"username", //string
	}, ...],
}
```
### 2.3 `管理员`权限添加下线员工
```
url: /admin/add_employee
method: post
path variable: token
request body: {
	"username", //string
	"authority", //integer
}
```
```
response body: {
	"errCode", //integer
	"subEmployee": {
		"uid", //string
	}
}
```
### 2.4 `经纪人`权限查看自己位置（树）
```
url: /agent/position
method: get
path variable: token
```
```
response body: {
	"errCode", //integer
	"position": {
		"super": {
			"uid", //string
			"username", //string
		}, 
		"sub": [{
			"uid", //string
			"username", //string
			"superIndex", //long
		}, ...]
	}
}
```