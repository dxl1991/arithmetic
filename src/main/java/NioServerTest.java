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
 */
public class NioServerTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(8888));
        System.out.println("NIO server has started,listening on port:" + serverChannel.getLocalAddress());
        Selector selector = Selector.open();
        //这就话的含义，每个客户端来了之后，就把客户端注册到Selector选择器上，默认状态是Accepted
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while(true){
            int select = selector.select();
            if(select == 0){
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = channel.accept();
                    System.out.println("Connection from "+ clientChannel.getRemoteAddress());
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector,SelectionKey.OP_READ);
                }
                if(key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    channel.read(byteBuffer);
                    String request = new String(byteBuffer.array()).trim();
                    byteBuffer.clear();
                    System.out.println(String.format("From %s : %s",channel.getRemoteAddress(),request));
                    if("q".equals(request)){
                        channel.close();
                    }else {
                        channel.write(ByteBuffer.wrap("aaaa".getBytes()));
                    }
                }
            }
        }
    }
}
