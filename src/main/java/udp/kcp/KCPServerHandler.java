package udp.kcp;

import io.jpower.kcp.netty.UkcpChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class KCPServerHandler extends SimpleChannelInboundHandler<KCPMessage> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("remote address active : " + ctx.channel().remoteAddress().toString());
        ChannelMgr mgr = new ChannelMgr(ctx);
        System.out.println(ctx.channel().getClass());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("remote address inactive : " + ctx.channel().remoteAddress().toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, KCPMessage msg) throws Exception {
        UkcpChannel kcpCh = (UkcpChannel) ctx.channel();
        System.out.println("conv = " + kcpCh.conv());
        System.out.println(msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
