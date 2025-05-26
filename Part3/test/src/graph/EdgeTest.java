package graph;

import static org.junit.Assert.*;
import org.junit.Test;

public class EdgeTest {
    
    private static final String INFO1 = "Vertex1";
    private static final String INFO2 = "Vertex2";
    
    @Test
    public void testUndirectedEdgeCreation() {
        // Given
        Vertex v1 = new Vertex(INFO1, Color.RED);
        Vertex v2 = new Vertex(INFO2, Color.BLUE);
        
        // When
        UndirectedEdge edge = new UndirectedEdge(v1, v2);
        
        // Then
        Vertex[] ends = edge.getEnds();
        assertEquals(v1, ends[0]);
        assertEquals(v2, ends[1]);
        assertEquals(Color.RED, edge.getColor()); // Default color should be RED
    }
    
    @Test
    public void testDirectedEdgeCreation() {
        // Given
        Vertex v1 = new Vertex(INFO1, Color.RED);
        Vertex v2 = new Vertex(INFO2, Color.BLUE);
        int source = 0;
        
        // When
        DirectedEdge edge = new DirectedEdge(v1, v2, source);
        
        // Then
        assertEquals(v1, edge.getSource());
        assertEquals(v2, edge.getSink());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEdgeCreationWithNullVertex1() {
        // Given
        Vertex v1 = null;
        Vertex v2 = new Vertex(INFO2, Color.BLUE);
        
        // When
        new UndirectedEdge(v1, v2); // Should throw IllegalArgumentException
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEdgeCreationWithNullVertex2() {
        // Given
        Vertex v1 = new Vertex(INFO1, Color.RED);
        Vertex v2 = null;
        
        // When
        new UndirectedEdge(v1, v2); // Should throw IllegalArgumentException
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDirectedEdgeWithInvalidSource() {
        // Given
        Vertex v1 = new Vertex(INFO1, Color.RED);
        Vertex v2 = new Vertex(INFO2, Color.BLUE);
        int invalidSource = 2; // Source must be 0 or 1
        
        // When
        new DirectedEdge(v1, v2, invalidSource); // Should throw IllegalArgumentException
    }
    
    @Test
    public void testSetColor() {
        // Given
        Vertex v1 = new Vertex(INFO1, Color.RED);
        Vertex v2 = new Vertex(INFO2, Color.BLUE);
        Edge edge = new UndirectedEdge(v1, v2);
        
        // When
        edge.setColor(Color.BLUE);
        
        // Then
        assertEquals(Color.BLUE, edge.getColor());
    }
    
    @Test
    public void testToString() {
        // Given
        Vertex v1 = new Vertex(INFO1, Color.RED);
        Vertex v2 = new Vertex(INFO2, Color.BLUE);
        Edge edge = new UndirectedEdge(v1, v2);
        
        // When
        String result = edge.toString();
        
        // Then
        assertTrue(result.contains(INFO1));
        assertTrue(result.contains(INFO2));
    }
}