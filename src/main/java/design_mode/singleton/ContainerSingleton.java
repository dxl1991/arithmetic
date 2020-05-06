package design_mode.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/24 14:51
 * 容器式单例,spring IOC使用这种
 */
public class ContainerSingleton {
    private ContainerSingleton(){}
    private static Map<String,Object> ioc = new ConcurrentHashMap<>();

    public static Object getInstance(String className){
        if(!ioc.containsKey(className)){
            try{
                synchronized (ioc){
                    if(ioc.containsKey(className)){
                        return ioc.get(className);
                    }
                    Object obj = Class.forName(className).newInstance();
                    ioc.put(className,obj);
                    return obj;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return ioc.get(className);
        }
    }
}
