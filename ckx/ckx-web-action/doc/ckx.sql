/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50704
Source Host           : localhost:3306
Source Database       : ckx

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2015-11-24 18:01:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `catelogs`
-- ----------------------------
DROP TABLE IF EXISTS `catelogs`;
CREATE TABLE `catelogs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `pid` int(11) DEFAULT NULL COMMENT '上级id',
  `url` varchar(512) DEFAULT NULL COMMENT '接口地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='影片分类';

-- ----------------------------
-- Records of catelogs
-- ----------------------------
INSERT INTO `catelogs` VALUES ('1', '喜剧片', null, 'http://www.66ys.tv/xijupian/');
INSERT INTO `catelogs` VALUES ('2', '动作片', null, 'http://www.66ys.tv/dongzuopian/');
INSERT INTO `catelogs` VALUES ('3', '爱情片', null, 'http://www.66ys.tv/aiqingpian/');
INSERT INTO `catelogs` VALUES ('4', '科幻片', null, 'http://www.66ys.tv/kehuanpian/');
INSERT INTO `catelogs` VALUES ('5', '恐怖片', null, 'http://www.66ys.tv/kongbupian/');
INSERT INTO `catelogs` VALUES ('6', '战争片', null, 'http://www.66ys.tv/zhanzhengpian/');
INSERT INTO `catelogs` VALUES ('7', '纪录片', null, 'http://www.66ys.tv/jilupian/');
INSERT INTO `catelogs` VALUES ('8', '剧情片', null, 'http://www.66ys.tv/bd/');
INSERT INTO `catelogs` VALUES ('9', '3D电影', null, 'http://www.66ys.tv/3ddy/');
INSERT INTO `catelogs` VALUES ('10', '国产剧', null, 'http://www.66ys.tv/dsj/');
INSERT INTO `catelogs` VALUES ('11', '港台剧', null, 'http://www.66ys.tv/dsj2/');
INSERT INTO `catelogs` VALUES ('12', '日韩剧', null, 'http://www.66ys.tv/dsj3/');
INSERT INTO `catelogs` VALUES ('13', '欧美剧', null, 'http://www.66ys.tv/dsj4/');
INSERT INTO `catelogs` VALUES ('14', '国配电影', null, 'http://www.66ys.tv/gy/');
INSERT INTO `catelogs` VALUES ('15', '综艺', null, 'http://www.66ys.tv/zy/');
INSERT INTO `catelogs` VALUES ('16', '动漫', null, 'http://www.66ys.tv/dmq/');

-- ----------------------------
-- Table structure for `live_interface`
-- ----------------------------
DROP TABLE IF EXISTS `live_interface`;
CREATE TABLE `live_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platform` varchar(20) DEFAULT NULL COMMENT '平台',
  `platform_cn` varchar(20) DEFAULT NULL COMMENT '平台（中文）',
  `game` varchar(20) DEFAULT NULL COMMENT '游戏',
  `game_cn` varchar(20) DEFAULT NULL COMMENT '游戏（中文）',
  `url` varchar(255) DEFAULT NULL COMMENT '接口地址',
  `type` char(1) DEFAULT '1' COMMENT '类型（0 分类接口   1 直播列表接口）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='直播接口地址';

-- ----------------------------
-- Records of live_interface
-- ----------------------------
INSERT INTO `live_interface` VALUES ('1', 'douyu', '斗鱼', 'dota2', 'dota2', 'http://www.douyutv.com/api/v1/live/3?aid=android&auth=5b29ce8060866f98416614e8476e14d5&client_sys=android&limit=200&offset=0&time=1444565894', '1');
INSERT INTO `live_interface` VALUES ('2', 'yy', '虎牙', '1', '英雄联盟', 'http://api.m.huya.com/channel/game/?pageSize=40&platform=android&page=1&version=3.2.2&game_id=1', '1');
INSERT INTO `live_interface` VALUES ('3', 'douyu', '斗鱼', null, null, 'http://www.douyu.com/api/v1/game?aid=ios&auth=b7f7ed444211e119b77363d995b21f07&client_sys=ios&time=1444640640', '0');
INSERT INTO `live_interface` VALUES ('4', 'yy', '虎牙', null, null, 'http://api.m.huya.com/live/all', '0');
INSERT INTO `live_interface` VALUES ('5', 'yy', '虎牙', '7', 'dota2', 'http://api.m.huya.com/channel/game/?pageSize=40&platform=android&page=1&version=3.2.2&game_id=7', '1');
INSERT INTO `live_interface` VALUES ('6', 'douyu', '斗鱼', 'lol', '英雄联盟', 'http://www.douyutv.com/api/v1/live/1?aid=android&auth=6cb9dda5f9c2e860630b3928fe5eea1d&client_sys=android&limit=20&offset=0&time=1444822908', '1');

-- ----------------------------
-- Table structure for `live_room`
-- ----------------------------
DROP TABLE IF EXISTS `live_room`;
CREATE TABLE `live_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` varchar(10) DEFAULT NULL COMMENT '房间号',
  `room_src` varchar(255) DEFAULT NULL COMMENT '小图标',
  `cate_id` varchar(5) DEFAULT NULL COMMENT '类别',
  `room_name` varchar(50) DEFAULT NULL,
  `show_status` char(1) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `show_time` bigint(20) DEFAULT NULL,
  `owner_uid` varchar(10) DEFAULT NULL,
  `specific_catalog` varchar(50) DEFAULT NULL,
  `specific_status` char(1) DEFAULT NULL,
  `vod_quality` char(1) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `online` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `game_url` varchar(255) DEFAULT NULL,
  `game_name` varchar(20) DEFAULT NULL,
  `fans` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of live_room
-- ----------------------------
INSERT INTO `live_room` VALUES ('1', '93608', 'http://staticlive.douyutv.com/upload/web_pic/8/93608_1510112202_thumb.jpg', '3', '秋季赛欧洲区:8点半NAVI vs MB', '1', '', '1444556145', '2906559', 'xiaofuxxx', '0', '0', 'Fairy_xiaofu', '8054', '/93608', '/directory/game/DOTA2', 'DOTA2', '4151');
INSERT INTO `live_room` VALUES ('2', '40287', 'http://staticlive.douyutv.com/upload/web_pic/7/40287_1510112201_thumb.jpg', '3', '秋季赛中国区wings vs FTD.B', '1', '', '1444564759', '911563', 'xtdota', '1', '0', 'Dota2啸天', '27118', '/xtdota', '/directory/game/DOTA2', 'DOTA2', '5027');
INSERT INTO `live_room` VALUES ('3', '58428', 'http://staticlive.douyutv.com/upload/web_pic/8/58428_1510112202_thumb.jpg', '3', '暴走狂魔，杀！！！', '1', '', '1444561078', '236231', '', '1', '0', 'yyfyyf', '398774', '/58428', '/directory/game/DOTA2', 'DOTA2', '397901');
INSERT INTO `live_room` VALUES ('4', '339715', 'http://staticlive.douyutv.com/upload/web_pic/5/339715_1510112203_thumb.jpg', '3', 'IG vs Newbee.Y 秋季赛', '1', '', '1444527070', '24166828', 'dcboys', '1', '0', '单车比DC讲道理', '259285', '/dcboys', '/directory/game/DOTA2', 'DOTA2', '81244');
INSERT INTO `live_room` VALUES ('5', '20360', 'http://staticlive.douyutv.com/upload/web_pic/0/20360_1510112203_thumb.jpg', '3', '冷冷直播 秋季邀请赛 ig vs ftd', '1', '', '1444539195', '498062', 'cold', '1', '0', 'Pc冷冷', '240609', '/cold', '/directory/game/DOTA2', 'DOTA2', '363895');
INSERT INTO `live_room` VALUES ('6', '120166', 'http://staticlive.douyutv.com/upload/web_pic/6/120166_1510112203_thumb.jpg', '3', '狗哥 海涛TongF vs CDEC.Y', '1', '', '1444528435', '5546417', 'NewBeeTV', '1', '0', 'NewBeeTV', '23063', '/NewBeeTV', '/directory/game/DOTA2', 'DOTA2', '20635');
INSERT INTO `live_room` VALUES ('7', '261291', 'http://staticlive.douyutv.com/upload/web_pic/1/261291_1510112202_thumb.jpg', '3', '回来了~~~  =。=', '1', '', '1444563915', '17004913', 'xiaoyu1', '1', '0', '电锯甜心', '16076', '/xiaoyu1', '/directory/game/DOTA2', 'DOTA2', '172828');
INSERT INTO `live_room` VALUES ('8', '212689', 'http://staticlive.douyutv.com/upload/web_pic/9/212689_1510112201_thumb.jpg', '3', '雪崩的刘海啊。。。。', '1', '', '1444567017', '2160778', 'qwerTail', '1', '0', '初逃酱', '15349', '/qwerTail', '/directory/game/DOTA2', 'DOTA2', '110378');
INSERT INTO `live_room` VALUES ('9', '234544', 'http://staticlive.douyutv.com/upload/web_pic/4/234544_1510112157_thumb.jpg', '3', '八点狗哥海涛解说CDEC.Y VS TF', '1', '', '1444554073', '1863977', 'imbatvhaitao', '1', '0', 'ImbaTV海涛', '10801', '/imbatvhaitao', '/directory/game/DOTA2', 'DOTA2', '54375');
INSERT INTO `live_room` VALUES ('10', '209225', 'http://staticlive.douyutv.com/upload/web_pic/5/209225_1510112200_thumb.jpg', '3', 'nada:低调の直播间', '1', '', '1444569303', '1878199', 'hayabusa', '1', '0', 'hayabusa_7', '10677', '/hayabusa', '/directory/game/DOTA2', 'DOTA2', '38304');
INSERT INTO `live_room` VALUES ('11', '52887', 'http://staticlive.douyutv.com/upload/web_pic/7/52887_1510112203_thumb.jpg', '3', '小刘直播间，比赛看累了屠龙去！', '1', '', '1444527636', '469111', 'xiaoliu', '1', '0', '小刘_qq', '9459', '/xiaoliu', '/directory/game/DOTA2', 'DOTA2', '82119');
INSERT INTO `live_room` VALUES ('12', '217344', 'http://staticlive.douyutv.com/upload/web_pic/4/217344_1510112158_thumb.jpg', '3', '10月11日法兰克福特锦赛中国预选赛', '1', '', '1443921373', '11684885', 'imba3', '1', '0', 'imbatv直播3', '7143', '/imba3', '/directory/game/DOTA2', 'DOTA2', '10791');
INSERT INTO `live_room` VALUES ('13', '319598', 'http://staticlive.douyutv.com/upload/web_pic/8/319598_1510112159_thumb.jpg', '3', '训练完了播一会!!', '1', '', '1444571705', '22679400', 'burning', '1', '0', 'burning徐志雷', '3221', '/burning', '/directory/game/DOTA2', 'DOTA2', '127635');
INSERT INTO `live_room` VALUES ('14', '19002', 'http://staticlive.douyutv.com/upload/web_pic/2/19002_1510112203_thumb.jpg', '3', '秋季联赛预选赛直播 综合频道2场同播', '1', '', '1442716378', '465904', 'imba', '1', '0', 'ImbaTV直播', '1974', '/imba', '/directory/game/DOTA2', 'DOTA2', '297405');
INSERT INTO `live_room` VALUES ('15', '83619', 'http://staticlive.douyutv.com/upload/web_pic/9/83619_1510112203_thumb.jpg', '3', '炼金术之毒瘤之术，定位赛出分！！', '1', '', '1444544068', '3514516', 'Carson875', '1', '0', '鱼塘救世主', '1488', '/Carson875', '/directory/game/DOTA2', 'DOTA2', '4092');
INSERT INTO `live_room` VALUES ('16', '125', 'http://staticlive.douyutv.com/upload/web_pic/5/125_1510112203_thumb.jpg', '3', 'Gamefy游戏风云', '1', '', '1444535568', '4314', '', '1', '0', 'G联赛专用房间', '1187', '/125', '/directory/game/DOTA2', 'DOTA2', '48705');
INSERT INTO `live_room` VALUES ('17', '74123', 'http://staticlive.douyutv.com/upload/web_pic/3/74123_1510112202_thumb.jpg', '3', '腹肌君.10点抽4K账号！还有不朽！快进', '1', '', '1444560624', '2072306', 'lamb93', '1', '0', '腹肌君', '1178', '/lamb93', '/directory/game/DOTA2', 'DOTA2', '1573');
INSERT INTO `live_room` VALUES ('18', '426215', 'http://staticlive.douyutv.com/upload/web_pic/5/426215_1510112202_thumb.jpg', '3', '〓来听歌吧！我就是鱼塘的鱼〓', '1', '', '1444564227', '24741227', '', '1', '0', 'sugizo123', '1163', '/426215', '/directory/game/DOTA2', 'DOTA2', '4');
INSERT INTO `live_room` VALUES ('19', '338877', 'http://staticlive.douyutv.com/upload/web_pic/7/338877_1510112157_thumb.jpg', '3', '【好鱼】开个小黑~', '1', '', '1444558208', '24048761', '', '0', '0', '徐好鱼', '1105', '/338877', '/directory/game/DOTA2', 'DOTA2', '10723');
INSERT INTO `live_room` VALUES ('20', '294494', 'http://staticlive.douyutv.com/upload/web_pic/4/294494_1510112203_thumb.jpg', '3', 'CC单排6500带水友开黑', '1', '', '1444364624', '19947476', '', '0', '0', '18978664704', '1085', '/294494', '/directory/game/DOTA2', 'DOTA2', '1098');
INSERT INTO `live_room` VALUES ('21', '424030', 'http://staticlive.douyutv.com/upload/web_pic/0/424030_1510112203_thumb.jpg', '3', '【3】激情10V10 搞起来', '1', '', '1444530220', '422514', '', '0', '0', '3__________', '1083', '/424030', '/directory/game/DOTA2', 'DOTA2', '1065');
INSERT INTO `live_room` VALUES ('22', '44697', 'http://staticlive.douyutv.com/upload/web_pic/7/44697_1510112203_thumb.jpg', '3', '常熟人打dota那叫一个凶', '1', '', '1444539181', '1006118', '', '1', '0', '叫我光神谢谢', '1077', '/44697', '/directory/game/DOTA2', 'DOTA2', '26');
INSERT INTO `live_room` VALUES ('23', '419302', 'http://staticlive.douyutv.com/upload/web_pic/2/419302_1510112155_thumb.jpg', '3', '浅唱：玩会儿dota2', '1', '', '1444569879', '2616810', 'Sakurachao', '1', '0', 'dbczxr', '1044', '/Sakurachao', '/directory/game/DOTA2', 'DOTA2', '1132');
INSERT INTO `live_room` VALUES ('24', '414497', 'http://staticlive.douyutv.com/upload/web_pic/7/414497_1510112203_thumb.jpg', '3', '（老朱）宝石TD，排行榜我上定了！', '1', '', '1444530124', '655953', '', '0', '0', '一个人的狂欢', '997', '/414497', '/directory/game/DOTA2', 'DOTA2', '137');
INSERT INTO `live_room` VALUES ('25', '252274', 'http://staticlive.douyutv.com/upload/web_pic/4/252274_1510112159_thumb.jpg', '3', 'Zack Dota2 6666666', '1', '', '1444571414', '16246399', 'wasabiz', '1', '0', 'PinzakTV', '961', '/wasabiz', '/directory/game/DOTA2', 'DOTA2', '527');
INSERT INTO `live_room` VALUES ('26', '183729', 'http://staticlive.douyutv.com/upload/web_pic/9/183729_1510112159_thumb.jpg', '3', '（老狼）赌石双傻 顺风装B 逆风装死', '1', '', '1444571727', '1565671', '', '0', '0', '狼来了的故事', '933', '/183729', '/directory/game/DOTA2', 'DOTA2', '3183');
INSERT INTO `live_room` VALUES ('27', '85792', 'http://staticlive.douyutv.com/upload/web_pic/2/85792_1510112157_thumb.jpg', '3', '校长：6300 走心的直播间', '1', '', '1444490771', '2293200', '', '0', '0', '原諒我這一生不羈放縱', '857', '/85792', '/directory/game/DOTA2', 'DOTA2', '1119');
INSERT INTO `live_room` VALUES ('28', '229131', 'http://staticlive.douyutv.com/upload/web_pic/1/229131_1510112158_thumb.jpg', '3', 'WCA2015世界电子竞技大赛', '1', '', '1443598347', '13566725', 'WCA2015', '1', '0', 'WCA世界电子竞技大赛', '755', '/WCA2015', '/directory/game/DOTA2', 'DOTA2', '20062');
INSERT INTO `live_room` VALUES ('29', '62371', 'http://staticlive.douyutv.com/upload/web_pic/1/62371_1510112157_thumb.jpg', '3', '卡尔狂暴屠杀！', '1', '', '1444556034', '1323705', 'm31', '1', '0', 'lovejunedull', '582', '/m31', '/directory/game/DOTA2', 'DOTA2', '179');
INSERT INTO `live_room` VALUES ('30', '414742', 'http://staticlive.douyutv.com/upload/web_pic/2/414742_1510112155_thumb.jpg', '3', '开播第21天，免费帮水友上分渡劫。', '1', '', '1444569806', '19456639', '', '0', '0', 'kingofcontrol', '494', '/414742', '/directory/game/DOTA2', 'DOTA2', '142');

-- ----------------------------
-- Table structure for `location`
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `longitude` varchar(20) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(20) DEFAULT NULL COMMENT '纬度',
  `location` varchar(50) DEFAULT NULL COMMENT '地图坐标（经纬度全）',
  `loc_time` datetime DEFAULT NULL COMMENT '定位时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO `location` VALUES ('1', '2', 'admin', '106.476725', '29.607961', '106.476725,29.607961', '2015-11-23 15:46:02', null, null);
INSERT INTO `location` VALUES ('2', '2', 'admin', '106.475097', '29.606148', '106.475097,29.606148', '2015-11-23 16:46:17', null, null);

-- ----------------------------
-- Table structure for `movie`
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '影片名称',
  `icon` varchar(512) DEFAULT NULL COMMENT '小图标',
  `catelog` int(11) DEFAULT NULL COMMENT '类别',
  `cTime` datetime DEFAULT NULL COMMENT '创建时间',
  `content` text COMMENT '具体详情',
  `detailurl` varchar(512) DEFAULT NULL,
  `pub_date` date DEFAULT NULL COMMENT '上架日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqueIndex` (`name`,`catelog`) USING BTREE,
  KEY `cateIndex` (`catelog`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='影片';

-- ----------------------------
-- Records of movie
-- ----------------------------

-- ----------------------------
-- Table structure for `picture`
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `map_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture
-- ----------------------------

-- ----------------------------
-- Table structure for `picture_catelogs`
-- ----------------------------
DROP TABLE IF EXISTS `picture_catelogs`;
CREATE TABLE `picture_catelogs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture_catelogs
-- ----------------------------

-- ----------------------------
-- Table structure for `picture_map`
-- ----------------------------
DROP TABLE IF EXISTS `picture_map`;
CREATE TABLE `picture_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `catelogs` int(11) DEFAULT NULL,
  `context` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`url`,`catelogs`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture_map
-- ----------------------------

-- ----------------------------
-- Table structure for `picture_map_relation`
-- ----------------------------
DROP TABLE IF EXISTS `picture_map_relation`;
CREATE TABLE `picture_map_relation` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture_map_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `config_id` varchar(50) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_index` (`key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='系统设置';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('31', 'XXTV', 'HOST', 'http://www.xxtv.com', '熊熊TV HOST', '', '2015-11-11 17:57:31', '2015-11-18 14:02:57');

-- ----------------------------
-- Table structure for `sys_menus`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_name` varchar(30) NOT NULL COMMENT '菜单名称',
  `remark` varchar(30) DEFAULT NULL COMMENT '描述',
  `is_parent` tinyint(1) NOT NULL COMMENT '是否为父菜单',
  `parent_id` int(11) NOT NULL COMMENT '上级菜单编号',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `status` int(11) NOT NULL COMMENT '状态',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
INSERT INTO `sys_menus` VALUES ('1', '系统管理', '', '1', '0', '', '1', '100');
INSERT INTO `sys_menus` VALUES ('2', '用户管理', null, '0', '1', 'menus/user_mgr.html', '1', '1');
INSERT INTO `sys_menus` VALUES ('3', '角色管理', null, '0', '1', 'menus/role_mgr.html', '1', '2');
INSERT INTO `sys_menus` VALUES ('4', '菜单管理', null, '0', '1', 'menus/menu_mgr.html', '1', '3');
INSERT INTO `sys_menus` VALUES ('205', '数据字典', '', '0', '1', 'config/index', '1', null);
INSERT INTO `sys_menus` VALUES ('227', '分类管理', '', '1', '0', '', '1', null);
INSERT INTO `sys_menus` VALUES ('228', '分类管理', '', '0', '227', 'catelogs/index', '1', null);
INSERT INTO `sys_menus` VALUES ('229', '影片管理', '', '1', '0', '', '1', null);
INSERT INTO `sys_menus` VALUES ('230', '影片管理', '', '0', '229', 'movie/index', '1', null);
INSERT INTO `sys_menus` VALUES ('231', '熊熊鹰眼', '', '1', '0', '', '1', null);
INSERT INTO `sys_menus` VALUES ('232', '熊熊鹰眼', '', '0', '231', 'location/index', '1', null);

-- ----------------------------
-- Table structure for `sys_posts`
-- ----------------------------
DROP TABLE IF EXISTS `sys_posts`;
CREATE TABLE `sys_posts` (
  `post_id` int(11) NOT NULL COMMENT '岗位编号',
  `post_name` varchar(30) NOT NULL COMMENT '岗位名称',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_posts
-- ----------------------------
INSERT INTO `sys_posts` VALUES ('1', '超级管理员');
INSERT INTO `sys_posts` VALUES ('2', '商务主管');
INSERT INTO `sys_posts` VALUES ('3', '媒介主管');
INSERT INTO `sys_posts` VALUES ('4', '运营主管');
INSERT INTO `sys_posts` VALUES ('5', '产品总监');
INSERT INTO `sys_posts` VALUES ('6', '产品经理');
INSERT INTO `sys_posts` VALUES ('7', '研发主管');
INSERT INTO `sys_posts` VALUES ('8', '研发组长');
INSERT INTO `sys_posts` VALUES ('9', '研发工程师');
INSERT INTO `sys_posts` VALUES ('10', '测试主管');
INSERT INTO `sys_posts` VALUES ('11', '测试工程师');
INSERT INTO `sys_posts` VALUES ('12', '人事主管');
INSERT INTO `sys_posts` VALUES ('13', '管理员');

-- ----------------------------
-- Table structure for `sys_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `parent_role` int(11) NOT NULL COMMENT '上级角色',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('1', '超级管理员', '0');
INSERT INTO `sys_roles` VALUES ('2', '管理员', '1');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_menu_role_key` (`menu_id`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menus` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('2', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '205');
INSERT INTO `sys_role_menu` VALUES ('2', '205');
INSERT INTO `sys_role_menu` VALUES ('1', '227');
INSERT INTO `sys_role_menu` VALUES ('2', '227');
INSERT INTO `sys_role_menu` VALUES ('1', '228');
INSERT INTO `sys_role_menu` VALUES ('2', '228');
INSERT INTO `sys_role_menu` VALUES ('1', '229');
INSERT INTO `sys_role_menu` VALUES ('2', '229');
INSERT INTO `sys_role_menu` VALUES ('1', '230');
INSERT INTO `sys_role_menu` VALUES ('2', '230');
INSERT INTO `sys_role_menu` VALUES ('1', '231');
INSERT INTO `sys_role_menu` VALUES ('2', '231');
INSERT INTO `sys_role_menu` VALUES ('1', '232');
INSERT INTO `sys_role_menu` VALUES ('2', '232');

-- ----------------------------
-- Table structure for `sys_users`
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `nick` varchar(30) DEFAULT NULL COMMENT '昵称',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT 'Email',
  `status` int(11) NOT NULL COMMENT '状态 0-停用 1-正常',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `qq` bigint(20) DEFAULT NULL COMMENT '用户QQ',
  `telephone` varchar(30) DEFAULT NULL COMMENT '用户电话',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('2', '系统管理员', 'admin', '96E79218965EB72C92A549DD5A330112', 'huyax1990@163.com', '1', '2015-11-11 20:38:32', '2013-11-18 10:58:23', null, null);
INSERT INTO `sys_users` VALUES ('187', '新产品', 'sst1', '96E79218965EB72C92A549DD5A330112', 'shishuting@yingmob.com', '1', '2015-03-06 16:25:50', '2015-03-03 15:06:36', '1111111', '15002334444');
INSERT INTO `sys_users` VALUES ('189', '国内', 'sst2', '96E79218965EB72C92A549DD5A330112', '727282576@qq.com', '1', '2015-03-06 16:24:13', '2015-03-03 17:40:10', '12341234', '13212341234');
INSERT INTO `sys_users` VALUES ('190', 'tuaner', 'tuaner', '96E79218965EB72C92A549DD5A330112', 'zhengxueya@yingmob.com', '1', null, '2015-06-12 16:26:19', '11111111', '11111111111');
INSERT INTO `sys_users` VALUES ('192', '1212', '1212', '96E79218965EB72C92A549DD5A330112', '121@1.com', '1', null, '2015-06-15 15:10:11', '123121413', '12112');
INSERT INTO `sys_users` VALUES ('193', '熊熊TV', 'xxtv', '96E79218965EB72C92A549DD5A330112', 'huyax1990@163.com', '1', '2015-11-11 14:37:38', '2015-11-11 14:05:02', '387614798', '13648301954');

-- ----------------------------
-- Table structure for `sys_user_post`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`post_id`),
  KEY `FK_post_user_key` (`post_id`) USING BTREE,
  CONSTRAINT `sys_user_post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_post_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `sys_posts` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('2', '1');
INSERT INTO `sys_user_post` VALUES ('192', '1');
INSERT INTO `sys_user_post` VALUES ('193', '1');
INSERT INTO `sys_user_post` VALUES ('187', '11');
INSERT INTO `sys_user_post` VALUES ('189', '11');
INSERT INTO `sys_user_post` VALUES ('190', '11');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_role_user_key` (`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('2', '1');
INSERT INTO `sys_user_role` VALUES ('193', '2');

-- ----------------------------
-- Table structure for `tv`
-- ----------------------------
DROP TABLE IF EXISTS `tv`;
CREATE TABLE `tv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(5000) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tv
-- ----------------------------
INSERT INTO `tv` VALUES ('1', '湖南卫视', '<embed width=\"900\" height=\"630\" src=\"http://player.hz.letv.com/live.swf\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" align=\"middle\" play=\"true\" loop=\"true\" scale=\"noscale\" wmode=\"opaque\" devicefont=\"false\" bgcolor=\"#000000\" name=\"www_player\" menu=\"true\" allowscriptaccess=\"always\" allowfullscreen=\"true\" salign=\"TL\" flashvars=\"streamid=ws_hunanwsHD_1800&amp;cid=4&amp;gslb=http://live.gslb.letv.com/&amp;autoplay=true&amp;rate=800&amp;typeFrom=letv_live_hao123live&amp;ark=100&amp;cid=4&amp;ch=letv_live_sports_ws_hunanwsHD_1800_1800&amp;usewhitelist=0\" type=\"application/x-shockwave-flash\">', '湖南电视台卫星频道，通称为湖南卫视 推出了《快乐大本营》、《玫瑰之约》、《晚间新闻》、《新青年》、《音乐不断》《超级女声》《快乐男声》等一系列名牌栏目，在中国国内外产生了广泛影响，确立了娱乐传媒的强势品牌地位。被中国媒体誉为“快乐旋风”、“玫瑰花香”的“湖南电视现象”，一时间风靡大江南北 ，是湖南电视台第一套节目，于1997年1月1日正式通过亚洲2号卫星传送，并更名为湖南电视台卫星频道。是目前中国国内比较大的几个地方电视台之一。湖南卫视收视率连续几年居中国地方卫视第一、全国总收视第四的成绩（前三位均为央视）。尤其是2009年湖南卫视全年收视率位居全国第二，创下新高！湖南卫视已成为省级卫视中连续4年收视第一和单频道广告创收第一的频道。目前湖南卫视在中国各卫视中的影响力与知名度非常广泛。湖南卫视的台标原意为“鱼米之乡”，外形像一条鱼的鱼头，中间的空位如同一粒大米，象征湖南是鱼米之乡。而椭圆的形状和橙色的颜色，像极了一个芒果。所以观众们给它取了个绰号“芒果台”，湖南卫视似乎接受了这个绰号，其下属网站金鹰网的网络电视台则叫“芒果网络电视台”。 2009年5月20日，其国际频道开播，呼号为湖南卫视国际频道，口号为“快乐全球华人”(Be Happy Be Chinese)。', '卫视');
INSERT INTO `tv` VALUES ('2', '凤凰卫视', '<embed width=\"900\" height=\"630\" src=\"http://player.letvcdn.com/p/201505/05/17/newplayer/LivePlayer.swf?ark=100&amp;usewhitelist=0&amp;streamid=fenghuang\" type=\"application/x-shockwave-flash\">', '凤凰卫视中文台在线直播，1996年3月31日，凤凰卫视中文台伴随着凤凰卫视有限公司的成立，同步启播。\r\n\r\n  凤凰卫视中文台以“不断创新、超越自己”为节目制作目标－－《时事直通车》、《凤凰早班车》、《相聚凤凰台》、《鲁豫有约》、《名人面对面》、《一虎一席谈》、《娱乐大风暴》、《完全时尚手册》、《小莉看世界》、《时事辩论会》、《锵锵三人行》、《时事开讲》以及不同品种的剧集，不单成为观众喜爱的名牌节目，亦使凤凰卫视成为电视传媒潮流的领导者。凤凰卫视透过亚卫三号S卫星，24小时以普通话向中国大陆、香港、台湾、日本、印度尼西亚、马来西亚、菲律宾、新加坡、南韩、泰国、澳洲、新西兰、中东地区播出，凤凰卫视中文台在全世界130多个国家与地区落地。凤凰卫视中文台节目包括中外电视的代表作，并集新闻资讯、体育、音乐、电视剧于一身。凤凰卫视中文台的节目取向，主要代表亚洲华人地区内各种社会文化动态及观众的生活方式和口味，以新鲜的题材，多样的形式，清新的风格，新奇的内容，引领观众走向一个崭新的视听空间。\r\n\r\n全球华人首选的普通话综合频道－－凤凰卫视中文台是普通话综合频道的最佳选择，节目种类包罗万象，提供流行而质优的节目，包括戏剧、清谈、时事、体育和时尚节目。作为中国人的桥梁，凤凰卫视中文台以其国际级水平称誉，并为全球观众带来中国大陆的电视节目。\r\n\r\n凤凰的新闻及时事节目受到中国大陆领导人、商界领袖和高收入人士的肯定。 ', '卫视');
INSERT INTO `tv` VALUES ('3', '浙江卫视', '', '浙江卫视，中国蓝！ 1994年1月1日，浙江电视台第一套节目(4频道)开始利用卫星传送，有效覆盖全国及周边40多个国家和地区的20亿人口，并为全国95%以上的有线台转播，拥有相对固定观众一亿五千万，其中一亿左右为城市观众。2009年，浙江卫视入选中国世界纪录协会2009年度中国覆盖率最高的省级卫视，创造了省级卫视覆盖率中国之最。浙江卫视还在美国中文电视台、斯柯拉电视台、日本的福井放送、宫城放送、韩国光州文化放送和香港无线电视台、香港有线电视台先后开设窗口,传送节目，影响波及海内外，对促进世界了解浙江、浙江走向世界发挥了积极作用。\r\n浙江电视台有一支专业素质良好的电视从业队伍，具有中、高级职称的职工占全台职工总数的50%左右。拥有数字化程度较高的电视制作和技术保障服务系统，能同时进行7个频道的节目传输、发送和播出，实现跨国多点双向传送。电视制作和播出水平在国内同行中名列前茅。\r\n\r\n　　近十年来，浙江电视台的新闻节目在全国性评比中连连获奖，先后四次摘取中国新闻最高奖——“中国新闻奖”一等奖；获中国电视特等奖、一等奖二十多个，曾被广电部评为“先进集体”。浙江电视台在社教、文艺节目的摄制上，注重江南文化特色，面向全国，跨出国门，关注人类生命主题，拍摄了大量国际性专题，如 “三极”系列——《南极与人类》、《北极随想》、《跨越地球之颠》；完成了“三看”系列——《环球看香港》、《话说澳门》、《跨海看台湾》，以及《闯荡世界的浙江人》等“闯荡系列”。这些专题片的摄制、播出，在海内外引起巨大反响。浙江电视台拍摄的电视剧《鲁迅》、《华罗庚》、《马寅初》、《新闻启示录》、《女记者的话外音》，电视戏剧片《秋瑾》等，在全国播出后也获得了广泛好评。', '卫视');
INSERT INTO `tv` VALUES ('4', '江苏卫视', '<embed width=\"900\" height=\"630\"  src=\"http://player.letvcdn.com/p/201502/02/15/newplayer/LivePlayer.swf\" type=\"application/x-shockwave-flash\" allowfullscreen=\"true\" allowscriptaccess=\"always\" bgcolor=\"#000000\" wmode=\"Opaque\" flashvars=\"streamid=jiangsuHD_1300&amp;cid=4&amp;gslb=http://live.gslb.letv.com/&amp;autoplay=true&amp;rate=800&amp;typeFrom=letv_live_hao123live&amp;ark=100&amp;cid=4&amp;ch=letv_live_sports_jiangsuHD_1300&amp;usewhitelist=0\">', '江苏省电视台基地位于南京鼓楼广场东南角，处在三条城市干道的交叉路转角处，是南京市的黄金地带之一。它与深受群众欢迎的\"市民广场\"隔街相望，是市区车流、人流交通和市民商业、文化、休闲活动的集中地区。此项工程总体规划上将分四期完成。本方案为中标方案，主要完成了这四期工程的总平面规划以及二期工程前期方案设计。二期工程主要功能包括演播、制作及播放区，办公区、商业区、车库、设备及后勤服务区。\r\n\r\n设计上特别重视结合环境，充分利用建筑三面临街的有利地形，利用建筑群体的内外空间，在电视台周围设计了六个城市广场：人行文化广场、传媒文化广场、商业广场、剧院广场、交通广场、后勤广场，并将六个广场串连起来，为市民提供开阔的室外活动空间。\r\n\r\n办公设在塔楼内，塔楼设置在基地东北角，总高为168米，占据城市景观的焦点位置。塔顶采取逐步退台的办法，构成了塔顶节节上升，生气盎然的建筑形态。为了面向最好的城市景观--玄武湖风景区，特将塔楼顶部沿对角线切成斜面，使塔楼更显挺拔。总体造型简洁明快，突出了现代电视传媒高效、高科技和开放的特点。\r\n\r\n江苏卫视频道是新闻综合频道，江苏省广播电视总台的主频道。是宣传江苏的主阵地，具有强烈时代特色和鲜明苏派文化特色。2004年，江苏卫视频道以其独特的情感定位脱颖于全国省级卫视频道，08年上半年收视排名成长最快的卫星频道，全国卫视排名第二，其率先覆盖全国31个省会直辖市，并覆盖全国337个地市级单位中的333个只有4个城市没有覆盖，地市综合覆盖率达到98%，即将达到100%，牢牢把握全国市场。\r\n\r\n江苏传媒实力，传递江苏地方热点的新闻综合频道，在节目中充分强调新闻的时效性，政治性，益智综艺节目的独创性，整体版块编排的系统性，将浓郁的江苏地方特色与现代化传媒特征相融合，成为面向全国、全世界展示江苏经济、文化发展的窗口。 \r\n\r\n以现代感、传媒感、资讯感及江苏文化风味形成江苏现代电视传媒的鲜明特色，频道以新闻版块、益智版块、电视剧版块的合理划分，清晰地将新闻、综艺、电视剧三项最受观众喜爱的栏目形式，呈现给全国观众，成为全国卫视频道中备受瞩目的电视新生力量，拥有与江苏经济文化大省地位相符的传媒位置。', '卫视');
INSERT INTO `tv` VALUES ('8', 'CCTV-1', '', 'CCTV-1 综合频道 于1958年9月2日正式开播，通过中星6B，鑫诺三号，亚太6号和中星9号卫星覆盖全国，全天24小时滚动播出，是中国中央电视台开办最早、影响力最大的综合频道。2009年9月28日起，央视一套全面改版，实现高清标清同步播出，打造成“央视王牌精品综合频道”。CCTV-1作为党和政府的喉舌，在党的宣传工作中有着极其重要的地位，承担着宣传党的方针政策，引导舆论，传播社会主义先进文化的重要使命。\r\n\r\nCCTV-1 中国中央电视台的旗舰频道和精品频道，是中国覆盖面最广、受众人群最多、品牌效应最高、影响力最大的国家级频道。全国入户率高达99.61%，收视人口过13亿，城市入户率则高达100％！全天候平均收视率及收视份额均位居全国电视台第一！中国最优秀的电视剧首播平台和最佳传播平台。\r\n\r\n新版央视一套制定的目标为“坚持新闻主线，形成与CCTV新闻频道差异化，同时汇聚央视全台的精品栏目，在节目编排上形成形态各异的品牌形象”。 中央电视台综合频道\r\n\r\n央视一套还汇聚和精选优秀电视剧作品，打造CCTV-1的精品电视剧播出平台；同时针对不同观众需求，更加突出服务性，实施周末差异化、长假特殊化编排。除在每晚19∶55的黄金剧场外，再增开一档22：30档的电视剧首播剧场；在每周六和周日的13∶10还特别安排《周末大片影院》放映一部优秀影片。', 'CCTV');
INSERT INTO `tv` VALUES ('9', 'CCTV-2', null, 'CCTV-5中央体育频道是国内创办最早、规模最大、拥有世界众多顶级赛事国内独家报道权的专业体育频道，于1995年1月1日正式开播，通过亚太1A卫星覆盖全国，每天平均播出16小时以上。\r\n　　中央电视台体育频道自2005年9月5日起全面改版，实现全天24小时播出。新改版的体育频道突出赛事、加强新闻、改进编排，在原有的早中晚三档体育新闻栏目《早安中国》、《体坛快讯》和《体育世界》基础上，新增两个半小时左右的新闻栏目，分别在早上7点和晚间24点播出。另外，体育频道将增加体育赛事的转播和国际顶级赛事的重播，形成赛事转播和新闻报道两条主线的风格。\r\n　　体育频道以新世纪、新形象为宗旨，让中国亿万观众与世界体育同行，分享世界杯狂欢盛宴，亲历奥运会精彩全程，体验欧洲杯群雄逐鹿。体育频道目前开设的主要栏目有《体育新闻》、《体育世界》、《足球之夜》、《天下足球》、《顶级赛事》、《精彩F1》、《NBA赛场》、《巅锋时刻》、《全明星猜想》、《早安中国》、《棋牌乐》等，内容包括国内外重大赛事的现场直播、体育热点问题追踪报道、全民健身及娱乐、体育知识普及教育等。体育频道每年把三大赛事、六项联赛、一千二百场赛事直播给广大电视观众，深受体育爱好者的喜爱。\r\n\r\n    播出栏目有\r\n　　体育新闻栏目：\r\n　　《体育世界》《体育新闻》《体坛快讯》《体育晨报》\r\n　　体育专题栏目：\r\n　　《篮球公园》《赛车时代》《天下足球》《足球之夜》《体育人间》《我的奥林匹克》《奥运传奇》《精彩F1》《运动空间》\r\n　　其他栏目：\r\n　　《武林大会》《城市之间》《拳霸天下》', 'CCTV');
INSERT INTO `tv` VALUES ('10', '北京卫视', '<embed width=\"900\" height=\"630\" id=\"cmp\" type=\"application/x-shockwave-flash\" src=\"../cmp.swf?ver=201406301600\"  allowscriptaccess=\"always\" allowfullscreen=\"true\" bgcolor=\"#000000\" wmode=\"Opaque\" flashvars=\"context_menu=0&amp;auto_play=1&amp;buffer_time=0&amp;video_max=0&amp;skin=skins/tvlive2.swf&amp;lists=file%2Flist%2Easp%3Fcode%3D6v3P2v3PRvffRXVJRXoJ6vxSJvCYjvVTx8sLgD7mgMv1\">', '　 北京卫视原名BTV-1，是北京电视台的首个频道。北京卫视于1998年上星，该频道覆盖全国29个省市和亚洲部分国家地区，以新闻节目为主体，同时汇集全台精品节目，全面展示北京电视台的整体风貌，立足北京，面向全国，走向世界。\r\n      经国家广电总局批准，2009年1月1日起，北京电视台将启用全新的台标设计和频道形象。北京卫视新台标新北京电视台台标设计以北京电视台的英文缩写BTV（BeijingTelevision)为  主要设计元素，图形简约，含义简洁，更具现代感、更加国际化。在主体色彩上，北京电视台台标采用故宫的宫墙红色和汉白玉的白色为主色调，其庄重、现代、国际化的整体感与北京电视台自身形象无隙契合。\r\n      新台标系统的使用，将体现北京城市品格，真诚服务首都观众，围绕国际化、品牌化的概念，全面凸显北京电视台整体形象的改变和提升。\r\n频道定位以高尚的文化情趣为主基调，秉承大家风范，追求文化品位，依托首都地缘优势，整合资源，打造具有全国影响力的节目平台。', '卫视');

-- ----------------------------
-- Function structure for `GET_ROLE_CHILD`
-- ----------------------------
DROP FUNCTION IF EXISTS `GET_ROLE_CHILD`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GET_ROLE_CHILD`(rootId INT) RETURNS varchar(1000) CHARSET utf8
    COMMENT '#查询该角色的所有子角色'
BEGIN
	DECLARE sTemp VARCHAR (1000);
	DECLARE sTempChd VARCHAR (1000);
	SET sTemp = '$';
	SET sTempChd = cast(rootId AS CHAR);
	WHILE sTempChd IS NOT NULL DO
		SET sTemp = concat(sTemp, ',', sTempChd);
		SELECT group_concat(role_id) INTO sTempChd FROM sys_roles WHERE FIND_IN_SET(parent_role, sTempChd) > 0;
	END
	WHILE;
	RETURN sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `GET_ROLE_PARENT`
-- ----------------------------
DROP FUNCTION IF EXISTS `GET_ROLE_PARENT`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GET_ROLE_PARENT`(rootId INT) RETURNS varchar(1000) CHARSET utf8
    COMMENT '#查询该角色的所有父角色'
BEGIN
	DECLARE sTemp VARCHAR (1000);
	DECLARE sTempChd VARCHAR (1000);
	SET sTemp = '$';
	SET sTempChd = cast(rootId AS CHAR);
	WHILE sTempChd IS NOT NULL DO
		SET sTemp = concat(sTemp, ',', sTempChd);
		SELECT group_concat(parent_role) INTO sTempChd FROM sys_roles WHERE FIND_IN_SET(role_id, sTempChd) > 0;
	END
	WHILE;
	RETURN sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `GET_ROLE_SECTION`
-- ----------------------------
DROP FUNCTION IF EXISTS `GET_ROLE_SECTION`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GET_ROLE_SECTION`(type INT,rootId INT) RETURNS varchar(1000) CHARSET utf8
    COMMENT '#type = 0 查询管理员级别，type > 0 查询主管级别'
BEGIN
	DECLARE sTemp VARCHAR (1000);
	DECLARE sTempChd VARCHAR (1000);
	SET sTemp = '';
	SET sTempChd = cast(rootId AS CHAR);
	IF(type = 0) THEN
		SELECT A.TREE INTO sTemp FROM (SELECT GET_ROLE_CHILD(role_id) AS TREE FROM sys_roles WHERE parent_role = 1) A WHERE CONCAT(A.tree, ',') LIKE CONCAT('%,',sTempChd,',%');
			ELSE
		SELECT A.TREE INTO sTemp FROM (SELECT GET_ROLE_CHILD(role_id) AS TREE FROM sys_roles WHERE parent_role IN ( SELECT role_id FROM sys_roles WHERE parent_role = 1 )) A WHERE CONCAT(A.tree, ',') LIKE CONCAT('%,',sTempChd,',%');
	END IF;
	RETURN sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `GET_ROLE_TREE`
-- ----------------------------
DROP FUNCTION IF EXISTS `GET_ROLE_TREE`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GET_ROLE_TREE`(rootId INT) RETURNS varchar(1000) CHARSET utf8
    COMMENT '#查询该角色的所有子父角色'
BEGIN
	DECLARE sTemp VARCHAR (1000);
	DECLARE sTempChd VARCHAR (1000);
	SET sTemp = '$';
  #parent
  SET sTempChd = cast(rootId AS CHAR);
	WHILE sTempChd IS NOT NULL DO
		SET sTemp = concat(sTemp, ',', sTempChd);
		SELECT group_concat(parent_role) INTO sTempChd FROM sys_roles WHERE FIND_IN_SET(role_id, sTempChd) > 0;
	END
	WHILE;
	#child
	SET sTempChd = cast(rootId AS CHAR);
	WHILE sTempChd IS NOT NULL DO
		SET sTemp = concat(sTemp, ',', sTempChd);
		SELECT group_concat(role_id) INTO sTempChd FROM sys_roles WHERE FIND_IN_SET(parent_role, sTempChd) > 0;
	END
	WHILE;
	RETURN sTemp;
END
;;
DELIMITER ;
