import java.util.*;

public class DepthFirstSearch<T> extends Search<T> {
    private Set<T> visited;

    public DepthFirstSearch(WeightedGraph<T> graph, T source) {
        super(source);
        visited = new HashSet<>();
        dfs(graph, source);
    }

    public void dfs(WeightedGraph<T> graph, T current) {
        marked.add(current);
        visited.add(current);

        for (Vertex<T> v : graph.getAdjacentVertices(current)) {
            if (!visited.contains(v)) {
                edgeTo.put((T) v, current);
                dfs(graph, (T) v);
            }
        }
    }
}