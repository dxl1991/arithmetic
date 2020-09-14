package tcp;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务器消息转发接口<br>
 * 基于Netty的消息接口, 用于处理服务器的消息接受处理.
 */
@Sharable
public class ServerChannelHandler extends SimpleChannelInboundHandler<PBMessage> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server socket connect, address : " + ctx.channel().remoteAddress() + ", channel = " + ctx.hashCode());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server socket disconnect, address : " + ctx.channel().remoteAddress() + ", channel = " + ctx.hashCode());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server socket exception, address : " + ctx.channel().remoteAddress());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PBMessage packet) throws Exception {
        System.out.println(packet);
    }

}
