package udp.kcp;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class KCPEncoder extends MessageToMessageEncoder<KCPMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, KCPMessage msg, List<Object> out) throws Exception {
        String temp = msg.getMsg();

        ByteBuf byteBuf1 = ctx.alloc().buffer(msg.getLength()); //byteBuf 是 PooledUnsafeDirectByteBuf
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.buffer(msg.getLength()); //byteBuf 是 PooledUnsafeDirectByteBuf
        byteBuf.setZero(0, byteBuf.capacity()); //把数组所有值设置为0
        byteBuf.clear(); //读指针和写指针设置为0
        //        byteBuf.writeBytes(temp.getBytes());
        msg.write(byteBuf);
        out.add(byteBuf);
    }
}
