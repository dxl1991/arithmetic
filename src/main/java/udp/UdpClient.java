package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/***
 * UDP Client端
 * 以太网（Ethernet）的数据帧在链路层（传输中的最小可识别单元）
 * IP包在网络层 　　
 * TCP或UDP包在传输层 　　
 * TCP或UDP中的数据（Data)在应用层 　　
 * 它们的关系是 == 数据帧｛IP包｛TCP或UDP包｛Data｝｝｝
 *
 * MTU(Maximum Transmission Unit) ： 链路层 数据帧的内容最大值(不包括帧头和帧尾)
 * MTU其实就是在每一个节点的管控值，只要是大于这个值的数据帧，要么选择分片，要么直接丢弃。大部分网络设备的MTU值是1500
 * 在普通的局域网环境下，链路层MTU值为1500，所以UDP的数据最大为1500-20-8=1472字节(避免分片重组)
 * Internet上的标准MTU值为576，所以UDP数据长度最好在576－20－8＝548字节以内
 * udp分片后，若某一片丢失，则整个包都丢失了
 *
 * udp丢包：
 *   1、linux内核socket缓冲区设的太小
 *   2、服务器负载过高，占用了大量cpu资源，无法及时处理linux内核socket缓冲区中的udp数据包，导致丢包
 *   3、磁盘IO忙
 *   4、磁盘满导致无法IO
 *   5、发送的包太大
 *   6、发送的频率太快，发送端网卡处理不过来
 *   https://www.jianshu.com/p/7f4700a5e970
 ***/
public class UdpClient {

    private String sendStr = "hello";
    private String netAddress = "255.255.255.255";
    private final int PORT = 5060;

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public UdpClient() {
        try {
            datagramSocket = new DatagramSocket();
            //            byte[] buf = sendStr.getBytes();
            byte[] buf =
                    new byte[65507];//局域网数据包最大 = 65535(ip包头2个字节标识总长度) - 20( ip包头) - 8（udp包头） = 65507
            for (int i = 0; i < buf.length; i++) {
                buf[i] = 'j';
            }
            InetAddress address = InetAddress.getByName(netAddress);
            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT);
            datagramSocket.setSoTimeout(5000);//设置接收数据的超时时间5秒
            datagramSocket.send(datagramPacket);
            while (true) {
                byte[] receBuf = new byte[1024];
                DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
                datagramSocket.receive(recePacket);
                String receStr = new String(recePacket.getData(), 0, recePacket.getLength());
                System.out.println("收到数据包：" + receStr);
                //获取服务端ip
                String serverIp = recePacket.getAddress().getHostAddress();
                System.out.println("服务器id：" + serverIp);
            }

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
                    UdpClient udpClient = new UdpClient();
                }
            }).start();
        }
    }
}