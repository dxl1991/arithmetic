package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * udp连接，用于动态ip, pos向255.255.255.255：5060发送请求即可
 * **/
public class UdpServer extends Thread implements Runnable {
    private final int MAX_LENGTH = 65535;
    private final int PORT = 5060;
    private DatagramSocket datagramSocket;
    private int id;

    public void run() {
        try {
            init();
            while (true) {
                try {
                    byte[] buffer = new byte[MAX_LENGTH];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    System.out.println("没收到包的packet长度：" + packet.getLength());//没收到包，这个长度变为初始字节数组长度
                    System.out.println("没收到包的port = " + packet.getPort());//没收到包，这个port为-1
                    datagramSocket.receive(packet);
                    System.out.println("packet长度：" + packet.getLength()); //收到包后，这个长度变为实际接收到的数据包长度
                    System.out.println("port = " + packet.getPort());//没收到包，这个port为客户端的port
                    System.out.println("客户端地址：" + packet.getSocketAddress());
                    System.out.println("收到数据包：" +
                            new String(packet.getData(), packet.getOffset(), packet.getLength())
                                    .length());
                    new Thread(new ServerSender(datagramSocket, packet, id++)).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化连接
     */
    public void init() {
        try {
            datagramSocket = new DatagramSocket(PORT);
            System.out.println("udp服务端已经启动！");
            System.out.println("ReceiveBufferSize=" + datagramSocket.getReceiveBufferSize());
        } catch (Exception e) {
            datagramSocket = null;
            System.out.println("udp服务端启动失败！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UdpServer().start();
    }
}
