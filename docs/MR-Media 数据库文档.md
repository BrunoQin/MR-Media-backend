# 数据库文档


## user

`用户的实体集`

Column| DataType | Attr| default | Explanation |
----|------|----|----|----|
id | int(11)  | PK,NN,AI| | 记录的唯一标示符|
uid| varchar(24) | NN| |用户登录时使用的用户名|
username | varchar(128) | NN | | 用户的姓名
password| varchar(128)  | NN| 'password'||
avatar | varchar(128) | |NULL| 用户的头像URL地址|
authority| int(11) | NN | | 用户的权限，角色|
level | int(11) | NN | | 用户的等级|
super_id | int(11) | NN | | 上级的id（不是uid), 外键指向user表
disable | int(11) | NN | 0 | 用户是否是不可用的（有可能用户被解雇等）
token | varchar(128) | | NULL |
valid_time | DATETIME | |NULL |token的有效时间


## actor_video

`记录actor上传的视频的位置`

Column| DataType | Attr| default | Explanation |
----|------|----|----|----|
id | int(11)  | PK,NN,AI| |记录的唯一标示符|
owner_id | int(11) | | |外键指向user表 |
location | varchar(128) | NN | | actor视频的url|



