import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private Map<V, Vertex<V>> map = new HashMap<>();

    public WeightedGraph() {
        this.undirected = true;
    }
    public Vertex<V> getVertex(V v){
        return map.get(v);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V v) {
        map.put(v, new Vertex<>(v));
    }

    public void addEdge(V source, V dest, double weight) {
        if (source.equals(dest) || hasEdge(source, dest)) {
            return;
        }
        addVertex(source);
        addVertex(dest);
        map.get(source).addAdjacentVertices(map.get(dest), weight);
        if (undirected) {
            map.get(dest).addAdjacentVertices(map.get(source), weight);
        }
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = map.values().stream().mapToInt(Vertex::countOfAdjacent).sum();
        return undirected ? count / 2 : count;
    }


    public boolean hasVertex(V v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(V source, V dest) {
        return hasVertex(source) && map.get(source).containsDest(map.get(dest));
    }

    public Iterable<V> adjacencyList(V v) {
        return hasVertex(v) ? map.get(v).getAdjacent() : null;
    }
}