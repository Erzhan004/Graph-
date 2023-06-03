import java.util.*;

public class Search<T> {
    protected Set<T> marked;
    protected Map<T, T> edgeTo;
    protected final T source;

    public Search(T source) {
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(T v) {
        return marked.contains(v);
    }

    public Iterable<T> pathTo(T v) {
        LinkedList<T> path = new LinkedList<>();
        if (!hasPathTo(v)) {
            return path;
        }
        T current = v;
        while (!current.equals(source)) {
            path.addFirst(current);
            current = edgeTo.get(current);
        }
        path.addFirst(source);
        return path;
    }

    protected void dfs(WeightedGraph<T> graph, T current) {
        marked.add(current);

        for (Vertex<T> v : graph.getAdjacentVertices(current)) {
            if (!marked.contains(v)) {
                edgeTo.put((T) v, current);
                dfs(graph, (T) v);
            }
        }
    }
}
