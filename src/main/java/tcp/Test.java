package tcp;

import io.netty.channel.Channel;

/**
 * @author dengxinlong
 * @date 2020/9/1 18:04
 * @version 1.0
 */
public class Test {
    public static void main(String[] args)  throws Exception{
        for(int i=0;i<10000;i++){
            NettyClient client = new NettyClient("10.1.11.151",10000);
            client.start();
            Channel channel = client.getChannel();
            short code = 123;
            long userId = 123123123;
            PBMessage msg = new PBMessage(code,userId);
            msg.setBytes("hello".getBytes());
            channel.writeAndFlush(msg);
        }
    }
}
