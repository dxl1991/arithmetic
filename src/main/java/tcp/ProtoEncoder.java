package tcp;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * 协议加密器<br>
 */
public class ProtoEncoder extends MessageToMessageEncoder<PBMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, PBMessage pkg, List<Object> out) throws Exception {
            try {
                encode0(ctx, pkg, out);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
    }

    private void encode0(ChannelHandlerContext ctx, PBMessage pkg, List<Object> out) {
        int dataLength = 0;
        if (pkg.getBytes() != null) {
            dataLength = pkg.getBytes().length;
        }

        if (dataLength > Integer.MAX_VALUE || dataLength < 0) {
            System.out.println(String.format("packet send error, the data is too long, userId:{},code:{},len:{}", pkg.getUserId(), pkg.getCode(), dataLength));
            return;
        }

        ByteBuf buffer = ctx.alloc().buffer(PBMessage.SERVER_HEADER_SIZE + dataLength);
        pkg.writeServer((short) dataLength, buffer);

        out.add(buffer);
    }

}
