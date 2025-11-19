package remote_load;

public class RemoteJarLoader {

    public static void main(String[] args) {
        ClassLoaderManager.init();
        ProxyPlugin proxyPlugin = new ProxyPlugin();
        proxyPlugin.execute();
        System.out.println(proxyPlugin.getName(10086));
    }
}