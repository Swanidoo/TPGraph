package graph;

/**
 * A test class for the graph implementation.
 */
public class MyTest {

    /**
     * Default constructor for the MyTest class.
     */
    public MyTest() {
        // Default constructor
    }

    /**
     * The main method to test the graph implementation.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Create two vertices
        Vertex vertex1 = new Vertex("Vertex 1", Color.RED);
        Vertex vertex2 = new Vertex("Vertex 2", Color.BLUE);

        // Create an edge between the two vertices
        UndirectedEdge edge = new UndirectedEdge(vertex1, vertex2);

        // Print the vertices and the edge
        System.out.println(vertex1);
        System.out.println(vertex2);
        System.out.println(edge);
    }
}