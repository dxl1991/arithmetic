package udp.kcp;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author dengxinlong
 * @date 2020/6/17 15:21
 * @version 1.0
 */
public class ChannelMgr implements Runnable {
    public ChannelHandlerContext ctx;
    Thread thread;
    int id = 10086;

    public ChannelMgr(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                if (ctx != null && ctx.channel().isActive() && ctx.channel().isWritable()) {
                    KCPMessage message = new KCPMessage();
                    message.setMsg("repsonse" + id);
                    message.setCode(id++);
                    ctx.writeAndFlush(message);
                }
            } catch (Exception e) {
                System.out.println("发送失败");
                e.printStackTrace();
            }
        }
    }
}
