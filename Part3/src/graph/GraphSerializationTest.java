package graph;

import java.io.IOException;

/**
 * Test class for demonstrating graph serialization and deserialization.
 */
public class GraphSerializationTest {

    public static void main(String[] args) {
        try {
            // Create and populate a graph
            System.out.println("=== Creating Original Graph ===");
            IncidenceArrayGraph originalGraph = new IncidenceArrayGraph(5);
            
            // Create vertices
            Vertex v1 = new Vertex("Paris", Color.RED);
            Vertex v2 = new Vertex("London", Color.BLUE);
            Vertex v3 = new Vertex("Berlin", Color.RED);
            Vertex v4 = new Vertex("Madrid", Color.BLUE);
            
            // Add vertices to graph
            originalGraph.addVertex(v1);
            originalGraph.addVertex(v2);
            originalGraph.addVertex(v3);
            originalGraph.addVertex(v4);
            
            // Create and add edges
            UndirectedEdge e1 = new UndirectedEdge(v1, v2);
            UndirectedEdge e2 = new UndirectedEdge(v2, v3);
            UndirectedEdge e3 = new UndirectedEdge(v3, v4);
            UndirectedEdge e4 = new UndirectedEdge(v1, v4);
            
            originalGraph.addEdge(e1);
            originalGraph.addEdge(e2);
            originalGraph.addEdge(e3);
            originalGraph.addEdge(e4);
            
            // Display original graph info
            System.out.println("Original graph has " + originalGraph.nbOfVertices() + " vertices");
            System.out.println("Vertices:");
            for (Vertex v : originalGraph.getVertices()) {
                if (v != null) {
                    System.out.println("  " + v);
                }
            }
            System.out.println("Edges:");
            for (Edge e : originalGraph.getEdges()) {
                if (e != null) {
                    System.out.println("  " + e);
                }
            }
            
            // Test connectivity
            System.out.println("Is " + v1.getInfo() + " connected to " + v3.getInfo() + "? " 
                             + originalGraph.isConnected(v1, v3));
            
            // Serialize the graph
            String filename = "graph_data.ser";
            System.out.println("\n=== Serializing Graph ===");
            originalGraph.serialize(filename);
            System.out.println("Graph serialized to " + filename);
            
            // Deserialize the graph
            System.out.println("\n=== Deserializing Graph ===");
            IncidenceArrayGraph deserializedGraph = IncidenceArrayGraph.deserialize(filename);
            System.out.println("Graph deserialized from " + filename);
            
            // Verify deserialized graph
            System.out.println("\n=== Verifying Deserialized Graph ===");
            System.out.println("Deserialized graph has " + deserializedGraph.nbOfVertices() + " vertices");
            
            System.out.println("Vertices in deserialized graph:");
            for (Vertex v : deserializedGraph.getVertices()) {
                if (v != null) {
                    System.out.println("  " + v);
                }
            }
            
            System.out.println("Edges in deserialized graph:");
            for (Edge e : deserializedGraph.getEdges()) {
                if (e != null) {
                    System.out.println("  " + e);
                }
            }
            
            // Test connectivity on deserialized graph
            // Note: We need to get the vertices from the deserialized graph
            Vertex[] deserializedVertices = deserializedGraph.getVertices();
            Vertex dv1 = null, dv3 = null;
            for (Vertex v : deserializedVertices) {
                if (v != null) {
                    if (v.getInfo().equals("Paris")) dv1 = v;
                    if (v.getInfo().equals("Berlin")) dv3 = v;
                }
            }
            
            if (dv1 != null && dv3 != null) {
                System.out.println("Is " + dv1.getInfo() + " connected to " + dv3.getInfo() + " in deserialized graph? " 
                                 + deserializedGraph.isConnected(dv1, dv3));
            }
            
            System.out.println("\n=== Serialization Test Completed Successfully ===");
            
        } catch (GraphOverflowException e) {
            System.err.println("Graph overflow error: " + e.getMessage());
        } catch (GraphException e) {
            System.err.println("Graph error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error during serialization/deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found during deserialization: " + e.getMessage());
        }
    }
}