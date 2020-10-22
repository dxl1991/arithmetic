package kudu;

import org.apache.kudu.client.Insert;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.apache.kudu.client.KuduSession;
import org.apache.kudu.client.KuduTable;
import org.apache.kudu.client.SessionConfiguration;

/**
 * @author dengxinlong
 * @date 2020/10/14 17:53
 * @version 1.0
 */
public class InsertRow {
    public static void main(String[] args) {
        //master地址
        String masterAddr = "10.1.12.205:7051";

        KuduClient client = new KuduClient.KuduClientBuilder(masterAddr)
                .defaultOperationTimeoutMs(6000).build();

        try {
            KuduTable table = client.openTable("impala::default.log_battle1");
            KuduSession kuduSession = client.newSession();

            kuduSession.setFlushMode(SessionConfiguration.FlushMode.MANUAL_FLUSH);
            kuduSession.setMutationBufferSpace(3000);
            for (int i = 0; i <10 ; i++) {
                Insert insert = table.newInsert();
                insert.getRow().addLong("id",123+i);
                insert.getRow().addString("createtime","2020-10-15 00:00:00");
                insert.getRow().addInt("gametype",1);
                insert.getRow().addInt("sceneid",223);
                insert.getRow().addInt("heroid",245);
                insert.getRow().addLong("userid",2678);
                insert.getRow().addInt("win",1);
                insert.getRow().addLong("gameid",2678566);
                kuduSession.flush();
                kuduSession.apply(insert);
            }
            kuduSession.close();
        } catch (KuduException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (KuduException e) {
                e.printStackTrace();
            }
        }

    }
}
