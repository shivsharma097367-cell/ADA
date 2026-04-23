import java.util.*;

// Edge class for Bellman-Ford
class Edge {
    int u, v, w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

// Pair class for Dijkstra (node, distance)
class Pair {
    int node, dist;

    Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}

public class ShortestPathAlgorithms {

    // 🔹 1. Dijkstra Algorithm (Greedy)
    public static Map<Character, Integer> dijkstra(Map<Character, List<Pair>> graph, char start) {
        Map<Character, Integer> dist = new HashMap<>();

        for (char node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(start, 0);

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));
        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int d = current.dist;
            char node = (char) current.node;

            for (Pair neighbor : graph.get(node)) {
                if (dist.get((char)neighbor.node) > d + neighbor.dist) {
                    dist.put((char)neighbor.node, d + neighbor.dist);
                    pq.add(new Pair(neighbor.node, dist.get((char)neighbor.node)));
                }
            }
        }
        return dist;
    }

    // 🔹 2. Bellman-Ford Algorithm (DP)
    public static int[] bellmanFord(List<Edge> edges, int V, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            for (Edge e : edges) {
                if (dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.w;
                }
            }
        }

        // Check negative cycle
        for (Edge e : edges) {
            if (dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.w < dist[e.v]) {
                System.out.println("Negative cycle detected");
                return null;
            }
        }

        return dist;
    }

    // 🔹 Main Function
    public static void main(String[] args) {

        // ✅ Dijkstra Example
        Map<Character, List<Pair>> graph = new HashMap<>();

        graph.put('A', Arrays.asList(new Pair('B', 1), new Pair('C', 4)));
        graph.put('B', Arrays.asList(new Pair('C', 2), new Pair('D', 5)));
        graph.put('C', Arrays.asList(new Pair('D', 1)));
        graph.put('D', new ArrayList<>());

        Map<Character, Integer> result = dijkstra(graph, 'A');

        System.out.println("Dijkstra Result:");
        for (char node : result.keySet()) {
            System.out.println(node + " -> " + result.get(node));
        }

        // ✅ Bellman-Ford Example
        int V = 4;
        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(2, 3, 1));

        int[] bfResult = bellmanFord(edges, V, 0);

        System.out.println("\nBellman-Ford Result:");
        if (bfResult != null) {
            for (int i = 0; i < bfResult.length; i++) {
                System.out.println("Node " + i + " -> " + bfResult[i]);
            }
        }
    }
}
