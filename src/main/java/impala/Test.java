package impala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author dengxinlong
 * @date 2020/10/15 10:27
 * @version 1.0
 */
public class Test {
    static {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        long currentTimeMillis = System.currentTimeMillis();
        testDruidPool();
        System.out.println("耗时:"+(System.currentTimeMillis() - currentTimeMillis));
    }

    public static void testDruidPool() throws SQLException{
        Connection conn = ImpalaPool.getConnection();
        String sql = "SELECT to_date(t1.createTime) AS 'createTime',t1.sceneId AS 'gameType',t1.heroId,t2.winCnt AS 'winCnt',\n" +
                "t1.totalCnt,(t2.winCnt/t1.totalCnt) * 100 AS 'winRate',\n" +
                "t1.totalCnt AS 'gameCnt',t3.gameTotalCnt,(t1.totalCnt/t3.gameTotalCnt) * 100 AS gameRate\n" + "FROM \n" +
                "(SELECT to_date(createTime) AS 'createTime',sceneId,heroId,COUNT(1) AS totalCnt FROM log_battle GROUP BY to_date(createTime),sceneId,heroId) t1\n" +
                "LEFT JOIN \n" + "(SELECT sceneId,heroId,COUNT(1) AS winCnt FROM log_battle WHERE win = 1 GROUP BY sceneId,heroId) t2\n" +
                "ON t1.sceneId = t2.sceneId AND t1.heroId = t2.heroId\n" + "LEFT JOIN \n" +
                "(SELECT sceneId,COUNT(1) AS gameTotalCnt FROM log_battle GROUP BY sceneId) t3\n" + "ON t1.sceneId = t3.sceneId;";
        //        System.out.println("查询语句："+sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Date date = rs.getDate("createTime");
            System.out.println("date="+date);
        }
        ImpalaPool.connectionClose(conn);
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        String JDBCUrl = "jdbc:hive2://hadoop002:21050/;auth=noSasl";
        String username = "";
        String password = "";
        return DriverManager.getConnection(JDBCUrl,username,password);
    }

    public static void insert()throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        String sql = "insert into log_battle1 values(2,\"2020-10-15 00:00:00\",100,10001,1223,13,31,22223);";
        //        System.out.println("查询语句："+sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
    }

    public static void count() throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        String sql = "SELECT to_date(t1.createTime) AS 'createTime',t1.sceneId AS 'gameType',t1.heroId,t2.winCnt AS 'winCnt',\n" +
                "t1.totalCnt,(t2.winCnt/t1.totalCnt) * 100 AS 'winRate',\n" +
                "t1.totalCnt AS 'gameCnt',t3.gameTotalCnt,(t1.totalCnt/t3.gameTotalCnt) * 100 AS gameRate\n" + "FROM \n" +
                "(SELECT to_date(createTime) AS 'createTime',sceneId,heroId,COUNT(1) AS totalCnt FROM log_battle GROUP BY to_date(createTime),sceneId,heroId) t1\n" +
                "LEFT JOIN \n" + "(SELECT sceneId,heroId,COUNT(1) AS winCnt FROM log_battle WHERE win = 1 GROUP BY sceneId,heroId) t2\n" +
                "ON t1.sceneId = t2.sceneId AND t1.heroId = t2.heroId\n" + "LEFT JOIN \n" +
                "(SELECT sceneId,COUNT(1) AS gameTotalCnt FROM log_battle GROUP BY sceneId) t3\n" + "ON t1.sceneId = t3.sceneId;";
//        System.out.println("查询语句："+sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Date date = rs.getDate("createTime");
            System.out.println("date="+date);
        }
//        int columnCount = rs.getMetaData().getColumnCount();
//        while (rs.next()){
//            for(int i=1;i<=columnCount;i++){
//                System.out.print(rs.getString(i)+"\t");
//            }
//            System.out.println("");
//        }
    }

}
