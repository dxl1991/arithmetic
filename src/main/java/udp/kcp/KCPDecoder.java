package udp.kcp;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class KCPDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //        byte[] data = new byte[in.readableBytes()];
        //        in.readBytes(data);

        KCPMessage message = new KCPMessage();
        //        message.setMsg(new String(data));
        message.read(in);
        out.add(message);
    }
}
