package guava;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.text.NumberFormat;
import java.util.*;

/**
 * 布隆过滤器
 * 是一种数据结构，比较巧妙的概率型数据结构（probabilistic data structure）
 * 特点是高效地插入和查询，可以用来告诉你 “某样东西一定不存在或者可能存在”
 * 但是没有删除数据的方法，因为用的位标记，可能会误删，如果存的是计数器就可以删
 */
public class BloomFilterTest {
    private static final int insertions = 1000000; //100万
    public static void main(String[] args) {

        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions);
        List<String> list = new ArrayList<>(insertions);
        Set<String> set = new HashSet<>(insertions);

        for(int i=0;i<insertions;i++){
            String uuid = UUID.randomUUID().toString();
            bloomFilter.put(uuid);
            list.add(uuid);
            set.add(uuid);
        }

        int right = 0;
        int wrong = 0;
        for(int i=0;i<10000;i++){
            String s;
            //实际是有100个存在，9900个不存在
            if(i % 100 == 0){
                s = list.get(i);
            }else{
                s = UUID.randomUUID().toString();
            }
            if(!bloomFilter.mightContain(s)){
                System.out.println("布隆过滤器判断没有值！！");
                continue;
            }
            if(set.contains(s)){
                right++;
            }else{
                wrong++;
            }
        }
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        System.out.println("right = "+right+",wrong="+wrong+",误判率="+numberFormat.format((float)wrong/(10000 - right)));
    }
}
