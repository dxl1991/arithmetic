package shardingSphere;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author dengxinlong
 * @date 2020/10/29 14:59
 * @version 1.0
 */
public class MyComplexShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValues) {
        Object userName = shardingValues.getColumnNameAndShardingValuesMap().get("userName");
        Object channel = shardingValues.getColumnNameAndShardingValuesMap().get("channel");
        List<String> shardingSuffix = new ArrayList<>();
        int hashCode = userName.hashCode() + channel.hashCode();
        String tableName = "u_account" + (hashCode % availableTargetNames.size());
        shardingSuffix.add(tableName);
        return shardingSuffix;
    }
}
