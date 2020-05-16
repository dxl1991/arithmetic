package hash;

/**
 * @Author dengxinlong
 * @Date 2020/5/14 10:56
 * @slogan CODE IS TRUTH
 */
public class VirtualServer {
    public final String virtualName;
    public final ServerData serverData;

    VirtualServer(String virtualName, ServerData serverData) {
        this.virtualName = virtualName;
        this.serverData = serverData;
    }
}
