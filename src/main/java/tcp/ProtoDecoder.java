package tcp;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

public class ProtoDecoder extends ByteToMessageDecoder {

    @Override
    protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object decoded = decode(ctx, in);
        if (decoded != null) {
            out.add(decoded);
        }
    }

    private Object decode(ChannelHandlerContext ctx, ByteBuf in) {
        if (in.readableBytes() < PBMessage.SERVER_HEADER_SIZE) {
            return null;
        }

        // 解密头
        ByteBuf buf = in.slice();
        short head = buf.readShort();
        if (head != PBMessage.HEADER) {
            close(ctx);
            in.clear();
            return null;
        }

        short code = buf.readShort();
        byte param = buf.readByte();
        long userId = buf.readLong();

        byte traceIdLen = buf.readByte();
        String traceId = null;
        if (traceIdLen > 0) {
            traceId = buf.readCharSequence(traceIdLen, CharsetUtil.UTF_8).toString();
            buf.skipBytes(PBMessage.TRACE_LENGTH - traceIdLen - 1);
        } else {
            buf.skipBytes(PBMessage.TRACE_LENGTH - 1);
        }

        short length = buf.readShort();
        // 协议号和长度异常
        if (code <= 0 || length < 0) {
            System.out.println("Error code or length,code:" + code + ",length:" + length + ",from:" + ctx.channel().remoteAddress());
            in.skipBytes(1);// 只跳过一个字节，尽最大可能保证不丢包
            return null;
        }

        // 数据包不完整
        if (length > buf.readableBytes()) {
            return null;
        }

        byte[] data = null;
        if (length > 0) {
            data = new byte[length];
            buf.readBytes(data);
        }

        in.readerIndex(in.readerIndex() + buf.readerIndex());
        PBMessage message = new PBMessage(code, param, data, userId);
        message.setTraceId(traceId);
        return message;
    }

    private void close(ChannelHandlerContext ctx) {
        ctx.channel().close();
    }

}
