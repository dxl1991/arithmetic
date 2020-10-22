package kudu;

import org.apache.kudu.ColumnSchema;
import org.apache.kudu.ColumnSchema.ColumnSchemaBuilder;
import org.apache.kudu.ColumnTypeAttributes;
import org.apache.kudu.Schema;
import org.apache.kudu.Type;
import org.apache.kudu.client.CreateTableOptions;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;

import java.util.LinkedList;

/**
 * @author dengxinlong
 * @date 2020/10/9 11:08
 * @version 1.0
 */
public class CreateTable {
    private static ColumnSchema newColumn(String name, Type type, Boolean isKey){
        ColumnSchema.ColumnSchemaBuilder column =
                new ColumnSchema.ColumnSchemaBuilder(name, type);
        column.key(isKey);
        return column.build();
    }
    private static ColumnSchema newDecimalColumn(String name, int precision,int scale){
        ColumnSchema.ColumnSchemaBuilder column =
                new ColumnSchema.ColumnSchemaBuilder(name, Type.DECIMAL);
        column.key(false);
        column.typeAttributes(new ColumnTypeAttributes.ColumnTypeAttributesBuilder().precision(precision).scale(scale).build());
        return column.build();
    }

    public static void main(String[] args) {
        //master地址
        String masterAddr = "10.1.12.205:7051";

        //创建kudu的数据库连接
        KuduClient client = new KuduClient.KuduClientBuilder(masterAddr)
                .defaultOperationTimeoutMs(6000).build();

        //设置表的schema
        LinkedList<ColumnSchema> columns = new LinkedList<>();

        /*
         *和RDBMS不同的是，Kudu不提供自动递增列功能，因此应用程序必须始终
         * 在插入期间提供完整的主键
         */
        columns.add(newColumn("id", Type.INT64,true));
        columns.add(newColumn("gameType",Type.INT8,false));
        columns.add(newColumn("heroId",Type.INT32,false));
        columns.add(newColumn("winCnt",Type.INT32,false));
        columns.add(newColumn("totalCnt",Type.INT32,false));
        columns.add(newDecimalColumn("winRate",10,2));
        columns.add(newColumn("gameCnt",Type.INT32,false));
        columns.add(newColumn("gameTotalCnt",Type.INT32,false));
        columns.add(newDecimalColumn("gameRate",10,2));
        columns.add(newColumn("createTime",Type.UNIXTIME_MICROS,false));

        Schema schema = new Schema(columns);

        //创建表时提供的所有选项
        CreateTableOptions options = new CreateTableOptions();

        //设置表的replica备份和分区规则
        LinkedList<String> parcols = new LinkedList<>();
        parcols.add("id");

        //设置表的备份数
        options.setNumReplicas(1);

        //设置hash分区和数量
        options.addHashPartitions(parcols,3);

        try {
            client.createTable("data_hero",schema,options);
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
