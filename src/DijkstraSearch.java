import java.util.*;

public class DijkstraSearch<Vertex> extends Search<Vertex> {
    private Set<Vertex> unsettledNodes = new HashSet<>();
    private Map<Vertex, Double> distances = new HashMap<>();
    private WeightedGraph<Vertex> graph;

    public DijkstraSearch(WeightedGraph<Vertex> graph, Vertex source) {
        super(source);
        this.graph = graph;
        distances.put(source, 0D);
        unsettledNodes.add(source);
        dijkstra();
    }


    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Vertex node = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(node);
            unsettledNodes.remove(node);
            for (Vertex target : graph.adjacencyList(node)) {
                double newDistance = distances.getOrDefault(node, Double.MAX_VALUE) + graph.getVertex(node).getWeight(graph.getVertex(target));
                if (newDistance < distances.getOrDefault(target, Double.MAX_VALUE)) {
                    distances.put(target, newDistance);
                    edgeTo.put(target, node);
                    unsettledNodes.add(target);
                }
            }
        }
    }


    private double getDistance(Vertex node, Vertex target) {
        return graph.getVertex(node).getWeight(graph.getVertex(target));
    }

    private Vertex getVertexWithMinimumWeight(Set<Vertex> vertices) {
        return Collections.min(vertices, Comparator.comparing(this::getShortestDistance));
    }

    private double getShortestDistance(Vertex destination) {
        return distances.getOrDefault(destination, Double.MAX_VALUE);
    }
}
