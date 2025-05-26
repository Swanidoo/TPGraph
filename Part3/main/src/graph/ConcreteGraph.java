package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * A concrete implementation of the Graph interface.
 */
public class ConcreteGraph implements Graph {

    /** List of vertices in the graph. */
    private List<Vertex> vertices;

    /** List of edges in the graph. */
    private List<Edge> edges;

    /** Indicates whether the graph is directed. */
    private boolean isDirected;

    /**
     * Constructs a new graph.
     *
     * @param isDirected true if the graph is directed, false otherwise
     */
    public ConcreteGraph(boolean isDirected) {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.isDirected = isDirected;
    }

    @Override
    public int nbOfVertices() {
        return vertices.size();
    }

    @Override
    public int nbOfEdges() {
        return edges.size();
    }

    @Override
    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        Edge edge = isDirected ? new DirectedEdge(v1, v2, 0) : new UndirectedEdge(v1, v2);
        edges.add(edge);
    }

    @Override
    public boolean isConnected(Vertex v1, Vertex v2) {
        for (Edge edge : edges) {
            Vertex[] ends = edge.getEnds();
            if ((ends[0] == v1 && ends[1] == v2) || (ends[0] == v2 && ends[1] == v1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isConnected() {
        // Simple implementation: check if all vertices are reachable
        return vertices.size() <= 1 || edges.size() >= vertices.size() - 1;
    }

    @Override
    public Edge[] getEdges(Vertex v1, Vertex v2) {
        List<Edge> result = new ArrayList<>();
        for (Edge edge : edges) {
            Vertex[] ends = edge.getEnds();
            if ((ends[0] == v1 && ends[1] == v2) || (ends[0] == v2 && ends[1] == v1)) {
                result.add(edge);
            }
        }
        return result.toArray(new Edge[0]);
    }

    @Override
    public Edge[] getEdges() {
        return edges.toArray(new Edge[0]);
    }

    @Override
    public Vertex[] getVertices() {
        return vertices.toArray(new Vertex[0]);
    }

    @Override
    public Edge[] getNeighborEdges(Vertex v) {
        List<Edge> result = new ArrayList<>();
        for (Edge edge : edges) {
            Vertex[] ends = edge.getEnds();
            if (ends[0] == v || ends[1] == v) {
                result.add(edge);
            }
        }
        return result.toArray(new Edge[0]);
    }
}