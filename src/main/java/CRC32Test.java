import java.util.zip.CRC32;

/**
 * CRC
 * 循环冗余校验（Cyclic Redundancy Check， CRC）是一种根据网络数据包或计算机文件等数据产生简短固定位数校验码的一种信道编码技术，
 * 主要用来检测或校验数据传输或者保存后可能出现的错误。它是利用除法及余数的原理来作错误侦测的。
 */
public class CRC32Test {
    public static void main(String[] args) {
        CRC32 crc32 = new CRC32();
        crc32.update("Hello dxl".getBytes());
        System.out.println(crc32.getValue());
    }
}
