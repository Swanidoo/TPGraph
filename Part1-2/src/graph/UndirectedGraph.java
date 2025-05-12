package graph;

/**
 * Represents an undirected graph.
 */
public interface UndirectedGraph {

    /**
     * Gets the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    public int nbOfVertices();

    /**
     * Adds a vertex to the graph.
     *
     * @param v the vertex to add
     */
    public void addVertex(Vertex v);

    /**
     * Adds an edge to the graph.
     *
     * @param e the edge to add
     * @throws GraphException if the edge cannot be added
     * @throws GraphOverflowException if the graph is full
     */
    public void addEdge(Edge e) throws GraphException, GraphOverflowException;

    /**
     * Gets the edge connecting two vertices.
     *
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @return the edge connecting the vertices
     * @throws GraphException if the edge does not exist
     */
    public Edge getEdge(Vertex v1, Vertex v2) throws GraphException, GraphStructureException;
    /**
     * Gets all vertices in the graph.
     *
     * @return an array of vertices
     */
    public Vertex[] getVertices();

    /**
     * Gets all edges in the graph.
     *
     * @return an array of edges
     */
    public Edge[] getEdges();

    /**
     * Gets all edges connected to a vertex.
     *
     * @param v1 the vertex
     * @return an array of edges connected to the vertex
     * @throws GraphException if the vertex is invalid
     */
    public Edge[] getNeighborEdges(Vertex v1) throws GraphException;
}

