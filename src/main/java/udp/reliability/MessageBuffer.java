package udp.reliability;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;

/**
 * @Author dengxinlong
 * @Date 2020/5/30 20:48
 * @slogan CODE IS TRUTH
 */
public class MessageBuffer {
    private ChannelHandlerContext ctx;
    private InetSocketAddress address;
    private AtomicLong seqIds = new AtomicLong();
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture scheduledFuture;
    private short sf, sn;
    private static short windowsSize = 7;
    private Map<Short, Message> sendBuff = new HashMap<>(windowsSize);


    public MessageBuffer(ChannelHandlerContext ctx, InetSocketAddress address) {
        this.ctx = ctx;
        this.address = address;
    }

    public void reciveAck(short ack) {
        sendBuff.remove(ack);
        if (sf == ack) {
            sf = (short) ((sf + 1) % Message.MAX_SEQ);
        } else {

        }
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        if (sf != sn) {
            startSchedule();
        }
    }

    public void sendMessage(Message message) {
        if (sn - sf >= windowsSize) {
            return;
        }
        short seq = (short) (seqIds.incrementAndGet() % Message.MAX_SEQ);
        message.setSeq(seq);
        if (sendBuff.size() == 0) {
            sn = seq;
        }
        sn = seq;
        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(message.encode()), address));
        sendBuff.put(seq, message);
        startSchedule();
    }

    private void startSchedule() {
        if (scheduledFuture == null || scheduledFuture.isDone() || scheduledFuture.isCancelled()) {
            scheduledFuture = scheduledExecutorService.schedule(() -> {
                reSend();
            }, 100, TimeUnit.MILLISECONDS);
        }
    }

    public void reSend() {

    }
}
