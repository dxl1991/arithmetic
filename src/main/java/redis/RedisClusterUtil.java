package redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author dengxinlong
 * @date 2020/7/23 11:29
 * @version 1.0
 */
public class RedisClusterUtil {
    private static JedisCluster jedisCluster;

    private static void initCluster() {
        Set<HostAndPort> clusterNodes = new HashSet<>();
        clusterNodes.add(new HostAndPort("10.1.12.213", 7001));
        clusterNodes.add(new HostAndPort("10.1.12.213", 7002));
        clusterNodes.add(new HostAndPort("10.1.12.213", 7003));
        clusterNodes.add(new HostAndPort("10.1.12.213", 7004));
        clusterNodes.add(new HostAndPort("10.1.12.213", 7005));
        clusterNodes.add(new HostAndPort("10.1.12.213", 7006));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);//最大连接个数
        jedisPoolConfig.setMaxIdle(10);//最大空闲连接个数
        jedisPoolConfig.setMaxWaitMillis(2000);//获取连接时的最大等待毫秒数，若超时则抛异常。-1代表不确定的毫秒数
        jedisPoolConfig.setTestOnBorrow(true);//获取连接时检测其有效性
        jedisCluster = new JedisCluster(clusterNodes, 15000, 100, jedisPoolConfig);//第二个参数：超时时间     第三个参数：最大尝试重连次数
    }

    public static String get(String key) {
        String value = jedisCluster.get(key);
        return value;
    }

    public static void close() throws IOException {
        if(jedisCluster != null){
            jedisCluster.close();
        }
    }
}
