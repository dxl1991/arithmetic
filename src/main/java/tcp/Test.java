package tcp;

import io.netty.channel.Channel;

/**
 * @author dengxinlong
 * @date 2020/9/1 18:04
 * @version 1.0
 */
public class Test {
    public static void main(String[] args)  throws Exception{
        NettyClient client = new NettyClient("127.0.0.1",6688);
        client.start();
        Channel channel = client.getChannel();
        short code = 123;
        long userId = 123123123;
        PBMessage msg = new PBMessage(code,userId);
        msg.setBytes("hello".getBytes());
        channel.writeAndFlush(msg);
    }
}
