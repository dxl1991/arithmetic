package cycle_rely;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * spring 解决循环依赖的问题，用map把bean存起来，然后用放射对bean的字段赋值（从map里找）
 */
public class Solution {
    private Map<String,Object> map = new HashMap<>();

    private <T> T getBean(Class<T> clazz) throws Exception{
        String beanName = clazz.getSimpleName();
        if(map.containsKey(beanName)){
            return (T) map.get(beanName);
        }
        Object object = clazz.getDeclaredConstructor().newInstance();
        map.put(beanName,object);
        for(Field field : clazz.getDeclaredFields()){
            field.setAccessible(true);
            Class fieldClass = field.getType();
            field.set(object,getBean(fieldClass));
        }
        return (T) object;
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        A a = solution.getBean(A.class);
        B b = solution.getBean(B.class);
        System.out.println(a.getB() == b);
        System.out.println(b.getA() == a);
    }
}
