package tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/22 11:56
 * cmd连接server命令：telnet localhost 9999
 * BIO：accept() 会阻塞，来一个连接就会跳过阻塞
 *      receive() 会阻塞，来一个io数据会跳过阻塞
 * NIO：多路复用器只告诉你哪些可以读
 *      读取数据的时候还是同步的
 *      netty：同步 IO，异步 计算。使用epoll多路复用器
 */
public class NioServerTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(8888));
        System.out.println(
                "NIO server has started,listening on port:" + serverChannel.getLocalAddress());
        Selector selector = Selector.open();
        //这个channel关心Accept事件，epoll.ctl
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            //多路复用器告诉我有没有事件需要处理,epoll.wait
            int select = selector.select();
            //没有事件处理，就不往下执行了，防止用户态和内核态切换
            if (select == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys;
            //有事件的channel集合
            selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = channel.accept();
                    System.out.println("Connection from " + clientChannel.getRemoteAddress());
                    clientChannel.configureBlocking(false);
                    //这个channel关心Read事件
                    clientChannel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    channel.read(byteBuffer);
                    String request = new String(byteBuffer.array()).trim();
                    byteBuffer.clear();
                    System.out.println(
                            String.format("From %s : %s", channel.getRemoteAddress(), request));
                    if ("q".equals(request)) {
                        channel.close();
                    } else {
                        channel.write(ByteBuffer.wrap("aaaa".getBytes()));
                    }
                }
            }
        }
    }
}
