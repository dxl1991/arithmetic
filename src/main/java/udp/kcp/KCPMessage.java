package udp.kcp;

import io.netty.buffer.ByteBuf;

public class KCPMessage {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int code;
    private int code1;

    public int getLength() {
        return 4 + msg.length() + 4;
    }

    public void write(ByteBuf byteBuf) {
        byteBuf.writeInt(code);
        byteBuf.writeByte(msg.length());
        byteBuf.writeBytes(msg.getBytes());
        byteBuf.writeInt(code1);
    }

    public void read(ByteBuf byteBuf) {
        code = byteBuf.readInt();
        byte[] data = new byte[byteBuf.readByte()];
        byteBuf.readBytes(data);
        code1 = byteBuf.readInt();
        msg = new String(data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        code1 = code + 1000000;
    }

    @Override
    public String toString() {
        return "KCPMessage{" + "msg='" + msg + '\'' + ", code=" + code + ", code1=" + code1 + '}';
    }
}
