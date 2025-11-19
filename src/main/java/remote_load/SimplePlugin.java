package remote_load;

/**
 * @author dengxinlong
 * @date 2025/10/11 16:52
 * @description TODO
 */
public class SimplePlugin implements IPlugin{
    // 静态代码块 - 类加载时执行
    static {
        System.out.println("SimplePlugin类已被加载");
    }
    @Override
    public String getName(int id) {
        return "name_is_" + id;
    }

    @Override
    public void execute() {
        System.out.println("🎉 插件执行成功！");
        System.out.println("这是从远程JAR包加载的插件功能");

        // 演示插件可以访问系统资源
        System.out.println("当前用户: " + System.getProperty("user.name"));
        System.out.println("Java版本: " + System.getProperty("java.version"));
    }
}
