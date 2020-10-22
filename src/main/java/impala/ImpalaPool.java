package impala;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * @author dengxinlong
 * @date 2020/10/16 15:36
 * @version 1.0
 */
public class ImpalaPool {
    private static DruidDataSource dataSource; //Druid 连接池
    private static Connection conn; //数据库连接对象
    private static Logger logger = LoggerFactory.getLogger(ImpalaPool.class);
    private static InputStream in = null;
    static{
        Properties properties = new Properties();
        try{
            in = InputStream.class.getClass().getResourceAsStream("/impala-druid.properties");
            properties.load(in);
            in.close();
        }catch (IOException e){
            logger.error("load impala-druid.properties error:",e);
        }
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            logger.error("init DataSource error:",e);
        }
    }

    public static DataSource getDataSource()
    {
        return dataSource;
    }

    public static Connection getConnection(){
        try {
            conn  =  dataSource.getConnection();
        } catch (Exception e) {
            logger.error("getConnection error:",e);
        }
        return conn;
    }

    public static void connectionClose(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            logger.error("connectionClose error:",e);
        }
    }

    public static void shutdown(){
        dataSource.close();
        logger.info("impalaPool shutdown!!!");
    }
}