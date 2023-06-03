import java.util.*;

public class Vertex<V> {
    private V data;
    private final Map<Vertex<V>, Double> adjacentVertices;

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        this.adjacentVertices.put(destination, weight);
    }

    public int countEdges() {
        return this.adjacentVertices.size();
    }

    public boolean containsEdge(Vertex<V> vertex) {
        return adjacentVertices.containsKey(vertex);
    }

    public void deleteAdjacentVertex(Vertex<V> destination) {
        this.adjacentVertices.remove(destination);
    }

    public V getData() {
        return this.data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public Iterable<Vertex<V>> getAdjacentVertices() {
        return adjacentVertices.keySet();
    }

    public double getWeight(Vertex<V> destination) {
        return this.adjacentVertices.get(destination);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<V> vertex = (Vertex<V>) o;
        return Objects.equals(data, vertex.data);
    }


    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}