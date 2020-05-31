package udp.reliability;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;


/**
 * @Author dengxinlong
 * @Date 2020/5/30 15:24
 * @slogan CODE IS TRUTH
 */
public class Server extends Thread implements Runnable {
    private final int MAX_LENGTH = 9;
    private final int PORT = 5060;
    private DatagramSocket datagramSocket;

    @Override
    public void run() {
        try {
            init();
            while (true) {
                try {
                    byte[] buffer = new byte[MAX_LENGTH];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    receive(packet);
                    Message message = Message.decode(packet.getData());
                    System.out.println("接收数据包" + message);
                    byte[] bt = new byte[packet.getLength()];

                    //                    System.arraycopy(packet.getData(), 0, bt, 0, packet.getLength());
                    //                    System.out.println(packet.getAddress().getHostAddress() + "：" + packet.getPort() + "：" + new String(bt));
                    //                    packet.setData(bt);
                    //                    response(packet);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receive(DatagramPacket packet) throws Exception {
        datagramSocket.receive(packet);
    }

    public void response(DatagramPacket packet) throws Exception {
        datagramSocket.send(packet);
    }

    public void initNetty() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            //通过NioDatagramChannel创建Channel，并设置Socket参数支持广播
            //UDP相对于TCP不需要在客户端和服务端建立实际的连接，因此不需要为连接（ChannelPipeline）设置handler
            Bootstrap b = new Bootstrap();
            b.group(bossGroup).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true).handler(new ServerHandler());
            b.bind(PORT).sync().channel().closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * 初始化连接
     */
    public void init() {
        try {
            datagramSocket = new DatagramSocket(PORT);
            System.out.println("udp服务端已经启动！");
        } catch (Exception e) {
            datagramSocket = null;
            System.out.println("udp服务端启动失败！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().initNetty();
    }
}
