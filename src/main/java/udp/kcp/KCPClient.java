package udp.kcp;

import io.jpower.kcp.netty.ChannelOptionHelper;
import io.jpower.kcp.netty.UkcpChannel;
import io.jpower.kcp.netty.UkcpChannelOption;
import io.jpower.kcp.netty.UkcpClientChannel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class KCPClient {

    static final int CONV = Integer.parseInt(System.getProperty("conv", "10"));
    static final String HOST = System.getProperty("host", "10.1.11.21");
    static final int PORT = Integer.parseInt(System.getProperty("port", "32000"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(UkcpClientChannel.class).handler(new ChannelInitializer<UkcpChannel>() {
                @Override
                public void initChannel(UkcpChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new KCPEncoder());
                    p.addLast(new KCPDecoder());
                    p.addLast(new KCPClientHandler());
                }
            });
            //nodelay ：是否启用 nodelay模式，0不启用；1启用。
            //interval ：协议内部工作的 interval，单位毫秒，比如 10ms或者 20ms
            //resend ：快速重传模式，默认0关闭，可以设置2（2次ACK跨越将会直接重传）
            //nc ：是否关闭流控，默认是0代表不关闭，1代表关闭。
            ChannelOptionHelper.nodelay(b, true, 20, 2, true);
            b.option(UkcpChannelOption.UKCP_MTU, 512);

            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();

            Channel channel = f.channel();

            for (int i = 0; i < 1; i++) {
                //                ByteBuf message = channel.alloc().buffer(8); // Unpooled.buffer(5);
                //                message.writeInt(123);
                //                message.writeBytes("sama".getBytes());
                KCPMessage message = new KCPMessage();
                message.setCode(10086);
                message.setMsg("aaaaaddd");
                channel.writeAndFlush(message);
            }

            Thread.sleep(5000);
//            channel.close();

            // Wait until the connection is closed.
            //            f.channel().closeFuture().sync(); //与服务器一直保持连接
        } finally {
            // Shut down the event loop to terminate all threads.
//            group.shutdownGracefully();
        }
    }

}
