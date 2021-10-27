import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * @author dengxinlong
 * @date 2021/9/16 11:22
 * 一个long可以代表1到64是否存在，两个long就可以代表1到128是否出现过，一个1G的空间，有 8102410241024=8.5810^9bit，也就是可以表示85亿个不同的数
 * 统计1亿个数据中没有出现的数据，比如判断8976是否出现，就判断第8976的标本的bit是否等于1
 */
public class TestBitset
{
    public static void main(String[] args)
    {
        Random random=new Random();

        List<Integer> list=new ArrayList<>();
        for(int i=0;i<10000000;i++)
        {
            int randomResult=random.nextInt(100000000);
            list.add(randomResult);
        }
        System.out.println("产生的随机数有");
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }
        BitSet bitSet=new BitSet(100000000);
        for(int i=0;i<10000000;i++)
        {
            bitSet.set(list.get(i));
        }

        System.out.println("0~1亿不在上述随机数中有"+bitSet.cardinality());
        for (int i = 0; i < 100000000; i++)
        {
            if(!bitSet.get(i))
            {
                System.out.println(i);
            }
        }
    }
}