package remote_load;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dengxinlong
 * @date 2025/10/11 16:58
 * @description TODO
 */
public class ClassLoaderManager {
    public static Map<String,Class<?>> classMap = new ConcurrentHashMap<>();
    public static Map<String,Object> objectMap = new ConcurrentHashMap<>();

    private static final String REMOTE_JAR_URL = "http://localhost/jars/plugin.jar";
    public static void init(){
        try {
            // 创建URL对象指向远程JAR
            URL[] urls = {new URL(REMOTE_JAR_URL)};

            // 创建URLClassLoader实例，传入远程JAR的URL
            try (URLClassLoader classLoader = new URLClassLoader(urls)) {
                String className = "remote_load.SimplePlugin";
                // 加载JAR包中的特定类（需知道全限定类名）
                Class<?> clazz = classLoader.loadClass(className);

                classMap.put(className,clazz);
                objectMap.put(className,clazz.getDeclaredConstructor().newInstance());
                System.out.println("远程JAR加载和方法调用成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
