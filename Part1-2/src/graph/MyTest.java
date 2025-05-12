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
     * Test method for exceptions in Part1-2
     */
    public static void testBasicExceptions() {
        System.out.println("\n=== Tests des exceptions (Part1-2) ===");
        
        // Test 1: IllegalArgumentException dans Vertex avec info null
        try {
            Vertex badVertex = new Vertex(null, Color.RED);
            System.out.println("❌ Test IllegalArgumentException (Vertex info null) raté");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Test IllegalArgumentException (Vertex info null) réussi: " + e.getMessage());
        }
        
        // Test 2: IllegalArgumentException dans Vertex avec color null
        try {
            Vertex badVertex2 = new Vertex("Test", null);
            System.out.println("❌ Test IllegalArgumentException (Vertex color null) raté");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Test IllegalArgumentException (Vertex color null) réussi: " + e.getMessage());
        }
        
        // Test 3: IllegalArgumentException dans Edge avec vertices null
        try {
            UndirectedEdge badEdge = new UndirectedEdge(null, new Vertex("A", Color.RED));
            System.out.println("❌ Test IllegalArgumentException (Edge vertex null) raté");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Test IllegalArgumentException (Edge vertex null) réussi: " + e.getMessage());
        }
        
        // Test 4: IllegalArgumentException dans Edge.setEnds avec mauvais nombre d'éléments
        try {
            Vertex v1 = new Vertex("A", Color.RED);
            Vertex v2 = new Vertex("B", Color.BLUE);
            UndirectedEdge edge = new UndirectedEdge(v1, v2);
            edge.setEnds(new Vertex[]{v1}); // Seulement 1 vertex au lieu de 2
            System.out.println("❌ Test IllegalArgumentException (setEnds mauvaise taille) raté");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Test IllegalArgumentException (setEnds mauvaise taille) réussi: " + e.getMessage());
        }
        
    }

    /**
     * The main method to test the graph implementation.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Code existant
        Vertex vertex1 = new Vertex("Vertex 1", Color.RED);
        Vertex vertex2 = new Vertex("Vertex 2", Color.BLUE);

        // Create an edge between the two vertices
        UndirectedEdge edge = new UndirectedEdge(vertex1, vertex2);

        // Print the vertices and the edge
        System.out.println(vertex1);
        System.out.println(vertex2);
        System.out.println(edge);
        
        // Ajouter l'appel aux tests d'exceptions
        testBasicExceptions();
    }
}