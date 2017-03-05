# MR-Media 接口文档 v0.03
## 1.网站首页相关
### 1.1 经纪人自行注册
```
url: /agent/register
method: post
path variable: none
request body: {
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
path variable: none
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
### 1.3 艺人自行注册
```
url: /actor/register
method: post
path variable: none
request body: {
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
path variable: none
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
### 2.1 员工获取所有通知（已读/未读）
### 2.2 员工将通知标为已读
### 2.3 `管理员`权限添加员工
### 2.4 `管理员`权限更换员工上级
### 2.5 `管理员`权限删除员工
### 2.6 `管理员`权限查看所有审核（分页，经纪人/艺人，已处理/未处理）
### 2.7 `管理员`权限处理审核（通过/拒绝/删除）
### 2.8 `管理员`权限结算`艺人`
### 2.9 `管理员`权限结算`经纪人`
### 2.10 `管理员`权限或`经纪人`权限查看下属员工（经纪人/艺人）
### 2.11 `经纪人`权限查看下午员工详细情况（经纪人/艺人）
### 2.12 `经纪人`权限查看提交审核情况列表
