package graph;

/**
 * Represents an edge in the graph.
 */
public abstract class Edge {

    /** Static counter to assign unique IDs to edges. */
    static private int nbEdges = 0;

    /** Unique ID of the edge. */
    private int id;

    /** Color of the edge. */
    private Color color;

    /** The two vertices connected by the edge. */
    private Vertex[] ends;

    /**
     * Constructs an edge between two vertices.
     *
     * @param v1 the first vertex
     * @param v2 the second vertex
     */
    public Edge(Vertex v1, Vertex v2) {
        this.id = nbEdges++;
        this.color = Color.RED; // Default color
        this.ends = new Vertex[] {v1, v2};
    }

    /**
     * Gets the unique ID of the edge.
     *
     * @return the unique ID of the edge
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the color of the edge.
     *
     * @return the color of the edge
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the edge.
     *
     * @param color the new color of the edge
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the vertices connected by the edge.
     *
     * @return an array of two vertices connected by the edge
     */
    public Vertex[] getEnds() {
        return ends;
    }

    /**
     * Sets the vertices connected by the edge.
     *
     * @param ends an array of two vertices to connect by the edge
     * @throws IllegalArgumentException if the array does not contain exactly two vertices
     */
    public void setEnds(Vertex[] ends) {
        if (ends.length != 2) {
            throw new IllegalArgumentException("An edge must connect exactly two vertices.");
        }
        this.ends = ends;
    }

    /**
     * Returns a string representation of the edge.
     *
     * @return a string representation of the edge
     */
    @Override
    public String toString() {
        return ends[0].toString() 
              + " -- " 
              + id + ","
              + color + " -- " 
              + ends[1].toString();
    }
}