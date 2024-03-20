/**
 * @author dengxinlong
 * @date 2024/3/6 16:10
 */
import java.util.*;

/**
 * 迪杰斯特拉算法(Dijkstra)
 * 在图中寻找任意两点的最短路径（按照路径权重）
 */
class Graph {
    //图的数据结构，key:节点id，value：相邻的边
    private final Map<Integer, List<Edge>> adjList = new HashMap<>();

    /**
     * 添加边
     * @param u 起点
     * @param v 终点
     * @param w 权重
     */
    public void addEdge(int u, int v, int w) {
        adjList.computeIfAbsent(u,k -> new ArrayList<>()).add(new Edge(v, w));
    }

    /**
     * Dijkstra算法
     * @param source 起点
     * @param target 终点
     * @return 路径（点的集合）
     */
    public List<Integer> dijkstra(int source, int target) {
        PriorityQueue<Vertex> minHeap = new PriorityQueue<>();
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> prev = new HashMap<>();

        // 初始化距离和前驱节点
        for (int node : adjList.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
            prev.put(node, -1);
        }
        dist.put(source, 0);
        minHeap.add(new Vertex(source, 0));

        while (!minHeap.isEmpty()) {
            Vertex u = minHeap.poll();
            int uDist = dist.get(u.getId());

            // 遍历所有邻接节点
            for (Edge edge : adjList.getOrDefault(u.getId(), Collections.emptyList())) {
                int v = edge.to;
                int weight = edge.weight;

                // 松弛操作
                if (uDist + weight < dist.get(v)) {
                    dist.put(v, uDist + weight);
                    prev.put(v, u.id);
                    minHeap.add(new Vertex(v, dist.get(v)));
                }
            }
        }

        // 构建最短路径
        List<Integer> shortestPath = new ArrayList<>();
        int current = target;
        while (prev.get(current) != -1) {
            shortestPath.add(0, current);
            current = prev.get(current);
        }
        shortestPath.add(0, source); // 添加源点

        return shortestPath;
    }

    // 边的表示
    private static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // 辅助类，用于优先队列中的节点表示
    private static class Vertex implements Comparable<Vertex> {
        int id;
        int dist;

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        public int getId() {
            return id;
        }

        public int getDist() {
            return dist;
        }

        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.dist, other.dist);
        }
    }
}

public class DijkstraExample {
    public static void main(String[] args) {
        Graph graph = new Graph();
        // 添加边，例如：graph.addEdge(1, 2, 7); // 从节点1到节点2的权重为7
        // ... 添加更多边 ...
        graph.addEdge(1,2,2);
        graph.addEdge(1,3,3);
        graph.addEdge(1,4,6);
        graph.addEdge(3,4,2);
        graph.addEdge(4,5,1);
        graph.addEdge(4,6,3);
        graph.addEdge(2,5,4);
        graph.addEdge(2,6,6);

        graph.addEdge(2,1,2);
        graph.addEdge(3,1,3);
        graph.addEdge(4,1,6);
        graph.addEdge(4,3,2);
        graph.addEdge(5,4,1);
        graph.addEdge(6,4,3);
        graph.addEdge(5,2,4);
        graph.addEdge(6,2,6);
        int source = 6; // 源点
        int target = 5; // 目标点
        List<Integer> shortestPath = graph.dijkstra(source, target);

        System.out.println("Shortest path from " + source + " to " + target + " is: " + shortestPath);
    }
}
