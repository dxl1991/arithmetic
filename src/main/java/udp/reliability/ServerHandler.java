package udp.reliability;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * @Author dengxinlong
 * @Date 2020/5/30 17:17
 * @slogan CODE IS TRUTH
 */
public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private MessageBuffer messageBuffer;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
            DatagramPacket datagramPacket) throws Exception {
        ByteBuf byteBuf = datagramPacket.content();
        short len = byteBuf.getShort(0);
        len = Short.reverseBytes(len);
        byte[] buf = new byte[len];
        byteBuf.getBytes(0, buf);
        Message req = Message.decode(buf);
        System.out.println(req);
        Message ack = new Message();
        ack.setAck(req.getSeq());
        channelHandlerContext.writeAndFlush(
                new DatagramPacket(Unpooled.copiedBuffer(ack.encode()), datagramPacket.sender()));
        if (messageBuffer == null) {
            messageBuffer = new MessageBuffer(channelHandlerContext, datagramPacket.sender());
        }
        messageBuffer.reciveAck(req.getAck());
    }
}
