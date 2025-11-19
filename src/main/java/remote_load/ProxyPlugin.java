package remote_load;

/**
 * @author dengxinlong
 * @date 2025/10/11 16:57
 * @description TODO
 */
public class ProxyPlugin implements IPlugin{
    private final String pluginClassName = "remote_load.SimplePlugin";

    @Override
    public String getName(int id) {
        Class<?> simplePluginClass = ClassLoaderManager.classMap.get(pluginClassName);
        Object instance = ClassLoaderManager.objectMap.get(pluginClassName);
        try {
            return (String) simplePluginClass.getMethod("getName",int.class).invoke(instance,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void execute() {
        Class<?> simplePluginClass = ClassLoaderManager.classMap.get(pluginClassName);
        Object instance = ClassLoaderManager.objectMap.get(pluginClassName);
        try {
            simplePluginClass.getMethod("execute").invoke(instance);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
