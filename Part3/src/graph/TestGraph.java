package graph;

/**
 * A test class for the graph implementation.
 */
public class TestGraph {

    /**
     * Default constructor for the TestGraph class.
     */
    public TestGraph() {
        // Default constructor
    }

    /**
     * The main method to test the graph implementation.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Vertex v1 = new Vertex("MPL", Color.BLUE);
        Vertex v2 = new Vertex("ORY", Color.BLUE);
        Vertex v3 = new Vertex("GAT", Color.RED);
        Vertex v4 = new Vertex("TOR", Color.RED);
        UndirectedEdge e1 = new UndirectedEdge(v1, v2);
        UndirectedEdge e2 = new UndirectedEdge(v3, v1);
        UndirectedEdge e3 = new UndirectedEdge(v2, v4);
        UndirectedGraph g = new IncidenceArrayGraph(10);

        try {
            g.addVertex(v1);
            g.addVertex(v2);
            g.addVertex(v3);
            g.addVertex(v4);
            g.addEdge(e1);
            g.addEdge(e2);
            g.addEdge(e3);
        } catch (GraphOverflowException e) {
            System.out.println("Graph is full!");
            System.exit(-1);
        } catch (GraphException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        System.out.println("Graph has " + g.nbOfVertices() + " vertices");
    }
}
