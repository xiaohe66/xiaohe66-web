-- 2018.10.30
ALTER TABLE `xiaohe66_web_resume_func`
CHANGE COLUMN `func_text` `article_id` bigint(20) NULL DEFAULT NULL COMMENT '文章id' AFTER `func_name`;

ALTER TABLE `xiaohe66_web_resume_func`
ADD CONSTRAINT `FK_resume_func_link__article_id` FOREIGN KEY (`article_id`) REFERENCES
`xiaohe66_web_text_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;