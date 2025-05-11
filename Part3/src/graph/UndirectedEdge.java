package graph;

/**
 * Represents an undirected edge in the graph.
 */
public class UndirectedEdge extends Edge {

    /**
     * Constructs an undirected edge between two vertices.
     *
     * @param v1 the first vertex
     * @param v2 the second vertex
     */
    public UndirectedEdge(Vertex v1, Vertex v2) {
        super(v1, v2);
    }

    /**
     * Returns a string representation of the undirected edge.
     *
     * @return a string representation of the edge
     */
    @Override
    public String toString() {
        return super.toString();
    }
}