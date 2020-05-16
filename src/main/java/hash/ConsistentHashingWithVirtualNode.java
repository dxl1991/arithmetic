package hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author dengxinlong
 * @Date 2020/5/13 21:09
 * @slogan CODE IS TRUTH
 * 带虚拟节点的一致性Hash算法
 */
public class ConsistentHashingWithVirtualNode {
    private static Logger logger = LoggerFactory.getLogger(ConsistentHashingWithVirtualNode.class);
    //待添加入Hash环的服务器列表
    private static String[] servers =
            {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111",
                    "192.168.0.4:111"};

    private static Map<String, ServerData> realNodes = new HashMap<>();

    //虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
    private static SortedMap<Integer, VirtualServer> virtualNodes = new TreeMap<>();

    //虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
    private static final int VIRTUAL_NODES = 5;

    static {
        //先把原始的服务器添加到真实结点列表中
        for (int i = 0; i < servers.length; i++) {
            realNodes.put(servers[i], new ServerData(servers[i]));
        }

        for (ServerData serverData : realNodes.values()) {
            addVirtualNode(serverData);
        }
        System.out.println();
    }

    private static void addVirtualNode(ServerData realServer) {
        for (int i = 0; i < VIRTUAL_NODES; i++) {
            String virtualNodeName = realServer.server + "&&VN" + String.valueOf(i);
            int hash = getHash(virtualNodeName);
            //            System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
            virtualNodes.put(hash, new VirtualServer(virtualNodeName, realServer));
        }
    }

    public static boolean addServer(String server) {
        if (realNodes.containsKey(server)) {
            return false;
        }
        ServerData serverData = new ServerData(server);
        realNodes.put(server, serverData);
        Set<ServerData> tempSet = new HashSet<>();
        for (int i = 0; i < VIRTUAL_NODES; i++) {
            String virtualNodeName = server + "&&VN" + String.valueOf(i);
            ServerData tempServerData = getServer(virtualNodeName);
            tempSet.add(tempServerData);
        }
        addVirtualNode(serverData);
        tempSet.forEach(s -> s.getDatas().forEach(data -> {
            ServerData newServerData = getServer(data);
            if (newServerData != s) {
                newServerData.addData(data);
            }
        }));
        return true;
    }

    public static void removeServer(String server) {
        ServerData serverData = realNodes.remove(server);
        if (serverData == null) {
            return;
        }
        for (int i = 0; i < VIRTUAL_NODES; i++) {
            String virtualNodeName = server + "&&VN" + String.valueOf(i);
            int hash = getHash(virtualNodeName);
            virtualNodes.remove(hash);
        }
        serverData.getDatas().forEach(data -> putData(data));
    }

    //使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    //得到应当路由到的结点
    private static ServerData getServer(String key) {
        //得到该key的hash值
        int hash = getHash(key);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, VirtualServer> subMap = virtualNodes.tailMap(hash);
        VirtualServer virtualNode;
        if (subMap.isEmpty()) {
            //如果没有比该key的hash值大的，则从第一个node开始
            Integer i = virtualNodes.firstKey();
            //返回对应的服务器
            virtualNode = virtualNodes.get(i);
        } else {
            //第一个Key就是顺时针过去离node最近的那个结点
            Integer i = subMap.firstKey();
            //返回对应的服务器
            virtualNode = subMap.get(i);
        }
        if (virtualNode != null) {
            return virtualNode.serverData;
        }
        logger.error("没找到serverData，value=" + key + ",hash=" + hash);
        return null;
    }

    public static void putData(String data) {
        int hash = getHash(data);
        ServerData serverData = getServer(data);
        if (serverData == null) {
            return;
        }
        serverData.addData(data);
        //        System.out.println("[" + data + "]的hash值为" + hash + ", 被路由到结点[" + serverData + "]");
    }

    public static boolean getData(String data) {
        ServerData serverData = getServer(data);
        if (serverData == null) {
            return false;
        }
        boolean get = serverData.getData(data);
        if (get) {
            //            System.out.println(data + "在" + serverData);
        } else {
            System.out.println(data + "不在" + serverData);
        }
        return get;
    }

    public static void showServerInfo() {
        realNodes.values().forEach(serverData -> System.out.println(serverData));
    }
}
