package websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author dengxinlong
 * @date 2021/8/25 10:11
 * websocket 与 HTTP 协议有着良好的兼容性，在请求头加了websocket内容
 * 浏览器输入http://localhost:8081/可以测试
 * 浏览器通过GET方式发送Websocket请求，服务器通过判断其请求头部是否包含 “Upgrade: websocket”请求头来判断是否Websocket协议，如果存在这个请求头，则表示用Websocket协议来通信
 * websocket数据是以帧(frame)形式传递
 *
 * （1）建立在 TCP 协议之上，服务器端的实现比较容易。
 * （2）与 HTTP 协议有着良好的兼容性。默认端口也是80和443，并且握手阶段采用 HTTP 协议，因此握手时不容易屏蔽，能通过各种 HTTP 代理服务器。
 * （3）数据格式比较轻量，性能开销小，通信高效。
 * （4）可以发送文本，也可以发送二进制数据。
 * （5）没有同源限制，客户端可以与任意服务器通信。
 * （6）协议标识符是ws（如果加密，则为wss），服务器网址就是 URL。
 */
public class NioWebSocketServer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private void init(){
        logger.info("正在启动websocket服务器");
        NioEventLoopGroup boss=new NioEventLoopGroup();
        NioEventLoopGroup work=new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(boss,work);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new NioWebSocketChannelInitializer());
            Channel channel = bootstrap.bind(8081).sync().channel();
            logger.info("webSocket服务器启动成功："+channel);
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.info("运行出错："+e);
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
            logger.info("websocket服务器已关闭");
        }
    }

    public static void main(String[] args) {
        new NioWebSocketServer().init();
    }
}

