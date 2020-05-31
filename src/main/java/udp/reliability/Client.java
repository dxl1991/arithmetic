package udp.reliability;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author dengxinlong
 * @Date 2020/5/30 15:27
 * @slogan CODE IS TRUTH
 */
public class Client {
    private String sendStr = "hello";
    private String netAddress = "255.255.255.255";
    private final int PORT = 5060;

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public Client() {
        try {
            datagramSocket = new DatagramSocket();
            Message message = new Message();
            message.setSeq((short) 7);
            message.setAck((short) 4);
            message.setData("hello".getBytes());
            byte[] buf = message.encode();
            InetAddress address = InetAddress.getByName(netAddress);
            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT);
            datagramSocket.send(datagramPacket);

            //            byte[] receBuf = new byte[1024];
            //            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            //            datagramSocket.receive(recePacket);
            //
            //            String receStr = new String(recePacket.getData(), 0, recePacket.getLength());
            //            System.out.println("收到数据包：" + receStr);
            //            //获取服务端ip
            //            String serverIp = recePacket.getAddress().getHostAddress();
            //            System.out.println("服务器id：" + serverIp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Client client = new Client();
                }
            }).start();
        }
    }
}
