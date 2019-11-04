SET NAMES utf8mb4;
--  用户表
CREATE TABLE IF NOT EXISTS `user`
(
   `id`                    char(32)             NOT NULL ,
   `nickname`              varchar(255)         NOT NULL ,
   `avatar_url`            varchar(255)         NOT NULL ,
   `major`                 char(32)             NULL ,  -- 职位
   `grade`                 int UNSIGNED         NULL ,
   `last_login_at`         datetime             NOT NULL ,
   `created_at`            datetime             NOT NULL ,
   `deleted`               tinyint(1)           NOT NULL DEFAULT 0 ,
   PRIMARY KEY (`id`),
   KEY (`nickname`)
) ;

CREATE TABLE IF NOT EXISTS `user_sercet`
(
    `id`                    int UNSIGNED         NOT NULL AUTO_INCREMENT ,
    `user_id`               char(32)             NOT NULL ,
    `email`                 varchar(255)         NOT NULL ,
    `password`              varchar(255)         NOT NULL ,
    `created_at`            datetime             NOT NULL ,
    `last_modified`         datetime             NOT NULL ,
    `deleted`               tinyint(1)           NOT NULL DEFAULT 0 ,
    PRIMARY KEY (`id`) ,
    UNIQUE (`user_id`),
    UNIQUE (`email`)
);

-- 用户关注
CREATE TABLE IF NOT EXISTS `user_attachment`
(
    `id`                  int UNSIGNED      NOT NULL AUTO_INCREMENT ,
    `from_user_id`        char(32)          NOT NULL ,
    `to_user_id`          char (32)         NOT NULL ,
    `created_at`            datetime         NOT NULL ,
    PRIMARY KEY (`id`) ,
    KEY (`from_user_id`)
);

-- 问题关注
CREATE TABLE IF NOT EXISTS `question_attachment`
(
    `id`                  int UNSIGNED      NOT NULL AUTO_INCREMENT ,
    `user_id`             char(32)          NOT NULL ,
    `question_id`         int UNSIGNED      NOT NULL ,
    `created_at`          datetime           NOT NULL ,
    `last_modified`       datetime           NOT NULL ,
    PRIMARY KEY (`id`) ,
    KEY (`user_id`) ,
    KEY (`question_id`)
);

-- 问题回答
CREATE TABLE IF NOT EXISTS `answer`
(
    `id`                    int UNSIGNED      NOT NULL AUTO_INCREMENT ,
    `question_id`           int UNSIGNED      NOT NULL ,
    `user_id`               char(32)          NOT NULL ,
    `content`               text              NOT NULL ,
    `like_count`            int UNSIGNED      NOT NULL DEFAULT 0 ,  -- 点赞
    `comment_count`         int UNSIGNED      NOT NULL DEFAULT 0 , -- 评论总数
    `created_at`            datetime          NOT NULL ,
    `last_modified`         datetime          NOT NULL ,
    `deleted`               tinyint(1)        NOT NULL DEFAULT 0 ,
    PRIMARY KEY (`id`) ,
    KEY (`question_id`)
);

-- 回答评论
CREATE TABLE IF NOT EXISTS `answer_comment`
(
    `id`                   int UNSIGNED        NOT NULL AUTO_INCREMENT ,
    `content`              text                NOT NULL ,
    `post_id`              int UNSIGNED        NOT NULL ,
    `parent_id`            int UNSIGNED        NOT NULL DEFAULT 0 ,
    `answer_id`            int UNSIGNED        NOT NULL ,
    `user_id`              char(32)            NOT NULL ,
    `created_at`           datetime            NOT NULL ,
    `last_modified`        datetime             NOT NULL ,
    `deleted`              tinyint(1)          NOT NULL DEFAULT 0 ,
    PRIMARY KEY (`id`) ,
    KEY (`answer_id`) ,
    KEY (`user_id`)
);

-- 文章/问题/实习
CREATE TABLE IF NOT EXISTS `post`
(
    `id`             int UNSIGNED      NOT NULL AUTO_INCREMENT ,
    `user_id`        char(32)          NOT NULL ,
    `title`          varchar(255)      NOT NULL ,
    `board`          smallint UNSIGNED NOT NULL ,
    `content`        text               NOT NULL ,
    `looked`         int UNSIGNED      NOT NULL DEFAULT 0 ,
    `like_count`     int UNSIGNED      NOT NULL DEFAULT 0 ,  -- 点赞
    `join_count`     int UNSIGNED      NOT NULL DEFAULT 0 ,  -- 评论、回答总数
    `created_at`     datetime          NOT NULL ,
    `last_active_at` datetime          NOT NULL ,
    `deleted`        tinyint(1)         NOT NULL DEFAULT 0 ,
    PRIMARY KEY (`id`) ,
    KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS `post_photo`
(
    `id`                    int UNSIGNED        NOT NULL AUTO_INCREMENT,
    `post_id`               int UNSIGNED        NOT NULL ,
    `path`                  varchar(255)        NOT NULL,
    `created_at`            datetime             NOT NULL ,
    `deleted`               tinyint(1)           NOT NULL DEFAULT 0 ,
    PRIMARY KEY (`id`) ,
    KEY (`post_id`)
);

--  文章评论
CREATE TABLE IF NOT EXISTS `comment`
(
    `id`                    int UNSIGNED         NOT NULL AUTO_INCREMENT ,
    `content`               text                 NOT NULL ,
    `parent_id`             int UNSIGNED         NOT NULL DEFAULT 0 ,
    `post_id`               int UNSIGNED         NOT NULL ,
    `user_id`               char(32)             NOT NULL ,
    `created_at`            datetime             NOT NULL ,
    `last_modified`         datetime             NOT NULL ,
    `deleted`               tinyint(1)           NOT NULL DEFAULT 0 ,
    PRIMARY KEY (`id`) ,
    KEY (`post_id`) ,
    KEY (`user_id`)
);

--  主题
CREATE TABLE IF NOT EXISTS `topic`
(
    `id`                     int UNSIGNED        NOT NULL AUTO_INCREMENT ,--
    `board`                  smallint UNSIGNED   NOT NULL ,  -- 类别
    `description`            varchar(255)        NOT NULL ,  -- 具体划分
    `created_at`             datetime            NOT NULL ,
    `deleted`                tinyint(1)           NOT NULL DEFAULT 0 ,
    PRIMARY KEY (`id`) ,
    KEY (`board`)
);

--  内容-主题联系
CREATE TABLE IF NOT EXISTS `post_topic`
(
    `id`       int UNSIGNED  NOT NULL AUTO_INCREMENT ,
    `post_id`  int UNSIGNED  NOT NULL ,
    `topic`    varchar(255)  NOT NULL ,
    PRIMARY KEY (`id`),
    KEY (`post_id`)
);

--  内容收藏
CREATE TABLE IF NOT EXISTS  `post_collection`
(
    `id`                    int UNSIGNED         NOT NULL AUTO_INCREMENT,
    `post_id`               int UNSIGNED         NOT NULL,
    `user_id`               char(32)             NOT NULL,
    `created_at`            datetime             NOT NULL,

    PRIMARY KEY (`id`),
    KEY(`post_id`, `user_id`)
);

-- 通知
CREATE TABLE IF NOT EXISTS  `notification`
(
    `id`                   int UNSIGNED      NOT NULL AUTO_INCREMENT,
    `type`                  smallint         NOT NULL,  -- 通知类型
    `reference_id`          int              NOT NULL,  -- 涉及到的事
    `summary`               varchar(255)     NOT NULL,  -- 总结
    `content`               varchar(255)     NOT NULL,  -- 主要内容
    `be_read`               tinyint(1)        NOT NULL default 0,
    `from_user_id`          char(32)         NOT NULL,
    `to_user_id`            char(32)         NOT NULL,
    `created_at`            datetime         NOT NULL,
    `last_modified`         datetime         NOT NULL ,
    `deleted`               tinyint(1)       NOT NULL DEFAULT 0 ,
    PRIMARY KEY (`id`),
    KEY (`to_user_id`),
    KEY (`reference_id`)
);

-- 项目
CREATE TABLE IF NOT EXISTS `project`
(
    `id`                    int UNSIGNED         NOT NULL AUTO_INCREMENT ,
    `user_id`               char(32)             NOT NULL ,
    `title`                 varchar (255)        NOT NULL ,  -- 简单情况说明
    `project_content`       text                  NULL ,      -- 项目简介/项目意向
--     `partner_content`        text                  NOT NULL ,  --队友需求/个人介绍
    `conpensation`          tinyint(1)            NOT NULL default 0 , -- 是否有偿
    `type`                  tinyint(1)            NOT NULL default 0 ,
    `created_at`            datetime             NOT NULL ,
    `last_modified`         datetime             NOT NULL ,
    `deleted`               tinyint(1)           NOT NULL DEFAULT 0 ,
    PRIMARY KEY (`id`) ,
    KEY (`user_id`)
);
-- 项目标签
CREATE TABLE IF NOT EXISTS `project_label`
(
    `id`                            bigint UNSIGNED         NOT NULL AUTO_INCREMENT,
    `project_id`                    int UNSIGNED         NOT NULL ,
    `label`                         varchar (255)        NOT NULL ,
     PRIMARY KEY (`id`) ,
     KEY (`project_id`)
);

CREATE TABLE IF NOT EXISTS  `post_like`
(
    `id`                    int UNSIGNED         NOT NULL AUTO_INCREMENT,
    `post_id`               int UNSIGNED         NOT NULL,
    `user_id`               char(32)             NOT NULL,
    `created_at`            datetime             NOT NULL,

    PRIMARY KEY (`id`),
    KEY(`post_id`, `user_id`)
);
CREATE TABLE IF NOT EXISTS  `answer_like`
(
    `id`                    int UNSIGNED         NOT NULL AUTO_INCREMENT,
    `answer_id`             int UNSIGNED         NOT NULL,
    `user_id`               char(32)             NOT NULL,
    `created_at`            datetime             NOT NULL,
    PRIMARY KEY (`id`),
    KEY(`answer_id`, `user_id`)
);
CREATE TABLE IF NOT EXISTS `report`
(
  `id`                     int UNSIGNED         NOT NULL AUTO_INCREMENT ,
  `post_id`                int UNSIGNED         NOT NULL ,
  `from_user_id`           char(32)             NOT NULL ,
  `to_user_id`             char(32)             NOT NULL ,
  `result`                 text                 NOT NULL ,
  `created_at`             datetime             NOT NULL ,
  `last_modified`          datetime             NOT NULL ,
  `deleted`                tinyint(1)           NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`)

)
