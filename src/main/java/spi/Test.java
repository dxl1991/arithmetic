package spi;


import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * JAVA 的 spi机制
 * 1 定义一个接口
 * 2 第三方jar去实现接口
 * 3 第三方jar在classpath:META-INF.services文件夹下定义以接口全路径名为文件名的文件
 * 4 文件里指定实现接口的类的全路径
 * 5 使用类方式如下 Service.providers(SPIService.class) 或者 ServiceLoader.load(SPIService.class)
 *   实现原理：其实就是去解析ClassPath:META-INF.services.接口名文件里面的内容，拿到实现类的全路径，放射创建实例
 */
public class Test {
    public static void main(String[] args) {
//        Iterator<SPIService> providers = Service.providers(SPIService.class);
//        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
//
//        while(providers.hasNext()) {
//            SPIService ser = providers.next();
//            ser.execute();
//        }
//        System.out.println("--------------------------------");
//
//        Iterator<SPIService> iterator = load.iterator();
//        while(iterator.hasNext()) {
//            SPIService ser = iterator.next();
//            ser.execute();
//        }
    }
}
