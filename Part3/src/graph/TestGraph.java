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

    // Ajoutez cette méthode pour tester les exceptions
    public static void testExceptions() {
        System.out.println("\n=== Tests des exceptions ===");
        
        try {
            // Test de IllegalArgumentException
            Vertex badVertex = new Vertex(null, Color.RED); // Devrait lancer IllegalArgumentException
            System.out.println("Test IllegalArgumentException raté");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Test IllegalArgumentException réussi: " + e.getMessage());
        }
        
        try {
            // Test de GraphOverflowException
            UndirectedGraph g = new IncidenceArrayGraph(2); // Capacité limitée à 2 sommets
            Vertex v1 = new Vertex("A", Color.BLUE);
            Vertex v2 = new Vertex("B", Color.RED);
            Vertex v3 = new Vertex("C", Color.BLUE); // Dépasse la capacité
            g.addVertex(v1);
            g.addVertex(v2);
            g.addVertex(v3); // Devrait lancer GraphOverflowException
            System.out.println("Test GraphOverflowException raté");
        } catch (GraphOverflowException e) {
            System.out.println("✓ Test GraphOverflowException réussi: " + e.getMessage());
        }
        
        try {
            // Test de GraphStructureException
            UndirectedGraph g = new IncidenceArrayGraph(5);
            Vertex v1 = new Vertex("A", Color.BLUE);
            Vertex v2 = new Vertex("B", Color.RED);
            g.addVertex(v1);
            g.addVertex(v2);
            // Les sommets existent mais ne sont pas connectés
            Edge e = g.getEdge(v1, v2); // Devrait lancer GraphStructureException
            System.out.println("Test GraphStructureException raté");
        } catch (GraphStructureException e) {
            System.out.println("✓ Test GraphStructureException réussi: " + e.getMessage());
        } catch (GraphException e) {
            System.out.println("✓ Autre GraphException: " + e.getMessage());
        }
    }

    /**
     * The main method to test the graph implementation.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Code existant
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
        
        // Ajouter l'appel aux tests d'exceptions
        testExceptions();
    }
}