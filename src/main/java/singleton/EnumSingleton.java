package singleton;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/24 14:38
 * 枚举不能被放射创建,最安全最优雅的创建单例。但是也存在过早初始化的问题
 */
public enum EnumSingleton {
    INSTANCE;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance(){return INSTANCE;}
}
