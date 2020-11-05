package shardingSphere;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author dengxinlong
 * @date 2020/10/26 10:25
 * @version 1.0
 */
public class TestShardingProxy {
    public static void main(String[] args) throws Exception{
        String dbUrl = "jdbc:mysql://localhost:3307/sharding_db?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl); // 数据库连接地址

        config.setUsername("root"); // 用户名
        config.setPassword("root"); // 密码
        config.setMaximumPoolSize(10);// 设置最大连接数，默认10
        config.setMinimumIdle(5);// 设置最小连接数，默认10

        HikariDataSource dataSource = new HikariDataSource(config);

        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id=? AND o.order_id=?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, 10);
            preparedStatement.setInt(2, 1001);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while(rs.next()) {
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getInt(2));
                }
            }
        }
    }
}
