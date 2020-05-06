package design_mode.singleton;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/24 14:23
 */
public class LazyStaticInnerClassSingleton {

    private LazyStaticInnerClassSingleton(){
        //防止反射创建单例
        if(LazyHoder.singleton != null){
            throw new RuntimeException("不能非法创建单例对象");
        }
    }

    public static LazyStaticInnerClassSingleton getInstance(){
        return LazyHoder.singleton;
    }

    //内部类只有在使用的时候才会被加载，避免过早初始化单例的情况
    private final static class LazyHoder{
        private static final LazyStaticInnerClassSingleton singleton = new LazyStaticInnerClassSingleton();
    }
}
