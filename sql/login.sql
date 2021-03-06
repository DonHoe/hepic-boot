CREATE TABLE `sys_user`
(
  `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name`   varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `login_name`  varchar(100) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '登录名',
  `password`    varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `phone`       varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '电话号码',
  `salt`        varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '加密盐',
  `status`      int(255)                                NOT NULL DEFAULT 0 COMMENT '状态',
  `create_time` datetime                                NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime                                NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户表';

CREATE TABLE `sys_role`
(
  `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_key`    varchar(100) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '角色键',
  `role_name`   varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '角色名',
  `create_time` datetime                                NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime                                NOT NULL DEFAULT current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='角色表';

CREATE TABLE `sys_menu`
(
  `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id`   bigint(20)                              NOT NULL DEFAULT 0 COMMENT '父菜单',
  `menu_name`   varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单名',
  `url`         varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '请求地址',
  `permit`      varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限',
  `menu_type`   int(255)                                NOT NULL DEFAULT 0 COMMENT '菜单类型',
  `visible`     int(255)                                NOT NULL DEFAULT 0 COMMENT '可见性',
  `icon`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '图标',
  `create_time` datetime                                NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime                                NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='菜单权限表';

CREATE TABLE `sys_user_role`
(
  `id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户id',
  `role_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户角色关系表';

CREATE TABLE `sys_role_menu`
(
  `id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='角色菜单关系表';