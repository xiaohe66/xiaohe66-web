/*==============================================================*/
/* Table: xh_web_org_usr                                        */
/*==============================================================*/
create table xh_web_org_usr
(
   id                   bigint not null auto_increment,
   create_id            bigint comment '创建者id',
   create_time          timestamp(6) not null default CURRENT_TIMESTAMP(6) comment '创建时间',
   update_id            bigint comment '更新者id',
   update_time          timestamp(6) not null default CURRENT_TIMESTAMP(6) comment '更新时间',
   is_delete            tinyint(4) not null default 0 comment '是否删除（0:未删除,1:删除）',
   usr_name             varchar(16) not null comment '用户名',
   usr_pwd              varchar(64) not null comment '用户密码',
   signature            varchar(128) comment '签名',
   primary key (id)
);

alter table xh_web_org_usr comment '用户表';
