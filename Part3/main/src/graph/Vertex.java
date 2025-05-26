package graph;

import java.io.Serializable;

/**
 * Represents a vertex in the graph.
 */
public class Vertex implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Static counter to assign unique IDs to vertices. */
    static private int nbVertices = 0;

    /** Unique ID of the vertex. */
    private int id;

    /** Information associated with the vertex. */
    private Object info;

    /** Color of the vertex. */
    private Color color;

    /**
     * Constructs a Vertex with the given information and color.
     *
     * @param info  the information associated with the vertex
     * @param color the color of the vertex
     */
    public Vertex(Object info, Color color) {
        if (info == null) {
            throw new IllegalArgumentException("Vertex info cannot be null.");
        }
        if (color == null) {
            throw new IllegalArgumentException("Vertex color cannot be null.");
        }
        this.id = nbVertices++;
        this.info = info;
        this.color = color;
    }

    /**
     * Gets the ID of the vertex.
     *
     * @return the ID of the vertex
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the information associated with the vertex.
     *
     * @return the information of the vertex
     */
    public Object getInfo() {
        return info;
    }

    /**
     * Sets the information associated with the vertex.
     *
     * @param info the new information for the vertex
     */
    public void setInfo(Object info) {
        this.info = info;
    }

    /**
     * Gets the color of the vertex.
     *
     * @return the color of the vertex
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the vertex.
     *
     * @param color the new color for the vertex
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns a string representation of the vertex.
     *
     * @return a string representation of the vertex
     */
    @Override
    public String toString() {
        return "(" + id + " : " + info.toString() + ',' + color + ')';
    }
}