/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.19 : Database - emall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`emall` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `emall`;

/*Table structure for table `admins` */

DROP TABLE IF EXISTS `admins`;

CREATE TABLE `admins` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='管理员';

/*Data for the table `admins` */

insert  into `admins`(`id`,`username`,`password`) values (1,'admin','tuShOfiBrA8+br7ENrMS8A=='),(2,'777','OkoxZRZ4yZgkgzx3IlxzKQ=='),(3,'1','ag3OB0a0vnwiWexZ+52IfQ==');

/*Table structure for table `carts` */

DROP TABLE IF EXISTS `carts`;

CREATE TABLE `carts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL DEFAULT '0' COMMENT '数量',
  `good_id` int NOT NULL COMMENT '商品ID',
  `user_id` int NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='购物车';

/*Data for the table `carts` */

insert  into `carts`(`id`,`amount`,`good_id`,`user_id`) values (6,1,6,3);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '封面',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '介绍',
  `spec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规格',
  `price` int NOT NULL DEFAULT '0' COMMENT '价格',
  `stock` int NOT NULL DEFAULT '0' COMMENT '库存',
  `sales` int NOT NULL DEFAULT '0' COMMENT '销量',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '详情',
  `type_id` int NOT NULL DEFAULT '0' COMMENT '分类ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品';

/*Data for the table `goods` */

insert  into `goods`(`id`,`cover`,`name`,`intro`,`spec`,`price`,`stock`,`sales`,`content`,`type_id`) values (1,'../upload/1-1.jpg','四川耙耙柑 酸甜甘香 柔嫩多汁','皮薄易剥 肉质软嫩','约500g',13,3,8,'',1),(2,'../upload/1-2.jpg','红心火龙果',NULL,'750g-1kg/两个',28,2,10,'',1),(3,'../upload/1-3.jpg','皇冠梨 松软多汁','果肉甘甜 细腻无渣','约500g',9,4,9,'',1),(4,'../upload/1-4.jpg','西州蜜瓜','果肉厚质 中心橘红','约1kg-1.5kg 一个',30,6,8,'',1),(5,'../upload/1-5.jpg','小台芒果 自然生长 生态种植','皮薄核小 果肉丰厚','约500g',26,0,15,'',1),(6,'../upload/1-6.jpg','栖霞苹果 爽脆清甜','肉质紧密 味道甘甜','约550g/2个',9,997,19,'',1),(7,'../upload/1-7.jpg','龙眼/桂圆 新鲜水果 汁多味甜','增强记忆 消除疲劳','约500g',18,10,7,'',1),(8,'../upload/1-8.jpg','广西百香果 香气浓郁 甜酸可口','果香浓郁 搭配蜂蜜更美味','2颗/份',5,10,8,'',1),(9,'../upload/1-9.jpg','美早樱桃 果大核小 饱满均匀','堪比车厘子的美味','约300g/盒',36,9,10,'',1),(10,'../upload/1-10.jpg','智利红提 珍贵品种 品质上等','紧实爆汁 果肉新鲜','约500g',18,6,14,'',1),(11,'../upload/1-11.jpg','尖椒 烧烤食材 新鲜蔬菜','农家种植 健康饮食','约300g',5,10,0,'',1),(12,'../upload/1-12.jpg','荷兰瓜 生态种植 自然生长','颜色翠绿，鲜嫩爽脆','约300g',5,10,0,'',1),(13,'../upload/1-13.jpg','小白菜 清脆鲜嫩 清甜回甘','农家种植 找回儿时味道','约500g',10,10,0,'',1),(14,'../upload/1-14.jpg','菠菜 口感鲜嫩 入口清香','不催熟 生长周期长','约300g',12,10,0,'',1),(15,'../upload/2-1.jpg','福成尚品前腱子',NULL,'1000g',99,10,0,'',2),(16,'../upload/2-2.jpg','牦牛精肉2斤+ 牦牛肋排2斤','自然生长','2kg',226,10,0,'',2),(17,'../upload/2-3.jpg','巴美草香猪后臀尖 原味原香','精致美味，解锁味觉记忆','约500g',32,10,0,'',2),(18,'../upload/2-4.jpg','农畉猪肉-猪脊骨 肉厚骨细','骨鲜髓足','约500g',13,10,0,'',2),(19,'../upload/2-5.jpg','一品江村黄鸡','味道鲜美，口感软嫩弹滑','约1kg',46,10,0,'',2),(20,'../upload/2-6.jpg','正大单冻鸡翅根 精选种鸡','富有弹性','约1kg',35,10,0,'',2),(21,'../upload/3-1.jpg','五梁红有机种植 大米','五常市有机大米','5kg',68,10,0,'',3),(23,'../upload/3-3.jpg','五得利强筋雪花小麦粉',NULL,'2.5kg',32,10,0,'',3),(24,'../upload/3-4.jpg','香满园美味富强小麦粉',NULL,'5kg',21,10,0,'',3),(25,'../upload/3-5.jpg','老农帝国 黑豆 自然成熟',NULL,'2.5kg',82,10,0,'',3),(26,'../upload/3-6.jpg','中畅佳禾 大麦 自然生长',NULL,'400g',59,10,0,'',3),(27,'../upload/4-1.jpg','百威啤酒 经典灌装 小麦醇正拉罐',NULL,'500ml*3',26,10,0,'',4),(28,'../upload/4-2.jpg','Perrier巴黎水天然含气矿泉水 开启生活新方式','天然气泡','330ml',9,10,0,'',4),(30,'../upload/4-4.jpg','北冰洋汽水','桔汁汽水330ml',NULL,5,10,0,'',4);

/*Table structure for table `items` */

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `price` int NOT NULL DEFAULT '0' COMMENT '购买时价格',
  `amount` int NOT NULL DEFAULT '0' COMMENT '购买数量',
  `order_id` int NOT NULL DEFAULT '0' COMMENT '订单ID',
  `good_id` int NOT NULL DEFAULT '0' COMMENT '商品ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单项';

/*Data for the table `items` */

insert  into `items`(`id`,`price`,`amount`,`order_id`,`good_id`) values (1,26,1,1,5),(2,30,1,1,4),(3,13,1,1,1),(4,23,1,2,29),(5,12,2,3,32),(6,9,2,4,6),(7,26,1,4,5),(8,9,4,5,6),(9,26,3,6,5),(10,18,1,6,10),(11,9,3,6,3),(12,9,1,6,6),(13,9,1,7,6),(14,9,1,8,6),(15,9,1,9,6),(16,26,1,10,5),(17,30,1,11,4),(18,9,1,12,3),(19,28,1,13,2),(20,13,1,14,1),(21,13,3,15,1),(22,9,1,16,3),(23,28,1,16,2),(24,18,1,16,10),(25,26,1,17,5),(26,26,1,18,5),(27,26,1,19,5),(28,9,1,20,3),(29,28,1,20,2),(30,13,1,20,1),(31,26,1,21,5),(32,28,3,22,2),(33,36,1,23,9),(34,13,1,24,1),(35,28,1,25,2),(36,18,1,26,10),(37,28,1,26,2),(38,30,1,27,4),(39,30,1,28,4),(40,18,1,29,10),(41,9,1,29,6),(42,9,1,30,6),(43,9,1,31,6);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `total` int NOT NULL DEFAULT '0' COMMENT '订单金额',
  `amount` int NOT NULL DEFAULT '0' COMMENT '商品总数',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '订单状态(1未付款/2已付款/3已发货/4已完成)',
  `paytype` tinyint NOT NULL DEFAULT '0' COMMENT '支付方式 (1微信/2支付宝)',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货人',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货地址',
  `systime` datetime DEFAULT NULL COMMENT '下单时间',
  `user_id` int NOT NULL DEFAULT '0' COMMENT '下单用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单';

/*Data for the table `orders` */

insert  into `orders`(`id`,`total`,`amount`,`status`,`paytype`,`name`,`phone`,`address`,`systime`,`user_id`) values (1,69,3,2,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区','2020-04-19 00:07:48',1),(2,23,1,4,2,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区','2020-05-25 15:54:33',1),(3,24,1,2,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区','2020-05-25 16:22:39',1),(4,44,2,2,1,'张三','13311112222','aaa','2020-05-29 10:38:19',5),(5,36,1,4,2,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区','2020-05-29 14:13:39',1),(6,132,4,2,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区','2020-09-08 11:57:57',1),(7,9,1,4,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区','2020-09-10 11:17:12',1),(8,9,1,4,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区','2020-09-14 10:07:39',1),(9,9,1,1,0,NULL,NULL,NULL,'2020-09-14 10:36:46',1),(10,26,1,2,2,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区','2020-09-14 10:57:16',1),(11,30,1,1,0,NULL,NULL,NULL,'2020-09-14 11:05:02',1),(12,9,1,1,0,NULL,NULL,NULL,'2020-09-14 11:20:42',1),(13,28,1,4,2,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区','2020-09-14 14:36:28',1),(14,13,1,4,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区','2020-09-15 10:52:20',1),(15,39,1,2,1,'1','10086','1','2020-09-16 09:13:55',6),(16,55,3,4,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区11','2020-09-23 11:44:36',1),(17,26,1,2,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区11','2020-09-27 16:03:30',1),(18,26,1,1,0,NULL,NULL,NULL,'2020-09-29 10:15:38',1),(19,26,1,1,0,NULL,NULL,NULL,'2020-10-12 09:17:50',1),(20,50,3,1,0,NULL,NULL,NULL,'2020-10-12 09:18:40',1),(21,26,1,1,0,NULL,NULL,NULL,'2020-10-12 10:46:49',1),(22,84,1,1,0,NULL,NULL,NULL,'2020-10-12 11:25:46',1),(23,36,1,1,0,NULL,NULL,NULL,'2020-10-13 18:33:00',1),(24,13,1,1,0,NULL,NULL,NULL,'2020-10-13 18:34:02',1),(25,28,1,1,0,NULL,NULL,NULL,'2020-10-13 19:12:39',1),(26,46,2,1,0,NULL,NULL,NULL,'2020-10-13 19:14:15',1),(27,30,1,3,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区11','2020-10-14 13:57:10',1),(28,30,1,1,0,NULL,NULL,NULL,'2020-10-14 14:58:54',1),(29,9,1,2,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区11','2020-11-27 16:42:48',1),(30,9,1,1,0,NULL,NULL,NULL,'2020-11-27 16:55:45',1),(31,9,1,3,1,'七哥宝宝宝宝宝宝宝','123456789','上海浦东新区11','2020-11-27 19:55:18',1);

/*Table structure for table `tops` */

DROP TABLE IF EXISTS `tops`;

CREATE TABLE `tops` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` tinyint NOT NULL DEFAULT '0' COMMENT '推荐类型(1今日推荐)',
  `good_id` int NOT NULL DEFAULT '0' COMMENT '商品ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='推荐商品';

/*Data for the table `tops` */

insert  into `tops`(`id`,`type`,`good_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6);

/*Table structure for table `types` */

DROP TABLE IF EXISTS `types`;

CREATE TABLE `types` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `num` int DEFAULT '0' COMMENT '排序号 (从小到大)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='分类';

/*Data for the table `types` */

insert  into `types`(`id`,`name`,`num`) values (1,'生态果蔬',1),(2,'肉禽蛋奶',2),(3,'米面粮油',3),(4,'休闲零食',4),(5,'饮料酒水-测试',-1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货人',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户';

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`name`,`phone`,`address`) values (1,'user','tuShOfiBrA8+br7ENrMS8A==','七哥宝宝宝宝宝宝宝','123456789','上海浦东新区11'),(2,'77','h1OIcgSG6ZNcQ+5L4t4e1A==','小宝宝','12315','黑龙江漠河'),(3,'88','tuShOfiBrA8+br7ENrMS8A==','大宝贝','10010','海南西沙群岛'),(4,'99','cMdK4vKHu03eruGlpzU4VQ==','哈哈哈','10086','新疆阿拉善'),(5,'zhangsan','HAMVRZRssPCADKqGjGWJtQ==','张三','13311112222','aaa'),(6,'1','iUOoOdMAl7FsB1Kig37hmg==','1','10086','1'),(7,'2','2','2','2','2'),(8,'3','3','3','3','3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
