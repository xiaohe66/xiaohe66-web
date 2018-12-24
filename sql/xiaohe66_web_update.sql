-- 2018.10.30
ALTER TABLE `xiaohe66_web_resume_func`
CHANGE COLUMN `func_text` `article_id` bigint(20) NULL DEFAULT NULL COMMENT '文章id' AFTER `func_name`;

ALTER TABLE `xiaohe66_web_resume_func`
ADD CONSTRAINT `FK_resume_func_link__article_id` FOREIGN KEY (`article_id`) REFERENCES
`xiaohe66_web_text_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- 2018.11.02
ALTER TABLE `xiaohe66_web_resume_project`
MODIFY COLUMN `project_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目说明(富文本)' AFTER `project_name`,
ADD COLUMN `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序' AFTER `resume_id`,
ADD COLUMN `project_link` varchar(255) NULL COMMENT '项目链接' AFTER `project_desc`;

-- 2018.12.24
ALTER TABLE `xiaohe66_web_text_message_board`
ADD COLUMN `usr_name` varchar(255) NULL COMMENT '匿名留言的用户名称' AFTER `anonymity`,
MODIFY COLUMN `create_id` bigint(20) NULL COMMENT '创建者id' AFTER `id`,
MODIFY COLUMN `update_id` bigint(20) NULL COMMENT '更新者id' AFTER `create_time`;
