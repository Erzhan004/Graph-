import java.util.*;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<T, Vertex<T>> vertices = new HashMap<>();

    public WeightedGraph() {
        this.undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public Vertex<T> addVertex(T data) {
        Vertex<T> vertex = new Vertex<>(data);
        this.vertices.put(data, vertex);
        return vertex;
    }

    public void addEdge(T source, T dest, double weight) {
        if (hasEdge(source, dest) || source.equals(dest))
            return;

        Vertex<T> sourceVertex = vertices.getOrDefault(source, addVertex(source));
        Vertex<T> destVertex = vertices.getOrDefault(dest, addVertex(dest));
        sourceVertex.addAdjacentVertex(destVertex, weight);

        if (undirected) {
            destVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }

    public int getVerticesCount() {
        return this.vertices.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<T> vertex : vertices.values()) {
            count += vertex.countEdges();
        }

        if (undirected) {
            count /= 2;
        }

        return count;
    }

    public boolean hasVertex(T data) {
        return this.vertices.containsKey(data);
    }

    public boolean hasEdge(T source, T dest) {
        if (!hasVertex(source)) return false;
        return vertices.get(source).containsEdge(new Vertex<>(dest));
    }

    public Iterable<T> adjacencyList(T v) {
        List<T> list = new ArrayList<>();
        if (!hasVertex(v)) return list;
        for (Vertex<T> vertex : vertices.get(v).getAdjacentVertices()) {
            list.add(vertex.getData());
        }
        return list;
    }

    public Iterable<Vertex<T>> getAdjacentVertices(T v) {
        if (!hasVertex(v)) return null;
        return vertices.get(v).getAdjacentVertices();
    }

    public Vertex<T> getVertex(T data) {
        return vertices.get(data);
    }
}
