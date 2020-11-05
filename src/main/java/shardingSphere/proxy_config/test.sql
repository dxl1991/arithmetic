CREATE TABLE `t_order0` (
  `user_id` bigint(20) NOT NULL COMMENT '玩家id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '玩家昵称',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='玩家表';

CREATE TABLE `t_order1` (
  `user_id` bigint(20) NOT NULL COMMENT '玩家id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '玩家昵称',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='玩家表';

CREATE TABLE `t_order_item0` (
  `user_id` bigint(20) NOT NULL COMMENT '玩家id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `items` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '订单详细',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详细表';

CREATE TABLE `t_order_item1` (
  `user_id` bigint(20) NOT NULL COMMENT '玩家id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `items` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '订单详细',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详细表';