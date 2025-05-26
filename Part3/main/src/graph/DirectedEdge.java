package graph;

/**
 * Represents a directed edge in the graph.
 */
public class DirectedEdge extends Edge {

    /** Indicates the source vertex (0 or 1). */
    private int source;

    /**
     * Constructs a directed edge between two vertices.
     *
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @param source the source vertex (0 or 1)
     */
    public DirectedEdge(Vertex v1, Vertex v2, int source) {
        super(v1, v2);
        if (source != 0 && source != 1) {
            throw new IllegalArgumentException("Source must be 0 or 1.");
        }
        this.source = source;
    }

    /**
     * Gets the source vertex of the directed edge.
     *
     * @return the source vertex
     */
    public Vertex getSource() {
        return getEnds()[source];
    }

    /**
     * Gets the sink (destination) vertex of the directed edge.
     *
     * @return the sink vertex
     */
    public Vertex getSink() {
        return getEnds()[1 - source];
    }

    /**
     * Returns a string representation of the directed edge.
     *
     * @return a string representation of the edge
     */
    @Override
    public String toString() {
        return getSource() + " -> " + getSink();
    }
}