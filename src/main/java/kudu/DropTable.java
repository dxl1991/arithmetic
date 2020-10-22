package kudu;

import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;

/**
 * @author dengxinlong
 * @date 2020/10/14 17:28
 * @version 1.0
 */
public class DropTable {
    public static void main(String[] args) {
        //master地址
        String masterAddr = "10.1.12.205:7051";

        KuduClient client = new KuduClient.KuduClientBuilder(masterAddr)
                .defaultOperationTimeoutMs(6000).build();

        try {
            client.deleteTable("data_hero");
        } catch (KuduException e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (KuduException e) {
                e.printStackTrace();
            }
        }

    }
}
