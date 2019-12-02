-- auto-generated definition
create table oauth_client_details
(
    client_id               varchar(255) charset utf8           not null comment '客户端标识'
        primary key,
    resource_ids            varchar(255) charset utf8           null comment '资源列表',
    client_secret           varchar(255) charset utf8           null comment '客户端密码',
    scope                   varchar(255) charset utf8           null comment '授权范围',
    authorized_grant_types  varchar(255) charset utf8           null comment '授权类型',
    web_server_redirect_uri varchar(255) charset utf8           null comment '回调地址',
    authorities             varchar(255) charset utf8           null comment '权限列表',
    access_token_validity   int                                 null comment 'access_token 有效性',
    refresh_token_validity  int                                 null comment 'refresh_token 有效性',
    additional_information  longtext charset utf8               null comment '附件信息',
    create_time             timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    archived                tinyint                             null,
    trusted                 tinyint                             null comment '是否信任',
    autoapprove             varchar(255) charset utf8           null
);

-- auto-generated definition
create table oauth_code
(
    create_time    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    code           varchar(255) charset utf8           null,
    authentication blob                                null
);

create index oauth_code_code_index
    on oauth_code (code);

