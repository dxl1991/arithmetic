package tcp;

import java.io.Serializable;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

public class PBMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final short HEADER = 0x71ab;

    public static final byte TRACE_LENGTH = 48;

    public static final short HEADER_SIZE = 7;
    public static final short SERVER_HEADER_SIZE = 15 + TRACE_LENGTH;

    private short header = HEADER; // 消息头标识
    private short code;// 协议号
    private byte param;// 扩展字段标识

    private byte[] bytes; // 消息体数据

    private long userId; // 玩家ID
    private String traceId = null; // 消息追踪ID

    public PBMessage(short code) {
        this(code, -1);
    }

    public PBMessage(short code, byte param, byte[] bytes) {
        this(code, -1);
        this.param = param;
        this.bytes = bytes;
    }

    public PBMessage(short code, byte param, byte[] bytes, long userId) {
        this(code, userId);
        this.param = param;
        this.bytes = bytes;
    }

    public PBMessage(short code, long userId) {
        this.code = code;
        this.userId = userId;
    }

    public short getHeader() {
        return header;
    }

    public void setHeader(short header) {
        this.header = header;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public byte getParam() {
        return param;
    }

    public void setParam(byte param) {
        this.param = param;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }


    public void writeClient(short len, ByteBuf out) {
        out.writeShort(HEADER);
        out.writeShort(code);
        out.writeByte(param);

        out.writeShort(len);
        if (bytes != null) {
            out.writeBytes(bytes);
        }
    }

    public void writeServer(short len, ByteBuf out) {
        out.writeShort(HEADER);
        out.writeShort(code);
        out.writeByte(param);
        out.writeLong(userId);

        if (traceId == null) {
            out.writeZero(TRACE_LENGTH);
        } else {
            int traceLen = traceId.length();
            if (traceLen > TRACE_LENGTH) {
                out.writeZero(TRACE_LENGTH);
            } else {
                int wIdx = out.writerIndex();
                out.writeByte(0);
                traceLen = out.writeCharSequence(traceId, CharsetUtil.UTF_8);
                out.setByte(wIdx, traceLen);
                out.writeZero(TRACE_LENGTH - 1 - traceLen);
            }
        }

        out.writeShort(len);
        if (bytes != null) {
            out.writeBytes(bytes);
        }
    }

}
