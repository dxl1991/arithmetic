package udp.reliability;

/**
 * @Author dengxinlong
 * @Date 2020/5/30 15:01
 * @slogan CODE IS TRUTH
 */
public class Message {
    public static short MAX_SEQ = 32;
    private short len;
    private short seq;
    private short ack;
    private byte[] data;

    public byte[] encode() {
        len = (short) (data == null ? 6 : data.length + 6);
        byte[] buf = new byte[len];
        buf[0] = (byte) len;
        buf[1] = (byte) (len >> 8);
        buf[2] = (byte) seq;
        buf[3] = (byte) (seq >> 8);
        buf[4] = (byte) ack;
        buf[5] = (byte) (ack >> 8);
        if (data != null) {
            System.arraycopy(data, 0, buf, 6, data.length);
        }
        return buf;
    }

    public static Message decode(byte[] buf) {
        Message message = new Message();
        message.len = buf[1];
        message.len = (short) ((message.len << 8) | buf[0]);
        message.seq = buf[3];
        message.seq = (short) ((message.seq << 8) | buf[2]);
        message.ack = buf[5];
        message.ack = (short) ((message.ack << 8) | buf[4]);
        if (buf.length > 4) {
            message.data = new byte[buf.length - 6];
            System.arraycopy(buf, 6, message.data, 0, message.data.length);
        }
        return message;
    }

    public short getSeq() {
        return seq;
    }

    public void setSeq(short seq) {
        this.seq = seq;
    }

    public short getAck() {
        return ack;
    }

    public void setAck(short ack) {
        this.ack = ack;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public short getLen() {
        return len;
    }

    public void setLen(short len) {
        this.len = len;
    }

    @Override
    public String toString() {
        return "Message{" + "len=" + len + ", seq=" + seq + ", ack=" + ack + ", data=" +
                new String(data) + '}';
    }
}
