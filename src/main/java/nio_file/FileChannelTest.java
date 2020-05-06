package nio_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class FileChannelTest {
    public static void main(String[] args) throws Exception {
        File source = new File("test.txt");
        File target = new File("test2.txt");
        copyByChannel(source,target);
//        copyByStream(source,target);
    }

    private static void copyByStream(File source, File target) throws Exception{
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(target);
        byte[] read = new byte[1024];
        try{
            while (in.read(read) > 0){
                out.write(read);
            }
        }finally {
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }
    }

    private static void copyByChannel(File source, File target) throws Exception {
        RandomAccessFile rs = new RandomAccessFile(source,"r");
        FileChannel sourceChanel = rs.getChannel();
        RandomAccessFile rt = new RandomAccessFile(target,"rw");
        FileChannel targetChanel = rt.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try{
            //第一种方式，通过byteBuffer
//            while (sourceChanel.read(byteBuffer) > 0){
//                byteBuffer.flip();
//                targetChanel.write(byteBuffer);
//                byteBuffer.clear();
//            }
            //第二种方式
            Files.copy(source.toPath(),target.toPath());
            //第三种方式
            sourceChanel.transferTo(0,sourceChanel.size(),targetChanel);
        }finally {
            if(sourceChanel != null){
                sourceChanel.close();
            }
            if(targetChanel != null){
                targetChanel.close();
            }
            if(rs != null){
                rs.close();
            }
            if(rt != null){
                rt.close();
            }
        }
    }
}
