CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_name` varchar(255) NOT NULL COMMENT '表名',
  `remark` varchar(5000) DEFAULT NULL,
  `delete_flg` int(1) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user_id` int(11) NOT NULL COMMENT '创建用户id',
  `create_user_name` varchar(20) NOT NULL,
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user_id` int(11) NOT NULL COMMENT '更新用户id',
  `update_user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE job_record ADD COLUMN project_id bigint(20)  DEFAULT NULL COMMENT '工程ID';
ALTER TABLE excel_data ADD COLUMN project_id bigint(20)  DEFAULT NULL COMMENT '工程ID';