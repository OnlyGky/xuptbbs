# Xuptbbs接口文档  

注：如果没有写明请求或返回参数可在swagger查看（Try it out）

## 一 entrance-controller (注册登录登出)

###　1 、/entrance/register    (注册 )

注：注册使用邮箱发送验证码

请求参数

```json
{
  "code": "string",  验证码
  "email": "string", 邮箱
  "nickname": "string", 昵称
  "password": "string" 密码
}
```



### 2  /entrance/login      (登录)   

```json
{
  "code": "string", 验证码 （通过接口会获得一张图片）
  "email": "string", 邮箱
  "password": "string" 密码
}
```

### 3 /entrance/login   （登出）

无需参数通过登录时存于cookie进行校验判断



## 二  code-controller（验证码）

### 1  /code    （ 登录时的验证码图片）

### 2 /code/{email}  (注册时发送邮箱的接口)



## 三  user-controller   用户个人信息管理 

### 1  /user/{id}     获得用户个人信息 

请求参数 ： 

| 参数(String) | 说明   |
| ---------- | ---- |
| id         | 用户id |

```json
{
  "id": "111",                             用户id
  "nickname": "111",                       昵称
  "avatarUrl": "1a9e5e80-d1ba-49f9-b663-898b03e862cb.jpg",    头像
  "major": "11",                           专业
  "grade": 1,                              年级
  "follow": 0,                             我的关注数量
  "fans": 0                                粉丝数量
}
```



### ２　/user/   修改密码

请求参数

```json
{
  "againPassword": "string",  再次确认
  "id": "string",             用户id
  "newPassword": "string",    新密码
  "password": "string"        旧密码
}
```

### 3 /upphoto  修改头像

| id   | 用户id |
| ---- | ---- |
| file | 文件名  |

###  4 /up_user  修改个人信息

请求参数 （swagger中其他参数可不写）

```json
{  "grade": 0,             年级
  "id": "string",          用户id
  "major": "string",       专业
  "nickname": "string"     昵称
}
```

返回参数

```json
{
  "avatarUrl": "string",
  "grade": 0,
  "id": "string",
  "major": "string",
  "nickname": "string"
}
```



## 四  user-attachment-controller 查看我的关注与关注我

### 1    /userattachment     关注 

### 2  /userattachment/fans  查看我的粉丝 

eg：   http://localhost:8080/userattachment/fans?id=1&page=1&per_page=10     

id为用户id  page为第几页   per_page 为每页的数量

### 3   /userattachment/fans    查看我的关注     

eg： 同上

###4   /userattachment         取消关注

## 五 project-controller    项目

### 1  /project    查看所有的项目

eg:  http://localhost:8080/project?page=1&per_page=10

```json
{
  "content": [
    {
      "createdAt": null,
      "deleted": 0,
      "id": 7,             //项目id
      "userId": "111",     //发起人id
      "title": "111",      //标题
      "projectContent": "11",  //项目简介/项目意向
      "partnerContent": "11",  //队友需求/个人介绍
      "conpensation": 1,      //是否有偿  0 为无偿 1 为有偿
      "type": 1,              //0 为找队友    1为找项目    
      "lastModified": "2019-08-19T12:41:30",   //最晚更新时间
      "user": {    //发起人的信息
        "createdAt": null,
        "deleted": 0,
        "id": "7",
        "nickname": "111",
        "avatarUrl": "1a9e5e80-d1ba-49f9-b663-898b03e862cb.jpg",
        "major": "11",
        "grade": 1,
        "lastLoginAt": null
      },
      "label": []    //项目标签
    }
  ],
  "pageable": {     
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "offset": 0,
    "pageSize": 10,
    "pageNumber": 0,
    "unpaged": false,
    "paged": true
  },
  "totalPages": 1,  //总共几页
  "totalElements": 1,  //几条数据
  "last": true,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "numberOfElements": 1,
  "first": true,
  "empty": false
}
```



### 2   /project  创建项目

请求参数：（表单提交）

| label          | 项目标签            | 可多个  |
| -------------- | --------------- | ---- |
| userId         | 用户id            |      |
| type           | 0 为找队友    1为找项目 |      |
| title          | 标题              |      |
| projectContent | 项目简介/项目意向       |      |
| partnerContent | 队友需求/个人介绍       |      |

### 3  、/project/{id}    查看项目详情  

### 4     /project/myproject    查看我的项目 （个人主页中）

eg：http://localhost:8080/project/myproject?id=3&page=1&per_page=10

id：  当前用户id

### 5    /project/type   找队友/找项目

type    =   0 为找队友    1为找项目    



## ６ post-like-controller  文章点赞

### 1 /postlike

```json
{
  "myId": "string",   我的id
  "postId": 0,        文章id
  "summary": "string", 文章标题
  "type": 0,           0 为点赞    1 为取消点赞
  "userId": "string"   文章主人id
}
```



## 7 post-controller  文章

### 1  /post        查看所有文章

eg：http://localhost:8080/post?board=ARTICLE&id=1&page=1&per_page=10

board为类别   id为用户id（没有时为未登录状态）

返回数据

```json
{
  "content": [
    {
      "id": 13,                  文章id
      "title": "安师大",          标题
      "board": "ARTICLE",         分类
      "classification": "其他相关", 二级分类
      "content": "爱上大叔大叔蔷薇蔷薇蔷",   内容
      "likeCount": 0,                      点赞数量  
      "joinCount": 0,                       收藏数量
      "lastActiveAt": "2019-08-18T05:48:56",  发布时间
      "looked": 0,                            观看数量
      "isLike": 0,                             当前用户是否点赞过 0 未点赞   1 已点赞  未登录时为空
      "isCollection": 0,                       当前用户是否收藏过 0 未收藏   1 已收藏
      "topic": [                                文章标签
        "hahaha"
      ],
      "path": [  图片多张
        "MmViOGIzYjMyODAzNDI1YzhkZjNkMjQzYjgyYWI3NTA=.jpg"
      ],
      "userVO": {  用户信息
        "id": "13",
        "nickname": "asas",
        "avatarUrl": "asa"
      }
    },
    {
      "id": 12,
      "title": "安师大",
      "board": "ARTICLE",
      "classification": "其他相关",
      "content": "爱上大叔大叔蔷薇蔷薇蔷",
      "likeCount": 0,
      "joinCount": 0,
      "lastActiveAt": "2019-08-18T05:48:38",
      "looked": 0,
      "isLike": 0,
      "isCollection": 0,
      "topic": [
        "hahaha"
      ],
      "path": [],
      "userVO": {
        "id": "12",
        "nickname": "asas",
        "avatarUrl": "asa"
      }
    },
    {
      "id": 11,
      "title": "安师大",
      "board": "ARTICLE",
      "classification": "其他相关",
      "content": "爱上大叔大叔蔷薇蔷薇蔷",
      "likeCount": 0,
      "joinCount": 0,
      "lastActiveAt": "2019-08-18T05:47:32",
      "looked": 0,
      "isLike": 0,
      "isCollection": 0,
      "topic": [
        "hahaha"
      ],
      "path": [],
      "userVO": {
        "id": "11",
        "nickname": "asas",
        "avatarUrl": "asa"
      }
    },
    {
      "id": 10,
      "title": "安师大",
      "board": "ARTICLE",
      "classification": "全部文章",
      "content": "爱上大叔大叔蔷薇蔷薇蔷",
      "likeCount": 0,
      "joinCount": 0,
      "lastActiveAt": "2019-08-14T20:12:35",
      "looked": 0,
      "isLike": 0,
      "isCollection": 0,
      "topic": [],
      "path": [
        "YTI1MjcxZDc2YmM0NDEwYjhlYzIyMDAyODA0MzMxMWM=.jpg",
        "NTMwZWQyZjczOTZkNGY4OTllZGY1ZjE4YjkxYjA1ODQ=.jpg"
      ],
      "userVO": {
        "id": "10",
        "nickname": "asas",
        "avatarUrl": "asa"
      }
    }
  ],
  "pageable": {
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "offset": 0,
    "pageSize": 10,
    "pageNumber": 0,
    "unpaged": false,
    "paged": true
  },
  "totalPages": 1,
  "totalElements": 4,
  "last": true,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "numberOfElements": 4,
  "first": true,
  "empty": false
}
```



### 2 /post 发表文章

### 3  /post  删除文章

### 4   /post/classification   根据二级标签查看其中文章

### 5 /post/collection  查看我的收藏

###  6 /post/detail  查看文章详情

返回参数

```json
{
  "id": 11,
  "title": "安师大",
  "board": "ARTICLE",
  "classification": "其他相关",
  "content": "爱上大叔大叔蔷薇蔷薇蔷",
  "likeCount": 0,
  "joinCount": 0,
  "lastActiveAt": "2019-08-18T05:47:32",
  "looked": 1,
  "isLike": 0,
  "isCollection": 0,
  "topic": [
    "hahaha"
  ],
  "path": [],
  "userVO": {
    "id": "11",
    "nickname": "asas",
    "avatarUrl": "asa"
  },
  "comment": [   //评论
    {    
      "id": 1,    评论id
      "content": "11",   内容
      "parentId": 0,     父评论
      "lastActiveAt": "2019-08-20T10:16:58"
    }
  ]
}
```



### 7 /post/detail  查看我的文章

### 8  /post/myquestion 查看我的提问

### 9 /post/update  修改文章

只能修改标题 内容

注：

board :

```
ARTICLE(1, "文章"),
QUESTION(2, "问答"),
PROJECT(3, "做项目"),
PRACTICE(4, "实习专栏");
```

classification:

```
全部文章(11, "全部文章"),
产品测试(12, "产品测试"),
产品运营(13, "产品运营"),
其他相关(21, "其他相关"),
Java后台(22, "Java后台"),
web前端(23, "web前端");
```

## 六 post-collection-controller   文章收藏

### 1 /postcollection   

请求参数  （同文章点赞）

```
{
  "myId": "string",
  "postId": 0,
  "summary": "string",
  "type": 0,
  "userId": "string"
}
```



## 七  notification-controller  通知

### 1  /notification 查询通知  

eg： http://localhost:8080/notification?id=111&page=1&per_page=10

id为用户id

返回参数

```json
{
  "code": 0,
  "msg": "SUCCESS",
  "data": {
    "content": [
      {
        "id": 3,      //通知id
        "type": 1,    //类型
        "summary": "asdasdas",  //总结
        "beRead": 0,            //是否已读
        "userVO": {             //通知用户
          "id": "3",
          "nickname": "22",
          "avatarUrl": "22"
        },
        "lastModified": "2019-08-18T09:00:12"
      }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      },
      "offset": 0,
      "pageSize": 10,
      "pageNumber": 0,
      "unpaged": false,
      "paged": true
    },
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
  }
}
```



### 2  /notification 删除通知

### 3 /notification/detail/{id}  查看详细通知 

注：id为通知id

## 八  comment-controller  文章评论

### 1 /comment  创建评论

请求参数

```json
{
  "content": "string",   //内容
  "parentId": 0,         //父评论id
  "postId": 0,           //文章id
  "toUserId": "string",  //评论用户id
  "userId": "string"     //被评论用户id
}
```



### 2、/comment  删除评论

 ###　３　/comment/{id}  根据父评论查找子评论

eg：http://localhost:8080/comment/0   id为某个评论的id



返回参数

```json
[
  {
    "id": 1,
    "content": "11",
    "parentId": 0,
    "lastActiveAt": "2019-08-20T10:16:58",
    "userVO": {
      "id": "111",
      "nickname": "111",
      "avatarUrl": "1a9e5e80-d1ba-49f9-b663-898b03e862cb.jpg"
    }
  }
]
```



## 九   answer-like-controller  回答点赞

### 1  /answerlike   回答点赞   

请求参数 （同文章点赞）

```json
{
  "answerId": 0,
  "myId": "string",
  "summary": "string",
  "type": 0,
  "userId": "string"
}
```



## 十   answer-controller  回答

### 1 、/answer   根据问题id查找回答

返回参数

```json
{
  "content": [
    {
      "id": 1,               回答id
      "content": "123123",   回答内容
      "likeCount": 0,        点赞总数
      "commentCount": 0,     评论总数
      "lastModified": "2019-08-15T06:12:56",
      "isLike": 0,          未登录默认为0 未点赞为0 已点赞为1
      "userVO": {           发表回答的用户信息
        "id": "1",
        "nickname": "111",
        "avatarUrl": "1a9e5e80-d1ba-49f9-b663-898b03e862cb.jpg"
      },
      "commentVOS": []     //对该回答发表的评论
    },
    {
      "id": 2,
      "content": "123123ssssssssssssssssssss",
      "likeCount": 0,
      "commentCount": 1,
      "lastModified": "2019-08-15T06:14:47",
      "isLike": 0,
      "userVO": {
        "id": "2",
        "nickname": "111",
        "avatarUrl": "1a9e5e80-d1ba-49f9-b663-898b03e862cb.jpg"
      },
      "commentVOS": [
        {
          "id": 3,
          "content": "123123",
          "parentId": 0,
          "lastModified": "2019-08-16T04:02:11",
          "userVO": {
            "id": "111",
            "nickname": "111",
            "avatarUrl": "1a9e5e80-d1ba-49f9-b663-898b03e862cb.jpg"
          }
        }
      ]
    }
  ],
  "pageable": {
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "offset": 0,
    "pageSize": 10,
    "pageNumber": 0,
    "unpaged": false,
    "paged": true
  },
  "totalPages": 1,
  "totalElements": 2,
  "last": true,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```

### 2 /answer    发布回答

请求参数

```json
{
  "content": "string",  文章内容
  "questionId": 0,      问题id
  "touserId": "string",  文章主人的用户id
  "userId": "string"     我的用户id
}
```



###　３ /answer    删除回答

ｅｇ：　http://localhost:8080/answer?id=8&userId=111

## 十一   answer-comment-controller  回答的评论

### 1  /answercomment  提交评论

```json
{
  "answerId": 0,    回答的id
  "content": "string",  内容
  "parentId": 0,       父评论的id
  "postId": 0,         文章的id
  "toUserId": "string",  被评论的id  
  "userId": "string"     评论的人id
}
```



### 2  /answercomment  删除评论 

eg:http://localhost:8080/answercomment?answerId=1&id=1&userId=1

### 3  /answercomment/{id}  根据父评论查找子评论

eg: http://localhost:8080/answercomment/0

```json
[
  {
    "id": 3,
    "content": "123123",
    "parentId": 0,
    "lastModified": "2019-08-16T04:02:11",
    "userVO": {
      "id": "111",
      "nickname": "111",
      "avatarUrl": "1a9e5e80-d1ba-49f9-b663-898b03e862cb.jpg"
    }
  }
]
```











