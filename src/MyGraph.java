import java.util.*;


public class MyGraph<V> {
    private final boolean undirected;
    private Map<V, Vertex<V>> map = new HashMap<>();

    public MyGraph() {
        this.undirected = true;
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V v) {
        map.put(v, new Vertex<>(v));
    }

    public void addEdge(V source, V dest) {
        if (source.equals(dest) || hasEdge(source, dest)) {
            return;
        }
        addVertex(source);
        addVertex(dest);
        map.get(source).addAdjacentVertices(map.get(dest), 0);
        if (undirected) {
            map.get(dest).addAdjacentVertices(map.get(source), 0);
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
        return map.containsKey(source) && map.get(source).containsDest(map.get(dest));
    }

    public Iterable<V> adjacencyList(V v) {
        if (!hasVertex(v)) return null;
        return map.get(v).getAdjacent();
    }
}