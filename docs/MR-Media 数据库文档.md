# 数据库文档


## user

`用户的实体集`

| Column        | DataType     | Attr     | default    | Explanation             |      |
| :------------ | ------------ | -------- | ---------- | ----------------------- | ---- |
| id            | int(11)      | PK,NN,AI |            | 记录的唯一标示符                |      |
| uid           | varchar(24)  | NN       |            | 用户登录时使用的用户名             |      |
| real_name     | varchar(64)  |          |            | 真实姓名                    |      |
| phone_number  | varchar(64)  |          |            | 电话号码                    |      |
| wechat_number | varchar(64)  |          |            | 微信账号                    |      |
| email         | varchar(64)  |          |            | 邮箱账号                    |      |
| talent_type   | int(11)      |          |            | 才艺类型(经纪人为空)         |      |
| location      | varchar(64)  |          |            | 直播平台                    |      |
| settleType    | int(11)      |          |            | 支付类型                    |      |
| settleAccount | varchar(64)  |          |            | 支付账号                    |      |
| password      | varchar(128) | NN       | 'password' |                         |      |
| avatar        | varchar(128) |          | NULL       | 用户的头像URL地址              |      |
| authority     | int(11)      | NN       |            | 用户的权限，角色                |      |
| level         | int(11)      | NN       |            | 用户的等级                   |      |
| super_id      | int(11)      | NN       |            | 上级的id（不是uid), 外键指向user表 |      |
| disable       | int(11)      | NN       | 0          | 用户是否是不可用的（有可能用户被解雇等）    |      |
| active        | int(11)      | NN       | 0          | 用户是否审核被通过，0代表未通过，1代表通过              |      |
| token         | varchar(128) |          | NULL       |                         |      |
| valid_time    | DATETIME     |          | NULL       | token的有效时间              |      |



## actor_video

`记录actor上传的视频的位置`

Column| DataType | Attr| default | Explanation |
----|------|----|----|----|
id | int(11)  | PK,NN,AI| |记录的唯一标示符|
owner_id | int(11) | | |外键指向user表 |
location | varchar(128) | NN | | actor视频的url|


## user_uploaded_pictures

`记录用户上传的图片的位置`

| Column   | DataType     | Attr     | default | Explanation      |      |
| -------- | ------------ | -------- | ------- | ---------------- | ---- |
| id       | int(11)      | PK,NN,AI |         | 记录的唯一标示符         |      |
| owner_id | int(11)      |          |         | 外键指向user表        |      |
| locations| varchar(256) | NN       |         | 用户上传的图片。用;分割多个照片 |      |


## notifications

`消息表`

| Column       | DataType     | Attr     | default | Explanation    |    |
| ------------ | ------------ | -------- | ------- | -------------- | ---- |
| id           | int(11)      | PK,NN,AI |         | 记录的唯一标示符       |      |
| sender       | int(11)      | NN       |         | 外键指向user表，发送人  |      |
| receiver     | int(11)      | NN       |         | 外键指向user表，接收人  |      |
| text_content | varchar(256) | NN       |         | 消息文字内容         |      |
| link         | varchar(256) |          | NULL    | 超链接            |      |
| pictures_url | varchar(256) |          | NULL    | 相关图片（多个图片用；分割） |     |
| status       | int(11)      | NN       | 0       |  消息状态,0代表未读,1代表已读      |      |


## reviews
`审核表`

| Column       | DataType | Attr     | default | Explanation                        |      |
| ------------ | -------- | -------- | ------- | ---------------------------------- | ---- |
| id           | int(11)  | PK,NN,AI |         | 记录的唯一标示符                           |      |
| creator_id   | int(11)  | NN       |         | 提交审核的用户id，外键指向user表                |      |
| recommend_id | int(11)  | NN       |         | 推荐的用户的id（外键指向user表），如果没有推荐的人，则指向自己 |      |
| status       | int(11)  | NN       | 0       | 审核状态（0代表未通过）                       |      |




## authority_group
`权限表`

| Column       | DataType | Attr     | default | Explanation                        |      |
| ------------ | -------- | -------- | ------- | ---------------------------------- | ---- |
| id           | int(11)  | PK,NN,AI |         | 记录的唯一标示符                           |      |
| name         | int(11)  | NN       |         | 权限组名字                    |      |
| authority_id | int(11)  | NN       |         | 该权限组内涵权限, 外键指向authority表 |     |

## authority
`权限\侧边栏`

| Column       | DataType | Attr     | default | Explanation                        |      |
| ------------ | -------- | -------- | ------- | ---------------------------------- | ---- |
| id           | int(11)  | PK,NN,AI |         | 记录的唯一标示符                           |      |
| name         | varchar(64)  | NN       |         | id对应的authority名称 |     |



