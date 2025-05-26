package graph;

import static org.junit.Assert.*;
import org.junit.Test;

public class VertexTest {
    
    private static final String INFO = "TestVertex";
    
    @Test
    public void testVertexCreation() {
        // Given
        Object info = INFO;
        Color color = Color.RED;
        
        // When
        Vertex vertex = new Vertex(info, color);
        
        // Then
        assertEquals(info, vertex.getInfo());
        assertEquals(color, vertex.getColor());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testVertexCreationWithNullInfo() {
        // Given
        Object info = null;
        Color color = Color.RED;
        
        // When
        new Vertex(info, color); // Should throw IllegalArgumentException
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testVertexCreationWithNullColor() {
        // Given
        Object info = INFO;
        Color color = null;
        
        // When
        new Vertex(info, color); // Should throw IllegalArgumentException
    }
    
    @Test
    public void testSetColor() {
        // Given
        Vertex vertex = new Vertex(INFO, Color.RED);
        
        // When
        vertex.setColor(Color.BLUE);
        
        // Then
        assertEquals(Color.BLUE, vertex.getColor());
    }
    
    @Test
    public void testSetInfo() {
        // Given
        Vertex vertex = new Vertex(INFO, Color.RED);
        String newInfo = "NewInfo";
        
        // When
        vertex.setInfo(newInfo);
        
        // Then
        assertEquals(newInfo, vertex.getInfo());
    }
    
    @Test
    public void testToString() {
        // Given
        Vertex vertex = new Vertex(INFO, Color.RED);
        
        // When
        String result = vertex.toString();
        
        // Then
        assertTrue(result.contains(INFO));
        assertTrue(result.contains("RED"));
    }
}