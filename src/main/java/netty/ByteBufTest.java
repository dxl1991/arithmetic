package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

/**
 * @author dengxinlong
 * @date 2021/10/28 17:20
 */
public class ByteBufTest {
    public static void main(String[] args) throws Exception {
        PooledByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;
        ByteBuf original = allocator.directBuffer(32);
        original.writeByte(1);
        original.writeByte(2);
        original.writeByte(3);

        //相当于original.slice(original.readerIndex(), original.readableBytes());
        ByteBuf sub = original.slice();

        //新缓冲区修改值后互相影响
        sub.setByte(0, 10);
        System.out.println("sub.readByte = " + sub.readByte());
        System.out.println("original.readByte = " + original.readByte());

        //原始缓冲区再读一次
        System.out.println("original.readByte = " + original.readByte());

        //Index互不影响
        System.out.println("sub.readerIndex = " + sub.readerIndex());
        System.out.println("original.readerIndex = " + original.readerIndex());

        //必须释放
        original.release();
        test();
    }

    public static void test() throws Exception {
        PooledByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;
        ByteBuf original = allocator.directBuffer(32);
        original.writeByte(1);
        original.writeByte(2);
        original.writeByte(3);

        ByteBuf sub = original.retainedSlice(); //与slice一模一样，只是调用了retain，在使用完毕后需要多释放一次。

        //在使用完毕后，必须要释放俩次
        sub.release();
        original.release();

        System.out.println("sub.refCnt = " + sub.refCnt());
        System.out.println("original.refCnt = " + original.refCnt());
    }
}
