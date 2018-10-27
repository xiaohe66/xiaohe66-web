/*
 Target Server Type    : MySQL
 Target Server Version : 50703
 File Encoding         : 65001

 Date: 23/10/2018 18:54:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xiaohe66_web_comm_category
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_comm_category`;
CREATE TABLE `xiaohe66_web_comm_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `category_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名',
  `category_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类说明',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父级分类id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_category_link_category`(`pid`) USING BTREE,
  INDEX `FK_usr_category_link__create_id`(`create_id`) USING BTREE,
  INDEX `FK_usr_category_link__update_id`(`update_id`) USING BTREE,
  CONSTRAINT `FK_usr_category_link__update_id` FOREIGN KEY (`update_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_category_link_category` FOREIGN KEY (`pid`) REFERENCES `xiaohe66_web_comm_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_usr_category_link__create_id` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统通用分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xiaohe66_web_comm_category
-- ----------------------------
INSERT INTO `xiaohe66_web_comm_category` VALUES (1, 1, '2018-10-23 18:36:47.767824', 1, '2018-10-23 18:36:47.767824', 0, '分章文类', '系统文章分类顶级', NULL);
INSERT INTO `xiaohe66_web_comm_category` VALUES (2, 1, '2018-10-23 18:36:47.813051', 1, '2018-10-23 18:36:47.813051', 0, '技术文档', '', 1);
INSERT INTO `xiaohe66_web_comm_category` VALUES (3, 1, '2018-10-23 18:36:47.813051', 1, '2018-10-23 18:36:47.813051', 0, '心情琐事', '', 1);
INSERT INTO `xiaohe66_web_comm_category` VALUES (4, 1, '2018-10-23 18:36:47.813051', 1, '2018-10-23 18:36:47.813051', 0, '生活科普', '', 1);

-- ----------------------------
-- Table structure for xiaohe66_web_comm_file
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_comm_file`;
CREATE TABLE `xiaohe66_web_comm_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件的md5摘要',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件保存路径',
  `file_byte` int(11) NULL DEFAULT 0 COMMENT '文件大小/字节',
  `start_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '文件开始上传的时间',
  `end_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '文件上传成功的时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_md5_sole`(`md5`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xiaohe66_web_comm_file
-- ----------------------------
INSERT INTO `xiaohe66_web_comm_file` VALUES (1, '1d60691dfa928f8ceceb8532bbdddf76', '0/1d60691dfa928f8ceceb8532bbdddf76', 7755, '2018-10-23 18:31:28.537379', '2018-10-23 18:31:28.537379');
INSERT INTO `xiaohe66_web_comm_file` VALUES (2, 'e9d1e8cbc34fd6e97d9b1c656f12070d', '0/e9d1e8cbc34fd6e97d9b1c656f12070d', 1354, '2018-10-23 18:31:28.589392', '2018-10-23 18:31:28.589392');
INSERT INTO `xiaohe66_web_comm_file` VALUES (3, '0a3d387a7dc9f320ccb319bd0c520667', '/0/0a3d387a7dc9f320ccb319bd0c520667', 4876, '2018-04-26 23:35:34.644440', '2018-04-26 23:35:34.000000');

-- ----------------------------
-- Table structure for xiaohe66_web_comm_file_tmp
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_comm_file_tmp`;
CREATE TABLE `xiaohe66_web_comm_file_tmp`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主文件的md5值',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '临时文件保存路径',
  `file_byte` int(11) NOT NULL COMMENT '临时文件大小/字节',
  `chunk` int(11) NOT NULL COMMENT '文件的区块数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件分块上传临时表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_org_usr
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_org_usr`;
CREATE TABLE `xiaohe66_web_org_usr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `usr_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名，用作登录',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `usr_pwd` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `signature` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名',
  `img_file_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '头像文件id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_usr_name`(`usr_name`) USING BTREE,
  UNIQUE INDEX `AK_email`(`email`) USING BTREE,
  INDEX `FK_usr_usr_file_link__img_file_id`(`img_file_id`) USING BTREE,
  CONSTRAINT `FK_usr_usr_file_link__img_file_id` FOREIGN KEY (`img_file_id`) REFERENCES `xiaohe66_web_org_usr_file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xiaohe66_web_org_usr
-- ----------------------------
INSERT INTO `xiaohe66_web_org_usr` VALUES (1, 1, '2018-10-23 18:33:43.520331', 1, '2018-10-23 18:33:43.520331', 0, 'root', '', 'e26e480cf8254c3ca4d826be11368d8bc3024960qj1bQQu8QVXcjtvGx5PUQA==', NULL, 1);
INSERT INTO `xiaohe66_web_org_usr` VALUES (2, 1, '2018-10-23 18:33:43.520331', 1, '2018-10-23 18:33:43.520331', 0, 'yanzi', '', 'b8e24f823dffc04426b2474462b2211402c170b2gANcVzMQqQUpiVRJssqIRA==', NULL, 1);
INSERT INTO `xiaohe66_web_org_usr` VALUES (3, 1, '2018-10-23 18:33:43.520331', 1, '2018-10-23 18:33:43.520331', 0, 'xiaohe', 'ty@xiaohe66.com', '206d62ab099e7fd1bd765fdcb04787a14c52e887Dv+by5RNmuCzC272XzSuEw==', NULL, 3);

-- ----------------------------
-- Table structure for xiaohe66_web_org_usr_file
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_org_usr_file`;
CREATE TABLE `xiaohe66_web_org_usr_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `file_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称(不带扩展名)',
  `file_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件说明',
  `file_id` bigint(20) NOT NULL COMMENT '附件id',
  `file_type` tinyint(4) NOT NULL COMMENT '文件类型(0:普通文件,1:头像,2:文章配图)',
  `extension` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_usr_file_file_link__file_id`(`file_id`) USING BTREE,
  INDEX `FK_file_usr_link__create_id`(`create_id`) USING BTREE,
  INDEX `FK_usr_file_link__update_id`(`update_id`) USING BTREE,
  CONSTRAINT `FK_usr_file_link__update_id` FOREIGN KEY (`update_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_file_usr_link__create_id` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_usr_file_file_link__file_id` FOREIGN KEY (`file_id`) REFERENCES `xiaohe66_web_comm_file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户文件关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xiaohe66_web_org_usr_file
-- ----------------------------
INSERT INTO `xiaohe66_web_org_usr_file` VALUES (1, 1, '2018-10-23 18:33:18.303263', 1, '2018-10-23 18:33:18.303263', 0, 'head_img', '默认头像', 1, 1, '.png');
INSERT INTO `xiaohe66_web_org_usr_file` VALUES (2, 1, '2018-10-23 18:33:18.355206', 1, '2018-10-23 18:33:18.355206', 0, 'org_logo', '默认企业logo', 2, 1, '.png');
INSERT INTO `xiaohe66_web_org_usr_file` VALUES (3, 1, '2018-10-23 18:33:18.440301', 1, '2018-10-23 18:33:18.440301', 0, 'xh_default_img', '小何的默认头像', 3, 1, '.png');

-- ----------------------------
-- Table structure for xiaohe66_web_org_usr_file_log
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_org_usr_file_log`;
CREATE TABLE `xiaohe66_web_org_usr_file_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '当前操作者',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `log_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '操作类型(0:下载)',
  `usr_file_id` bigint(20) NOT NULL COMMENT '当前操作的用户文件id',
  `ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当前操作者的ip',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_usr__usr_file_log`(`create_id`) USING BTREE,
  INDEX `FK_usr_file__usr_file_log`(`usr_file_id`) USING BTREE,
  CONSTRAINT `FK_usr_file__usr_file_log` FOREIGN KEY (`usr_file_id`) REFERENCES `xiaohe66_web_org_usr_file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_usr__usr_file_log` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户文件操作日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_resume_func
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_resume_func`;
CREATE TABLE `xiaohe66_web_resume_func`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `project_id` bigint(20) NOT NULL COMMENT '工作经历id',
  `func_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能名称',
  `func_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '功能说明(富文本)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_resume_func_link__create_id`(`create_id`) USING BTREE,
  INDEX `FK_resume_func_link__project_id`(`project_id`) USING BTREE,
  INDEX `FK_resume_func_link__update_id`(`update_id`) USING BTREE,
  CONSTRAINT `FK_resume_func_link__update_id` FOREIGN KEY (`update_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_resume_func_link__create_id` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_resume_func_link__project_id` FOREIGN KEY (`project_id`) REFERENCES `xiaohe66_web_resume_project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '简历-功能点' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_resume_job
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_resume_job`;
CREATE TABLE `xiaohe66_web_resume_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `resume_id` bigint(20) NOT NULL COMMENT '简历id',
  `logo` bigint(20) NOT NULL DEFAULT 3 COMMENT '企业logo',
  `org_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司/企业名称',
  `org_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司/企业描述',
  `start_date` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开始日期',
  `end_date` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '结束日期',
  `obligation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作职责',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_resume_job_link__logo`(`logo`) USING BTREE,
  INDEX `FK_resume_jobs_link__create_id`(`create_id`) USING BTREE,
  INDEX `FK_resume_jobs_link__cupdate_id`(`update_id`) USING BTREE,
  INDEX `FK_resume_jobs_link__resume_id`(`resume_id`) USING BTREE,
  CONSTRAINT `FK_resume_jobs_link__resume_id` FOREIGN KEY (`resume_id`) REFERENCES `xiaohe66_web_resume_main` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_resume_jobs_link__create_id` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_resume_jobs_link__cupdate_id` FOREIGN KEY (`update_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_resume_job_link__logo` FOREIGN KEY (`logo`) REFERENCES `xiaohe66_web_org_usr_file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作经历' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_resume_main
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_resume_main`;
CREATE TABLE `xiaohe66_web_resume_main`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `name` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号码',
  `email` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `education` tinyint(4) NOT NULL DEFAULT 0 COMMENT '学历(0:大专,1:本科,:2研究生,3:中专,4:其它)',
  `graduationschool` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '毕业院校',
  `graduation_date` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '毕业时间',
  `ability_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人能力说明',
  `ability_json` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人能力json',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_resume_usr_link__create_id`(`create_id`) USING BTREE,
  INDEX `FK_resume_usr_link__update_id`(`update_id`) USING BTREE,
  CONSTRAINT `FK_resume_usr_link__update_id` FOREIGN KEY (`update_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_resume_usr_link__create_id` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '简历主记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_resume_project
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_resume_project`;
CREATE TABLE `xiaohe66_web_resume_project`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `resume_id` bigint(20) NOT NULL COMMENT '简历id',
  `logo` bigint(20) NOT NULL COMMENT '项目logo(usrFile表id)',
  `project_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目名称',
  `project_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_resume_project_link__create_id`(`create_id`) USING BTREE,
  INDEX `FK_resume_project_link__logo`(`logo`) USING BTREE,
  INDEX `FK_resume_project_link__resume_id`(`resume_id`) USING BTREE,
  INDEX `FK_resume_project_link__update_id`(`update_id`) USING BTREE,
  CONSTRAINT `FK_resume_project_link__update_id` FOREIGN KEY (`update_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_resume_project_link__create_id` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_resume_project_link__logo` FOREIGN KEY (`logo`) REFERENCES `xiaohe66_web_org_usr_file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_resume_project_link__resume_id` FOREIGN KEY (`resume_id`) REFERENCES `xiaohe66_web_resume_main` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '简历-项目经历' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_security_func
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_security_func`;
CREATE TABLE `xiaohe66_web_security_func`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `func_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名',
  `func_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限说明',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_func_name`(`func_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '功能权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xiaohe66_web_security_func
-- ----------------------------
INSERT INTO `xiaohe66_web_security_func` VALUES (1, 'admin:view', '查看后台管理');

-- ----------------------------
-- Table structure for xiaohe66_web_security_role
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_security_role`;
CREATE TABLE `xiaohe66_web_security_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色说明',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xiaohe66_web_security_role
-- ----------------------------
INSERT INTO `xiaohe66_web_security_role` VALUES (1, 'root', '系统管理员');
INSERT INTO `xiaohe66_web_security_role` VALUES (2, 'usr', '注册用户');

-- ----------------------------
-- Table structure for xiaohe66_web_security_role_func
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_security_role_func`;
CREATE TABLE `xiaohe66_web_security_role_func`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `func_id` bigint(20) NOT NULL COMMENT '功能权限id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_role_func_link__func`(`func_id`) USING BTREE,
  INDEX `FK_role_func_link__role`(`role_id`) USING BTREE,
  CONSTRAINT `FK_role_func_link__role` FOREIGN KEY (`role_id`) REFERENCES `xiaohe66_web_security_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_role_func_link__func` FOREIGN KEY (`func_id`) REFERENCES `xiaohe66_web_security_func` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-功能权限关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_security_usr_role
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_security_usr_role`;
CREATE TABLE `xiaohe66_web_security_usr_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usr_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_usr_role_link__role`(`role_id`) USING BTREE,
  INDEX `FK_usr_role_link__usr`(`usr_id`) USING BTREE,
  CONSTRAINT `FK_usr_role_link__usr` FOREIGN KEY (`usr_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_usr_role_link__role` FOREIGN KEY (`role_id`) REFERENCES `xiaohe66_web_security_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_sys_cfg
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_sys_cfg`;
CREATE TABLE `xiaohe66_web_sys_cfg`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `cfg_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '键',
  `cfg_val` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '值',
  `cfg_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sys_cfg_link__update_id`(`update_id`) USING BTREE,
  INDEX `FK_sys_cfg_usr_link__create_id`(`create_id`) USING BTREE,
  CONSTRAINT `FK_sys_cfg_usr_link__create_id` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_sys_cfg_link__update_id` FOREIGN KEY (`update_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xiaohe66_web_sys_cfg
-- ----------------------------
INSERT INTO `xiaohe66_web_sys_cfg` VALUES (1, 1, '2018-10-23 18:39:35.305151', 1, '2018-10-23 18:39:35.305151', 0, 'DEFAULT_ROLE_IDS', '1', '默认角色ids');
INSERT INTO `xiaohe66_web_sys_cfg` VALUES (2, 1, '2018-10-23 18:39:35.354294', 1, '2018-10-23 18:39:35.354294', 0, 'DEFAULT_ARTICLE_PID', '1', '文章系统分类pid');
INSERT INTO `xiaohe66_web_sys_cfg` VALUES (3, 1, '2018-10-23 18:39:35.402324', 1, '2018-10-23 18:39:35.402324', 0, 'DEFAULT_USR_IMG_FILE_ID', '1', '默认头像文件id');
INSERT INTO `xiaohe66_web_sys_cfg` VALUES (4, 1, '2018-10-23 18:39:35.491359', 1, '2018-10-23 18:39:35.491359', 0, 'XIAO_HE_USR_ID', '3', '小何的usrid');
INSERT INTO `xiaohe66_web_sys_cfg` VALUES (5, 1, '2018-10-26 15:45:57.567174', 1, '2018-10-26 15:45:57.567174', 0, 'SYS_EMAIL_HOST', '', '邮箱账号');
INSERT INTO `xiaohe66_web_sys_cfg` VALUES (6, 1, '2018-10-26 15:45:57.647719', 1, '2018-10-26 15:45:57.647719', 0, 'SYS_EMAIL_PWD', '', '邮箱密码');
INSERT INTO `xiaohe66_web_sys_cfg` VALUES (7, 1, '2018-10-26 15:45:57.694055', 1, '2018-10-26 15:45:57.694055', 0, 'SYS_EMAIL_SMTP_HOST', 'smtp.exmail.qq.com', NULL);
INSERT INTO `xiaohe66_web_sys_cfg` VALUES (8, 1, '2018-10-26 15:45:58.303576', 1, '2018-10-26 15:45:58.303576', 0, 'SYS_EMAIL_USR_NAME', 'xiaohe66网站管理员', NULL);

-- ----------------------------
-- Table structure for xiaohe66_web_text_article
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_text_article`;
CREATE TABLE `xiaohe66_web_text_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `is_publish` tinyint(4) NOT NULL DEFAULT 0 COMMENT '发表状态(0:未发表;1:已发表)',
  `secret_level` tinyint(4) NOT NULL DEFAULT 0 COMMENT '私密等级,对谁可见(0:公开,1:仅自己)',
  `sys_category_id` bigint(20) NOT NULL COMMENT '系统分类id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_article_category_link__sys_category_id`(`sys_category_id`) USING BTREE,
  INDEX `FK_article_usr_link__create_id`(`create_id`) USING BTREE,
  INDEX `FK_article_usr_link__update_id`(`update_id`) USING BTREE,
  CONSTRAINT `FK_article_usr_link__update_id` FOREIGN KEY (`update_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_article_category_link__sys_category_id` FOREIGN KEY (`sys_category_id`) REFERENCES `xiaohe66_web_comm_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_article_usr_link__create_id` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_text_article_category_link
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_text_article_category_link`;
CREATE TABLE `xiaohe66_web_text_article_category_link`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  `category_id` bigint(20) NOT NULL COMMENT '个人分类id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_article_category_link__article_id`(`article_id`) USING BTREE,
  INDEX `FK_article_category_link__category_id`(`category_id`) USING BTREE,
  CONSTRAINT `FK_article_category_link__category_id` FOREIGN KEY (`category_id`) REFERENCES `xiaohe66_web_text_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_article_category_link__article_id` FOREIGN KEY (`article_id`) REFERENCES `xiaohe66_web_text_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章，个人分类映射表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_text_article_log
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_text_article_log`;
CREATE TABLE `xiaohe66_web_text_article_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '当前操作者',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `log_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '操作类型(0:查看)',
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  `ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '查看者的ip地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_article__article_log`(`article_id`) USING BTREE,
  CONSTRAINT `FK_article__article_log` FOREIGN KEY (`article_id`) REFERENCES `xiaohe66_web_text_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_text_category
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_text_category`;
CREATE TABLE `xiaohe66_web_text_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `category_name` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名',
  `category_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类说明',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父级分类id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_category_category_link__id_pid`(`pid`) USING BTREE,
  INDEX `FK_category_usr_link__create_id`(`create_id`) USING BTREE,
  INDEX `FK_category_usr_link__update_id`(`update_id`) USING BTREE,
  CONSTRAINT `FK_category_usr_link__update_id` FOREIGN KEY (`update_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_category_category_link__id_pid` FOREIGN KEY (`pid`) REFERENCES `xiaohe66_web_text_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_category_usr_link__create_id` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章个人分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xiaohe66_web_text_message_board
-- ----------------------------
DROP TABLE IF EXISTS `xiaohe66_web_text_message_board`;
CREATE TABLE `xiaohe66_web_text_message_board`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新者id',
  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除,1:删除）',
  `usr_id` bigint(20) NOT NULL COMMENT '被留言的用户id',
  `msg` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_msg_board_link__usr_id`(`usr_id`) USING BTREE,
  INDEX `FK_msg_board_link_create_id`(`create_id`) USING BTREE,
  INDEX `FK_msg_board_link_update_id`(`update_id`) USING BTREE,
  CONSTRAINT `FK_msg_board_link_update_id` FOREIGN KEY (`update_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_msg_board_link_create_id` FOREIGN KEY (`create_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_msg_board_link__usr_id` FOREIGN KEY (`usr_id`) REFERENCES `xiaohe66_web_org_usr` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '留言信息表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
