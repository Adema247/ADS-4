import java.util.*;

public class BreadthFirstSearch implements Search {
    private final Map<Vertex, Vertex> edgeTo = new HashMap<>();
    private final Set<Vertex> marked = new HashSet<>();
    private final Vertex source;

    public BreadthFirstSearch(WeightedGraph graph, Vertex source) {
        this.source = source;
        bfs(graph, source);
    }

    private void bfs(WeightedGraph graph, Vertex start) {
        Queue<Vertex> queue = new LinkedList<>();
        marked.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            for (Vertex neighbor : graph.getNeighbors(current).keySet()) {
                if (!marked.contains(neighbor)) {
                    marked.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(Vertex v) {
        return !marked.contains(v);
    }

    @Override
    public List<Vertex> pathTo(Vertex v) {
        if (hasPathTo(v)) return null;
        List<Vertex> path = new ArrayList<>();
        for (Vertex x = v; x != null && !x.equals(source); x = edgeTo.get(x)) {
            path.add(x);
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }
}
//