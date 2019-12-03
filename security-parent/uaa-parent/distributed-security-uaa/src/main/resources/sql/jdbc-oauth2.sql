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

INSERT INTO security.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, create_time, archived, trusted, autoapprove) VALUES ('c1', 'rest1', '$2a$10$LiUdeW6zNp99kbNZ2C2Sh.o9yu35Zco89kUfBGho6xGZY7OWNL7Vu', 'all', 'authorization_code,password,implicit,client-credentials,refresh_token', 'http://localhost:53022/order/oauth2', '', 7200, 25200, '{"name":"wpp"}', '2019-12-03 11:57:41', 0, 0, 'false');