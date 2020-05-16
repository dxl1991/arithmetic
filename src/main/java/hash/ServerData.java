package hash;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author dengxinlong
 * @Date 2020/5/13 21:38
 * @slogan CODE IS TRUTH
 */
public class ServerData {
    public final String server;
    private Set<String> datas;

    protected ServerData(String server) {
        this.server = server;
        this.datas = new HashSet<>();
    }

    public void addData(String data) {
        datas.add(data);
    }

    public boolean getData(String data) {
        return datas.contains(data);
    }

    public Collection<String> getDatas() {
        return datas;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ServerData ? this.server.equals(((ServerData) obj).server) : false;
    }

    @Override
    public String toString() {
        return "server = " + server + ",dataSize = " + datas.size();
    }
}
