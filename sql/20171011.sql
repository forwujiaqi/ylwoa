ALTER TABLE job_record ADD COLUMN status int(1)  DEFAULT NULL COMMENT '状态';
ALTER TABLE job_record ADD COLUMN reply varchar(500)  DEFAULT NULL COMMENT '回复';