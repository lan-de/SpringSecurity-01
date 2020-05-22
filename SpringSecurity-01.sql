/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.11 : Database - security
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`security` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `security`;

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `permission_id` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_url` varchar(765) DEFAULT NULL,
  `permission_code` varchar(765) DEFAULT NULL,
  `permission_desc` varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `permission_status` varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`permission_id`,`permission_url`,`permission_code`,`permission_desc`,`permission_status`) values ('11111','/user/hello','user.hello','USER','0'),('142354325345','/getNews','getnews','ADMIN,USER','0'),('25d3a1861c3443c49be383b145f1576d','/role/list','role.list','ADMIN','0'),('364245783567456345436','/permission/getAll','permission.getall','ADMIN,USER','0'),('567456342543','/user/test','user.test',NULL,'0');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编号',
  `role_code` varchar(25) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色代码',
  `role_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色描述',
  `role_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '角色状态，0正常，-1删除',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

/*Data for the table `role` */

insert  into `role`(`role_id`,`role_code`,`role_name`,`role_desc`,`role_status`) values ('3ac96c378b2b4df1a9d96dda9ef5f7f1','ROLE_ADMIN','管理员','系统管理员','0'),('c1867457d1c34cd7a705eb5700daccef','ROLE_USER','用户','普通用户','0');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_permission_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限编号',
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编号',
  `permission_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编号',
  `role_permission_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '角色权限状态',
  PRIMARY KEY (`role_permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色权限表';

/*Data for the table `role_permission` */

insert  into `role_permission`(`role_permission_id`,`role_id`,`permission_id`,`role_permission_status`) values ('0cc1b1428fe945dcb52d95bc1d1b1b86','3ac96c378b2b4df1a9d96dda9ef5f7f1','25d3a1861c3443c49be383b145f1576d','0'),('234353454','c1867457d1c34cd7a705eb5700daccef','11111','0'),('3464528735878653425','3ac96c378b2b4df1a9d96dda9ef5f7f1','142354325345','0'),('436347657367','3ac96c378b2b4df1a9d96dda9ef5f7f1','364245783567456345436','0'),('456456754653434','c1867457d1c34cd7a705eb5700daccef','364245783567456345436','0'),('535683567456433464576','c1867457d1c34cd7a705eb5700daccef','142354325345','0');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编号',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `user_phone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户手机号码\r\n',
  `user_password` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `user_last_login_time` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户最近一次登录时间',
  `user_create_time` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户注册时间',
  `user_status` int(1) NOT NULL DEFAULT '0' COMMENT '用户状态，0正常，-1删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`user_name`,`user_phone`,`user_password`,`user_last_login_time`,`user_create_time`,`user_status`) values ('1601e658096048f48225c50f7e879a02','冯钒荣','15159302973','$2a$10$tDsFVTyEPebE2PSeKBoduu0Hokl2LUrQD5yBgBsPoDmuIZe8eW6.y','1554780930910','1554692301493',0),('2e405a31b084486b996c27c96e6c1d8c','冯初','15295939085','$2a$10$tDsFVTyEPebE2PSeKBoduu0Hokl2LUrQD5yBgBsPoDmuIZe8eW6.y','1554797136702','1554692301492',0),('4dd2cd7c843542469cc3d5e2764aa35e','秦罨','13914971140','$2a$10$tDsFVTyEPebE2PSeKBoduu0Hokl2LUrQD5yBgBsPoDmuIZe8eW6.y',NULL,'1554692301493',0),('53fae163e43442ea8493c1558bcb2e10','褚蠲','13068973140','$2a$10$tDsFVTyEPebE2PSeKBoduu0Hokl2LUrQD5yBgBsPoDmuIZe8eW6.y',NULL,'1554692301493',0),('72600c1533f3491e9da0ff6f3899d9b6','秦钞','13287831741','$2a$10$tDsFVTyEPebE2PSeKBoduu0Hokl2LUrQD5yBgBsPoDmuIZe8eW6.y','1554797042895','1554692301490',0),('88ad850b79794f1f94fb3dfad4644224','郑殡操','13331333893','$2a$10$tDsFVTyEPebE2PSeKBoduu0Hokl2LUrQD5yBgBsPoDmuIZe8eW6.y',NULL,'1554692301492',0),('c50b9b75ced043629dfc5a1b4a5dcb69','尤儿','13520809386','$2a$10$tDsFVTyEPebE2PSeKBoduu0Hokl2LUrQD5yBgBsPoDmuIZe8eW6.y',NULL,'1554692301493',0),('ddc2117aabfa4c4f9f4e632a7f9c696f','钱讲支','13538888864','$2a$10$tDsFVTyEPebE2PSeKBoduu0Hokl2LUrQD5yBgBsPoDmuIZe8eW6.y',NULL,'1554692301493',0),('e683f274ba534f969758ef636788876d','卫孳苫','13917242800','$2a$10$tDsFVTyEPebE2PSeKBoduu0Hokl2LUrQD5yBgBsPoDmuIZe8eW6.y',NULL,'1554692301492',0),('fc75ce7d00dd4319b65ba616a408a1e9','卫渊','15572008602','$2a$10$tDsFVTyEPebE2PSeKBoduu0Hokl2LUrQD5yBgBsPoDmuIZe8eW6.y',NULL,'1554692301492',0);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户角色编号',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编号',
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编号',
  `user_role_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '用户角色状态，0正常，-1删除',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

/*Data for the table `user_role` */

insert  into `user_role`(`user_role_id`,`user_id`,`role_id`,`user_role_status`) values ('123456','fc75ce7d00dd4319b65ba616a408a1e9','3ac96c378b2b4df1a9d96dda9ef5f7f1','0'),('316hfgh24575421ht246j654y5234645','1601e658096048f48225c50f7e879a02','c1867457d1c34cd7a705eb5700daccef','0'),('31b9adde41804b70bf7c76921ddb2fbc','53fae163e43442ea8493c1558bcb2e10','c1867457d1c34cd7a705eb5700daccef','0'),('523de48205634300afcb1dc4ce705a87','c50b9b75ced043629dfc5a1b4a5dcb69','3ac96c378b2b4df1a9d96dda9ef5f7f1','0'),('6731e8195ce24f729509314ee93c49c3','e683f274ba534f969758ef636788876d','3ac96c378b2b4df1a9d96dda9ef5f7f1','0'),('6caaa88bbf704992ba868807a37aeb3a','ddc2117aabfa4c4f9f4e632a7f9c696f','c1867457d1c34cd7a705eb5700daccef','0'),('6cc02688ce0f42ef8aae9b5f09ff9771','72600c1533f3491e9da0ff6f3899d9b6','3ac96c378b2b4df1a9d96dda9ef5f7f1','0'),('90321c12b44844268f13019d89efe2e5','2e405a31b084486b996c27c96e6c1d8c','c1867457d1c34cd7a705eb5700daccef','0'),('92409d5d6da44d70a74dfa15609eef29','fc75ce7d00dd4319b65ba616a408a1e9','c1867457d1c34cd7a705eb5700daccef','0'),('939bccfcc0554ab9b9fad605d9714cc2','88ad850b79794f1f94fb3dfad4644224','c1867457d1c34cd7a705eb5700daccef','0'),('c38813f45a4c4087a712a09e5700c6e1','4dd2cd7c843542469cc3d5e2764aa35e','3ac96c378b2b4df1a9d96dda9ef5f7f1','0'),('c44e3f15f5d74e28959144c4bd1dc34d','1601e658096048f48225c50f7e879a02','3ac96c378b2b4df1a9d96dda9ef5f7f1','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
