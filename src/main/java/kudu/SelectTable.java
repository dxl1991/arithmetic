package kudu;

import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.apache.kudu.client.KuduScanner;
import org.apache.kudu.client.KuduTable;
import org.apache.kudu.client.RowResult;

/**
 * @author dengxinlong
 * @date 2020/10/12 17:46
 * @version 1.0
 */
public class SelectTable {
    public static void main(String[] args) {
        //master地址
        String masterAddr = "10.1.12.205:7051";

        KuduClient client = new KuduClient.KuduClientBuilder(masterAddr)
                .defaultOperationTimeoutMs(6000).build();

        try {
            KuduTable table = client.openTable("log_battle");

            //创建scanner扫描
            KuduScanner scanner = client.newScannerBuilder(table).build();

            //遍历数据
            while(scanner.hasMoreRows()){
                for (RowResult rowResult : scanner.nextRows()){
                    System.out.println(rowResult.getLong("id")
                            +"\t"+rowResult.getInt("gameType"));
                }
            }
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
