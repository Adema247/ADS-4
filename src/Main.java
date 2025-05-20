public class Main {
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        graph.addEdge(a, b, 1);
        graph.addEdge(a, c, 4);
        graph.addEdge(b, c, 2);
        graph.addEdge(c, d, 1);

        Search bfs = new BreadthFirstSearch(graph, a);
        System.out.println("BFS path from A to D: " + bfs.pathTo(d));

        Search dijkstra = new DijkstraSearch(graph, a);
        System.out.println("Dijkstra path from A to D: " + dijkstra.pathTo(d));
    }
}
//

