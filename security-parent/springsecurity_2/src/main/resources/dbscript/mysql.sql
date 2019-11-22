create table if not exists sys_user
(
    id                  int(10) primary key auto_increment comment 'id',
    username            varchar(50)  not null comment '用户名',
    real_name           varchar(50) comment '真实名字',
    password            varchar(256) not null comment '密码',
    create_time         timestamp comment '创建时间',
    last_login_time     timestamp comment '最近登陆时间',
    enabled             int(5) default 1 comment '是否可用',
    expired             int(5) default 1 comment '是否过期',
    locked              int(5) default 1 comment '是否锁定',
    credentials_expired int(5) default 1 comment '凭证是否过期',
    remark              varchar(256) comment '备注'
);

create table if not exists sys_role
(
    id          int(10) primary key auto_increment comment 'id',
    role_name   varchar(50) not null comment '用户名',
    role_desc   varchar(50) not null comment '展示名称',
    create_time timestamp comment '创建时间',
    enabled     int(5) default 1 comment '是否可用',
    remark      varchar(256) comment '备注'
);

create table if not exists sys_permission
(
    id          int(10) primary key auto_increment comment 'id',
    perm_name   varchar(50) not null comment '用户名',
    perm_tag    varchar(50) not null comment '展示名称',
    create_time timestamp comment '创建时间',
    enabled     int(5) default 1 comment '是否可用',
    remark      varchar(256) comment '备注'
);


create table if not exists sys_user_role
(
    id      int(10) primary key auto_increment comment 'id',
    user_id int(10) not null,
    role_id int(10) not null,
    enabled int(5) default 1 comment '是否可用',
    constraint unique INDEX idx_user_role (user_id, role_id),
    constraint foreign key (user_id) references sys_user (id),
    constraint foreign key (role_id) references sys_role (id)
);

create table if not exists sys_role_permission
(
    id            int(10) primary key auto_increment,
    role_id       int(10) not null,
    permission_id int(10) not null,
    constraint unique index idx_role_permission (role_id, permission_id),
    constraint foreign key (role_id) references sys_role (id),
    constraint foreign key (permission_id) references sys_permission (id)
);



--
