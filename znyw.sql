/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.23
Source Server Version : 50640
Source Host           : 192.168.1.23:3306
Source Database       : znyw

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-08-30 11:19:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL COMMENT '上级部门ID，一级部门为0',
  `org_type` int(11) NOT NULL DEFAULT '1' COMMENT '类型：1默认，1 本单位，2 合作单位',
  `org_code` varchar(50) DEFAULT NULL,
  `name` varchar(100) NOT NULL COMMENT '部门名称',
  `short_name` varchar(100) NOT NULL,
  `org_desc` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `delflag` int(11) NOT NULL DEFAULT '1' COMMENT '是否可以删除标记：0 不能删除， 1可以删除， 默认 1',
  `useflag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `user_id_create` bigint(20) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_id_mod` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('6', '0', '1', '001', '阜宁县公安局', 'hh', 'aaa', '1', '1', '1', null, '2018-08-24 10:01:32', '2018-08-24 10:01:32', null);
INSERT INTO `sys_dept` VALUES ('7', '6', '1', '0011', '信通科', 'hh', 'aaa', '1', '1', '1', null, '2018-08-24 10:01:33', '2018-08-24 10:01:33', null);
INSERT INTO `sys_dept` VALUES ('9', '7', '1', '0013', '维护单位2', 'hh', 'aaa', '3', '1', '1', null, '2018-08-24 10:01:34', '2018-08-24 10:01:34', null);
INSERT INTO `sys_dept` VALUES ('16', '9', '1', '0014', 'why', 'hh', 'aaa', '3', '1', '1', null, '2018-08-24 10:01:35', '2018-08-24 10:01:35', null);
INSERT INTO `sys_dept` VALUES ('26', '0', '2', 'string', 'string', 'string', 'string', '0', '1', '0', null, '2018-08-26 15:04:22', '2018-08-26 15:04:22', null);
INSERT INTO `sys_dept` VALUES ('27', '0', '2', 'string', 'string', 'string', 'string', '0', '1', '0', null, '2018-08-26 15:04:25', '2018-08-26 15:04:25', null);
INSERT INTO `sys_dept` VALUES ('29', '0', '2', 'string', 'string', 'string', 'string', '0', '1', '0', null, '2018-08-26 15:04:39', '2018-08-26 15:04:39', null);
INSERT INTO `sys_dept` VALUES ('30', '0', '2', 'string', '成浩', 'string', 'string', '0', '1', '0', '1', '2018-08-23 18:31:35', '2018-08-23 18:32:55', '1');
INSERT INTO `sys_dept` VALUES ('31', '0', '2', 'string', 'string', '1111', 'string', '0', '1', '0', '1', '2018-08-26 15:04:36', '2018-08-26 15:04:36', null);
INSERT INTO `sys_dept` VALUES ('32', '0', '2', 'string', 'string', '1111', 'string', '0', '1', '0', '1', '2018-08-26 15:04:32', '2018-08-26 15:04:32', null);
INSERT INTO `sys_dept` VALUES ('33', '0', '1', '11', '成浩', 'tets', '这里是和电科技', '1', '1', '0', '1', '2018-08-26 15:04:29', '2018-08-26 15:04:29', null);
INSERT INTO `sys_dept` VALUES ('54', '6', '1', '1', '阜宁县妇联', '妇联', '妇联', '2', '1', '0', '1', '2018-08-26 14:51:17', '2018-08-26 14:51:17', null);
INSERT INTO `sys_dept` VALUES ('55', '6', '1', '1', '妇联', '妇联', '111', '1', '1', '1', '1', '2018-08-26 14:54:23', '2018-08-26 14:54:23', null);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `grp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户在ID',
  `grp_type` int(11) NOT NULL DEFAULT '1' COMMENT '用户组类型：1 普通用户组 2 工单处理组',
  `grp_name` varchar(100) NOT NULL COMMENT '用户组名称',
  `grp_desc` varchar(100) DEFAULT NULL COMMENT '用户组描述',
  `dept_id` bigint(20) DEFAULT NULL,
  `user_id_create` bigint(20) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `useflag` int(11) NOT NULL DEFAULT '1' COMMENT '使用标记： 1 使用， 0 不使用',
  `user_id_mod` bigint(20) DEFAULT NULL,
  `delflag` int(11) NOT NULL DEFAULT '1' COMMENT '删除标记：0 不能删除， 1可以删除， 默认 1',
  PRIMARY KEY (`grp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户组';

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES ('2', '0', 'why', 'hahha', null, '1', '2018-08-22 10:58:52', null, '1', null, '1');
INSERT INTO `sys_group` VALUES ('3', '1', 'why1', 'hhaha', null, '1', '2018-08-24 16:38:27', null, '1', null, '1');

-- ----------------------------
-- Table structure for sys_grp_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_grp_role`;
CREATE TABLE `sys_grp_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grp_id` int(11) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `useflag` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户组权限表';

-- ----------------------------
-- Records of sys_grp_role
-- ----------------------------
INSERT INTO `sys_grp_role` VALUES ('2', '2', '1', '1');
INSERT INTO `sys_grp_role` VALUES ('3', '4', '68', '1');

-- ----------------------------
-- Table structure for sys_grp_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_grp_user`;
CREATE TABLE `sys_grp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grp_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `showorder` int(11) NOT NULL,
  `useflag` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户组包含用户表';

-- ----------------------------
-- Records of sys_grp_user
-- ----------------------------
INSERT INTO `sys_grp_user` VALUES ('3', '2', '1', '1', '1');
INSERT INTO `sys_grp_user` VALUES ('4', '3', '1', '1', '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id_create` bigint(20) DEFAULT NULL,
  `user_id_mod` bigint(20) DEFAULT NULL,
  `delflag` int(11) DEFAULT NULL,
  `useflag` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '首页', '', '', '0', '', '0', '2017-08-09 22:49:47', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('2', '0', '我的工作台', '', '', '0', '', '1', '2017-08-09 22:55:15', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('3', '2', '我的工单', null, null, '1', '', '1', '2017-08-09 23:06:55', '2017-08-14 14:13:43', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('4', '0', '运维管理', '', '', '0', '', '3', '2017-08-10 14:12:11', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('5', '4', '工单管理', '', '', '0', '', '1', '2017-08-10 14:13:19', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('6', '5', '维修工单', '', '', '1', '', '1', '2017-08-14 10:51:35', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('7', '4', '服务评价', '', '', '1', '', '2', '2017-08-14 10:52:06', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('8', '0', '监控管理', null, '', '0', null, '5', '2017-08-14 10:52:24', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('9', '8', '监测对象管理', '', '', '0', '', '1', '2017-08-14 10:56:37', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('10', '9', '监测对象管理', '', '', '1', '', '1', '2017-08-14 10:59:32', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('11', '9', '监测指标管理', '', '', '1', '', '2', '2017-08-14 10:59:56', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('12', '9', '阈值规则定义', '', '', '1', '', '3', '2017-08-14 11:00:26', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('13', '9', '维护域管理', '', '', '1', '', '4', '2017-08-14 17:27:18', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('14', '8', '远程管控', null, '', '0', null, '2', '2017-08-14 17:27:43', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('15', '14', '智能运维终端管控', '', '', '1', '', '1', '2017-08-14 17:28:34', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('16', '8', '告警管理', '', '', '0', '', '3', '2017-08-14 22:11:53', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('17', '16', '实时告警', null, '', '1', null, '1', '2017-08-14 22:30:22', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('18', '16', '历史告警', null, '', '1', null, '2', '2017-08-14 22:30:43', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('19', '16', '维护期策略', null, '', '1', null, '3', '2017-08-14 22:31:02', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('20', '8', '凭证管理', '', '', '0', '', '4', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('21', '20', '智能运管终端凭证', '', '', '1', '', '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('22', '0', '资产管理', '', '', '0', '', '6', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('23', '22', '资源管理', '', '', '0', '', '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('24', '23', '资源登记', '', '', '1', '', '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('25', '23', '资源查询', '', '', '1', null, '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('26', '22', '资产管理', '', '', '0', '', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('27', '26', '资产登记', '', '', '1', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('28', '26', '资产维保管理', '', '', '1', null, '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('29', '26', '资产报废管理', '', '', '1', null, '3', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('30', '26', '资产查询', '', '', '1', null, '4', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('31', '0', '报表管理', '', '', '0', null, '7', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('32', '31', '资源统计', '', '', '0', '', '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('33', '31', '资产统计', '', '', '0', '', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('34', '31', '运维统计', '', '', '0', '', '3', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('35', '31', '监测周期统计', '', '', '0', '', '4', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('36', '0', '运维考核', '', '', '0', null, '8', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('37', '36', '维护单位考核', '', '', '1', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('38', '0', '移动运维管理', '', '', '0', null, '9', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('39', '38', '移动终端管理', '', '', '1', '', '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('40', '0', '系统管理', '', '', '0', '', '10', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('41', '40', '组织单位管理', '', '', '0', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('42', '41', '组织单位', '', '', '1', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('43', '40', '安全管理', '', '', '0', '', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('44', '43', '用户管理', '', '', '1', '', '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('45', '43', '用户组管理', '', '', '1', '', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('46', '43', '角色管理', '', '', '1', '', '3', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('47', '40', '考核管理', '', '', '0', '', '3', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('48', '47', 'SLA管理', '', '', '1', '', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('49', '40', '通知管理', null, null, '0', null, '4', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('50', '49', '通知策略管理', null, null, '1', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('51', '16', '故障维护策略', null, null, '1', null, '4', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  `showorder` int(11) DEFAULT '0' COMMENT '显示顺序',
  `delflag` int(11) DEFAULT '1' COMMENT '删除标记：0 不能删除， 1可以删除， 默认 1',
  `user_id_mod` bigint(20) DEFAULT NULL,
  `useflag` int(11) DEFAULT '1' COMMENT '使用标记： 1 使用， 0 不使用',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级用户角色', 'admin', '超级123', '2', '2017-08-12 00:43:52', '2017-08-12 19:14:59', null, '1', '1', '1');
INSERT INTO `sys_role` VALUES ('59', '普通用户', 'user', '基本用户权限', null, null, null, null, '1', null, '1');
INSERT INTO `sys_role` VALUES ('61', 'string', '1', null, '1', '2018-08-24 09:55:57', '2018-08-24 09:55:57', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('62', 'why', '1', null, '1', '2018-08-24 09:59:57', '2018-08-24 09:59:57', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('63', '称号i', null, '1', '1', '2018-08-24 13:47:55', '2018-08-24 13:47:55', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('64', '成浩', null, '123', '1', '2018-08-24 13:48:45', '2018-08-24 13:48:45', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('65', '超级用户角色', null, '拥有最高权限', '1', '2018-08-24 16:36:29', '2018-08-24 16:36:29', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('66', '超级用户角色', null, '拥有最高权限', '1', '2018-08-24 16:36:34', '2018-08-24 16:36:34', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('67', 'test', null, 'test2', '1', '2018-08-24 16:45:16', '2018-08-24 16:45:16', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('68', 'test', null, 'test2', '1', '2018-08-24 16:45:31', '2018-08-24 16:45:31', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('69', '成浩test', null, '123213', '1', '2018-08-24 17:21:40', '2018-08-24 17:21:40', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('70', '1', null, '1', '1', '2018-08-26 10:31:52', '2018-08-26 10:31:52', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('71', '2', null, '2', '1', '2018-08-26 10:31:59', '2018-08-26 10:31:59', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('72', '3', null, '3', '1', '2018-08-26 10:32:16', '2018-08-26 10:32:16', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('73', '4', null, '4', '1', '2018-08-26 10:32:22', '2018-08-26 10:32:22', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('74', '5', null, '5', '1', '2018-08-26 10:32:27', '2018-08-26 10:32:27', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('75', '6', null, '6', '1', '2018-08-26 10:32:44', '2018-08-26 10:32:44', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('76', '6', null, '6', '1', '2018-08-26 10:32:44', '2018-08-26 10:32:44', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('77', '6', null, '6', '1', '2018-08-26 10:32:44', '2018-08-26 10:32:44', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('78', '6', null, '6', '1', '2018-08-26 10:32:44', '2018-08-26 10:32:44', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('79', '6', null, '6', '1', '2018-08-26 10:32:44', '2018-08-26 10:32:44', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('80', '6', null, '6', '1', '2018-08-26 10:32:44', '2018-08-26 10:32:44', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('81', '6', null, '6', '1', '2018-08-26 10:32:45', '2018-08-26 10:32:45', '0', '1', null, '0');
INSERT INTO `sys_role` VALUES ('82', '6', null, '6', '1', '2018-08-26 10:32:45', '2018-08-26 10:32:45', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('83', '6', null, '6', '1', '2018-08-26 10:32:45', '2018-08-26 10:32:45', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('84', '7', null, '7', '1', '2018-08-26 10:38:52', '2018-08-26 10:38:52', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('85', '8', null, '8', '1', '2018-08-26 10:38:59', '2018-08-26 10:38:59', '0', '1', null, '1');
INSERT INTO `sys_role` VALUES ('86', '99', null, '8', '1', '2018-08-26 10:39:08', '2018-08-26 10:39:08', '0', '1', null, '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `useflag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3443 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('367', '1', '1', null);
INSERT INTO `sys_role_menu` VALUES ('368', '1', '32', null);
INSERT INTO `sys_role_menu` VALUES ('369', '44', '33', null);
INSERT INTO `sys_role_menu` VALUES ('370', '44', '34', null);
INSERT INTO `sys_role_menu` VALUES ('371', '44', '35', null);
INSERT INTO `sys_role_menu` VALUES ('372', '44', '28', null);
INSERT INTO `sys_role_menu` VALUES ('373', '44', '29', null);
INSERT INTO `sys_role_menu` VALUES ('374', '44', '30', null);
INSERT INTO `sys_role_menu` VALUES ('375', '44', '38', null);
INSERT INTO `sys_role_menu` VALUES ('376', '44', '4', null);
INSERT INTO `sys_role_menu` VALUES ('377', '44', '27', null);
INSERT INTO `sys_role_menu` VALUES ('378', '45', '38', null);
INSERT INTO `sys_role_menu` VALUES ('379', '46', '3', null);
INSERT INTO `sys_role_menu` VALUES ('380', '46', '20', null);
INSERT INTO `sys_role_menu` VALUES ('381', '46', '21', null);
INSERT INTO `sys_role_menu` VALUES ('382', '46', '22', null);
INSERT INTO `sys_role_menu` VALUES ('383', '46', '23', null);
INSERT INTO `sys_role_menu` VALUES ('384', '46', '11', null);
INSERT INTO `sys_role_menu` VALUES ('385', '46', '12', null);
INSERT INTO `sys_role_menu` VALUES ('386', '46', '13', null);
INSERT INTO `sys_role_menu` VALUES ('387', '46', '14', null);
INSERT INTO `sys_role_menu` VALUES ('388', '46', '24', null);
INSERT INTO `sys_role_menu` VALUES ('389', '46', '25', null);
INSERT INTO `sys_role_menu` VALUES ('390', '46', '26', null);
INSERT INTO `sys_role_menu` VALUES ('391', '46', '15', null);
INSERT INTO `sys_role_menu` VALUES ('392', '46', '2', null);
INSERT INTO `sys_role_menu` VALUES ('393', '46', '6', null);
INSERT INTO `sys_role_menu` VALUES ('394', '46', '7', null);
INSERT INTO `sys_role_menu` VALUES ('598', '50', '38', null);
INSERT INTO `sys_role_menu` VALUES ('632', '38', '42', null);
INSERT INTO `sys_role_menu` VALUES ('737', '51', '38', null);
INSERT INTO `sys_role_menu` VALUES ('738', '51', '39', null);
INSERT INTO `sys_role_menu` VALUES ('739', '51', '40', null);
INSERT INTO `sys_role_menu` VALUES ('740', '51', '41', null);
INSERT INTO `sys_role_menu` VALUES ('741', '51', '4', null);
INSERT INTO `sys_role_menu` VALUES ('742', '51', '32', null);
INSERT INTO `sys_role_menu` VALUES ('743', '51', '33', null);
INSERT INTO `sys_role_menu` VALUES ('744', '51', '34', null);
INSERT INTO `sys_role_menu` VALUES ('745', '51', '35', null);
INSERT INTO `sys_role_menu` VALUES ('746', '51', '27', null);
INSERT INTO `sys_role_menu` VALUES ('747', '51', '28', null);
INSERT INTO `sys_role_menu` VALUES ('748', '51', '29', null);
INSERT INTO `sys_role_menu` VALUES ('749', '51', '30', null);
INSERT INTO `sys_role_menu` VALUES ('750', '51', '1', null);
INSERT INTO `sys_role_menu` VALUES ('1064', '54', '53', null);
INSERT INTO `sys_role_menu` VALUES ('1095', '55', '2', null);
INSERT INTO `sys_role_menu` VALUES ('1096', '55', '6', null);
INSERT INTO `sys_role_menu` VALUES ('1097', '55', '7', null);
INSERT INTO `sys_role_menu` VALUES ('1098', '55', '3', null);
INSERT INTO `sys_role_menu` VALUES ('1099', '55', '50', null);
INSERT INTO `sys_role_menu` VALUES ('1100', '55', '49', null);
INSERT INTO `sys_role_menu` VALUES ('1101', '55', '1', null);
INSERT INTO `sys_role_menu` VALUES ('1856', '53', '28', null);
INSERT INTO `sys_role_menu` VALUES ('1857', '53', '29', null);
INSERT INTO `sys_role_menu` VALUES ('1858', '53', '30', null);
INSERT INTO `sys_role_menu` VALUES ('1859', '53', '27', null);
INSERT INTO `sys_role_menu` VALUES ('1860', '53', '57', null);
INSERT INTO `sys_role_menu` VALUES ('1861', '53', '71', null);
INSERT INTO `sys_role_menu` VALUES ('1862', '53', '48', null);
INSERT INTO `sys_role_menu` VALUES ('1863', '53', '72', null);
INSERT INTO `sys_role_menu` VALUES ('1864', '53', '1', null);
INSERT INTO `sys_role_menu` VALUES ('1865', '53', '7', null);
INSERT INTO `sys_role_menu` VALUES ('1866', '53', '55', null);
INSERT INTO `sys_role_menu` VALUES ('1867', '53', '56', null);
INSERT INTO `sys_role_menu` VALUES ('1868', '53', '62', null);
INSERT INTO `sys_role_menu` VALUES ('1869', '53', '15', null);
INSERT INTO `sys_role_menu` VALUES ('1870', '53', '2', null);
INSERT INTO `sys_role_menu` VALUES ('1871', '53', '61', null);
INSERT INTO `sys_role_menu` VALUES ('1872', '53', '20', null);
INSERT INTO `sys_role_menu` VALUES ('1873', '53', '21', null);
INSERT INTO `sys_role_menu` VALUES ('1874', '53', '22', null);
INSERT INTO `sys_role_menu` VALUES ('2084', '56', '68', null);
INSERT INTO `sys_role_menu` VALUES ('2085', '56', '60', null);
INSERT INTO `sys_role_menu` VALUES ('2086', '56', '59', null);
INSERT INTO `sys_role_menu` VALUES ('2087', '56', '58', null);
INSERT INTO `sys_role_menu` VALUES ('2088', '56', '51', null);
INSERT INTO `sys_role_menu` VALUES ('2089', '56', '50', null);
INSERT INTO `sys_role_menu` VALUES ('2090', '56', '49', null);
INSERT INTO `sys_role_menu` VALUES ('2243', '48', '72', null);
INSERT INTO `sys_role_menu` VALUES ('2247', '63', '-1', null);
INSERT INTO `sys_role_menu` VALUES ('2248', '63', '84', null);
INSERT INTO `sys_role_menu` VALUES ('2249', '63', '85', null);
INSERT INTO `sys_role_menu` VALUES ('2250', '63', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2251', '63', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2252', '64', '84', null);
INSERT INTO `sys_role_menu` VALUES ('2253', '64', '89', null);
INSERT INTO `sys_role_menu` VALUES ('2254', '64', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2255', '64', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2256', '64', '86', null);
INSERT INTO `sys_role_menu` VALUES ('2257', '64', '85', null);
INSERT INTO `sys_role_menu` VALUES ('2258', '65', '89', null);
INSERT INTO `sys_role_menu` VALUES ('2259', '65', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2260', '65', '86', null);
INSERT INTO `sys_role_menu` VALUES ('2262', '67', '48', null);
INSERT INTO `sys_role_menu` VALUES ('2263', '68', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2264', '68', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2265', '69', '89', null);
INSERT INTO `sys_role_menu` VALUES ('2266', '69', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2267', '69', '86', null);
INSERT INTO `sys_role_menu` VALUES ('2268', '69', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2269', '69', '85', null);
INSERT INTO `sys_role_menu` VALUES ('2270', '69', '84', null);
INSERT INTO `sys_role_menu` VALUES ('2271', '70', '85', null);
INSERT INTO `sys_role_menu` VALUES ('2272', '70', '89', null);
INSERT INTO `sys_role_menu` VALUES ('2273', '70', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2274', '70', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2275', '70', '86', null);
INSERT INTO `sys_role_menu` VALUES ('2276', '70', '84', null);
INSERT INTO `sys_role_menu` VALUES ('2277', '71', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2278', '72', '59', null);
INSERT INTO `sys_role_menu` VALUES ('2279', '73', '48', null);
INSERT INTO `sys_role_menu` VALUES ('2280', '74', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2281', '74', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2282', '75', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2283', '75', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2284', '76', '85', null);
INSERT INTO `sys_role_menu` VALUES ('2285', '76', '89', null);
INSERT INTO `sys_role_menu` VALUES ('2286', '76', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2287', '76', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2288', '76', '86', null);
INSERT INTO `sys_role_menu` VALUES ('2289', '76', '84', null);
INSERT INTO `sys_role_menu` VALUES ('2292', '78', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2293', '78', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2294', '78', null, null);
INSERT INTO `sys_role_menu` VALUES ('2295', '78', null, null);
INSERT INTO `sys_role_menu` VALUES ('2296', '78', null, null);
INSERT INTO `sys_role_menu` VALUES ('2308', '80', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2309', '80', '86', null);
INSERT INTO `sys_role_menu` VALUES ('2310', '80', '-1', null);
INSERT INTO `sys_role_menu` VALUES ('2311', '80', '84', null);
INSERT INTO `sys_role_menu` VALUES ('2312', '80', '85', null);
INSERT INTO `sys_role_menu` VALUES ('2328', '79', '72', null);
INSERT INTO `sys_role_menu` VALUES ('2329', '79', '48', null);
INSERT INTO `sys_role_menu` VALUES ('2330', '79', '77', null);
INSERT INTO `sys_role_menu` VALUES ('2331', '79', '84', null);
INSERT INTO `sys_role_menu` VALUES ('2332', '79', '89', null);
INSERT INTO `sys_role_menu` VALUES ('2333', '79', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2334', '79', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2335', '79', '86', null);
INSERT INTO `sys_role_menu` VALUES ('2336', '79', '85', null);
INSERT INTO `sys_role_menu` VALUES ('2337', '79', '-1', null);
INSERT INTO `sys_role_menu` VALUES ('2338', '77', '89', null);
INSERT INTO `sys_role_menu` VALUES ('2339', '77', '88', null);
INSERT INTO `sys_role_menu` VALUES ('2340', '77', '87', null);
INSERT INTO `sys_role_menu` VALUES ('2341', '77', '86', null);
INSERT INTO `sys_role_menu` VALUES ('2342', '77', '85', null);
INSERT INTO `sys_role_menu` VALUES ('2343', '77', '84', null);
INSERT INTO `sys_role_menu` VALUES ('2344', '77', '72', null);
INSERT INTO `sys_role_menu` VALUES ('2345', '77', '-1', null);
INSERT INTO `sys_role_menu` VALUES ('2346', '77', '77', null);
INSERT INTO `sys_role_menu` VALUES ('2974', '57', '93', null);
INSERT INTO `sys_role_menu` VALUES ('2975', '57', '99', null);
INSERT INTO `sys_role_menu` VALUES ('2976', '57', '95', null);
INSERT INTO `sys_role_menu` VALUES ('2977', '57', '101', null);
INSERT INTO `sys_role_menu` VALUES ('2978', '57', '96', null);
INSERT INTO `sys_role_menu` VALUES ('2979', '57', '94', null);
INSERT INTO `sys_role_menu` VALUES ('2980', '57', '-1', null);
INSERT INTO `sys_role_menu` VALUES ('2981', '58', '93', null);
INSERT INTO `sys_role_menu` VALUES ('2982', '58', '99', null);
INSERT INTO `sys_role_menu` VALUES ('2983', '58', '95', null);
INSERT INTO `sys_role_menu` VALUES ('2984', '58', '101', null);
INSERT INTO `sys_role_menu` VALUES ('2985', '58', '96', null);
INSERT INTO `sys_role_menu` VALUES ('2986', '58', '94', null);
INSERT INTO `sys_role_menu` VALUES ('2987', '58', '-1', null);
INSERT INTO `sys_role_menu` VALUES ('3115', '1', '103', null);
INSERT INTO `sys_role_menu` VALUES ('3116', '1', '98', null);
INSERT INTO `sys_role_menu` VALUES ('3117', '1', '101', null);
INSERT INTO `sys_role_menu` VALUES ('3118', '1', '99', null);
INSERT INTO `sys_role_menu` VALUES ('3119', '1', '95', null);
INSERT INTO `sys_role_menu` VALUES ('3120', '1', '92', null);
INSERT INTO `sys_role_menu` VALUES ('3121', '1', '57', null);
INSERT INTO `sys_role_menu` VALUES ('3122', '1', '30', null);
INSERT INTO `sys_role_menu` VALUES ('3123', '1', '29', null);
INSERT INTO `sys_role_menu` VALUES ('3124', '1', '28', null);
INSERT INTO `sys_role_menu` VALUES ('3125', '1', '90', null);
INSERT INTO `sys_role_menu` VALUES ('3126', '1', '89', null);
INSERT INTO `sys_role_menu` VALUES ('3127', '1', '88', null);
INSERT INTO `sys_role_menu` VALUES ('3128', '1', '87', null);
INSERT INTO `sys_role_menu` VALUES ('3129', '1', '86', null);
INSERT INTO `sys_role_menu` VALUES ('3130', '1', '72', null);
INSERT INTO `sys_role_menu` VALUES ('3131', '1', '48', null);
INSERT INTO `sys_role_menu` VALUES ('3132', '1', '68', null);
INSERT INTO `sys_role_menu` VALUES ('3133', '1', '60', null);
INSERT INTO `sys_role_menu` VALUES ('3134', '1', '59', null);
INSERT INTO `sys_role_menu` VALUES ('3135', '1', '58', null);
INSERT INTO `sys_role_menu` VALUES ('3136', '1', '51', null);
INSERT INTO `sys_role_menu` VALUES ('3137', '1', '76', null);
INSERT INTO `sys_role_menu` VALUES ('3138', '1', '75', null);
INSERT INTO `sys_role_menu` VALUES ('3139', '1', '74', null);
INSERT INTO `sys_role_menu` VALUES ('3140', '1', '62', null);
INSERT INTO `sys_role_menu` VALUES ('3141', '1', '56', null);
INSERT INTO `sys_role_menu` VALUES ('3142', '1', '55', null);
INSERT INTO `sys_role_menu` VALUES ('3143', '1', '15', null);
INSERT INTO `sys_role_menu` VALUES ('3144', '1', '26', null);
INSERT INTO `sys_role_menu` VALUES ('3145', '1', '25', null);
INSERT INTO `sys_role_menu` VALUES ('3146', '1', '24', null);
INSERT INTO `sys_role_menu` VALUES ('3147', '1', '14', null);
INSERT INTO `sys_role_menu` VALUES ('3148', '1', '13', null);
INSERT INTO `sys_role_menu` VALUES ('3149', '1', '12', null);
INSERT INTO `sys_role_menu` VALUES ('3150', '1', '61', null);
INSERT INTO `sys_role_menu` VALUES ('3151', '1', '22', null);
INSERT INTO `sys_role_menu` VALUES ('3152', '1', '21', null);
INSERT INTO `sys_role_menu` VALUES ('3153', '1', '20', null);
INSERT INTO `sys_role_menu` VALUES ('3154', '1', '83', null);
INSERT INTO `sys_role_menu` VALUES ('3155', '1', '81', null);
INSERT INTO `sys_role_menu` VALUES ('3156', '1', '80', null);
INSERT INTO `sys_role_menu` VALUES ('3157', '1', '79', null);
INSERT INTO `sys_role_menu` VALUES ('3158', '1', '71', null);
INSERT INTO `sys_role_menu` VALUES ('3159', '1', '102', null);
INSERT INTO `sys_role_menu` VALUES ('3160', '1', '97', null);
INSERT INTO `sys_role_menu` VALUES ('3161', '1', '96', null);
INSERT INTO `sys_role_menu` VALUES ('3162', '1', '94', null);
INSERT INTO `sys_role_menu` VALUES ('3163', '1', '93', null);
INSERT INTO `sys_role_menu` VALUES ('3164', '1', '27', null);
INSERT INTO `sys_role_menu` VALUES ('3165', '1', '91', null);
INSERT INTO `sys_role_menu` VALUES ('3166', '1', '85', null);
INSERT INTO `sys_role_menu` VALUES ('3167', '1', '84', null);
INSERT INTO `sys_role_menu` VALUES ('3168', '1', '50', null);
INSERT INTO `sys_role_menu` VALUES ('3169', '1', '49', null);
INSERT INTO `sys_role_menu` VALUES ('3170', '1', '73', null);
INSERT INTO `sys_role_menu` VALUES ('3171', '1', '7', null);
INSERT INTO `sys_role_menu` VALUES ('3172', '1', '6', null);
INSERT INTO `sys_role_menu` VALUES ('3173', '1', '2', null);
INSERT INTO `sys_role_menu` VALUES ('3174', '1', '3', null);
INSERT INTO `sys_role_menu` VALUES ('3175', '1', '78', null);
INSERT INTO `sys_role_menu` VALUES ('3176', '1', '1', null);
INSERT INTO `sys_role_menu` VALUES ('3177', '1', '104', null);
INSERT INTO `sys_role_menu` VALUES ('3178', '1', '77', null);
INSERT INTO `sys_role_menu` VALUES ('3179', '1', '-1', null);
INSERT INTO `sys_role_menu` VALUES ('3284', '59', '98', null);
INSERT INTO `sys_role_menu` VALUES ('3285', '59', '101', null);
INSERT INTO `sys_role_menu` VALUES ('3286', '59', '99', null);
INSERT INTO `sys_role_menu` VALUES ('3287', '59', '95', null);
INSERT INTO `sys_role_menu` VALUES ('3288', '59', '90', null);
INSERT INTO `sys_role_menu` VALUES ('3289', '59', '89', null);
INSERT INTO `sys_role_menu` VALUES ('3290', '59', '88', null);
INSERT INTO `sys_role_menu` VALUES ('3291', '59', '87', null);
INSERT INTO `sys_role_menu` VALUES ('3292', '59', '86', null);
INSERT INTO `sys_role_menu` VALUES ('3293', '59', '97', null);
INSERT INTO `sys_role_menu` VALUES ('3294', '59', '96', null);
INSERT INTO `sys_role_menu` VALUES ('3295', '59', '94', null);
INSERT INTO `sys_role_menu` VALUES ('3296', '59', '93', null);
INSERT INTO `sys_role_menu` VALUES ('3297', '59', '85', null);
INSERT INTO `sys_role_menu` VALUES ('3298', '59', '84', null);
INSERT INTO `sys_role_menu` VALUES ('3299', '59', '-1', null);
INSERT INTO `sys_role_menu` VALUES ('3302', '62', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3303', '62', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3304', '63', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3305', '63', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3306', '63', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3307', '64', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3308', '64', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3309', '64', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3310', '64', '7', '1');
INSERT INTO `sys_role_menu` VALUES ('3311', '67', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3312', '67', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3313', '67', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3314', '67', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('3315', '67', '5', '1');
INSERT INTO `sys_role_menu` VALUES ('3316', '67', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('3317', '67', '7', '1');
INSERT INTO `sys_role_menu` VALUES ('3318', '67', '8', '1');
INSERT INTO `sys_role_menu` VALUES ('3319', '67', '9', '1');
INSERT INTO `sys_role_menu` VALUES ('3320', '67', '10', '1');
INSERT INTO `sys_role_menu` VALUES ('3321', '67', '11', '1');
INSERT INTO `sys_role_menu` VALUES ('3322', '67', '12', '1');
INSERT INTO `sys_role_menu` VALUES ('3323', '67', '13', '1');
INSERT INTO `sys_role_menu` VALUES ('3324', '67', '14', '1');
INSERT INTO `sys_role_menu` VALUES ('3325', '67', '15', '1');
INSERT INTO `sys_role_menu` VALUES ('3326', '67', '16', '1');
INSERT INTO `sys_role_menu` VALUES ('3327', '67', '17', '1');
INSERT INTO `sys_role_menu` VALUES ('3328', '67', '18', '1');
INSERT INTO `sys_role_menu` VALUES ('3329', '67', '19', '1');
INSERT INTO `sys_role_menu` VALUES ('3330', '67', '51', '1');
INSERT INTO `sys_role_menu` VALUES ('3331', '67', '20', '1');
INSERT INTO `sys_role_menu` VALUES ('3332', '67', '21', '1');
INSERT INTO `sys_role_menu` VALUES ('3333', '67', '22', '1');
INSERT INTO `sys_role_menu` VALUES ('3334', '67', '23', '1');
INSERT INTO `sys_role_menu` VALUES ('3335', '67', '24', '1');
INSERT INTO `sys_role_menu` VALUES ('3336', '67', '25', '1');
INSERT INTO `sys_role_menu` VALUES ('3337', '67', '26', '1');
INSERT INTO `sys_role_menu` VALUES ('3338', '67', '27', '1');
INSERT INTO `sys_role_menu` VALUES ('3339', '67', '28', '1');
INSERT INTO `sys_role_menu` VALUES ('3340', '67', '29', '1');
INSERT INTO `sys_role_menu` VALUES ('3341', '67', '30', '1');
INSERT INTO `sys_role_menu` VALUES ('3342', '68', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3343', '68', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3344', '68', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3345', '68', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('3346', '68', '5', '1');
INSERT INTO `sys_role_menu` VALUES ('3347', '68', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('3348', '68', '7', '1');
INSERT INTO `sys_role_menu` VALUES ('3349', '68', '8', '1');
INSERT INTO `sys_role_menu` VALUES ('3350', '68', '9', '1');
INSERT INTO `sys_role_menu` VALUES ('3351', '68', '10', '1');
INSERT INTO `sys_role_menu` VALUES ('3352', '68', '11', '1');
INSERT INTO `sys_role_menu` VALUES ('3353', '68', '12', '1');
INSERT INTO `sys_role_menu` VALUES ('3354', '68', '13', '1');
INSERT INTO `sys_role_menu` VALUES ('3355', '68', '14', '1');
INSERT INTO `sys_role_menu` VALUES ('3356', '68', '15', '1');
INSERT INTO `sys_role_menu` VALUES ('3357', '68', '16', '1');
INSERT INTO `sys_role_menu` VALUES ('3358', '68', '17', '1');
INSERT INTO `sys_role_menu` VALUES ('3359', '68', '18', '1');
INSERT INTO `sys_role_menu` VALUES ('3360', '68', '19', '1');
INSERT INTO `sys_role_menu` VALUES ('3361', '68', '51', '1');
INSERT INTO `sys_role_menu` VALUES ('3362', '68', '20', '1');
INSERT INTO `sys_role_menu` VALUES ('3363', '68', '21', '1');
INSERT INTO `sys_role_menu` VALUES ('3364', '68', '22', '1');
INSERT INTO `sys_role_menu` VALUES ('3365', '68', '23', '1');
INSERT INTO `sys_role_menu` VALUES ('3366', '68', '24', '1');
INSERT INTO `sys_role_menu` VALUES ('3367', '68', '25', '1');
INSERT INTO `sys_role_menu` VALUES ('3368', '68', '26', '1');
INSERT INTO `sys_role_menu` VALUES ('3369', '68', '27', '1');
INSERT INTO `sys_role_menu` VALUES ('3370', '68', '28', '1');
INSERT INTO `sys_role_menu` VALUES ('3371', '68', '29', '1');
INSERT INTO `sys_role_menu` VALUES ('3372', '68', '30', '1');
INSERT INTO `sys_role_menu` VALUES ('3373', '1', '1', '0');
INSERT INTO `sys_role_menu` VALUES ('3374', '1', '2', '0');
INSERT INTO `sys_role_menu` VALUES ('3375', '1', '3', '0');
INSERT INTO `sys_role_menu` VALUES ('3376', '1', '4', '0');
INSERT INTO `sys_role_menu` VALUES ('3377', '1', '5', '0');
INSERT INTO `sys_role_menu` VALUES ('3378', '1', '6', '0');
INSERT INTO `sys_role_menu` VALUES ('3379', '1', '7', '0');
INSERT INTO `sys_role_menu` VALUES ('3380', '1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3381', '1', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3382', '1', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3383', '1', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('3384', '1', '5', '1');
INSERT INTO `sys_role_menu` VALUES ('3385', '1', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('3386', '1', '7', '1');
INSERT INTO `sys_role_menu` VALUES ('3387', '69', '8', '1');
INSERT INTO `sys_role_menu` VALUES ('3388', '69', '9', '1');
INSERT INTO `sys_role_menu` VALUES ('3389', '69', '10', '1');
INSERT INTO `sys_role_menu` VALUES ('3390', '69', '11', '1');
INSERT INTO `sys_role_menu` VALUES ('3391', '69', '12', '1');
INSERT INTO `sys_role_menu` VALUES ('3392', '69', '13', '1');
INSERT INTO `sys_role_menu` VALUES ('3393', '69', '14', '1');
INSERT INTO `sys_role_menu` VALUES ('3394', '69', '15', '1');
INSERT INTO `sys_role_menu` VALUES ('3395', '69', '16', '1');
INSERT INTO `sys_role_menu` VALUES ('3396', '69', '17', '1');
INSERT INTO `sys_role_menu` VALUES ('3397', '69', '18', '1');
INSERT INTO `sys_role_menu` VALUES ('3398', '69', '19', '1');
INSERT INTO `sys_role_menu` VALUES ('3399', '69', '51', '1');
INSERT INTO `sys_role_menu` VALUES ('3400', '69', '20', '1');
INSERT INTO `sys_role_menu` VALUES ('3401', '69', '21', '1');
INSERT INTO `sys_role_menu` VALUES ('3402', '70', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3403', '71', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3404', '72', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3405', '73', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3406', '74', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3407', '78', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3408', '76', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3409', '77', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3410', '79', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3411', '75', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3412', '80', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3413', '79', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3414', '75', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3415', '80', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3416', '79', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3417', '80', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3418', '75', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3419', '81', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3420', '81', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3421', '82', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3422', '82', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3423', '81', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3424', '83', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3425', '83', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3426', '82', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3427', '83', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3428', '84', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('3429', '84', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3430', '84', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('3431', '84', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('3432', '84', '5', '1');
INSERT INTO `sys_role_menu` VALUES ('3433', '84', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('3434', '84', '7', '1');
INSERT INTO `sys_role_menu` VALUES ('3435', '85', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('3436', '85', '5', '1');
INSERT INTO `sys_role_menu` VALUES ('3437', '85', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('3438', '85', '7', '1');
INSERT INTO `sys_role_menu` VALUES ('3439', '86', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('3440', '86', '5', '1');
INSERT INTO `sys_role_menu` VALUES ('3441', '86', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('3442', '86', '7', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `telephone` varchar(100) DEFAULT NULL COMMENT '固定电话',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `lastwrong_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '用户名或密码错误时间',
  `wrong_times` int(11) DEFAULT NULL COMMENT '在laswrong_time和当前时间相差30分钟内输入错误次数',
  `locktype` int(11) DEFAULT NULL COMMENT '锁定类型：1 自动锁定，2  手动锁定',
  `lockflag` int(11) DEFAULT NULL COMMENT '锁定标记，1 锁定，0 不锁定，当前设计输入密码5次错误，锁定30分钟',
  `unlocktime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '解锁时间，开始锁定时间+锁定时长=解锁时间，在验证解锁时间到达之后同时需要更新lockflag为解锁状态以及将锁定原因清空',
  `lockreason` varchar(255) DEFAULT NULL COMMENT '被锁定原因',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` bigint(32) DEFAULT NULL COMMENT '性别',
  `birth` datetime DEFAULT NULL COMMENT '出身日期，为兼容现有系统，暂时不不用。',
  `pic_id` bigint(32) DEFAULT NULL,
  `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地，为兼容现有系统，暂时不不用。',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好，为兼容现有系统，暂时不不用。',
  `province` varchar(255) DEFAULT NULL COMMENT '省份，为兼容现有系统，暂时不不用。',
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市，为兼容现有系统，暂时不不用。',
  `district` varchar(255) DEFAULT NULL COMMENT '所在地区，为兼容现有系统，暂时不不用。',
  `u_order` int(11) NOT NULL DEFAULT '0',
  `pwd_flag` int(11) DEFAULT '2' COMMENT '密码标记：1 正常 2  需要修改密码',
  `user_id_mod` bigint(20) DEFAULT NULL,
  `useflag` int(11) DEFAULT '1',
  `delflag` int(11) NOT NULL DEFAULT '1' COMMENT '删除标记：0 不能删除， 1可以删除， 默认 1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '$2a$10$UWRN7UWH.sjJaOZd9NU2EeOnzGvZsu5fFiQysiaBwvhH6zRxqHqQy', '6', 'admin@example.com', '17699999999', '123456', '1', '1', '2018-08-30 09:09:38', '7', null, '0', '2018-08-30 09:09:38', null, '2017-08-15 21:40:39', '2017-08-15 21:41:00', '0', '2017-12-14 00:00:00', '138', 'ccc', '122;121;', '北京市', '北京市市辖区', '东城区', '0', '2', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('2', 'test', '临时用户', '6cf3bb3deba2aadbd41ec9a22511084e', '6', 'test@bootdo.com', null, '123456', '1', '1', null, null, '2', '1', null, '2018-08-30 10:53:29,192.168.1.35,admin锁定', '2017-08-14 13:43:05', '2017-08-14 21:15:36', null, null, null, null, null, null, null, null, '0', null, '1', '1', '1');
INSERT INTO `sys_user` VALUES ('36', 'ldh', '刘德华', 'bfd9394475754fbe45866eba97738c36', '7', 'ldh@bootdo.com', null, '123456', '1', null, null, null, '2', '1', null, '2018-08-30 10:53:35,192.168.1.35,admin锁定', null, '2018-08-30 10:53:35', null, null, null, null, null, null, null, null, '0', null, '1', '1', '1');
INSERT INTO `sys_user` VALUES ('123', 'zxy', '张学友', '35174ba93f5fe7267f1fb3c1bf903781', '6', 'zxy@bootdo', null, '123456', '0', null, null, null, '2', '1', null, '2018-08-30 10:53:39,192.168.1.35,admin锁定', null, '2018-08-30 10:53:39', null, null, null, null, null, null, null, null, '0', null, '1', '1', '1');
INSERT INTO `sys_user` VALUES ('124', 'wyf', '吴亦凡', 'e179e6f687bbd57b9d7efc4746c8090a', '6', 'wyf@bootdo.com', null, '123456', '1', null, null, null, '2', '1', null, '2018-08-30 10:53:45,192.168.1.35,admin锁定', null, '2018-08-30 10:53:45', null, null, null, null, null, null, null, null, '0', null, '1', '1', '1');
INSERT INTO `sys_user` VALUES ('130', 'lh', '鹿晗', '7924710cd673f68967cde70e188bb097', '9', 'lh@bootdo.com', null, '123456', '1', null, null, null, '2', '1', null, '2018-08-30 11:11:50,192.168.1.35,admin锁定', null, '2018-08-30 11:11:51', null, null, null, null, null, null, null, null, '0', null, '1', '1', '1');
INSERT INTO `sys_user` VALUES ('131', 'lhc', '令狐冲', 'd515538e17ecb570ba40344b5618f5d4', '6', 'lhc@bootdo.com', null, '123456', '0', null, '2018-08-26 15:30:27', null, null, null, '2018-08-26 15:30:27', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, '1', '1');
INSERT INTO `sys_user` VALUES ('132', 'lyf', '刘亦菲', '7fdb1d9008f45950c1620ba0864e5fbd', '6', 'lyf@bootdo.com', null, '123456', '1', null, '2018-08-26 15:30:28', null, null, null, '2018-08-26 15:30:28', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, '1', '1');
INSERT INTO `sys_user` VALUES ('134', 'lyh', '李彦宏', 'dc26092b3244d9d432863f2738180e19', '8', 'lyh@bootdo.com', null, '123456', '1', null, '2018-08-26 15:30:28', null, null, null, '2018-08-26 15:30:28', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, '1', '1');
INSERT INTO `sys_user` VALUES ('135', 'wjl', '王健林', '3967697dfced162cf6a34080259b83aa', '6', 'wjl@bootod.com', null, '123456', '1', null, '2018-08-30 11:11:47', null, null, null, '2018-08-30 11:11:47', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, '0', '1');
INSERT INTO `sys_user` VALUES ('136', 'gdg', '郭德纲', '3bb1bda86bc02bf6478cd91e42135d2f', '9', 'gdg@bootdo.com', null, '123456', '1', null, '2018-08-30 11:11:47', null, null, '0', '2018-08-30 11:11:47', null, null, '2018-08-30 10:42:39', null, null, null, null, null, null, null, null, '0', null, '1', '0', '1');
INSERT INTO `sys_user` VALUES ('140', 'system', '系统', null, '0', null, null, '123456', '0', null, null, null, null, '0', null, null, null, '2018-08-30 10:35:34', null, null, null, null, null, null, null, null, '0', '2', '1', '1', '0');
INSERT INTO `sys_user` VALUES ('143', 'chenghao', '成浩', '$2a$10$UGPDCDSLGIwvtPRBuUpnZua5LTMm8cq746rgNlIZ5nFbyv1qm5zA6', '6', '111@qq.com', '17768148505', '11111', '1', null, null, null, null, '0', null, null, '2018-08-27 10:56:14', '2018-08-30 10:47:28', '1', null, null, null, null, null, null, null, '0', '2', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('144', 'chenhao', '成浩', '$2a$10$QB97GS6Zs4zK2R1OW0OpCuYOR.K1HbD7J9.oUxz0XbNM3w3LuTJQy', '6', '80@qq.com', '18888888888', '123123', '1', null, '2018-08-27 14:56:58', null, null, null, '2018-08-27 14:56:58', null, '2018-08-27 11:11:56', null, '1', null, null, null, null, null, null, null, '0', '2', null, '0', '1');
INSERT INTO `sys_user` VALUES ('145', 'chenghaotest', '12321', '$2a$10$bSl3GuRgkoETz5vwf7c7Xu5s9ArTTl4h4PYbPJ8YQGqGmXZco92uS', '6', '10@qq.com', '17777777777', '123', '1', null, '2018-08-30 09:29:59', null, null, null, '2018-08-30 09:29:59', null, '2018-08-27 11:20:09', null, '1', null, null, null, null, null, null, null, '0', '2', null, '0', '1');
INSERT INTO `sys_user` VALUES ('146', '128', 'chenghao', '$2a$10$dh5xYYavRK1cCaR9PMIaSuP8bRS3IiAGisEe.BI4UNDwWP7BPBj9K', '9', '1111@qq.com', '13911111111', '12089', '1', null, '2018-08-27 14:53:44', null, null, null, '2018-08-27 14:53:44', null, '2018-08-27 14:02:07', null, '2', null, null, null, null, null, null, null, '0', '2', null, '0', '1');
INSERT INTO `sys_user` VALUES ('147', '1', '1', '$2a$10$Sq4xbSbgodJBd.y6mCcvwumXujKnqNA57j/uZhdw8.PSeUyPaI92S', '9', '111@qq.com', '13800000000', '111', '1', null, '2018-08-27 14:53:39', null, null, null, '2018-08-27 14:53:39', null, '2018-08-27 14:06:03', null, '1', null, null, null, null, null, null, null, '0', '2', null, '0', '1');
INSERT INTO `sys_user` VALUES ('148', 'ccc', 'ccc', '$2a$10$v0mUoz58mfnomMzpRIssgemNeBiKHw7reFMGOVr3Xj6lVVewEg3nW', '6', '111@qq.com', '13911111111', '12311', '0', '1', '2018-08-27 14:52:58', null, null, null, '2018-08-27 14:52:58', null, '2018-08-27 14:26:53', '2018-08-27 14:27:00', '1', null, null, null, null, null, null, null, '0', '2', null, '0', '1');
INSERT INTO `sys_user` VALUES ('150', 'eee', 'eee', '$2a$10$WyeMayc0i0mjR.NqLwzvFeV/5ziWvawCV08olaTZp01ZKg0pg3EOm', '6', '11111@qq.com', '13900000000', '11111', '1', '1', '2018-08-27 14:42:33', null, null, null, '2018-08-27 14:42:33', null, '2018-08-27 14:38:19', '2018-08-27 14:38:21', '2', null, null, null, null, null, null, null, '0', '2', null, '0', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `useflag` int(11) DEFAULT '1' COMMENT '使用标记： 1 使用， 0 不使用 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('73', '30', '48', '1');
INSERT INTO `sys_user_role` VALUES ('74', '30', '49', '1');
INSERT INTO `sys_user_role` VALUES ('75', '30', '50', '1');
INSERT INTO `sys_user_role` VALUES ('76', '31', '48', '1');
INSERT INTO `sys_user_role` VALUES ('77', '31', '49', '1');
INSERT INTO `sys_user_role` VALUES ('78', '31', '52', '1');
INSERT INTO `sys_user_role` VALUES ('79', '32', '48', '1');
INSERT INTO `sys_user_role` VALUES ('80', '32', '49', '1');
INSERT INTO `sys_user_role` VALUES ('81', '32', '50', '1');
INSERT INTO `sys_user_role` VALUES ('82', '32', '51', '1');
INSERT INTO `sys_user_role` VALUES ('83', '32', '52', '1');
INSERT INTO `sys_user_role` VALUES ('84', '33', '38', '1');
INSERT INTO `sys_user_role` VALUES ('85', '33', '49', '1');
INSERT INTO `sys_user_role` VALUES ('86', '33', '52', '1');
INSERT INTO `sys_user_role` VALUES ('87', '34', '50', '1');
INSERT INTO `sys_user_role` VALUES ('88', '34', '51', '1');
INSERT INTO `sys_user_role` VALUES ('89', '34', '52', '1');
INSERT INTO `sys_user_role` VALUES ('106', '124', '1', '1');
INSERT INTO `sys_user_role` VALUES ('110', '1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('111', '2', '1', '1');
INSERT INTO `sys_user_role` VALUES ('113', '131', '48', '1');
INSERT INTO `sys_user_role` VALUES ('117', '135', '1', '0');
INSERT INTO `sys_user_role` VALUES ('120', '134', '1', '1');
INSERT INTO `sys_user_role` VALUES ('121', '134', '48', '1');
INSERT INTO `sys_user_role` VALUES ('123', '130', '1', '1');
INSERT INTO `sys_user_role` VALUES ('124', null, '48', '1');
INSERT INTO `sys_user_role` VALUES ('125', '132', '52', '1');
INSERT INTO `sys_user_role` VALUES ('126', '132', '49', '1');
INSERT INTO `sys_user_role` VALUES ('127', '123', '48', '1');
INSERT INTO `sys_user_role` VALUES ('132', '36', '48', '1');
INSERT INTO `sys_user_role` VALUES ('133', '150', '1', '0');
INSERT INTO `sys_user_role` VALUES ('134', '150', '59', '0');
INSERT INTO `sys_user_role` VALUES ('135', '150', '65', '0');

-- ----------------------------
-- Table structure for tbl_md
-- ----------------------------
DROP TABLE IF EXISTS `tbl_md`;
CREATE TABLE `tbl_md` (
  `md_id` int(11) NOT NULL,
  `md_name` varchar(100) NOT NULL COMMENT '管理域名称',
  `md_desc` varchar(255) DEFAULT NULL COMMENT '管理域备注',
  `showorder` int(11) NOT NULL COMMENT '显示顺序',
  `delflag` int(11) NOT NULL COMMENT '删除标记：0 不能删除， 1可以删除， 默认 1',
  `useflag` int(11) NOT NULL COMMENT '使用标记： 1 使用， 0 不使用',
  `user_id_create` bigint(20) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `user_id_mod` bigint(20) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`md_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理域';

-- ----------------------------
-- Records of tbl_md
-- ----------------------------
INSERT INTO `tbl_md` VALUES ('1', '管理域1', '这个是管理域1', '2', '1', '1', null, null, null, null);
INSERT INTO `tbl_md` VALUES ('2', '管理域2', '这个是管理域2', '1', '1', '1', null, null, null, null);
INSERT INTO `tbl_md` VALUES ('3', '管理域3', '这个是管理域3', '3', '1', '1', null, null, null, null);

-- ----------------------------
-- Table structure for tbl_md_dept
-- ----------------------------
DROP TABLE IF EXISTS `tbl_md_dept`;
CREATE TABLE `tbl_md_dept` (
  `id` int(11) NOT NULL,
  `dept_id` bigint(11) NOT NULL,
  `md_id` int(11) NOT NULL,
  `useflag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位和管理域对应关系表';

-- ----------------------------
-- Records of tbl_md_dept
-- ----------------------------
INSERT INTO `tbl_md_dept` VALUES ('1', '8', '1', '1');
INSERT INTO `tbl_md_dept` VALUES ('2', '9', '2', '1');

-- ----------------------------
-- Table structure for tbl_md_res
-- ----------------------------
DROP TABLE IF EXISTS `tbl_md_res`;
CREATE TABLE `tbl_md_res` (
  `id` int(11) NOT NULL,
  `md_id` int(11) NOT NULL,
  `res_id` int(11) NOT NULL,
  `useflag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维护域与资源关系';

-- ----------------------------
-- Records of tbl_md_res
-- ----------------------------
INSERT INTO `tbl_md_res` VALUES ('1', '1', '1', '1');
INSERT INTO `tbl_md_res` VALUES ('2', '1', '3', '1');
INSERT INTO `tbl_md_res` VALUES ('3', '1', '13', '1');
INSERT INTO `tbl_md_res` VALUES ('4', '1', '14', '1');
INSERT INTO `tbl_md_res` VALUES ('5', '2', '2', '1');
INSERT INTO `tbl_md_res` VALUES ('6', '2', '4', '1');
INSERT INTO `tbl_md_res` VALUES ('7', '2', '6', '1');
INSERT INTO `tbl_md_res` VALUES ('8', '2', '8', '1');

-- ----------------------------
-- Table structure for tbl_md_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_md_user`;
CREATE TABLE `tbl_md_user` (
  `id` int(11) NOT NULL,
  `md_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `showorder` int(11) NOT NULL,
  `useflag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维护域和维护人员关系';

-- ----------------------------
-- Records of tbl_md_user
-- ----------------------------
INSERT INTO `tbl_md_user` VALUES ('1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for tbl_mo_abnormal_def
-- ----------------------------
DROP TABLE IF EXISTS `tbl_mo_abnormal_def`;
CREATE TABLE `tbl_mo_abnormal_def` (
  `mo_abnormal_id` int(11) NOT NULL,
  `mo_abnormalcode` int(11) NOT NULL COMMENT '异常码',
  `mo_abnormal_name` varchar(100) NOT NULL,
  `res_abnormallevel_id` int(11) NOT NULL,
  `mo_abnormal_desc` varchar(255) DEFAULT NULL,
  `mo_abnormal_showtemplate` varchar(500) NOT NULL,
  `showorder` int(11) NOT NULL,
  `useflag` int(11) NOT NULL,
  `user_id_create` bigint(20) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `user_id_mod` bigint(20) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`mo_abnormal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监控异常定义';

-- ----------------------------
-- Records of tbl_mo_abnormal_def
-- ----------------------------
INSERT INTO `tbl_mo_abnormal_def` VALUES ('1000', '80111000', '设备离线', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime离线，故障码：$$mo_abnormalcode。', '110001', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('1010', '80111010', '未知', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime状态未知，故障码：$$mo_abnormalcode。', '110002', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('1011', '80111011', '设备掉电', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime掉电，故障码：$$mo_abnormalcode。', '110012', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2000', '80112000', '电压过低', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime电压值过低，低于$$mo_th_down$$unit_ch，当前值为：$$res_abnormalvalue$$unit_ch，故障码：$$mo_abnormalcode。', '110003', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2001', '80112001', '电压过高', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime电压值过高，高于$$mo_th_up$$unit_ch，当前值为：$$res_abnormalvalue$$unit_ch，故障码：$$mo_abnormalcode。', '110004', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2002', '80112002', '电流过大', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime电流过大，大于$$mo_th_up$$unit_ch，当前值为：$$res_abnormalvalue$$unit_ch，故障码：$$mo_abnormalcode。', '110005', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2003', '80112003', '功率过大', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime功率过大，大于$$mo_th_up$$unit_ch，当前值为：$$res_abnormalvalue$$unit_ch，故障码：$$mo_abnormalcode。', '110006', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2004', '80112004', '电流为零', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime电流为零，故障码：$$mo_abnormalcode。', '110007', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2005', '80112005', '功率为零', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime功率为零，故障码：$$mo_abnormalcode。', '110008', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2006', '80112006', '风扇停止', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime停止运转，故障码：$$mo_abnormalcode。', '110009', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2007', '80142007', '柜门打开', '14', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime处于打开状态，故障码：$$mo_abnormalcode。', '140001', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2008', '80112008', '温度过高', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime温度过高，高于$$mo_th_up$$unit_ch，当前值为：$$res_abnormalvalue$$unit_ch，故障码：$$mo_abnormalcode。', '110010', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2009', '80112009', '温度过低', '11', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime温度过低，低于$$mo_th_up$$unit_ch，当前值为：$$res_abnormalvalue$$unit_ch，故障码：$$mo_abnormalcode。', '110011', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2010', '80142010', '继电器关', '14', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime处于关闭状态，故障码：$$mo_abnormalcode。', '140002', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2011', '80142011', '电压低异常', '14', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '140003', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2012', '80142012', '电压高异常', '14', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '140004', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2013', '80142013', '电流大异常', '14', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '140005', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2014', '80142014', '功率大异常', '14', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '140006', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2015', '80142015', '温度高异常', '14', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '140007', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2016', '80142016', '温度低异常', '14', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '140008', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2017', '80132017', '电压低异常', '13', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '130001', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2018', '80132018', '电压高异常', '13', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '130002', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2019', '80132019', '电流大异常', '13', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '130003', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2020', '80132020', '功率大异常', '13', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '130004', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2021', '80132021', '温度高异常', '13', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '130005', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2022', '80132022', '温度低异常', '13', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '130006', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2023', '80122023', '电压低异常', '12', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '120001', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2024', '80122024', '电压高异常', '12', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '120002', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2025', '80122025', '电流大异常', '12', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '120003', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2026', '80122026', '功率大异常', '12', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '120004', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2027', '80122027', '温度高异常', '12', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '120005', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2028', '80122028', '温度低异常', '12', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down$$unit_ch,$$mo_th_up$$unit_ch]，故障码：$$mo_abnormalcode。', '120006', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2029', '80122029', '电压高异常', '12', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，额定值为：$$mo_th_base$$unit_ch，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down,$$mo_th_up]，故障码：$$mo_abnormalcode。', '120007', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2030', '80122030', '电流大异常', '12', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，额定值为：$$mo_th_base$$unit_ch，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down,$$mo_th_up]，故障码：$$mo_abnormalcode。', '120008', '1', null, null, null, null);
INSERT INTO `tbl_mo_abnormal_def` VALUES ('2031', '80122031', '功率大异常', '12', null, '$$res_mtype_name：$$res_alias于$$res_abnomaltime$$mo_abnormal_name，额定值为：$$mo_th_base$$unit_ch，当前值为：$$res_abnormalvalue$$unit_ch，阈值为[$$mo_th_down,$$mo_th_up]，故障码：$$mo_abnormalcode。', '120009', '1', null, null, null, null);

-- ----------------------------
-- Table structure for tbl_mo_kpi
-- ----------------------------
DROP TABLE IF EXISTS `tbl_mo_kpi`;
CREATE TABLE `tbl_mo_kpi` (
  `mo_kpi_id` int(11) NOT NULL,
  `mo_kpi_name` varchar(50) NOT NULL COMMENT '监控指标名称',
  `mo_kpi_key` varchar(50) NOT NULL COMMENT '硬件上对于指标可能不是数字有可能是一个字符串，匹配硬件采集的数据通过这个key关联得到mo_kpi_id',
  `mo_kpi_desc` varchar(255) DEFAULT NULL COMMENT '监控指标描述',
  `unit_ch` varchar(50) DEFAULT NULL COMMENT '指标中文单位i',
  `unit_en` varchar(50) DEFAULT NULL COMMENT '指标英文单位',
  `showorder` int(11) NOT NULL,
  `useflag` int(11) NOT NULL,
  `user_id_create` bigint(20) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `user_id_mod` bigint(20) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`mo_kpi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监控指标';

-- ----------------------------
-- Records of tbl_mo_kpi
-- ----------------------------
INSERT INTO `tbl_mo_kpi` VALUES ('1', '电压', 'voltage', null, 'V', 'V', '1', '1', null, null, null, null);
INSERT INTO `tbl_mo_kpi` VALUES ('2', '电流', 'current', null, 'A', 'A', '2', '1', null, null, null, null);
INSERT INTO `tbl_mo_kpi` VALUES ('3', '功率', 'power', null, 'W', 'W', '3', '1', null, null, null, null);
INSERT INTO `tbl_mo_kpi` VALUES ('4', '温度', 'temperature', null, '℃', '℃', '4', '1', null, null, null, null);
INSERT INTO `tbl_mo_kpi` VALUES ('5', '开关状态', 'status', null, null, null, '5', '1', null, null, null, null);
INSERT INTO `tbl_mo_kpi` VALUES ('6', '继电器状态', 'relay', null, null, null, '6', '1', null, null, null, null);
INSERT INTO `tbl_mo_kpi` VALUES ('7', '网络状态', 'n', null, null, null, '7', '1', null, null, null, null);

-- ----------------------------
-- Table structure for tbl_mo_threshold
-- ----------------------------
DROP TABLE IF EXISTS `tbl_mo_threshold`;
CREATE TABLE `tbl_mo_threshold` (
  `mo_th_id` int(11) NOT NULL,
  `mo_th_type` int(11) NOT NULL COMMENT '阈值类型： 1 上下限监控，2  百分比监控(区间)，3  基准值百分比上下限监控 ，4 基准值百分比监控（区间），对于上下限 如果有配置上限或者下限则按照大于上限小于下限为异常，若只配置了上限或者下限则按照有配置的比较, 5 判断获取到到的值是否等于mo_th_value ',
  `mo_th_base` decimal(15,2) DEFAULT NULL COMMENT '基准值',
  `mo_th_up` decimal(15,2) DEFAULT NULL COMMENT '上限',
  `mo_th_down` decimal(15,2) DEFAULT NULL COMMENT '下限',
  `mo_th_inupdown` int(11) NOT NULL COMMENT '上下限包含标记 ：1 包含上下限，0 不包含上下限 默认0',
  `mo_th_value` varchar(50) DEFAULT NULL COMMENT '做等于比较时和该值进行比较',
  `mo_th_priority` int(11) DEFAULT NULL COMMENT '当同一个对象的同一监控指标 匹配出多个故障时 按照该优先级展示优先级高的故障，该数值越小优先级越高',
  `res_stype_id` int(11) NOT NULL,
  `mo_th_stype_flag` int(11) DEFAULT NULL COMMENT '子类型标记：1 该规则应用于该类型下所有子类型， 2 该规则只应用于该类型本身',
  `mo_kpi_id` int(11) NOT NULL,
  `mo_abnormal_id` int(11) NOT NULL,
  `showorder` int(11) NOT NULL,
  `useflag` int(11) NOT NULL,
  `user_id_create` bigint(20) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `user_id_mod` bigint(20) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`mo_th_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监控阈值配置';

-- ----------------------------
-- Records of tbl_mo_threshold
-- ----------------------------
INSERT INTO `tbl_mo_threshold` VALUES ('1', '1', null, null, '220.00', '0', null, '500', '10001', '1', '1', '2000', '100', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('2', '1', null, '240.00', null, '0', null, '500', '10001', '1', '1', '2001', '101', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('3', '1', null, '5.00', null, '1', null, '500', '10001', '1', '2', '2002', '102', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('4', '1', null, '2000.00', null, '1', null, '500', '10001', '1', '3', '2003', '103', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('5', '4', '1000.00', '0.40', '0.60', '0', null, '600', '10001', '1', '3', '2031', '104', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('6', '4', '10.00', '0.10', '0.50', '0', null, '600', '10001', '1', '2', '2030', '105', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('7', '5', null, null, null, '1', '0', '500', '20001', '1', '2', '2004', '106', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('8', '5', null, null, null, '1', '0', '500', '30001', '1', '2', '2004', '107', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('9', '5', null, null, null, '1', '0', '500', '40001', '1', '2', '2004', '108', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('10', '5', null, null, null, '1', '0', '500', '50001', '1', '2', '2004', '109', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('11', '5', null, null, null, '1', 'open', '500', '60001', '1', '5', '2006', '110', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('12', '5', null, null, null, '1', 'close', '500', '70001', '1', '5', '2007', '111', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('13', '2', null, '40.00', '50.00', '1', null, '800', '80001', '1', '4', '2015', '112', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('14', '2', null, '50.00', '60.00', '1', null, '700', '80001', '1', '4', '2021', '113', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('15', '2', null, '60.00', '70.00', '1', null, '600', '80001', '1', '4', '2027', '114', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('16', '1', null, '70.00', null, '1', null, '500', '80001', '1', '4', '2008', '115', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('17', '2', null, '50.00', '60.00', '1', null, '800', '90001', '1', '4', '2015', '116', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('18', '2', null, '60.00', '70.00', '1', null, '700', '90001', '1', '4', '2021', '117', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('19', '2', null, '70.00', '80.00', '1', null, '600', '90001', '1', '4', '2027', '118', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('20', '1', null, '80.00', null, '1', null, '500', '90001', '1', '4', '2008', '119', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('21', '2', null, '0.00', '-5.00', '1', null, '800', '80001', '1', '4', '2016', '120', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('22', '2', null, '-5.00', '-10.00', '1', null, '700', '80001', '1', '4', '2022', '121', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('23', '2', null, '-10.00', '-15.00', '1', null, '600', '80001', '1', '4', '2028', '122', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('24', '1', null, null, '-15.00', '1', null, '500', '80001', '1', '4', '2009', '123', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('25', '2', null, '-5.00', '-10.00', '1', null, '800', '90001', '1', '4', '2016', '124', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('26', '2', null, '-10.00', '-15.00', '1', null, '700', '90001', '1', '4', '2022', '125', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('27', '2', null, '-15.00', '-20.00', '1', null, '600', '90001', '1', '4', '2028', '126', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('28', '1', null, null, '-20.00', '1', null, '500', '90001', '1', '4', '2009', '127', '1', null, null, null, null);
INSERT INTO `tbl_mo_threshold` VALUES ('29', '5', null, null, null, '1', '0', '500', '10001', '1', '2', '2004', '128', '1', null, null, null, null);

-- ----------------------------
-- Table structure for tbl_mqtt_dev
-- ----------------------------
DROP TABLE IF EXISTS `tbl_mqtt_dev`;
CREATE TABLE `tbl_mqtt_dev` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `mqtt_terminal_id` int(11) DEFAULT NULL COMMENT 'mqtt终端消息ID',
  `res_serialnumber` varchar(50) DEFAULT NULL COMMENT '终端ID',
  `dev_id` int(11) DEFAULT NULL COMMENT '设备ID',
  `dev_type` varchar(50) DEFAULT NULL COMMENT '设备类型',
  `data_type` varchar(50) DEFAULT NULL COMMENT '数据类型',
  `data_value` varchar(50) DEFAULT NULL COMMENT '数据值',
  `msg_time` datetime DEFAULT NULL COMMENT '消息时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79524 DEFAULT CHARSET=utf8 COMMENT='mqtt监控设备消息';

-- ----------------------------
-- Records of tbl_mqtt_dev
-- ----------------------------
INSERT INTO `tbl_mqtt_dev` VALUES ('79479', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '11', 'total', 'current', '00.03', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79480', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '11', 'total', 'voltage', '216.1', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79481', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '11', 'total', 'power', '0001', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79482', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '21', 'camera', 'current', '00.00', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79483', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '21', 'camera', 'relay', 'on', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79484', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '22', 'comp_lamp', 'current', '00.00', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79485', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '22', 'comp_lamp', 'relay', 'on', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79486', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '23', 'flash_lamp', 'current', '00.00', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79487', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '23', 'flash_lamp', 'relay', 'on', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79488', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '24', 'optical', 'current', '00.00', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79489', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '24', 'optical', 'relay', 'on', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79490', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '25', 'temperature_ins', 'temperature', '24.8', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79491', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '26', 'temperature_out', 'temperature', '45.0', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79492', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '27', 'gate', 'status', 'open', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79493', '2018-08-22 10:21:49', '4024', 'HDKJABCD12345679', '28', 'fan', 'status', 'close', '2018-08-22 10:21:49');
INSERT INTO `tbl_mqtt_dev` VALUES ('79509', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '11', 'total', 'current', '00.03', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79510', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '11', 'total', 'voltage', '216.5', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79511', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '11', 'total', 'power', '0002', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79512', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '21', 'camera', 'current', '00.02', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79513', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '21', 'camera', 'relay', 'on', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79514', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '22', 'comp_lamp', 'current', '00.00', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79515', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '22', 'comp_lamp', 'relay', 'on', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79516', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '23', 'flash_lamp', 'current', '00.00', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79517', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '23', 'flash_lamp', 'relay', 'on', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79518', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '24', 'optical', 'current', '00.00', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79519', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '24', 'optical', 'relay', 'on', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79520', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '25', 'temperature_ins', 'temperature', '44.8', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79521', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '26', 'temperature_out', 'temperature', '25.1', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79522', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '27', 'gate', 'status', 'open', '2018-08-22 10:22:08');
INSERT INTO `tbl_mqtt_dev` VALUES ('79523', '2018-08-22 10:22:08', '4026', 'HDKJABCD12345678', '28', 'fan', 'status', 'close', '2018-08-22 10:22:08');

-- ----------------------------
-- Table structure for tbl_mqtt_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_mqtt_log`;
CREATE TABLE `tbl_mqtt_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `res_serialnumber` varchar(50) DEFAULT NULL COMMENT '终端ID',
  `topic` varchar(200) DEFAULT NULL COMMENT '主题',
  `payload` varchar(5000) DEFAULT NULL COMMENT '消息体',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3834 DEFAULT CHARSET=utf8 COMMENT='mqtt消息日志';

-- ----------------------------
-- Records of tbl_mqtt_log
-- ----------------------------
INSERT INTO `tbl_mqtt_log` VALUES ('3748', '2018-08-16 10:21:09', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065514\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3749', '2018-08-16 10:21:09', 'HDKJABCD12345679', 'HDKJABCD12345679PUB', '{\"id\":\"HDKJABCD12345679\",\"time\":\"20180718073345\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0001\",\"voltage\":\"216.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"24.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"45.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3750', '2018-08-16 10:21:10', 'HDKJABCD12345678', 'HDKJABCD12345678POWERDOWN', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718085931\"}');
INSERT INTO `tbl_mqtt_log` VALUES ('3751', '2018-08-16 10:21:16', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065524\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.2\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3752', '2018-08-16 10:21:26', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065534\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3753', '2018-08-16 10:21:36', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065544\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.2\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3754', '2018-08-16 10:21:47', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065556\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3755', '2018-08-16 10:21:57', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065606\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3756', '2018-08-16 10:22:07', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065616\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.2\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3757', '2018-08-16 10:22:17', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065626\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3758', '2018-08-16 10:22:27', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065635\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.2\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3759', '2018-08-16 10:22:37', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065646\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3760', '2018-08-16 10:22:47', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065656\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3761', '2018-08-16 10:22:57', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065706\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3762', '2018-08-16 10:23:07', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065716\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3763', '2018-08-16 10:23:17', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065726\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3764', '2018-08-16 10:23:27', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065736\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3765', '2018-08-16 10:23:38', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065746\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3766', '2018-08-16 10:23:48', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065756\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3767', '2018-08-16 10:23:58', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065806\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3768', '2018-08-16 10:24:08', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065816\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.8\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3769', '2018-08-16 10:24:18', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065826\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3770', '2018-08-16 10:24:28', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065836\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3771', '2018-08-16 10:24:38', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065846\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.2\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3772', '2018-08-16 10:24:48', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065856\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3773', '2018-08-16 10:24:58', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065906\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.2\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3774', '2018-08-16 10:24:31', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065938\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.2\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3775', '2018-08-16 10:24:31', 'HDKJABCD12345679', 'HDKJABCD12345679PUB', '{\"id\":\"HDKJABCD12345679\",\"time\":\"20180718073345\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0001\",\"voltage\":\"216.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"24.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"45.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3776', '2018-08-16 10:24:31', 'HDKJABCD12345678', 'HDKJABCD12345678POWERDOWN', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718085931\"}');
INSERT INTO `tbl_mqtt_log` VALUES ('3777', '2018-08-16 10:24:34', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065948\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.2\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3778', '2018-08-16 10:24:44', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065958\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.2\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3779', '2018-08-16 10:24:54', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070008\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3780', '2018-08-16 10:25:04', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070018\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3781', '2018-08-16 10:25:14', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070028\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3782', '2018-08-16 10:25:24', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070038\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3783', '2018-08-16 10:25:34', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070048\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.03\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3784', '2018-08-16 10:25:44', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070058\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3785', '2018-08-16 10:25:55', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070108\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3786', '2018-08-16 10:26:05', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070118\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3787', '2018-08-16 10:26:15', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070128\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3788', '2018-08-16 10:26:25', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070138\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3789', '2018-08-16 10:26:35', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070148\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3790', '2018-08-16 10:26:45', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070158\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3791', '2018-08-16 10:26:55', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070208\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3792', '2018-08-16 10:27:05', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070218\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3793', '2018-08-16 10:27:15', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070228\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.8\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3794', '2018-08-16 10:27:26', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070240\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3795', '2018-08-16 10:27:36', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070250\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3796', '2018-08-16 10:27:46', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070300\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3797', '2018-08-16 10:27:56', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070310\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3798', '2018-08-16 10:28:06', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070320\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3799', '2018-08-16 10:28:16', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070330\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3800', '2018-08-16 10:28:26', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070340\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3801', '2018-08-16 10:28:36', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070350\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3802', '2018-08-16 10:28:46', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070400\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3803', '2018-08-16 10:28:56', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070410\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3804', '2018-08-16 10:29:07', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070420\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3805', '2018-08-16 10:29:16', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070430\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.8\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3806', '2018-08-16 10:29:27', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070440\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.7\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3807', '2018-08-16 10:29:37', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070450\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.8\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3808', '2018-08-16 10:29:47', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070500\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3809', '2018-08-16 10:29:57', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070510\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3810', '2018-08-16 10:30:07', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070520\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.8\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3811', '2018-08-16 10:30:17', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070530\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.7\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3812', '2018-08-16 10:30:27', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070540\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.8\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3813', '2018-08-16 10:30:38', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070552\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.7\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.03\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3814', '2018-08-16 10:30:47', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070600\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3815', '2018-08-16 10:30:58', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070612\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3816', '2018-08-16 10:31:08', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070622\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3817', '2018-08-16 10:31:18', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070632\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3818', '2018-08-16 10:31:28', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070642\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.9\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3819', '2018-08-16 10:31:38', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070652\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"215.0\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3820', '2018-08-16 10:31:48', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070702\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.7\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3821', '2018-08-16 10:31:58', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070712\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.7\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3822', '2018-08-16 10:32:08', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070722\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.8\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3823', '2018-08-16 10:32:19', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718070732\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"214.7\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3824', '2018-08-22 10:20:28', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065347\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"216.4\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3825', '2018-08-22 10:20:28', 'HDKJABCD12345679', 'HDKJABCD12345679PUB', '{\"id\":\"HDKJABCD12345679\",\"time\":\"20180718073345\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0001\",\"voltage\":\"216.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"24.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"45.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3826', '2018-08-22 10:20:29', 'HDKJABCD12345678', 'HDKJABCD12345678POWERDOWN', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718085931\"}');
INSERT INTO `tbl_mqtt_log` VALUES ('3827', '2018-08-22 10:20:37', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065355\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"216.3\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3828', '2018-08-22 10:20:48', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065407\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"216.5\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3829', '2018-08-22 10:21:48', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065508\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"216.4\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3830', '2018-08-22 10:21:49', 'HDKJABCD12345679', 'HDKJABCD12345679PUB', '{\"id\":\"HDKJABCD12345679\",\"time\":\"20180718073345\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0001\",\"voltage\":\"216.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"24.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"45.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3831', '2018-08-22 10:21:49', 'HDKJABCD12345678', 'HDKJABCD12345678POWERDOWN', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718085931\"}');
INSERT INTO `tbl_mqtt_log` VALUES ('3832', '2018-08-22 10:21:58', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065518\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"216.5\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.9\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');
INSERT INTO `tbl_mqtt_log` VALUES ('3833', '2018-08-22 10:22:08', 'HDKJABCD12345678', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065527\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"216.5\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}');

-- ----------------------------
-- Table structure for tbl_mqtt_terminal
-- ----------------------------
DROP TABLE IF EXISTS `tbl_mqtt_terminal`;
CREATE TABLE `tbl_mqtt_terminal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `res_serialnumber` varchar(50) DEFAULT NULL COMMENT '终端ID',
  `msg_time` datetime DEFAULT NULL COMMENT '消息时间',
  `topic` varchar(200) DEFAULT NULL COMMENT '主题',
  `payload` varchar(5000) DEFAULT NULL COMMENT '消息体',
  `terminal_status` int(11) DEFAULT NULL COMMENT '终端状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4027 DEFAULT CHARSET=utf8 COMMENT='mqtt终端消息';

-- ----------------------------
-- Records of tbl_mqtt_terminal
-- ----------------------------
INSERT INTO `tbl_mqtt_terminal` VALUES ('4024', '2018-08-22 10:21:49', 'HDKJABCD12345679', '2018-08-22 10:21:49', 'HDKJABCD12345679PUB', '{\"id\":\"HDKJABCD12345679\",\"time\":\"20180718073345\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0001\",\"voltage\":\"216.1\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"24.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"45.0\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}', '11');
INSERT INTO `tbl_mqtt_terminal` VALUES ('4026', '2018-08-22 10:22:08', 'HDKJABCD12345678', '2018-08-22 10:22:08', 'HDKJABCD12345678PUB', '{\"id\":\"HDKJABCD12345678\",\"time\":\"20180718065527\",\"objects\":[{\"obj_id\":\"11\",\"type\":\"total\",\"data\":{\"power\":\"0002\",\"voltage\":\"216.5\",\"current\":\"00.03\"}},{\"obj_id\":\"21\",\"type\":\"camera\",\"data\":{\"current\":\"00.02\",\"relay\":\"on\"}},{\"obj_id\":\"22\",\"type\":\"comp_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"23\",\"type\":\"flash_lamp\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"24\",\"type\":\"optical\",\"data\":{\"current\":\"00.00\",\"relay\":\"on\"}},{\"obj_id\":\"25\",\"type\":\"temperature_ins\",\"data\":{\"temperature\":\"44.8\"}},{\"obj_id\":\"26\",\"type\":\"temperature_out\",\"data\":{\"temperature\":\"25.1\"}},{\"obj_id\":\"27\",\"type\":\"gate\",\"data\":{\"status\":\"open\"}},{\"obj_id\":\"28\",\"type\":\"fan\",\"data\":{\"status\":\"close\"}}]}', '11');

-- ----------------------------
-- Table structure for tbl_res_abnormallevel
-- ----------------------------
DROP TABLE IF EXISTS `tbl_res_abnormallevel`;
CREATE TABLE `tbl_res_abnormallevel` (
  `res_abnormallevel_id` int(11) NOT NULL,
  `res_abnormallevel_name` varchar(50) NOT NULL,
  `res_abnormallevel_color` varchar(20) NOT NULL COMMENT '告警级别对应颜色： 0 绿色 1 蓝色 2 黄色 3 橙色 4 红色 5 灰色',
  `res_abnormallevel_priority` int(11) NOT NULL COMMENT '告警级别优先级，数值越小优先级越高',
  `showorder` int(11) NOT NULL,
  `useflag` int(11) NOT NULL,
  PRIMARY KEY (`res_abnormallevel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源异常级别';

-- ----------------------------
-- Records of tbl_res_abnormallevel
-- ----------------------------
INSERT INTO `tbl_res_abnormallevel` VALUES ('11', '紧急', '#ff0000', '200', '11', '1');
INSERT INTO `tbl_res_abnormallevel` VALUES ('12', '重要', '#ff6600', '300', '12', '1');
INSERT INTO `tbl_res_abnormallevel` VALUES ('13', '次要', '#ffae00', '400', '13', '1');
INSERT INTO `tbl_res_abnormallevel` VALUES ('14', '提示', '#eed193', '500', '14', '1');

-- ----------------------------
-- Table structure for tbl_res_base
-- ----------------------------
DROP TABLE IF EXISTS `tbl_res_base`;
CREATE TABLE `tbl_res_base` (
  `res_id` int(11) NOT NULL,
  `res_name` varchar(100) NOT NULL COMMENT '资源名称',
  `res_alias` varchar(100) NOT NULL COMMENT '用户便于记忆自定义的名称，默认和资源名称一致，一般情况下展示该名称',
  `res_serialnumber` varchar(50) DEFAULT NULL COMMENT '资源序列号，有些硬件设备通过该序列号进行关联，比如与智能终端的关联',
  `res_no` varchar(100) DEFAULT NULL COMMENT '资源编号',
  `res_desc` varchar(255) DEFAULT NULL COMMENT '资源描述',
  `res_mtype_id` int(11) NOT NULL COMMENT '资源主类型',
  `res_stype_id` int(11) NOT NULL COMMENT '资源子类型',
  `res_ipv4` varchar(32) DEFAULT NULL COMMENT '资源IPV4地址',
  `res_port` int(11) DEFAULT NULL COMMENT '资源所在的端口，一般和IP地址配合使用这样可以和资源通过TCP/IP协议进行通信',
  `longitude` decimal(10,7) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,7) DEFAULT NULL COMMENT '纬度',
  `res_address` varchar(255) DEFAULT NULL COMMENT '资源所在地址',
  `res_status` int(11) NOT NULL COMMENT '资源状态: 1 正常 ， 2 异常，3 未知，4 离线',
  `res_abnormal_id` int(11) DEFAULT NULL,
  `res_abnormalcode` int(11) DEFAULT NULL COMMENT '资源异常码',
  `res_abnormallevel_id` int(11) DEFAULT NULL COMMENT '资源异常级别',
  `res_abnormal_name` varchar(100) DEFAULT NULL COMMENT '告警名称',
  `res_abnormaldesc` varchar(500) DEFAULT NULL COMMENT '资源异常状态描述',
  `res_abnomaltime` datetime DEFAULT NULL COMMENT '异常发生时间',
  `res_recoverytime` datetime DEFAULT NULL COMMENT '异常恢复时间',
  `res_color` varchar(20) DEFAULT NULL COMMENT '资源展示时的告警级别定义，默认和res_abnormallevel_id一致，但是对于智能终端需要按照其本身以及其监测对象的最高故障展示。',
  `useflag` int(11) NOT NULL,
  `user_id_create` bigint(20) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `user_id_mod` bigint(20) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源基础信息表';

-- ----------------------------
-- Records of tbl_res_base
-- ----------------------------
INSERT INTO `tbl_res_base` VALUES ('1', '智能运管终端001', '阜宁001终端', 'HDKJABCD12345678', null, null, '1001', '10001', null, null, '119.8087750', '33.7647550', null, '4', '1000', '80111000', '11', '电压过低', '智能终端：阜宁001终端于2018-08-10 15:08:41离线，故障码：80111000。', '2018-08-10 15:08:42', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('2', '智能运管终端002', '阜宁002终端', 'HDKJABCD12345679', null, null, '1001', '10001', null, null, '119.9457480', '33.8053210', null, '4', '1000', '80111000', '11', null, '智能终端：阜宁002终端于2018-08-11 19:32:19离线，故障码：80111000。', '2018-08-11 19:32:20', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('3', '智能运管终端003', '阜宁003终端', 'HDKJABCD12345680', null, null, '1001', '10001', null, null, '119.7928210', '33.8633770', null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('4', '智能运管终端004', '阜宁004终端', 'HDKJABCD12345681', null, null, '1001', '10001', null, null, '119.6209210', '33.8489870', null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('5', '智能运管终端005', '阜宁005终端', 'HDKJABCD12345682', null, null, '1001', '10001', null, null, '119.5427330', '33.7068710', null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('6', '智能运管终端006', '阜宁006终端', 'HDKJABCD12345683', null, null, '1001', '10001', null, null, '119.6226460', '33.6020540', null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('7', '智能运管终端007', '阜宁007终端', 'HDKJABCD12345684', null, null, '1001', '10001', null, null, '119.8129430', '33.6246630', null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('8', '智能运管终端008', '阜宁008终端', 'HDKJABCD12345685', null, null, '1001', '10001', null, null, '120.0417590', '33.7246490', null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('9', '摄像机01', '001-01摄像机', null, null, null, '2001', '20001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '摄像机：001-01摄像机于2018-08-10 15:08:41状态未知，故障码：80111010。', '2018-08-10 15:08:42', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('10', '补光灯01', '001-01补光灯', null, null, null, '3001', '30001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '补光灯：001-01补光灯于2018-08-10 15:08:41状态未知，故障码：80111010。', '2018-08-10 15:08:42', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('11', '闪光灯01', '001-01闪光灯', null, null, null, '4001', '40001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '闪光灯：001-01闪光灯于2018-08-10 15:08:41状态未知，故障码：80111010。', '2018-08-10 15:08:42', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('12', '光端机01', '001-01光端机', null, null, null, '5001', '50001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '光端机：001-01光端机于2018-08-10 15:08:41状态未知，故障码：80111010。', '2018-08-10 15:08:42', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('13', '风扇01', '001-01风扇', null, null, null, '6001', '60001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '风扇：001-01风扇于2018-08-10 15:08:41状态未知，故障码：80111010。', '2018-08-10 15:08:42', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('14', '柜门01', '001-01柜门', null, null, null, '7001', '70001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '柜门：001-01柜门于2018-08-10 15:08:41状态未知，故障码：80111010。', '2018-08-10 15:08:42', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('15', '箱内温度计01', '001-01箱内温度计', null, null, null, '8001', '80001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '箱内温度计：001-01箱内温度计于2018-08-10 15:08:41状态未知，故障码：80111010。', '2018-08-10 15:08:42', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('16', '箱外温度计01', '001-01箱外温度计', null, null, null, '9001', '90001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '箱外温度计：001-01箱外温度计于2018-08-10 15:08:41状态未知，故障码：80111010。', '2018-08-10 15:08:42', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('17', '摄像机02', '02摄像机', null, null, null, '2001', '20001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '摄像机：02摄像机于2018-08-11 19:32:20状态未知，故障码：80111010。', '2018-08-11 19:32:20', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('18', '补光灯02', '02补光灯', null, null, null, '3001', '30001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '补光灯：02补光灯于2018-08-11 19:32:20状态未知，故障码：80111010。', '2018-08-11 19:32:20', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('19', '闪光灯02', '02闪光灯', null, null, null, '4001', '40001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '闪光灯：02闪光灯于2018-08-11 19:32:20状态未知，故障码：80111010。', '2018-08-11 19:32:20', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('20', '光端机02', '02光端机', null, null, null, '5001', '50001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '光端机：02光端机于2018-08-11 19:32:20状态未知，故障码：80111010。', '2018-08-11 19:32:20', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('21', '风扇02', '02风扇', null, null, null, '6001', '60001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '风扇：02风扇于2018-08-11 19:32:20状态未知，故障码：80111010。', '2018-08-11 19:32:20', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('22', '柜门02', '02柜门', null, null, null, '7001', '70001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '柜门：02柜门于2018-08-11 19:32:20状态未知，故障码：80111010。', '2018-08-11 19:32:20', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('23', '箱内温度计02', '02箱内温度计', null, null, null, '8001', '80001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '箱内温度计：02箱内温度计于2018-08-11 19:32:20状态未知，故障码：80111010。', '2018-08-11 19:32:20', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('24', '箱外温度计02', '02箱外温度计', null, null, null, '9001', '90001', null, null, null, null, null, '3', '1010', '80111010', '11', null, '箱外温度计：02箱外温度计于2018-08-11 19:32:20状态未知，故障码：80111010。', '2018-08-11 19:32:20', null, '#ff0000', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('25', '摄像机03', '03摄像机', null, null, null, '2001', '20001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('26', '补光灯03', '03补光灯', null, null, null, '3001', '30001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('27', '闪光灯03', '03闪光灯', null, null, null, '4001', '40001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('28', '光端机03', '03光端机', null, null, null, '5001', '50001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('29', '风扇03', '03风扇', null, null, null, '6001', '60001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('30', '柜门03', '03柜门', null, null, null, '7001', '70001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('31', '箱内温度计03', '03箱内温度计', null, null, null, '8001', '80001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('32', '箱外温度计03', '03箱外温度计', null, null, null, '9001', '90001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('33', '摄像机04', '04摄像机', null, null, null, '2001', '20001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('34', '补光灯04', '04补光灯', null, null, null, '3001', '30001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('35', '闪光灯04', '04闪光灯', null, null, null, '4001', '40001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('36', '光端机04', '04光端机', null, null, null, '5001', '50001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('37', '风扇04', '04风扇', null, null, null, '6001', '60001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('38', '柜门04', '04柜门', null, null, null, '7001', '70001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('39', '箱内温度计04', '04箱内温度计', null, null, null, '8001', '80001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('40', '箱外温度计04', '04箱外温度计', null, null, null, '9001', '90001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('41', '摄像机05', '05摄像机', null, null, null, '2001', '20001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('42', '补光灯05', '05补光灯', null, null, null, '3001', '30001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('43', '闪光灯05', '05闪光灯', null, null, null, '4001', '40001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('44', '光端机05', '05光端机', null, null, null, '5001', '50001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('45', '风扇05', '05风扇', null, null, null, '6001', '60001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('46', '柜门05', '05柜门', null, null, null, '7001', '70001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('47', '箱内温度计05', '05箱内温度计', null, null, null, '8001', '80001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('48', '箱外温度计05', '05箱外温度计', null, null, null, '9001', '90001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('49', '摄像机06', '06摄像机', null, null, null, '2001', '20001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('50', '补光灯06', '06补光灯', null, null, null, '3001', '30001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('51', '闪光灯06', '06闪光灯', null, null, null, '4001', '40001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('52', '光端机06', '06光端机', null, null, null, '5001', '50001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('53', '风扇06', '06风扇', null, null, null, '6001', '60001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('54', '柜门06', '06柜门', null, null, null, '7001', '70001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('55', '箱内温度计06', '06箱内温度计', null, null, null, '8001', '80001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('56', '箱外温度计06', '06箱外温度计', null, null, null, '9001', '90001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('57', '摄像机07', '07摄像机', null, null, null, '2001', '20001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('58', '补光灯07', '07补光灯', null, null, null, '3001', '30001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('59', '闪光灯07', '07闪光灯', null, null, null, '4001', '40001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('60', '光端机07', '07光端机', null, null, null, '5001', '50001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('61', '风扇07', '07风扇', null, null, null, '6001', '60001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('62', '柜门07', '07柜门', null, null, null, '7001', '70001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('63', '箱内温度计07', '07箱内温度计', null, null, null, '8001', '80001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('64', '箱外温度计07', '07箱外温度计', null, null, null, '9001', '90001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('65', '摄像机08', '08摄像机', null, null, null, '2001', '20001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('66', '补光灯08', '08补光灯', null, null, null, '3001', '30001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('67', '闪光灯08', '08闪光灯', null, null, null, '4001', '40001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('68', '光端机08', '08光端机', null, null, null, '5001', '50001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('69', '风扇08', '08风扇', null, null, null, '6001', '60001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('70', '柜门08', '08柜门', null, null, null, '7001', '70001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('71', '箱内温度计08', '08箱内温度计', null, null, null, '8001', '80001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);
INSERT INTO `tbl_res_base` VALUES ('72', '箱外温度计08', '08箱外温度计', null, null, null, '9001', '90001', null, null, null, null, null, '1', null, null, null, null, null, null, null, '#04bbb7', '1', null, null, null, null);

-- ----------------------------
-- Table structure for tbl_res_maintype
-- ----------------------------
DROP TABLE IF EXISTS `tbl_res_maintype`;
CREATE TABLE `tbl_res_maintype` (
  `res_mtype_id` int(11) NOT NULL,
  `res_mtype_name` varchar(50) NOT NULL COMMENT '资源主类型名称',
  `res_mtype_desc` varchar(255) DEFAULT NULL COMMENT '资源主类型描述',
  `showorder` int(11) NOT NULL,
  `delflag` int(11) NOT NULL,
  `useflag` int(11) NOT NULL,
  PRIMARY KEY (`res_mtype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源主类型';

-- ----------------------------
-- Records of tbl_res_maintype
-- ----------------------------
INSERT INTO `tbl_res_maintype` VALUES ('1001', '智能终端', null, '1', '0', '1');
INSERT INTO `tbl_res_maintype` VALUES ('2001', '摄像机', null, '2', '1', '1');
INSERT INTO `tbl_res_maintype` VALUES ('3001', '补光灯', null, '3', '1', '1');
INSERT INTO `tbl_res_maintype` VALUES ('4001', '闪光灯', null, '4', '1', '1');
INSERT INTO `tbl_res_maintype` VALUES ('5001', '光端机', null, '5', '1', '1');
INSERT INTO `tbl_res_maintype` VALUES ('6001', '风扇', null, '6', '1', '1');
INSERT INTO `tbl_res_maintype` VALUES ('7001', '柜门', null, '7', '1', '1');
INSERT INTO `tbl_res_maintype` VALUES ('8001', '箱内温度计', null, '8', '1', '1');
INSERT INTO `tbl_res_maintype` VALUES ('9001', '箱外温度计', null, '9', '1', '1');

-- ----------------------------
-- Table structure for tbl_res_mo_abnormal_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_res_mo_abnormal_info`;
CREATE TABLE `tbl_res_mo_abnormal_info` (
  `res_abnormal_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `res_id` int(11) NOT NULL,
  `mo_th_id` int(11) DEFAULT NULL,
  `mo_kpi_id` int(11) NOT NULL,
  `mo_abnormal_id` int(11) NOT NULL,
  `res_abnormal_code` int(11) DEFAULT NULL,
  `res_abnormal_name` varchar(100) DEFAULT NULL,
  `res_abnormallevel_id` int(11) NOT NULL,
  `res_abnormaldesc` varchar(500) NOT NULL COMMENT '根据mo_abnormal_showtemplate转换得到的异常描述信息',
  `res_abnomaltime` datetime NOT NULL,
  `res_recoverytime` datetime DEFAULT NULL,
  `res_abnormalvalue` varchar(50) DEFAULT NULL,
  `res_revoveryvalue` varchar(50) DEFAULT NULL,
  `res_abnormalstatus` int(11) NOT NULL COMMENT '异常状态：1 异常 0 异常恢复',
  PRIMARY KEY (`res_abnormal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='资源监控异常信息';

-- ----------------------------
-- Records of tbl_res_mo_abnormal_info
-- ----------------------------
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('7', '1', '1', '1', '2000', '80112000', '电压过低', '11', '智能终端：阜宁001终端于2018-08-04 16:19:57电压值过低，低于220.00V，当前值为：214.7V，故障码：80112000。', '2018-08-04 16:19:57', '2018-08-04 16:25:24', '214.7', null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('8', '1', '3', '2', '2002', '80112002', '电流过大', '11', '智能终端：阜宁001终端于2018-08-04 16:34:18电流过大，大于4.00A，当前值为：04.45A，故障码：80112002。', '2018-08-04 16:34:19', '2018-08-04 16:34:40', '04.45', null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('9', '1', '5', '3', '2031', '80122031', '功率大异常', '12', '智能终端：阜宁001终端于2018-08-04 16:38:30功率大异常，额定值为：2000.00W，当前值为：0548W，阈值为[100.00,80.00]，故障码：80122031。', '2018-08-04 16:38:30', '2018-08-04 17:32:13', '0548', null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('12', '15', '15', '4', '2027', '80122027', '温度高异常', '12', '箱内温度计：001-01箱内温度计于2018-08-06 10:08:41温度高异常，当前值为：25.0℃，阈值为[70.00℃,60.00℃]，故障码：80122027。', '2018-08-06 10:08:42', '2018-08-06 10:17:47', '25.0', '25.0', '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('13', '16', '19', '4', '2027', '80122027', '温度高异常', '12', '箱外温度计：001-01箱外温度计于2018-08-06 10:09:11温度高异常，当前值为：44.8℃，阈值为[80.00℃,70.00℃]，故障码：80122027。', '2018-08-06 10:09:11', '2018-08-06 10:17:47', '44.8', '44.8', '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('14', '14', '12', '5', '2007', '80142007', '柜门打开', '14', '柜门：001-01柜门于2018-08-06 09:22:10处于打开状态，故障码：80142007。', '2018-08-06 09:22:11', '2018-08-06 09:23:02', 'open', 'open', '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('15', '13', '11', '5', '2006', '80112006', '风扇停止', '11', '风扇：001-01风扇于2018-08-06 09:54:54停止运转，故障码：80112006。', '2018-08-06 09:54:55', '2018-08-06 10:07:58', 'close', 'close', '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('20', '1', null, '10000', '1011', '80111011', '设备掉电', '11', '智能终端：阜宁001终端于2018-08-06 10:59:06掉电，故障码：80111011。', '2018-08-06 10:59:03', '2018-08-06 13:40:43', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('21', '13', '11', '5', '2006', '80112006', '风扇停止', '11', '风扇：001-01风扇于2018-08-08 17:28:08停止运转，故障码：80112006。', '2018-08-08 17:28:09', '2018-08-09 10:02:02', 'close', 'close', '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('22', '14', '12', '5', '2007', '80142007', '柜门打开', '14', '柜门：001-01柜门于2018-08-08 17:28:08处于打开状态，故障码：80142007。', '2018-08-08 17:28:09', '2018-08-09 09:32:47', 'open', 'open', '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('23', '1', null, '10000', '1011', '80111011', '设备掉电', '11', '智能终端：阜宁001终端于2018-08-06 15:54:02掉电，故障码：80111011。', '2018-08-06 15:54:03', '2018-08-06 15:55:16', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('25', '1', null, '10000', '1011', '80111011', '设备掉电', '11', '智能终端：阜宁001终端于2018-08-06 17:22:16掉电，故障码：80111011。', '2018-08-06 17:22:17', '2018-08-06 17:24:32', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('26', '1', null, '10000', '1011', '80111011', '设备掉电', '11', '智能终端：阜宁001终端于2018-08-07 09:41:09掉电，故障码：80111011。', '2018-08-07 09:41:10', '2018-08-07 09:43:54', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('27', '1', null, '10000', '1011', '80111011', '设备掉电', '11', '智能终端：阜宁001终端于2018-08-07 09:48:11掉电，故障码：80111011。', '2018-08-07 09:48:12', '2018-08-07 10:23:23', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('28', '1', null, '10000', '1011', '80111011', '设备掉电', '11', '智能终端：阜宁001终端于2018-08-07 10:31:58掉电，故障码：80111011。', '2018-08-07 10:31:58', '2018-08-07 10:37:25', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('29', '1', null, '10000', '1011', '80111011', '设备掉电', '11', '智能终端：阜宁001终端于2018-08-07 14:48:14掉电，故障码：80111011。', '2018-08-07 14:48:15', '2018-08-07 14:49:12', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('30', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-07 14:54:51离线，故障码：80111000。', '2018-08-07 14:54:48', '2018-08-07 14:55:57', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('31', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-07 14:56:38离线，故障码：80111000。', '2018-08-07 14:56:39', '2018-08-07 15:00:02', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('32', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-07 15:00:48离线，故障码：80111000。', '2018-08-07 15:00:46', '2018-08-07 15:01:09', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('33', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-07 15:02:45离线，故障码：80111000。', '2018-08-07 15:02:46', '2018-08-07 15:03:09', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('34', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-07 15:05:01离线，故障码：80111000。', '2018-08-07 15:05:02', '2018-08-07 15:05:27', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('35', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-07 15:48:00离线，故障码：80111000。', '2018-08-07 15:48:00', '2018-08-07 15:48:41', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('36', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-07 15:50:44离线，故障码：80111000。', '2018-08-07 15:50:45', '2018-08-07 15:51:06', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('37', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-07 16:00:41离线，故障码：80111000。', '2018-08-07 16:00:42', '2018-08-07 16:01:43', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('38', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-08 10:00:55离线，故障码：80111000。', '2018-08-08 10:00:55', '2018-08-08 10:01:38', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('39', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-08 10:03:02离线，故障码：80111000。', '2018-08-08 10:03:03', '2018-08-08 10:03:47', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('40', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-08 10:04:08离线，故障码：80111000。', '2018-08-08 10:04:08', '2018-08-08 10:05:31', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('41', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-08 10:07:34离线，故障码：80111000。', '2018-08-08 10:07:34', '2018-08-08 10:07:55', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('42', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-08 10:10:18离线，故障码：80111000。', '2018-08-08 10:10:18', '2018-08-08 10:10:39', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('43', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-08 10:21:34离线，故障码：80111000。', '2018-08-08 10:21:34', '2018-08-08 10:21:55', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('44', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-08 10:26:20离线，故障码：80111000。', '2018-08-08 10:26:21', '2018-08-08 10:27:22', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('45', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-08 10:29:25离线，故障码：80111000。', '2018-08-08 10:29:26', '2018-08-08 10:30:07', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('46', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-08 10:31:28离线，故障码：80111000。', '2018-08-08 10:31:29', '2018-08-08 10:32:30', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('47', '1', '1', '1', '2000', '80112000', '电压过低', '11', '智能终端：阜宁001终端于2018-08-10 15:08:23电压值过低，低于220.00V，当前值为：216.8V，故障码：80112000。', '2018-08-10 14:58:52', '2018-08-09 08:56:03', '216.8', null, '1');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('48', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-09 09:31:24离线，故障码：80111000。', '2018-08-09 09:31:24', '2018-08-09 09:32:47', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('49', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-09 09:34:29离线，故障码：80111000。', '2018-08-09 09:34:30', '2018-08-09 09:37:36', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('50', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-09 11:06:05离线，故障码：80111000。', '2018-08-09 11:06:06', '2018-08-09 15:33:59', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('51', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-09 16:04:50离线，故障码：80111000。', '2018-08-09 16:04:50', '2018-08-10 14:18:22', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('52', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:33:22离线，故障码：80111000。', '2018-08-10 14:33:22', '2018-08-10 14:47:17', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('53', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:47:25离线，故障码：80111000。', '2018-08-10 14:47:26', '2018-08-10 14:47:42', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('54', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:47:46离线，故障码：80111000。', '2018-08-10 14:47:46', '2018-08-10 14:48:03', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('55', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:48:06离线，故障码：80111000。', '2018-08-10 14:48:06', '2018-08-10 14:48:24', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('56', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:48:26离线，故障码：80111000。', '2018-08-10 14:48:26', '2018-08-10 14:48:44', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('57', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:48:46离线，故障码：80111000。', '2018-08-10 14:48:47', '2018-08-10 14:49:06', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('58', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:49:06离线，故障码：80111000。', '2018-08-10 14:49:07', '2018-08-10 14:49:40', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('59', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:49:46离线，故障码：80111000。', '2018-08-10 14:49:47', '2018-08-10 14:53:00', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('60', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:53:08离线，故障码：80111000。', '2018-08-10 14:53:08', '2018-08-10 14:53:21', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('61', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:53:28离线，故障码：80111000。', '2018-08-10 14:53:29', '2018-08-10 14:53:41', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('62', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:53:49离线，故障码：80111000。', '2018-08-10 14:53:49', '2018-08-10 14:54:02', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('63', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:54:09离线，故障码：80111000。', '2018-08-10 14:54:09', '2018-08-10 14:54:23', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('64', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:54:29离线，故障码：80111000。', '2018-08-10 14:54:30', '2018-08-10 14:54:43', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('65', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:54:49离线，故障码：80111000。', '2018-08-10 14:54:50', '2018-08-10 14:55:04', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('66', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:55:10离线，故障码：80111000。', '2018-08-10 14:55:10', '2018-08-10 14:55:25', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('67', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:55:30离线，故障码：80111000。', '2018-08-10 14:55:30', '2018-08-10 14:55:45', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('68', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:55:50离线，故障码：80111000。', '2018-08-10 14:55:51', '2018-08-10 14:56:06', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('69', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:56:10离线，故障码：80111000。', '2018-08-10 14:56:11', '2018-08-10 14:56:27', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('70', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:56:31离线，故障码：80111000。', '2018-08-10 14:56:31', '2018-08-10 14:56:48', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('71', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:56:51离线，故障码：80111000。', '2018-08-10 14:56:51', '2018-08-10 14:57:08', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('72', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:57:11离线，故障码：80111000。', '2018-08-10 14:57:11', '2018-08-10 14:57:29', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('73', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:57:31离线，故障码：80111000。', '2018-08-10 14:57:32', '2018-08-10 14:57:50', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('74', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:57:52离线，故障码：80111000。', '2018-08-10 14:57:52', '2018-08-10 14:58:10', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('75', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:58:12离线，故障码：80111000。', '2018-08-10 14:58:12', '2018-08-10 14:58:31', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('76', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:58:33离线，故障码：80111000。', '2018-08-10 14:58:33', '2018-08-10 14:58:52', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('77', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:58:53离线，故障码：80111000。', '2018-08-10 14:58:53', '2018-08-10 14:59:20', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('78', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:59:33离线，故障码：80111000。', '2018-08-10 14:59:34', '2018-08-10 14:59:41', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('79', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 14:59:54离线，故障码：80111000。', '2018-08-10 14:59:55', '2018-08-10 15:00:02', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('80', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:00:14离线，故障码：80111000。', '2018-08-10 15:00:15', '2018-08-10 15:00:23', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('81', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:00:35离线，故障码：80111000。', '2018-08-10 15:00:35', '2018-08-10 15:00:44', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('82', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:00:55离线，故障码：80111000。', '2018-08-10 15:00:56', '2018-08-10 15:01:05', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('83', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:01:15离线，故障码：80111000。', '2018-08-10 15:01:16', '2018-08-10 15:01:26', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('84', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:01:35离线，故障码：80111000。', '2018-08-10 15:01:36', '2018-08-10 15:01:47', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('85', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:01:56离线，故障码：80111000。', '2018-08-10 15:01:56', '2018-08-10 15:02:08', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('86', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:02:16离线，故障码：80111000。', '2018-08-10 15:02:16', '2018-08-10 15:02:28', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('87', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:02:36离线，故障码：80111000。', '2018-08-10 15:02:37', '2018-08-10 15:02:49', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('88', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:02:56离线，故障码：80111000。', '2018-08-10 15:02:57', '2018-08-10 15:03:11', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('89', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:03:16离线，故障码：80111000。', '2018-08-10 15:03:17', '2018-08-10 15:03:32', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('90', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:03:37离线，故障码：80111000。', '2018-08-10 15:03:37', '2018-08-10 15:03:52', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('91', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:03:57离线，故障码：80111000。', '2018-08-10 15:03:57', '2018-08-10 15:04:13', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('92', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:04:17离线，故障码：80111000。', '2018-08-10 15:04:18', '2018-08-10 15:04:34', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('93', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:04:37离线，故障码：80111000。', '2018-08-10 15:04:38', '2018-08-10 15:04:55', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('94', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:04:58离线，故障码：80111000。', '2018-08-10 15:04:59', '2018-08-10 15:05:16', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('95', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:05:18离线，故障码：80111000。', '2018-08-10 15:05:19', '2018-08-10 15:05:37', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('96', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:05:39离线，故障码：80111000。', '2018-08-10 15:05:39', '2018-08-10 15:05:58', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('97', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:05:59离线，故障码：80111000。', '2018-08-10 15:06:00', '2018-08-10 15:06:19', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('98', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:06:20离线，故障码：80111000。', '2018-08-10 15:06:20', '2018-08-10 15:06:40', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('99', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:06:40离线，故障码：80111000。', '2018-08-10 15:06:40', '2018-08-10 15:07:01', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('100', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:07:00离线，故障码：80111000。', '2018-08-10 15:07:00', '2018-08-10 15:07:22', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('101', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:07:20离线，故障码：80111000。', '2018-08-10 15:07:21', '2018-08-10 15:07:42', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('102', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:08:00离线，故障码：80111000。', '2018-08-10 15:08:01', '2018-08-10 15:08:23', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('103', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:08:21离线，故障码：80111000。', '2018-08-10 15:08:21', '2018-08-11 13:40:45', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('104', '1', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁001终端于2018-08-10 15:08:41离线，故障码：80111000。', '2018-08-10 15:08:42', null, null, null, '1');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('105', '2', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁002终端于2018-08-11 19:19:33离线，故障码：80111000。', '2018-08-11 19:19:33', '2018-08-11 19:28:17', null, null, '0');
INSERT INTO `tbl_res_mo_abnormal_info` VALUES ('106', '2', null, '10000', '1000', '80111000', '设备离线', '11', '智能终端：阜宁002终端于2018-08-11 19:32:19离线，故障码：80111000。', '2018-08-11 19:32:20', null, null, null, '1');

-- ----------------------------
-- Table structure for tbl_res_status
-- ----------------------------
DROP TABLE IF EXISTS `tbl_res_status`;
CREATE TABLE `tbl_res_status` (
  `res_status` int(11) NOT NULL,
  `res_status_name` varchar(50) NOT NULL,
  `res_status_color` varchar(20) NOT NULL,
  `showorder` int(11) NOT NULL,
  `delflag` int(11) NOT NULL,
  `useflag` int(11) NOT NULL,
  PRIMARY KEY (`res_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源状态定义';

-- ----------------------------
-- Records of tbl_res_status
-- ----------------------------
INSERT INTO `tbl_res_status` VALUES ('1', '正常', '#04bbb7', '4', '0', '1');
INSERT INTO `tbl_res_status` VALUES ('2', '故障', '#ff0000', '3', '0', '1');
INSERT INTO `tbl_res_status` VALUES ('3', '未知', '#1a689f', '2', '0', '1');
INSERT INTO `tbl_res_status` VALUES ('4', '离线', '#6d6c6c', '1', '0', '1');

-- ----------------------------
-- Table structure for tbl_res_stype_kpi
-- ----------------------------
DROP TABLE IF EXISTS `tbl_res_stype_kpi`;
CREATE TABLE `tbl_res_stype_kpi` (
  `id` int(11) NOT NULL,
  `res_stype_id` int(11) NOT NULL,
  `mo_kpi_id` int(11) NOT NULL,
  `stype_flag` int(11) NOT NULL COMMENT '子类型标记：1 该规则应用于该类型下所有子类型， 2 该规则只应用于该类型本身',
  `useflag` int(11) NOT NULL,
  `user_id_create` bigint(20) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `user_id_mod` bigint(20) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源子类型监控的指标';

-- ----------------------------
-- Records of tbl_res_stype_kpi
-- ----------------------------
INSERT INTO `tbl_res_stype_kpi` VALUES ('1', '10001', '1', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('2', '10001', '2', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('3', '10001', '3', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('4', '20001', '2', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('5', '20001', '6', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('6', '30001', '2', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('7', '30001', '6', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('8', '40001', '2', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('9', '40001', '6', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('10', '50001', '2', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('11', '50001', '6', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('12', '60001', '5', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('13', '70001', '5', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('14', '80001', '4', '1', '1', null, null, null, null);
INSERT INTO `tbl_res_stype_kpi` VALUES ('15', '90001', '4', '1', '1', null, null, null, null);

-- ----------------------------
-- Table structure for tbl_res_subtype
-- ----------------------------
DROP TABLE IF EXISTS `tbl_res_subtype`;
CREATE TABLE `tbl_res_subtype` (
  `res_stype_id` int(11) NOT NULL,
  `res_mtype_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `res_stype_name` varchar(50) NOT NULL,
  `res_stype_desc` varchar(255) DEFAULT NULL,
  `showorder` int(11) NOT NULL,
  `delflag` int(11) NOT NULL,
  `useflag` int(11) NOT NULL,
  PRIMARY KEY (`res_stype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源子类型';

-- ----------------------------
-- Records of tbl_res_subtype
-- ----------------------------
INSERT INTO `tbl_res_subtype` VALUES ('10001', '1001', null, '智能终端', null, '1', '0', '1');
INSERT INTO `tbl_res_subtype` VALUES ('20001', '2001', null, '摄像机', null, '2', '1', '1');
INSERT INTO `tbl_res_subtype` VALUES ('30001', '3001', null, '补光灯', null, '3', '1', '1');
INSERT INTO `tbl_res_subtype` VALUES ('40001', '4001', null, '闪光灯', null, '4', '1', '1');
INSERT INTO `tbl_res_subtype` VALUES ('50001', '5001', null, '光端机', null, '5', '1', '1');
INSERT INTO `tbl_res_subtype` VALUES ('60001', '6001', null, '风扇', null, '6', '1', '1');
INSERT INTO `tbl_res_subtype` VALUES ('70001', '7001', null, '柜门', null, '7', '1', '1');
INSERT INTO `tbl_res_subtype` VALUES ('80001', '8001', null, '箱内温度计', null, '8', '1', '1');
INSERT INTO `tbl_res_subtype` VALUES ('90001', '9001', null, '箱外温度计', null, '9', '1', '1');

-- ----------------------------
-- Table structure for tbl_res_terminal
-- ----------------------------
DROP TABLE IF EXISTS `tbl_res_terminal`;
CREATE TABLE `tbl_res_terminal` (
  `id` int(11) NOT NULL,
  `res_id_terminal` int(11) NOT NULL COMMENT '智能终端资源ID',
  `res_id` int(11) NOT NULL COMMENT '智能终端连接设备资源ID',
  `link_port` int(11) NOT NULL COMMENT '链接端口',
  `useflag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源和智能终端关系表';

-- ----------------------------
-- Records of tbl_res_terminal
-- ----------------------------
INSERT INTO `tbl_res_terminal` VALUES ('1', '1', '9', '21', '1');
INSERT INTO `tbl_res_terminal` VALUES ('2', '1', '10', '22', '1');
INSERT INTO `tbl_res_terminal` VALUES ('3', '1', '11', '23', '1');
INSERT INTO `tbl_res_terminal` VALUES ('4', '1', '12', '24', '1');
INSERT INTO `tbl_res_terminal` VALUES ('5', '1', '13', '28', '1');
INSERT INTO `tbl_res_terminal` VALUES ('6', '1', '14', '27', '1');
INSERT INTO `tbl_res_terminal` VALUES ('7', '1', '15', '25', '1');
INSERT INTO `tbl_res_terminal` VALUES ('8', '1', '16', '26', '1');
INSERT INTO `tbl_res_terminal` VALUES ('9', '2', '17', '21', '1');
INSERT INTO `tbl_res_terminal` VALUES ('10', '2', '18', '22', '1');
INSERT INTO `tbl_res_terminal` VALUES ('11', '2', '19', '23', '1');
INSERT INTO `tbl_res_terminal` VALUES ('12', '2', '20', '24', '1');
INSERT INTO `tbl_res_terminal` VALUES ('13', '2', '21', '28', '1');
INSERT INTO `tbl_res_terminal` VALUES ('14', '2', '22', '27', '1');
INSERT INTO `tbl_res_terminal` VALUES ('15', '2', '23', '25', '1');
INSERT INTO `tbl_res_terminal` VALUES ('16', '2', '24', '26', '1');
INSERT INTO `tbl_res_terminal` VALUES ('17', '3', '25', '21', '1');
INSERT INTO `tbl_res_terminal` VALUES ('18', '3', '26', '22', '1');
INSERT INTO `tbl_res_terminal` VALUES ('19', '3', '27', '23', '1');
INSERT INTO `tbl_res_terminal` VALUES ('20', '3', '28', '24', '1');
INSERT INTO `tbl_res_terminal` VALUES ('21', '3', '29', '28', '1');
INSERT INTO `tbl_res_terminal` VALUES ('22', '3', '30', '27', '1');
INSERT INTO `tbl_res_terminal` VALUES ('23', '3', '31', '25', '1');
INSERT INTO `tbl_res_terminal` VALUES ('24', '3', '32', '26', '1');
INSERT INTO `tbl_res_terminal` VALUES ('25', '4', '33', '21', '1');
INSERT INTO `tbl_res_terminal` VALUES ('26', '4', '34', '22', '1');
INSERT INTO `tbl_res_terminal` VALUES ('27', '4', '35', '23', '1');
INSERT INTO `tbl_res_terminal` VALUES ('28', '4', '36', '24', '1');
INSERT INTO `tbl_res_terminal` VALUES ('29', '4', '37', '28', '1');
INSERT INTO `tbl_res_terminal` VALUES ('30', '4', '38', '27', '1');
INSERT INTO `tbl_res_terminal` VALUES ('31', '4', '39', '25', '1');
INSERT INTO `tbl_res_terminal` VALUES ('32', '4', '40', '26', '1');
INSERT INTO `tbl_res_terminal` VALUES ('33', '5', '41', '21', '1');
INSERT INTO `tbl_res_terminal` VALUES ('34', '5', '42', '22', '1');
INSERT INTO `tbl_res_terminal` VALUES ('35', '5', '43', '23', '1');
INSERT INTO `tbl_res_terminal` VALUES ('36', '5', '44', '24', '1');
INSERT INTO `tbl_res_terminal` VALUES ('37', '5', '45', '28', '1');
INSERT INTO `tbl_res_terminal` VALUES ('38', '5', '46', '27', '1');
INSERT INTO `tbl_res_terminal` VALUES ('39', '5', '47', '25', '1');
INSERT INTO `tbl_res_terminal` VALUES ('40', '5', '48', '26', '1');
INSERT INTO `tbl_res_terminal` VALUES ('41', '6', '49', '21', '1');
INSERT INTO `tbl_res_terminal` VALUES ('42', '6', '50', '22', '1');
INSERT INTO `tbl_res_terminal` VALUES ('43', '6', '51', '23', '1');
INSERT INTO `tbl_res_terminal` VALUES ('44', '6', '52', '24', '1');
INSERT INTO `tbl_res_terminal` VALUES ('45', '6', '53', '28', '1');
INSERT INTO `tbl_res_terminal` VALUES ('46', '6', '54', '27', '1');
INSERT INTO `tbl_res_terminal` VALUES ('47', '6', '55', '25', '1');
INSERT INTO `tbl_res_terminal` VALUES ('48', '6', '56', '26', '1');
INSERT INTO `tbl_res_terminal` VALUES ('49', '7', '57', '21', '1');
INSERT INTO `tbl_res_terminal` VALUES ('50', '7', '58', '22', '1');
INSERT INTO `tbl_res_terminal` VALUES ('51', '7', '59', '23', '1');
INSERT INTO `tbl_res_terminal` VALUES ('52', '7', '60', '24', '1');
INSERT INTO `tbl_res_terminal` VALUES ('53', '7', '61', '28', '1');
INSERT INTO `tbl_res_terminal` VALUES ('54', '7', '62', '27', '1');
INSERT INTO `tbl_res_terminal` VALUES ('55', '7', '63', '25', '1');
INSERT INTO `tbl_res_terminal` VALUES ('56', '7', '64', '26', '1');
INSERT INTO `tbl_res_terminal` VALUES ('57', '8', '65', '21', '1');
INSERT INTO `tbl_res_terminal` VALUES ('58', '8', '66', '22', '1');
INSERT INTO `tbl_res_terminal` VALUES ('59', '8', '67', '23', '1');
INSERT INTO `tbl_res_terminal` VALUES ('60', '8', '68', '24', '1');
INSERT INTO `tbl_res_terminal` VALUES ('61', '8', '69', '28', '1');
INSERT INTO `tbl_res_terminal` VALUES ('62', '8', '70', '27', '1');
INSERT INTO `tbl_res_terminal` VALUES ('63', '8', '71', '25', '1');
INSERT INTO `tbl_res_terminal` VALUES ('64', '8', '72', '26', '1');

-- ----------------------------
-- Table structure for tbl_sys_conf
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_conf`;
CREATE TABLE `tbl_sys_conf` (
  `id` int(11) NOT NULL,
  `c_type` int(11) NOT NULL COMMENT '配置项类型',
  `parakey` varchar(50) NOT NULL COMMENT '配置项名称',
  `paravalue` varchar(500) NOT NULL COMMENT '配置值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数配置表';

-- ----------------------------
-- Records of tbl_sys_conf
-- ----------------------------
INSERT INTO `tbl_sys_conf` VALUES ('1', '10000', 'Forget_Pwd', '请联系系统管理员\\r\\n 联系人：李某某 \\r\\n 联系电话：12345678912');
INSERT INTO `tbl_sys_conf` VALUES ('2', '20000', 'offline_count', '3');
INSERT INTO `tbl_sys_conf` VALUES ('3', '20000', 'offline_interval', '10');
INSERT INTO `tbl_sys_conf` VALUES ('4', '10000', 'center_longitude', '119.808788');
INSERT INTO `tbl_sys_conf` VALUES ('5', '10000', 'center_latitude', '33.764668');
INSERT INTO `tbl_sys_conf` VALUES ('6', '10000', 'sys_name', '阜宁县视频监控智能运维管理系统');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) NOT NULL COMMENT '父菜单主键',
  `menu_code` varchar(50) NOT NULL COMMENT '菜单代号,规范权限标识',
  `code` varchar(50) DEFAULT NULL COMMENT '代码控制权限标识符',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_type` int(2) NOT NULL COMMENT '菜单类型，1：菜单  2：业务操作',
  `num` int(11) DEFAULT NULL COMMENT '菜单的序号',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单地址',
  `icon` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2024 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '0', 'menu-a8617c317b204969a054fdag233das2l', 'user', '用户管理列表', '1', '1', '', '1.jpg');
INSERT INTO `tb_menu` VALUES ('2', '0', 'menu-afd83fc912eb44d29012049aae184fd4', 'data', '数据管理列表', '1', '1', '/api/data/manager', null);
INSERT INTO `tb_menu` VALUES ('3', '0', 'menu-a8617c31654653a054b68a343254565fss', 'system', '系统管理', '1', '1', null, 'anticon-laptop');
INSERT INTO `tb_menu` VALUES ('101', '1', 'menu-974abc42a78040e7ac74ceecb70c02b5', 'user:list', '用户管理列表', '1', '1', '/api/user/manager', null);
INSERT INTO `tb_menu` VALUES ('102', '1', 'menu-ad61fb43be7d46e7a81e37593042f543', 'role:list', '角色列表', '1', '2', '', null);
INSERT INTO `tb_menu` VALUES ('201', '2', 'menu-b16897c1c79b45b099939f5333530eaf', 'data:list1', '数据列表1', '1', '1', '', null);
INSERT INTO `tb_menu` VALUES ('202', '2', 'menu-ca569a407de7459f94e8b096180bc5e9', 'data:list2', '数据列表2', '1', '2', '', null);
INSERT INTO `tb_menu` VALUES ('301', '3', 'menu-a8617c31654653a054fdsg23asdg5423', 'system:info', '网站信息', '1', '1', '/home/system-management/website-information', null);
INSERT INTO `tb_menu` VALUES ('302', '3', 'menu-a8617c317b204969a054b653df212zg712', 'system:passwd', '密码修改', '1', '1', '/home/system-management/password-modification', null);
INSERT INTO `tb_menu` VALUES ('303', '3', 'menu-a8617c31t34ytrfsdfg3j5e36u121rfdg465u', 'system:word', '屏蔽词', '1', '1', '/home/system-management/banned-word', null);
INSERT INTO `tb_menu` VALUES ('1011', '101', 'menu-2fcaf1983232442e9484b48114fe59f6', 'user:add', '新增用户', '2', '1', '', null);
INSERT INTO `tb_menu` VALUES ('1012', '101', 'menu-b3556e9a47204c8abe1bcdd50047f6b4', 'user:edit', '编辑用户', '2', '2', '', null);
INSERT INTO `tb_menu` VALUES ('1013', '101', 'menu-08a093222ab04020886049b726a89a4c', 'user:delete', '删除用户', '2', '3', '', null);
INSERT INTO `tb_menu` VALUES ('1021', '102', 'menu-3da7d8a0b35e42c7b4d0b3c9cb710a7a', 'role:add', '新增角色', '2', '1', '', null);
INSERT INTO `tb_menu` VALUES ('1022', '102', 'menu-c9623470db144ca68e961e053a6cc8c9', 'role:edit', '权限设置', '2', '2', '', null);
INSERT INTO `tb_menu` VALUES ('1023', '102', 'menu-71f1c4edd8f24bf09db20867f7fdad2b', 'role:delete', '角色删除', '2', '3', '', null);
INSERT INTO `tb_menu` VALUES ('2010', '201', 'menu-ac1a7dfa51474de7b205eee5ad4d4dd2', 'data:importlist', '数据导入列表', '1', '1', '', null);
INSERT INTO `tb_menu` VALUES ('2011', '201', 'menu-d3b091cadf644e66b49364e51641b10b', 'data:atrlist', '属性设置列表', '1', '2', '', null);
INSERT INTO `tb_menu` VALUES ('2012', '201', 'menu-1492623fee854b43b3e49b80e877e4a2', 'data:upload', '上传按钮', '2', '1', '', null);
INSERT INTO `tb_menu` VALUES ('2021', '202', 'menu-964ed4bc416840b48cdc2ab51510cfcf', 'data:delete', '属性删除', '2', '1', '', null);
INSERT INTO `tb_menu` VALUES ('2022', '202', 'menu-d68955aa58e0428ea824680484a074e6', 'data:add', '添加属性', '2', '2', '', null);
INSERT INTO `tb_menu` VALUES ('2023', '202', 'menu-a8617c317b204969a054b68a3473d3b4', 'data:edit', '属性编辑', '2', '3', '', null);

-- ----------------------------
-- Table structure for tb_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_operation_log`;
CREATE TABLE `tb_operation_log` (
  `operation_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_description` varchar(64) DEFAULT NULL COMMENT '日志描述',
  `action_args` varchar(300) DEFAULT NULL COMMENT '方法参数',
  `user_no` varchar(50) DEFAULT NULL COMMENT '用户主键',
  `class_name` varchar(300) DEFAULT NULL COMMENT '类名称',
  `method_name` varchar(64) DEFAULT NULL COMMENT '方法名称',
  `ip` varchar(32) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `succeed` int(2) DEFAULT NULL COMMENT '是否成功 1:成功 2异常',
  `message` longtext COMMENT '异常堆栈信息',
  PRIMARY KEY (`operation_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=540 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of tb_operation_log
-- ----------------------------
INSERT INTO `tb_operation_log` VALUES ('204', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"13888888888\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:64857', '1532606863634', '2', 'Invalid bound statement (not found): com.hedian.mapper.MenuMapper.findMenuByRoleCode &#10; org.apache.ibatis.binding.MapperMethod$SqlCommand.<init>(MapperMethod.java:225) &#10; org.apache.ibatis.binding.MapperMethod.<init>(MapperMethod.java:48) &#10; org.apache.ibatis.binding.MapperProxy.cachedMapperMethod(MapperProxy.java:65) &#10; org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:58) &#10; com.sun.proxy.$Proxy108.findMenuByRoleCode(Unknown Source) &#10; com.hedian.service.impl.MenuServiceImpl.findMenuByRoleCode(MenuServiceImpl.java:42) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333) &#10; org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:207) &#10; com.sun.proxy.$Proxy109.findMenuByRoleCode(Unknown Source) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:190) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157) &#10; org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99) &#10; org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282) &#10; org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:213) &#10; com.sun.proxy.$Proxy109.findMenuByRoleCode(Unknown Source) &#10; com.hedian.service.impl.UserServiceImpl.getLoginUserAndMenuInfo(UserServiceImpl.java:83) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333) &#10; org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:207) &#10; com.sun.proxy.$Proxy110.getLoginUserAndMenuInfo(Unknown Source) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:190) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157) &#10; org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99) &#10; org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282) &#10; org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:213) &#10; com.sun.proxy.$Proxy110.getLoginUserAndMenuInfo(Unknown Source) &#10; com.hedian.controller.LoginController.login(LoginController.java:65) &#10; com.hedian.controller.LoginController$$FastClassBySpringCGLIB$$9dee13a5.invoke(<generated>) &#10; org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204) &#10; org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:721) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157) &#10; org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:97) &#10; com.hedian.aspect.RecordLogAspect.doHandlerAspect(RecordLogAspect.java:47) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.ParameterCheckAspect.validationPoint(ParameterCheckAspect.java:53) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618) &#10; org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:656) &#10; com.hedian.controller.LoginController$$EnhancerBySpringCGLIB$$723dfb07.login(<generated>) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) &#10; org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) &#10; org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:116) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) &#10; org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) &#10; org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963) &#10; org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897) &#10; org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) &#10; org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:648) &#10; org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:729) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:230) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61) &#10; org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108) &#10; org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:365) &#10; org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90) &#10; org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83) &#10; org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) &#10; org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) &#10; org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:474) &#10; org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) &#10; org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79) &#10; org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) &#10; org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:349) &#10; org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:783) &#10; org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) &#10; org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:798) &#10; org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1434) &#10; org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) &#10; java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) &#10; java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) &#10; org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) &#10; java.lang.Thread.run(Thread.java:748) &#10; ');
INSERT INTO `tb_operation_log` VALUES ('205', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"13888888888\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:65092', '1532607004416', '1', null);
INSERT INTO `tb_operation_log` VALUES ('206', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"13888888888\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:65368', '1532607153781', '1', null);
INSERT INTO `tb_operation_log` VALUES ('207', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"13888888888\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:49867', '1532653276862', '1', null);
INSERT INTO `tb_operation_log` VALUES ('208', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"13888888888\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:52031', '1532654725209', '1', null);
INSERT INTO `tb_operation_log` VALUES ('209', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"13888888888\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:57060', '1532657995294', '1', null);
INSERT INTO `tb_operation_log` VALUES ('210', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"13888888888\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:59740', '1532680547235', '1', null);
INSERT INTO `tb_operation_log` VALUES ('211', '前台密码登录接口:/login', '{\"001\":\"aaaa\",\"Seq\":\"why\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:61332', '1534494773528', '2', '缺少必填参数:mobile  passWord &#10; com.hedian.aspect.ValidationParamAspect.hasAllRequired(ValidationParamAspect.java:54) &#10; com.hedian.aspect.ValidationParamAspect.doHandlerAspect(ValidationParamAspect.java:26) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.RecordLogAspect.doHandlerAspect(RecordLogAspect.java:45) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.ParameterCheckAspect.validationPoint(ParameterCheckAspect.java:53) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618) &#10; org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:656) &#10; com.hedian.controller.LoginController$$EnhancerBySpringCGLIB$$4a7a1306.login(<generated>) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) &#10; org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) &#10; org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:116) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) &#10; org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) &#10; org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963) &#10; org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897) &#10; org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) &#10; org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:648) &#10; org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:729) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:230) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) &#10; org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) &#10; org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:474) &#10; org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) &#10; org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79) &#10; org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) &#10; org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:349) &#10; org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:783) &#10; org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) &#10; org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:798) &#10; org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1434) &#10; org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) &#10; java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) &#10; java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) &#10; org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) &#10; java.lang.Thread.run(Thread.java:748) &#10; ');
INSERT INTO `tb_operation_log` VALUES ('212', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"13888888888\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:61571', '1534494930077', '1', null);
INSERT INTO `tb_operation_log` VALUES ('213', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"13888888888\"}', 'user-006efece76c8433d8974c1a2f98422b6', 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:52475', '1534730272618', '1', null);
INSERT INTO `tb_operation_log` VALUES ('214', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.35:65419', '1534908939477', '2', '缺少必填参数:userName &#10; com.hedian.aspect.ValidationParamAspect.hasAllRequired(ValidationParamAspect.java:54) &#10; com.hedian.aspect.ValidationParamAspect.doHandlerAspect(ValidationParamAspect.java:26) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.RecordLogAspect.doHandlerAspect(RecordLogAspect.java:45) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.ParameterCheckAspect.validationPoint(ParameterCheckAspect.java:53) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618) &#10; org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:656) &#10; com.hedian.controller.LoginController$$EnhancerBySpringCGLIB$$fd65cc58.login(<generated>) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) &#10; org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) &#10; org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:116) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) &#10; org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) &#10; org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963) &#10; org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897) &#10; org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) &#10; org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:648) &#10; org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:729) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:230) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) &#10; org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) &#10; org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:474) &#10; org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) &#10; org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79) &#10; org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) &#10; org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:349) &#10; org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:783) &#10; org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) &#10; org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:798) &#10; org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1434) &#10; org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) &#10; java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) &#10; java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) &#10; org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) &#10; java.lang.Thread.run(Thread.java:748) &#10; ');
INSERT INTO `tb_operation_log` VALUES ('215', '前台密码登录接口:/login', '{\"password\":\"123456\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.35:65419', '1534908952868', '1', null);
INSERT INTO `tb_operation_log` VALUES ('216', '前台密码登录接口:/login', '{\"password\":\"123456\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.35:53411', '1534915524948', '1', null);
INSERT INTO `tb_operation_log` VALUES ('217', '前台密码登录接口:/login', '{\"password\":\"123456\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.35:53411', '1534915624530', '1', null);
INSERT INTO `tb_operation_log` VALUES ('218', '前台密码登录接口:/login', '{\"password\":\"123456\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:54038', '1534915862436', '1', null);
INSERT INTO `tb_operation_log` VALUES ('219', '前台密码登录接口:/login', '{\"password\":\"123456\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:54523', '1534916171249', '1', null);
INSERT INTO `tb_operation_log` VALUES ('220', '前台密码登录接口:/login', '{\"password\":\"123456\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:55399', '1534916743836', '1', null);
INSERT INTO `tb_operation_log` VALUES ('221', '前台密码登录接口:/login', '{\"password\":\"123456\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.35:55503', '1534916861776', '1', null);
INSERT INTO `tb_operation_log` VALUES ('222', '前台密码登录接口:/login', '{\"password\":\"123456\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:65325', '1534922296227', '1', null);
INSERT INTO `tb_operation_log` VALUES ('223', '前台密码登录接口:/login', '{\"password\":\"123456\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:57847', '1534935522325', '1', null);
INSERT INTO `tb_operation_log` VALUES ('224', '前台密码登录接口:/login', '{\"password\":\"123456\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:59430', '1534936366265', '1', null);
INSERT INTO `tb_operation_log` VALUES ('225', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:57408', '1534995305742', '2', '缺少必填参数:userName &#10; com.hedian.aspect.ValidationParamAspect.hasAllRequired(ValidationParamAspect.java:99) &#10; com.hedian.aspect.ValidationParamAspect.doHandlerAspect(ValidationParamAspect.java:40) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.RecordLogAspect.doHandlerAspect(RecordLogAspect.java:47) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.ParameterCheckAspect.validationPoint(ParameterCheckAspect.java:50) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618) &#10; org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:656) &#10; com.hedian.controller.LoginController$$EnhancerBySpringCGLIB$$488730c1.login(<generated>) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) &#10; org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) &#10; org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:116) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) &#10; org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) &#10; org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963) &#10; org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897) &#10; org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) &#10; org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:648) &#10; org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:729) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:230) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61) &#10; org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108) &#10; org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:365) &#10; org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90) &#10; org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83) &#10; org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) &#10; org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) &#10; org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:474) &#10; org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) &#10; org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79) &#10; org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) &#10; org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:349) &#10; org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:783) &#10; org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) &#10; org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:798) &#10; org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1434) &#10; org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) &#10; java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) &#10; java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) &#10; org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) &#10; java.lang.Thread.run(Thread.java:748) &#10; ');
INSERT INTO `tb_operation_log` VALUES ('226', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:57500', '1534995344530', '1', null);
INSERT INTO `tb_operation_log` VALUES ('227', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:57500', '1534995357784', '1', null);
INSERT INTO `tb_operation_log` VALUES ('228', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:57500', '1534995371306', '1', null);
INSERT INTO `tb_operation_log` VALUES ('229', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:57856', '1534995512492', '1', null);
INSERT INTO `tb_operation_log` VALUES ('230', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:57979', '1534995595973', '1', null);
INSERT INTO `tb_operation_log` VALUES ('231', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59918', '1534995742055', '1', null);
INSERT INTO `tb_operation_log` VALUES ('232', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59918', '1534995747037', '1', null);
INSERT INTO `tb_operation_log` VALUES ('233', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59918', '1534995798707', '1', null);
INSERT INTO `tb_operation_log` VALUES ('234', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:53269', '1534996016075', '1', null);
INSERT INTO `tb_operation_log` VALUES ('235', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:60853', '1535006195015', '1', null);
INSERT INTO `tb_operation_log` VALUES ('236', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50291', '1535008129392', '1', null);
INSERT INTO `tb_operation_log` VALUES ('237', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50305', '1535008132594', '1', null);
INSERT INTO `tb_operation_log` VALUES ('238', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50307', '1535008132592', '1', null);
INSERT INTO `tb_operation_log` VALUES ('239', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50306', '1535008132594', '1', null);
INSERT INTO `tb_operation_log` VALUES ('240', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50821', '1535008274164', '1', null);
INSERT INTO `tb_operation_log` VALUES ('241', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50821', '1535008281342', '1', null);
INSERT INTO `tb_operation_log` VALUES ('242', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:56374', '1535088246475', '1', null);
INSERT INTO `tb_operation_log` VALUES ('243', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:56386', '1535088319558', '1', null);
INSERT INTO `tb_operation_log` VALUES ('244', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:53560', '1535099089632', '1', null);
INSERT INTO `tb_operation_log` VALUES ('245', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:53724', '1535099137354', '1', null);
INSERT INTO `tb_operation_log` VALUES ('246', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:53724', '1535099180016', '1', null);
INSERT INTO `tb_operation_log` VALUES ('247', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.101:58471', '1535099529255', '1', null);
INSERT INTO `tb_operation_log` VALUES ('248', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59922', '1535099826814', '1', null);
INSERT INTO `tb_operation_log` VALUES ('249', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62895', '1535101140431', '1', null);
INSERT INTO `tb_operation_log` VALUES ('250', '前台密码登录接口:/login', '{\"password\":\"1234565=\",\"userName\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61445', '1535249522022', '2', '缺少必填参数:username &#10; com.hedian.aspect.ValidationParamAspect.hasAllRequired(ValidationParamAspect.java:99) &#10; com.hedian.aspect.ValidationParamAspect.doHandlerAspect(ValidationParamAspect.java:40) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.RecordLogAspect.doHandlerAspect(RecordLogAspect.java:47) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.ParameterCheckAspect.validationPoint(ParameterCheckAspect.java:50) &#10; sun.reflect.GeneratedMethodAccessor173.invoke(Unknown Source) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618) &#10; org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:656) &#10; com.hedian.controller.LoginController$$EnhancerBySpringCGLIB$$15d6791c.login(<generated>) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) &#10; org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) &#10; org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:116) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) &#10; org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) &#10; org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963) &#10; org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897) &#10; org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) &#10; org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:648) &#10; org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:729) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:230) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61) &#10; org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108) &#10; org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:365) &#10; org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90) &#10; org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83) &#10; org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) &#10; org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) &#10; org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:474) &#10; org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) &#10; org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79) &#10; org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) &#10; org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:349) &#10; org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:783) &#10; org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) &#10; org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:798) &#10; org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1434) &#10; org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) &#10; java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) &#10; java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) &#10; org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) &#10; java.lang.Thread.run(Thread.java:748) &#10; ');
INSERT INTO `tb_operation_log` VALUES ('251', '前台密码登录接口:/login', '{\"password\":\"1234565=\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61445', '1535249532882', '1', null);
INSERT INTO `tb_operation_log` VALUES ('252', '前台密码登录接口:/login', '{\"password\":\"1234565=\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61445', '1535249546633', '1', null);
INSERT INTO `tb_operation_log` VALUES ('253', '前台密码登录接口:/login', '{\"password\":\"1234565=\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61445', '1535249547955', '1', null);
INSERT INTO `tb_operation_log` VALUES ('254', '前台密码登录接口:/login', '{\"password\":\"1234565\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61445', '1535249557178', '1', null);
INSERT INTO `tb_operation_log` VALUES ('255', '前台密码登录接口:/login', '{\"password\":\"1234565\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61445', '1535249564947', '1', null);
INSERT INTO `tb_operation_log` VALUES ('256', '前台密码登录接口:/login', '{\"password\":\"1234565\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61610', '1535249617679', '1', null);
INSERT INTO `tb_operation_log` VALUES ('257', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61610', '1535249635975', '1', null);
INSERT INTO `tb_operation_log` VALUES ('258', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:52045', '1535250846820', '1', null);
INSERT INTO `tb_operation_log` VALUES ('259', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:52045', '1535250861078', '1', null);
INSERT INTO `tb_operation_log` VALUES ('260', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:52354', '1535251043692', '1', null);
INSERT INTO `tb_operation_log` VALUES ('261', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:53581', '1535251668901', '1', null);
INSERT INTO `tb_operation_log` VALUES ('262', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:53760', '1535251788381', '1', null);
INSERT INTO `tb_operation_log` VALUES ('263', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:53760', '1535251836947', '1', null);
INSERT INTO `tb_operation_log` VALUES ('264', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:54123', '1535252083505', '1', null);
INSERT INTO `tb_operation_log` VALUES ('265', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:54387', '1535252206325', '1', null);
INSERT INTO `tb_operation_log` VALUES ('266', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:52402', '1535253439365', '1', null);
INSERT INTO `tb_operation_log` VALUES ('267', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:56425', '1535253494843', '1', null);
INSERT INTO `tb_operation_log` VALUES ('268', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:56609', '1535253627127', '1', null);
INSERT INTO `tb_operation_log` VALUES ('269', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:56609', '1535253634002', '1', null);
INSERT INTO `tb_operation_log` VALUES ('270', '前台登陆用户信息接口:/user/me', null, 'admin', 'com.hedian.controller.LoginController', 'userInfo', '0:0:0:0:0:0:0:1:56609', '1535253638388', '1', null);
INSERT INTO `tb_operation_log` VALUES ('271', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:50301', '1535255776882', '1', null);
INSERT INTO `tb_operation_log` VALUES ('272', '前台密码登录接口:/login', '{\"pasword\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:51215', '1535261332268', '2', '缺少必填参数:password &#10; com.hedian.aspect.ValidationParamAspect.hasAllRequired(ValidationParamAspect.java:99) &#10; com.hedian.aspect.ValidationParamAspect.doHandlerAspect(ValidationParamAspect.java:40) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.RecordLogAspect.doHandlerAspect(RecordLogAspect.java:47) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.ParameterCheckAspect.validationPoint(ParameterCheckAspect.java:50) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618) &#10; org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:656) &#10; com.hedian.controller.LoginController$$EnhancerBySpringCGLIB$$e82a09d4.login(<generated>) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) &#10; org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) &#10; org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:116) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) &#10; org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) &#10; org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963) &#10; org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897) &#10; org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) &#10; org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:648) &#10; org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:729) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:230) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61) &#10; org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108) &#10; org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:365) &#10; org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90) &#10; org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83) &#10; org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) &#10; org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) &#10; org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:474) &#10; org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) &#10; org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79) &#10; org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) &#10; org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:349) &#10; org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:783) &#10; org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) &#10; org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:798) &#10; org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1434) &#10; org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) &#10; java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) &#10; java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) &#10; org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) &#10; java.lang.Thread.run(Thread.java:748) &#10; ');
INSERT INTO `tb_operation_log` VALUES ('273', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:51215', '1535261338863', '1', null);
INSERT INTO `tb_operation_log` VALUES ('274', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57278', '1535262689305', '1', null);
INSERT INTO `tb_operation_log` VALUES ('275', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:60892', '1535262885411', '1', null);
INSERT INTO `tb_operation_log` VALUES ('276', '前台密码登录接口:/login', '{\"password\":\"12345\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:58878', '1535263088915', '1', null);
INSERT INTO `tb_operation_log` VALUES ('277', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:58878', '1535263104875', '1', null);
INSERT INTO `tb_operation_log` VALUES ('278', '前台密码登录接口:/login', '{\"password\":\"12345\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59143', '1535263155006', '1', null);
INSERT INTO `tb_operation_log` VALUES ('279', '前台密码登录接口:/login', '{\"password\":\"12345\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59143', '1535263156346', '1', null);
INSERT INTO `tb_operation_log` VALUES ('280', '前台密码登录接口:/login', '{\"password\":\"12345\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59143', '1535263156713', '1', null);
INSERT INTO `tb_operation_log` VALUES ('281', '前台密码登录接口:/login', '{\"password\":\"12345\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59143', '1535263156959', '1', null);
INSERT INTO `tb_operation_log` VALUES ('282', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59390', '1535263225878', '1', null);
INSERT INTO `tb_operation_log` VALUES ('283', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61688', '1535263302409', '1', null);
INSERT INTO `tb_operation_log` VALUES ('284', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61807', '1535263532489', '1', null);
INSERT INTO `tb_operation_log` VALUES ('285', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:63600', '1535264141407', '1', null);
INSERT INTO `tb_operation_log` VALUES ('286', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:52736', '1535265697386', '1', null);
INSERT INTO `tb_operation_log` VALUES ('287', '前台密码登录接口:/login', '{\"password\":\"12345\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:52900', '1535266379191', '1', null);
INSERT INTO `tb_operation_log` VALUES ('288', '前台密码登录接口:/login', '{\"password\":\"1234\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:52900', '1535266382078', '1', null);
INSERT INTO `tb_operation_log` VALUES ('289', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56408', '1535266674748', '1', null);
INSERT INTO `tb_operation_log` VALUES ('290', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56408', '1535266675548', '1', null);
INSERT INTO `tb_operation_log` VALUES ('291', '前台密码登录接口:/login', '{\"password\":\"12345\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56408', '1535268016031', '1', null);
INSERT INTO `tb_operation_log` VALUES ('292', '前台密码登录接口:/login', '{\"password\":\"12345\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56408', '1535268022914', '1', null);
INSERT INTO `tb_operation_log` VALUES ('293', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56408', '1535268050956', '1', null);
INSERT INTO `tb_operation_log` VALUES ('294', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56408', '1535268093949', '1', null);
INSERT INTO `tb_operation_log` VALUES ('295', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:60059', '1535268309203', '1', null);
INSERT INTO `tb_operation_log` VALUES ('296', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:60059', '1535268343114', '1', null);
INSERT INTO `tb_operation_log` VALUES ('297', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:60483', '1535268557967', '1', null);
INSERT INTO `tb_operation_log` VALUES ('298', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61210', '1535268872849', '1', null);
INSERT INTO `tb_operation_log` VALUES ('299', '前台密码登录接口:/login', '{\"password\":\"12345\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61210', '1535268899979', '1', null);
INSERT INTO `tb_operation_log` VALUES ('300', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61210', '1535268915139', '1', null);
INSERT INTO `tb_operation_log` VALUES ('301', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61210', '1535269152525', '1', null);
INSERT INTO `tb_operation_log` VALUES ('302', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61210', '1535269742124', '1', null);
INSERT INTO `tb_operation_log` VALUES ('303', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61210', '1535270062645', '1', null);
INSERT INTO `tb_operation_log` VALUES ('304', '前台密码登录接口:/login', '{\"password\":\"12345\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61210', '1535270115654', '1', null);
INSERT INTO `tb_operation_log` VALUES ('305', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61210', '1535270125436', '1', null);
INSERT INTO `tb_operation_log` VALUES ('306', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61210', '1535270218359', '1', null);
INSERT INTO `tb_operation_log` VALUES ('307', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61231', '1535270302369', '1', null);
INSERT INTO `tb_operation_log` VALUES ('308', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50941', '1535271829962', '1', null);
INSERT INTO `tb_operation_log` VALUES ('309', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:51017', '1535272131375', '1', null);
INSERT INTO `tb_operation_log` VALUES ('310', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:51017', '1535272246532', '1', null);
INSERT INTO `tb_operation_log` VALUES ('311', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:51017', '1535272352394', '1', null);
INSERT INTO `tb_operation_log` VALUES ('312', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:54263', '1535272796196', '2', 'The Secret cannot be null &#10; com.auth0.jwt.algorithms.HMACAlgorithm.getSecretBytes(HMACAlgorithm.java:40) &#10; com.auth0.jwt.algorithms.HMACAlgorithm.<init>(HMACAlgorithm.java:34) &#10; com.auth0.jwt.algorithms.Algorithm.HMAC256(Algorithm.java:144) &#10; com.hedian.util.JWTUtil.sign(JWTUtil.java:62) &#10; com.hedian.service.impl.SysUserServiceImpl.getLoginUserAndMenuInfo(SysUserServiceImpl.java:110) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333) &#10; org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:207) &#10; com.sun.proxy.$Proxy115.getLoginUserAndMenuInfo(Unknown Source) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:190) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157) &#10; org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99) &#10; org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282) &#10; org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:213) &#10; com.sun.proxy.$Proxy115.getLoginUserAndMenuInfo(Unknown Source) &#10; com.hedian.controller.LoginController.login(LoginController.java:58) &#10; com.hedian.controller.LoginController$$FastClassBySpringCGLIB$$9dee13a5.invoke(<generated>) &#10; org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204) &#10; org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:721) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157) &#10; org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:97) &#10; com.hedian.aspect.RecordLogAspect.doHandlerAspect(RecordLogAspect.java:49) &#10; com.hedian.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.hedian.aspect.ParameterCheckAspect.validationPoint(ParameterCheckAspect.java:50) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618) &#10; org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:656) &#10; com.hedian.controller.LoginController$$EnhancerBySpringCGLIB$$58ce3051.login(<generated>) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) &#10; org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) &#10; org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:116) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) &#10; org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) &#10; org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963) &#10; org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897) &#10; org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) &#10; org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:648) &#10; org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:729) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:230) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61) &#10; org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108) &#10; org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:365) &#10; org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90) &#10; org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83) &#10; org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) &#10; org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) &#10; org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:474) &#10; org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) &#10; org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79) &#10; org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) &#10; org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:349) &#10; org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:783) &#10; org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) &#10; org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:798) &#10; org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1434) &#10; org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) &#10; java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) &#10; java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) &#10; org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) &#10; java.lang.Thread.run(Thread.java:748) &#10; ');
INSERT INTO `tb_operation_log` VALUES ('314', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:54321', '1535272879698', '1', null);
INSERT INTO `tb_operation_log` VALUES ('315', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53024', '1535272990505', '1', null);
INSERT INTO `tb_operation_log` VALUES ('316', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53021', '1535273016906', '1', null);
INSERT INTO `tb_operation_log` VALUES ('317', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53022', '1535273016906', '1', null);
INSERT INTO `tb_operation_log` VALUES ('318', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53023', '1535273016906', '1', null);
INSERT INTO `tb_operation_log` VALUES ('319', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53026', '1535273016906', '1', null);
INSERT INTO `tb_operation_log` VALUES ('320', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53023', '1535273062621', '1', null);
INSERT INTO `tb_operation_log` VALUES ('321', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53026', '1535273175720', '1', null);
INSERT INTO `tb_operation_log` VALUES ('322', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53021', '1535273193915', '1', null);
INSERT INTO `tb_operation_log` VALUES ('323', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53022', '1535273286912', '1', null);
INSERT INTO `tb_operation_log` VALUES ('324', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53022', '1535273679419', '1', null);
INSERT INTO `tb_operation_log` VALUES ('325', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53021', '1535273808162', '1', null);
INSERT INTO `tb_operation_log` VALUES ('326', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53022', '1535273850134', '1', null);
INSERT INTO `tb_operation_log` VALUES ('327', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59755', '1535275594688', '1', null);
INSERT INTO `tb_operation_log` VALUES ('328', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59755', '1535275629181', '1', null);
INSERT INTO `tb_operation_log` VALUES ('329', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59755', '1535275891976', '1', null);
INSERT INTO `tb_operation_log` VALUES ('330', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:59755', '1535275930471', '1', null);
INSERT INTO `tb_operation_log` VALUES ('331', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61884', '1535276624351', '1', null);
INSERT INTO `tb_operation_log` VALUES ('332', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62302', '1535276886336', '1', null);
INSERT INTO `tb_operation_log` VALUES ('333', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63620', '1535277551280', '1', null);
INSERT INTO `tb_operation_log` VALUES ('334', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63620', '1535277588444', '1', null);
INSERT INTO `tb_operation_log` VALUES ('335', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63620', '1535277713636', '1', null);
INSERT INTO `tb_operation_log` VALUES ('336', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50890', '1535279093734', '1', null);
INSERT INTO `tb_operation_log` VALUES ('337', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50890', '1535280084185', '1', null);
INSERT INTO `tb_operation_log` VALUES ('338', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49203', '1535284892461', '1', null);
INSERT INTO `tb_operation_log` VALUES ('339', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49243', '1535284892465', '1', null);
INSERT INTO `tb_operation_log` VALUES ('340', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49173', '1535284892453', '1', null);
INSERT INTO `tb_operation_log` VALUES ('341', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65523', '1535284892446', '1', null);
INSERT INTO `tb_operation_log` VALUES ('342', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65303', '1535284892453', '1', null);
INSERT INTO `tb_operation_log` VALUES ('343', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49245', '1535284892469', '1', null);
INSERT INTO `tb_operation_log` VALUES ('344', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49242', '1535284892464', '1', null);
INSERT INTO `tb_operation_log` VALUES ('345', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49246', '1535284892469', '1', null);
INSERT INTO `tb_operation_log` VALUES ('346', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65522', '1535284892445', '1', null);
INSERT INTO `tb_operation_log` VALUES ('347', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49239', '1535284892465', '1', null);
INSERT INTO `tb_operation_log` VALUES ('348', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65505', '1535284892477', '1', null);
INSERT INTO `tb_operation_log` VALUES ('349', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65517', '1535284892485', '1', null);
INSERT INTO `tb_operation_log` VALUES ('350', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65518', '1535284892486', '1', null);
INSERT INTO `tb_operation_log` VALUES ('351', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65521', '1535284892507', '1', null);
INSERT INTO `tb_operation_log` VALUES ('352', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65514', '1535284892485', '1', null);
INSERT INTO `tb_operation_log` VALUES ('353', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65520', '1535284892496', '1', null);
INSERT INTO `tb_operation_log` VALUES ('354', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49244', '1535284892469', '1', null);
INSERT INTO `tb_operation_log` VALUES ('355', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50860', '1535285310653', '1', null);
INSERT INTO `tb_operation_log` VALUES ('356', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50860', '1535285325998', '1', null);
INSERT INTO `tb_operation_log` VALUES ('357', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50860', '1535285332102', '1', null);
INSERT INTO `tb_operation_log` VALUES ('358', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50860', '1535285379008', '1', null);
INSERT INTO `tb_operation_log` VALUES ('359', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50860', '1535285411647', '1', null);
INSERT INTO `tb_operation_log` VALUES ('360', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50860', '1535285457710', '1', null);
INSERT INTO `tb_operation_log` VALUES ('361', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50860', '1535285593350', '1', null);
INSERT INTO `tb_operation_log` VALUES ('362', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50860', '1535285635683', '1', null);
INSERT INTO `tb_operation_log` VALUES ('363', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56369', '1535286813275', '1', null);
INSERT INTO `tb_operation_log` VALUES ('364', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56369', '1535286922746', '1', null);
INSERT INTO `tb_operation_log` VALUES ('365', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56369', '1535286981028', '1', null);
INSERT INTO `tb_operation_log` VALUES ('366', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57671', '1535287172295', '1', null);
INSERT INTO `tb_operation_log` VALUES ('367', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57671', '1535287850493', '1', null);
INSERT INTO `tb_operation_log` VALUES ('368', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61617', '1535288152609', '1', null);
INSERT INTO `tb_operation_log` VALUES ('369', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63657', '1535331816659', '1', null);
INSERT INTO `tb_operation_log` VALUES ('370', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65338', '1535331891737', '1', null);
INSERT INTO `tb_operation_log` VALUES ('371', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63702', '1535331936120', '1', null);
INSERT INTO `tb_operation_log` VALUES ('372', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:52524', '1535333571279', '1', null);
INSERT INTO `tb_operation_log` VALUES ('373', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:52524', '1535333573982', '1', null);
INSERT INTO `tb_operation_log` VALUES ('374', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:52524', '1535333593933', '1', null);
INSERT INTO `tb_operation_log` VALUES ('375', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:52904', '1535333707628', '1', null);
INSERT INTO `tb_operation_log` VALUES ('376', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:52904', '1535333720666', '1', null);
INSERT INTO `tb_operation_log` VALUES ('377', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:58555', '1535336664113', '1', null);
INSERT INTO `tb_operation_log` VALUES ('378', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:58660', '1535336710058', '1', null);
INSERT INTO `tb_operation_log` VALUES ('379', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:58793', '1535336759607', '1', null);
INSERT INTO `tb_operation_log` VALUES ('380', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:64205', '1535350132731', '1', null);
INSERT INTO `tb_operation_log` VALUES ('381', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62360', '1535355326607', '1', null);
INSERT INTO `tb_operation_log` VALUES ('382', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:58383', '1535435452060', '1', null);
INSERT INTO `tb_operation_log` VALUES ('383', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63089', '1535506627083', '1', null);
INSERT INTO `tb_operation_log` VALUES ('384', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63163', '1535506658081', '1', null);
INSERT INTO `tb_operation_log` VALUES ('385', '前台密码登录接口:/login', '{\"password\":\"111111\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63163', '1535506662759', '1', null);
INSERT INTO `tb_operation_log` VALUES ('386', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63163', '1535506678299', '1', null);
INSERT INTO `tb_operation_log` VALUES ('387', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63876', '1535506969873', '1', null);
INSERT INTO `tb_operation_log` VALUES ('388', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64222', '1535507127240', '1', null);
INSERT INTO `tb_operation_log` VALUES ('389', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64435', '1535507217694', '1', null);
INSERT INTO `tb_operation_log` VALUES ('390', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65442', '1535507599897', '1', null);
INSERT INTO `tb_operation_log` VALUES ('391', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:52526', '1535509152466', '1', null);
INSERT INTO `tb_operation_log` VALUES ('392', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53126', '1535509382018', '1', null);
INSERT INTO `tb_operation_log` VALUES ('393', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:55186', '1535510182670', '1', null);
INSERT INTO `tb_operation_log` VALUES ('394', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:55898', '1535510493489', '1', null);
INSERT INTO `tb_operation_log` VALUES ('395', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:55809', '1535510648447', '1', null);
INSERT INTO `tb_operation_log` VALUES ('396', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56852', '1535510918891', '1', null);
INSERT INTO `tb_operation_log` VALUES ('397', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56954', '1535510983617', '1', null);
INSERT INTO `tb_operation_log` VALUES ('398', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56956', '1535511055231', '1', null);
INSERT INTO `tb_operation_log` VALUES ('399', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57859', '1535512238629', '1', null);
INSERT INTO `tb_operation_log` VALUES ('400', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57858', '1535512381332', '1', null);
INSERT INTO `tb_operation_log` VALUES ('401', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:60080', '1535512525729', '1', null);
INSERT INTO `tb_operation_log` VALUES ('402', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:60100', '1535512727001', '1', null);
INSERT INTO `tb_operation_log` VALUES ('403', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57857', '1535512784278', '1', null);
INSERT INTO `tb_operation_log` VALUES ('404', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57860', '1535512959826', '1', null);
INSERT INTO `tb_operation_log` VALUES ('405', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:59430', '1535513078045', '1', null);
INSERT INTO `tb_operation_log` VALUES ('406', '前台密码登录接口:/login', '{\"password\":\"1556\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:59623', '1535513214235', '1', null);
INSERT INTO `tb_operation_log` VALUES ('407', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57861', '1535513548917', '1', null);
INSERT INTO `tb_operation_log` VALUES ('408', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56739', '1535520505565', '1', null);
INSERT INTO `tb_operation_log` VALUES ('409', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56791', '1535521926183', '1', null);
INSERT INTO `tb_operation_log` VALUES ('410', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56843', '1535522061511', '1', null);
INSERT INTO `tb_operation_log` VALUES ('411', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56791', '1535522082871', '1', null);
INSERT INTO `tb_operation_log` VALUES ('412', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63049', '1535523039966', '1', null);
INSERT INTO `tb_operation_log` VALUES ('413', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63049', '1535523058171', '1', null);
INSERT INTO `tb_operation_log` VALUES ('414', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62467', '1535523075359', '1', null);
INSERT INTO `tb_operation_log` VALUES ('415', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63049', '1535523179639', '1', null);
INSERT INTO `tb_operation_log` VALUES ('416', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63048', '1535523315766', '1', null);
INSERT INTO `tb_operation_log` VALUES ('417', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63049', '1535523375567', '1', null);
INSERT INTO `tb_operation_log` VALUES ('418', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62467', '1535523540608', '1', null);
INSERT INTO `tb_operation_log` VALUES ('419', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63051', '1535523585533', '1', null);
INSERT INTO `tb_operation_log` VALUES ('420', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63048', '1535523749027', '1', null);
INSERT INTO `tb_operation_log` VALUES ('421', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63051', '1535523866892', '1', null);
INSERT INTO `tb_operation_log` VALUES ('422', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63048', '1535523885612', '1', null);
INSERT INTO `tb_operation_log` VALUES ('423', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63214', '1535523925502', '1', null);
INSERT INTO `tb_operation_log` VALUES ('424', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62467', '1535523958101', '1', null);
INSERT INTO `tb_operation_log` VALUES ('425', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49854', '1535524194984', '1', null);
INSERT INTO `tb_operation_log` VALUES ('426', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49854', '1535524603003', '1', null);
INSERT INTO `tb_operation_log` VALUES ('427', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49856', '1535524664669', '1', null);
INSERT INTO `tb_operation_log` VALUES ('428', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49854', '1535525099084', '1', null);
INSERT INTO `tb_operation_log` VALUES ('429', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49856', '1535525324748', '1', null);
INSERT INTO `tb_operation_log` VALUES ('430', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49852', '1535525658903', '1', null);
INSERT INTO `tb_operation_log` VALUES ('431', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:52292', '1535526080146', '1', null);
INSERT INTO `tb_operation_log` VALUES ('432', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49856', '1535526092769', '1', null);
INSERT INTO `tb_operation_log` VALUES ('433', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62292', '1535528140907', '1', null);
INSERT INTO `tb_operation_log` VALUES ('434', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62353', '1535529281707', '1', null);
INSERT INTO `tb_operation_log` VALUES ('435', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62290', '1535529487545', '1', null);
INSERT INTO `tb_operation_log` VALUES ('436', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62290', '1535529680025', '1', null);
INSERT INTO `tb_operation_log` VALUES ('437', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62287', '1535529826337', '1', null);
INSERT INTO `tb_operation_log` VALUES ('438', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62291', '1535530021959', '1', null);
INSERT INTO `tb_operation_log` VALUES ('439', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49751', '1535530145853', '1', null);
INSERT INTO `tb_operation_log` VALUES ('440', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49629', '1535530261584', '1', null);
INSERT INTO `tb_operation_log` VALUES ('441', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49751', '1535530321486', '1', null);
INSERT INTO `tb_operation_log` VALUES ('442', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61872', '1535531380501', '1', null);
INSERT INTO `tb_operation_log` VALUES ('443', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61884', '1535531435472', '1', null);
INSERT INTO `tb_operation_log` VALUES ('444', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61872', '1535533108553', '1', null);
INSERT INTO `tb_operation_log` VALUES ('445', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61888', '1535533377722', '1', null);
INSERT INTO `tb_operation_log` VALUES ('446', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62005', '1535533441887', '1', null);
INSERT INTO `tb_operation_log` VALUES ('447', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:62005', '1535533627063', '1', null);
INSERT INTO `tb_operation_log` VALUES ('448', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61872', '1535533709242', '1', null);
INSERT INTO `tb_operation_log` VALUES ('449', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61884', '1535534011074', '1', null);
INSERT INTO `tb_operation_log` VALUES ('450', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:61886', '1535534133023', '1', null);
INSERT INTO `tb_operation_log` VALUES ('451', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63042', '1535537848085', '1', null);
INSERT INTO `tb_operation_log` VALUES ('452', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63116', '1535538087258', '1', null);
INSERT INTO `tb_operation_log` VALUES ('453', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63042', '1535538123628', '1', null);
INSERT INTO `tb_operation_log` VALUES ('454', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63042', '1535538195707', '1', null);
INSERT INTO `tb_operation_log` VALUES ('455', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63059', '1535538524399', '1', null);
INSERT INTO `tb_operation_log` VALUES ('456', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63059', '1535538981667', '1', null);
INSERT INTO `tb_operation_log` VALUES ('457', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63062', '1535539160162', '1', null);
INSERT INTO `tb_operation_log` VALUES ('458', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63116', '1535539744084', '1', null);
INSERT INTO `tb_operation_log` VALUES ('459', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63059', '1535539819193', '1', null);
INSERT INTO `tb_operation_log` VALUES ('460', '前台密码登录接口:/login', '{\"password\":\"1\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:51271', '1535540063551', '1', null);
INSERT INTO `tb_operation_log` VALUES ('461', '前台密码登录接口:/login', '{\"password\":\"1\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:51271', '1535540076827', '1', null);
INSERT INTO `tb_operation_log` VALUES ('462', '前台密码登录接口:/login', '{\"password\":\"1\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:51271', '1535540083017', '1', null);
INSERT INTO `tb_operation_log` VALUES ('463', '前台密码登录接口:/login', '{\"password\":\"1\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:51890', '1535540163190', '1', null);
INSERT INTO `tb_operation_log` VALUES ('464', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:51611', '1535540231482', '1', null);
INSERT INTO `tb_operation_log` VALUES ('465', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:51611', '1535540245400', '1', null);
INSERT INTO `tb_operation_log` VALUES ('466', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:52045', '1535540256642', '1', null);
INSERT INTO `tb_operation_log` VALUES ('467', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:51611', '1535540257669', '1', null);
INSERT INTO `tb_operation_log` VALUES ('468', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:52045', '1535540259408', '1', null);
INSERT INTO `tb_operation_log` VALUES ('469', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:52281', '1535540422618', '1', null);
INSERT INTO `tb_operation_log` VALUES ('470', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:52281', '1535540430753', '1', null);
INSERT INTO `tb_operation_log` VALUES ('471', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:52143', '1535540454224', '1', null);
INSERT INTO `tb_operation_log` VALUES ('472', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:52454', '1535540600756', '1', null);
INSERT INTO `tb_operation_log` VALUES ('473', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:52699', '1535540706586', '1', null);
INSERT INTO `tb_operation_log` VALUES ('474', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:52699', '1535540739102', '1', null);
INSERT INTO `tb_operation_log` VALUES ('475', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:52945', '1535540791654', '1', null);
INSERT INTO `tb_operation_log` VALUES ('476', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:53520', '1535541036725', '1', null);
INSERT INTO `tb_operation_log` VALUES ('477', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:54235', '1535541308329', '1', null);
INSERT INTO `tb_operation_log` VALUES ('478', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:54823', '1535541544121', '1', null);
INSERT INTO `tb_operation_log` VALUES ('479', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:54911', '1535541569741', '1', null);
INSERT INTO `tb_operation_log` VALUES ('480', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:54987', '1535541592044', '1', null);
INSERT INTO `tb_operation_log` VALUES ('481', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:55152', '1535541644351', '1', null);
INSERT INTO `tb_operation_log` VALUES ('482', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:55341', '1535541714057', '1', null);
INSERT INTO `tb_operation_log` VALUES ('483', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:55480', '1535541756035', '1', null);
INSERT INTO `tb_operation_log` VALUES ('484', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63949', '1535590683989', '1', null);
INSERT INTO `tb_operation_log` VALUES ('485', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:63949', '1535590701962', '1', null);
INSERT INTO `tb_operation_log` VALUES ('486', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64119', '1535590768546', '1', null);
INSERT INTO `tb_operation_log` VALUES ('487', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64119', '1535590809947', '1', null);
INSERT INTO `tb_operation_log` VALUES ('488', '前台密码登录接口:/login', '{\"password\":\"1234556\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64247', '1535590823183', '1', null);
INSERT INTO `tb_operation_log` VALUES ('489', '前台密码登录接口:/login', '{\"password\":\"1234556\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64247', '1535590824751', '1', null);
INSERT INTO `tb_operation_log` VALUES ('490', '前台密码登录接口:/login', '{\"password\":\"1234556\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64247', '1535590825091', '1', null);
INSERT INTO `tb_operation_log` VALUES ('491', '前台密码登录接口:/login', '{\"password\":\"1234556\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64247', '1535590825291', '1', null);
INSERT INTO `tb_operation_log` VALUES ('492', '前台密码登录接口:/login', '{\"password\":\"1234556\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64247', '1535590825501', '1', null);
INSERT INTO `tb_operation_log` VALUES ('493', '前台密码登录接口:/login', '{\"password\":\"1234556\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64247', '1535590830331', '1', null);
INSERT INTO `tb_operation_log` VALUES ('494', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:64119', '1535590838762', '1', null);
INSERT INTO `tb_operation_log` VALUES ('495', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65022', '1535591254976', '1', null);
INSERT INTO `tb_operation_log` VALUES ('496', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65022', '1535591255940', '1', null);
INSERT INTO `tb_operation_log` VALUES ('497', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65022', '1535591257727', '1', null);
INSERT INTO `tb_operation_log` VALUES ('498', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65022', '1535591293057', '1', null);
INSERT INTO `tb_operation_log` VALUES ('499', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65022', '1535591324874', '1', null);
INSERT INTO `tb_operation_log` VALUES ('500', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65022', '1535591366957', '1', null);
INSERT INTO `tb_operation_log` VALUES ('501', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65022', '1535591406178', '1', null);
INSERT INTO `tb_operation_log` VALUES ('502', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65022', '1535591415208', '1', null);
INSERT INTO `tb_operation_log` VALUES ('503', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:63384', '1535591438930', '1', null);
INSERT INTO `tb_operation_log` VALUES ('504', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:63384', '1535591475518', '1', null);
INSERT INTO `tb_operation_log` VALUES ('505', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:65437', '1535591484757', '1', null);
INSERT INTO `tb_operation_log` VALUES ('506', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:49967', '1535591901590', '1', null);
INSERT INTO `tb_operation_log` VALUES ('507', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50116', '1535591944449', '1', null);
INSERT INTO `tb_operation_log` VALUES ('508', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50368', '1535592037502', '1', null);
INSERT INTO `tb_operation_log` VALUES ('509', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50581', '1535592129329', '1', null);
INSERT INTO `tb_operation_log` VALUES ('510', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50925', '1535592322064', '1', null);
INSERT INTO `tb_operation_log` VALUES ('511', '前台密码登录接口:/login', '{\"password\":\"123456\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:52669', '1535592509243', '1', null);
INSERT INTO `tb_operation_log` VALUES ('512', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:52669', '1535592525624', '1', null);
INSERT INTO `tb_operation_log` VALUES ('513', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:52905', '1535592601474', '1', null);
INSERT INTO `tb_operation_log` VALUES ('514', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50581', '1535592773335', '1', null);
INSERT INTO `tb_operation_log` VALUES ('515', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:53261', '1535592807444', '1', null);
INSERT INTO `tb_operation_log` VALUES ('516', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:53261', '1535592845904', '1', null);
INSERT INTO `tb_operation_log` VALUES ('517', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50590', '1535592990926', '1', null);
INSERT INTO `tb_operation_log` VALUES ('518', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:52305', '1535593538353', '1', null);
INSERT INTO `tb_operation_log` VALUES ('519', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50587', '1535593629821', '1', null);
INSERT INTO `tb_operation_log` VALUES ('520', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50581', '1535594043883', '1', null);
INSERT INTO `tb_operation_log` VALUES ('521', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:52305', '1535594073646', '1', null);
INSERT INTO `tb_operation_log` VALUES ('522', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50587', '1535594306280', '1', null);
INSERT INTO `tb_operation_log` VALUES ('523', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:56139', '1535594311738', '1', null);
INSERT INTO `tb_operation_log` VALUES ('524', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50581', '1535594396541', '1', null);
INSERT INTO `tb_operation_log` VALUES ('525', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:56542', '1535594458727', '1', null);
INSERT INTO `tb_operation_log` VALUES ('526', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:50586', '1535594498156', '1', null);
INSERT INTO `tb_operation_log` VALUES ('527', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:56760', '1535594557087', '1', null);
INSERT INTO `tb_operation_log` VALUES ('528', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:55612', '1535595121551', '1', null);
INSERT INTO `tb_operation_log` VALUES ('529', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.195:54460', '1535595161487', '1', null);
INSERT INTO `tb_operation_log` VALUES ('530', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:56673', '1535595548241', '1', null);
INSERT INTO `tb_operation_log` VALUES ('531', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57216', '1535595634471', '1', null);
INSERT INTO `tb_operation_log` VALUES ('532', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57214', '1535595971162', '1', null);
INSERT INTO `tb_operation_log` VALUES ('533', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', 'admin', 'com.hedian.controller.LoginController', 'login', '192.168.1.198:59699', '1535596156988', '1', null);
INSERT INTO `tb_operation_log` VALUES ('534', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.195:57215', '1535596183316', '1', null);
INSERT INTO `tb_operation_log` VALUES ('535', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:60063', '1535596340421', '1', null);
INSERT INTO `tb_operation_log` VALUES ('536', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61600', '1535597154498', '1', null);
INSERT INTO `tb_operation_log` VALUES ('537', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:61741', '1535597201460', '1', null);
INSERT INTO `tb_operation_log` VALUES ('539', '前台密码登录接口:/login', '{\"password\":\"12345678\",\"username\":\"admin\"}', null, 'com.hedian.controller.LoginController', 'login', '192.168.1.198:64344', '1535598683388', '1', null);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_code` varchar(50) NOT NULL COMMENT '角色代号主键',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('role-cf8fea2055344df59a0d3e80540c78f9', 'sysadmin');
INSERT INTO `tb_role` VALUES ('role-dfsg3tsdfgh55334fsdg2asdf23qrasdf3', 'admin');
INSERT INTO `tb_role` VALUES ('role-f7943542d87a4f028f446b71d9ede25d', 'user');

-- ----------------------------
-- Table structure for tb_role_to_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_to_menu`;
CREATE TABLE `tb_role_to_menu` (
  `role_to_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(50) NOT NULL COMMENT '角色代号',
  `menu_code` varchar(50) NOT NULL COMMENT '菜单代号,规范权限标识',
  PRIMARY KEY (`role_to_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of tb_role_to_menu
-- ----------------------------
INSERT INTO `tb_role_to_menu` VALUES ('1', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-e7c48090579444aeac20958f570d08b7');
INSERT INTO `tb_role_to_menu` VALUES ('2', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-afd83fc912eb44d29012049aae184fd4');
INSERT INTO `tb_role_to_menu` VALUES ('3', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-974abc42a78040e7ac74ceecb70c02b5');
INSERT INTO `tb_role_to_menu` VALUES ('4', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-ad61fb43be7d46e7a81e37593042f543');
INSERT INTO `tb_role_to_menu` VALUES ('5', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-b16897c1c79b45b099939f5333530eaf');
INSERT INTO `tb_role_to_menu` VALUES ('6', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-ca569a407de7459f94e8b096180bc5e9');
INSERT INTO `tb_role_to_menu` VALUES ('7', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-ebd8496d182446d4a5b4df3b61822141');
INSERT INTO `tb_role_to_menu` VALUES ('8', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-2fcaf1983232442e9484b48114fe59f6');
INSERT INTO `tb_role_to_menu` VALUES ('9', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-b3556e9a47204c8abe1bcdd50047f6b4');
INSERT INTO `tb_role_to_menu` VALUES ('10', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-08a093222ab04020886049b726a89a4c');
INSERT INTO `tb_role_to_menu` VALUES ('11', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-3da7d8a0b35e42c7b4d0b3c9cb710a7a');
INSERT INTO `tb_role_to_menu` VALUES ('13', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-fc40bd8965c44f17bbe0994e1aa96102');
INSERT INTO `tb_role_to_menu` VALUES ('14', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-1492623fee854b43b3e49b80e877e4a2');
INSERT INTO `tb_role_to_menu` VALUES ('15', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-d3b091cadf644e66b49364e51641b10b');
INSERT INTO `tb_role_to_menu` VALUES ('16', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-ac1a7dfa51474de7b205eee5ad4d4dd2');
INSERT INTO `tb_role_to_menu` VALUES ('17', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-71f1c4edd8f24bf09db20867f7fdad2b');
INSERT INTO `tb_role_to_menu` VALUES ('18', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-c9623470db144ca68e961e053a6cc8c9');
INSERT INTO `tb_role_to_menu` VALUES ('19', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-ee223032f573480e86e673c7d6754173');
INSERT INTO `tb_role_to_menu` VALUES ('20', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-f90d7a25a8514434a73f3b92ccb97fc2');
INSERT INTO `tb_role_to_menu` VALUES ('21', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-d68955aa58e0428ea824680484a074e6');
INSERT INTO `tb_role_to_menu` VALUES ('22', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c317b204969a054b68a3473d3b4');
INSERT INTO `tb_role_to_menu` VALUES ('23', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c317b204969a054fdag233das2l');
INSERT INTO `tb_role_to_menu` VALUES ('24', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c31654653a054b68a343254565fss');
INSERT INTO `tb_role_to_menu` VALUES ('25', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c31654653a054fdsg23asdg5423');
INSERT INTO `tb_role_to_menu` VALUES ('26', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c317b204969a054b653df212zg712');
INSERT INTO `tb_role_to_menu` VALUES ('27', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c31t34ytrfsdfg3j5e36u121rfdg465u');

-- ----------------------------
-- Table structure for tb_sms_verify
-- ----------------------------
DROP TABLE IF EXISTS `tb_sms_verify`;
CREATE TABLE `tb_sms_verify` (
  `sms_verify_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sms_id` varchar(64) NOT NULL COMMENT '短信编号（可以自己生成，也可以第三方复返回）',
  `mobile` varchar(11) NOT NULL COMMENT '电话号码',
  `sms_verify` varchar(4) NOT NULL COMMENT '验证码',
  `sms_type` int(2) NOT NULL COMMENT '验证码类型（1：登录验证，2：注册验证，3：忘记密码，4：修改账号）',
  `create_time` bigint(20) NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`sms_verify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='验证码发送记录';

-- ----------------------------
-- Records of tb_sms_verify
-- ----------------------------
INSERT INTO `tb_sms_verify` VALUES ('4', '18062610474527691', '13888888888', '5721', '3', '1529981271139');
INSERT INTO `tb_sms_verify` VALUES ('5', '18062610524128338', '13888888888', '1227', '2', '1529981567067');
INSERT INTO `tb_sms_verify` VALUES ('6', '18062610474527691', '13888888888', '5721', '2', '1529981271137');
INSERT INTO `tb_sms_verify` VALUES ('7', '18062611353322244', '13888888888', '4988', '1', '1529984138804');
INSERT INTO `tb_sms_verify` VALUES ('8', '18062611474923414', '13888888888', '9078', '1', '1529984874513');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_no` varchar(50) NOT NULL COMMENT '用户主键',
  `mobile` varchar(11) NOT NULL COMMENT '是电话号码，也是账号（登录用）',
  `user_name` varchar(50) NOT NULL COMMENT '姓名',
  `pass_word` varchar(255) NOT NULL COMMENT '密码',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `status` int(2) DEFAULT '2' COMMENT '状态值（1：启用，2：禁用，3：删除）',
  `job` varchar(32) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('user-006efece76c8433d8974c1a2f98422b6', '13888888888', 'javaer', '$2a$10$VwPL.rHo4PETgCcLDTN2LOwE.ksgCA0jLHbVX5yXEoisHWihX7S/i', null, '1529982192887', null, '2', 'java开发');
INSERT INTO `tb_user` VALUES ('user-190f8710857f4a239570387ffc676c39', '15802933752', 'eee', '$2a$10$fJ9Ou1Ffi9XDf1OQbn0NNe7UGqyRHkOj/hKiELuCXifLVqRATWB.W', 'eee', '1529999033844', null, '2', 'eee');
INSERT INTO `tb_user` VALUES ('user-573388ebd14348cf8b546a6bfdf98ca3', '18792420526', '5', '$2a$10$fRJZ6tiCQpGUxsKVcA4yeeRMySiMAtL60aGGRuvJEqzE4LEOuYg2q', '5', '1530086891950', null, '2', '5');

-- ----------------------------
-- Table structure for tb_user_to_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_to_role`;
CREATE TABLE `tb_user_to_role` (
  `user_to_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_no` varchar(50) NOT NULL COMMENT '用户编号',
  `role_code` varchar(50) NOT NULL COMMENT '角色代号',
  PRIMARY KEY (`user_to_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of tb_user_to_role
-- ----------------------------
INSERT INTO `tb_user_to_role` VALUES ('2', 'user-006efece76c8433d8974c1a2f98422b6', 'role-cf8fea2055344df59a0d3e80540c78f9');
INSERT INTO `tb_user_to_role` VALUES ('3', 'user-190f8710857f4a239570387ffc676c39', 'role-f7943542d87a4f028f446b71d9ede25d');
