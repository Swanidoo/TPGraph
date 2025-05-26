package graph;

/**
 * Represents a graph with basic operations.
 */
public interface Graph {

    /**
     * Gets the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    int nbOfVertices();

    /**
     * Gets the number of edges in the graph.
     *
     * @return the number of edges
     */
    int nbOfEdges();

    /**
     * Adds a vertex to the graph.
     *
     * @param v the vertex to add
     */
    void addVertex(Vertex v);

    /**
     * Adds an edge to the graph.
     *
     * @param v1 the first vertex
     * @param v2 the second vertex
     */
    void addEdge(Vertex v1, Vertex v2);

    /**
     * Checks if there is a path between two vertices.
     *
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @return true if there is a path, false otherwise
     */
    boolean isConnected(Vertex v1, Vertex v2);

    /**
     * Checks if all vertices in the graph are interconnected.
     *
     * @return true if all vertices are interconnected, false otherwise
     */
    boolean isConnected();

    /**
     * Gets the edges directly connecting two vertices.
     *
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @return an array of edges connecting the vertices
     */
    Edge[] getEdges(Vertex v1, Vertex v2);

    /**
     * Gets all edges in the graph.
     *
     * @return an array of all edges
     */
    Edge[] getEdges();

    /**
     * Gets all vertices in the graph.
     *
     * @return an array of all vertices
     */
    Vertex[] getVertices();

    /**
     * Gets all edges connected to a vertex.
     *
     * @param v the vertex
     * @return an array of edges connected to the vertex
     */
    Edge[] getNeighborEdges(Vertex v);
}