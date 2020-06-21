package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Author dengxinlong
 * @Date 2020/6/21 9:44
 * @slogan CODE IS TRUTH
 */
public class ServerSender implements Runnable {
    private DatagramPacket packet;
    private DatagramSocket datagramSocket;
    private int id;

    public ServerSender(DatagramSocket datagramSocket, DatagramPacket packet, int id) {
        this.packet = packet;
        this.datagramSocket = datagramSocket;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                packet.setData(("server data" + String.valueOf(id)).getBytes());
                datagramSocket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
