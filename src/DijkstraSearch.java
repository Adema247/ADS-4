import java.util.*;

public class DijkstraSearch implements Search {
    private final Map<Vertex, Integer> distTo = new HashMap<>();
    private final Map<Vertex, Vertex> edgeTo = new HashMap<>();
    private final Vertex source;

    public DijkstraSearch(WeightedGraph graph, Vertex source) {
        this.source = source;

        for (Vertex v : graph.getVertices()) {
            distTo.put(v, Integer.MAX_VALUE);
        }
        distTo.put(source, 0);

        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(distTo::get));
        pq.add(source);

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            for (Map.Entry<Vertex, Integer> neighborEntry : graph.getNeighbors(current).entrySet()) {
                Vertex neighbor = neighborEntry.getKey();
                int weight = neighborEntry.getValue();
                int newDist = distTo.get(current) + weight;

                if (newDist < distTo.get(neighbor)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    pq.remove(neighbor);
                    pq.add(neighbor);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(Vertex v) {
        return distTo.get(v) >= Integer.MAX_VALUE;
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