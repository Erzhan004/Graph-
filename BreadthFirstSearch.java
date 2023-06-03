import java.util.*;

public class BreadthFirstSearch<T> extends Search<T> {

    public BreadthFirstSearch(WeightedGraph<T> graph, T source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<T> graph, T source) {
        marked.add(source);
        Queue<T> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            T currentVertex = queue.remove();
            for (Vertex<T> adjacentVertex : graph.getAdjacentVertices(currentVertex)) {
                if (!marked.contains(adjacentVertex.getData())) {
                    marked.add(adjacentVertex.getData());
                    edgeTo.put(adjacentVertex.getData(), currentVertex);
                    queue.add(adjacentVertex.getData());
                }
            }
        }
    }
}