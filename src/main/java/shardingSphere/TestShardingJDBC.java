package shardingSphere;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

/**
 * @author dengxinlong
 * @date 2020/10/23 14:58
 * @version 1.0
 */
public class TestShardingJDBC {
    public static void main(String[] args) throws Exception{
        URL resource = File.class.getClass().getResource("/sharding-datasource.yml");
//        URL resource = File.class.getClass().getResource("/sharding-datasource-master-slave.yml");
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(new File(resource.getPath()));
        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id=? AND o.order_id=?";
//        sql = "select * from t_order where `name` = 'aaaa'";
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, 10);
            preparedStatement.setInt(2, 1001);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while(rs.next()) {
                    System.out.print(rs.getInt(1)+",");
                    System.out.print(rs.getInt(2));
                    System.out.println();
                }
            }
        }
        sql = "insert into t_order_item values(34,1022,'aaaa')";
//        sql = "CREATE TABLE IF NOT EXISTS aaa LIKE t_order0;";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.execute();
        }
    }
}
