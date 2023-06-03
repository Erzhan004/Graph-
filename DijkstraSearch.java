import java.util.*;

public class DijkstraSearch<T> extends Search<T> {
    private final Set<T> unsettledNodes;
    private final Map<T, Double> distances;
    private final WeightedGraph<T> graph;

    public DijkstraSearch(WeightedGraph<T> graph, T source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0.0);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            T node = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(node);
            unsettledNodes.remove(node);
            for (Vertex<T> target : graph.getAdjacentVertices(node)) {
                double distance = getShortestDistance(node) + getDistance(node, target.getData());
                if (distance < getShortestDistance(target.getData())) {
                    distances.put(target.getData(), distance);
                    edgeTo.put(target.getData(), node);
                    unsettledNodes.add(target.getData());
                }
            }
        }
    }

    private double getDistance(T node, T target) {
        Vertex<T> nodeVertex = graph.getVertex(node);
        Vertex<T> targetVertex = graph.getVertex(target);
        if (nodeVertex != null && targetVertex != null) {
            return nodeVertex.getWeight(targetVertex);
        }
        return Double.MAX_VALUE;
    }

    private T getVertexWithMinimumWeight(Set<T> vertices) {
        T minimum = null;
        for (T vertex : vertices) {
            if (minimum == null || getShortestDistance(vertex) < getShortestDistance(minimum)) {
                minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(T destination) {
        Double d = distances.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

}