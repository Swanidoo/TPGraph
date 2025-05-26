package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Represents a graph using an incidence array.
 */
public class IncidenceArrayGraph implements UndirectedGraph, Serializable {
    private static final long serialVersionUID = 1L;

    /** Array of vertices in the graph. */
    private Vertex[] vertices;

    /** Incidence array of edges in the graph. */
    private Edge[][] edges;

    /** Maximum number of vertices allowed in the graph. */
    private int maxVertices;

    /**
     * Constructs an incidence array graph with a maximum number of vertices.
     *
     * @param maxNbOfVertices the maximum number of vertices
     */
    public IncidenceArrayGraph(int maxNbOfVertices) {
        this.maxVertices = maxNbOfVertices;
        this.vertices = new Vertex[maxVertices];
        this.edges = new Edge[maxVertices][maxVertices];
    }

    public int nbOfVertices() {
        int nbV = 0;
        for (Vertex v : this.vertices) {
            if (v != null) nbV++;
        }
        return nbV;
    }

    public Vertex[] getVertices() {
        Vertex[] vList = new Vertex[this.maxVertices];
        int i = 0;
        for (Vertex v : this.vertices) {
            if (v != null) {
                vList[i] = v;
                i++;
            }
        }
        return vList;
    }

    public void addVertex(Vertex v) throws GraphOverflowException {
        if (v == null) {
            throw new IllegalArgumentException("Cannot add a null vertex to the graph.");
        }
        if ((v.getId() < 0) || (v.getId() >= this.maxVertices)) {
            throw new GraphOverflowException("Graph is full");
        }
        this.vertices[v.getId()] = v;
    }

    private int firstEmptyPlace(Edge[] edges) {
        int i = 0;
        while ((i < edges.length) && (edges[i] != null)) {
            i++;
        }
        return (i == edges.length) ? -1 : i;
    }

    public void addEdge(Edge e) throws GraphException, GraphOverflowException {
        // Parameter validation
        if (e == null) {
            throw new IllegalArgumentException("Cannot add a null edge to the graph.");
        }
        
        // Validate the edge endpoints
        Vertex[] ends = e.getEnds();
        if (ends[0] == null || ends[1] == null) {
            throw new GraphException("Edge endpoints cannot be null.");
        }
        
        int idFirstVertex = ends[0].getId();
        int idSecondVertex = ends[1].getId();
        
        // Verify that the vertices exist in the graph
        if (this.vertices[idFirstVertex] == null || this.vertices[idSecondVertex] == null) {
            throw new GraphException("One or both vertices are not in the graph.");
        }
        
        int p1 = this.firstEmptyPlace(this.edges[idFirstVertex]);
        if (p1 == -1) throw new GraphOverflowException("Graph is full");
        this.edges[idFirstVertex][p1] = e;
        int p2 = this.firstEmptyPlace(this.edges[idSecondVertex]);
        if (p2 == -1) throw new GraphOverflowException("Graph is full");
        this.edges[idSecondVertex][p2] = e;
    }

    private boolean alreadyConsidered(Edge e, Edge[] list) {
        for (Edge ep : list) {
            if (ep == e) return true;
        }
        return false;
    }

    public Edge[] getEdges() {
        Edge[] allEdges = new Edge[this.maxVertices * this.maxVertices];
        int ie = 0;
        for (int iv = 0; iv < this.vertices.length; iv++) {
            for (Edge e : this.edges[iv]) {
                if (!alreadyConsidered(e, allEdges)) {
                    allEdges[ie] = e;
                    ie++;
                }
            }
        }
        return allEdges;
    }

    public Edge[] getNeighborEdges(Vertex v) {
        List<Edge> neighbors = new ArrayList<>();
        for (Edge[] edgeList : edges) {
            for (Edge edge : edgeList) {
                if (edge != null && (edge.getEnds()[0].equals(v) || edge.getEnds()[1].equals(v))) {
                    neighbors.add(edge);
                }
            }
        }
        // Convert the list to an array and return
        return neighbors.toArray(new Edge[0]);
    }

    public Edge getEdge(Vertex v1, Vertex v2) throws GraphException, GraphStructureException {
        // Verify that the vertices exist in the graph
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vertices cannot be null.");
        }
        
        int i1 = v1.getId();
        for (Edge e : this.edges[i1]) {
            if (e != null) {
                Vertex[] extremities = e.getEnds();
                if ((extremities[0] == v2) || (extremities[1] == v2)) {
                    return e;
                }
            }
        }
        throw new GraphStructureException("No edge found between vertices " + v1.getId() + " and " + v2.getId());
    }

    public boolean isConnected(Vertex v1, Vertex v2) throws GraphException {
        // Use a breadth-first search (BFS) to check connectivity
        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(v1);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current.equals(v2)) {
                return true; // Found a path to v2
            }
            visited.add(current);

            // Add neighbors to the queue
            for (Edge edge : getNeighborEdges(current)) {
                if (edge == null) {
                    continue; // Skip null edges
                }
                Vertex neighbor = edge.getEnds()[0].equals(current) ? edge.getEnds()[1] : edge.getEnds()[0];
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }

        return false; // No path found
    }

    /**
     * Serializes the graph to a file.
     *
     * @param filename the name of the file to write to
     * @throws IOException if an I/O error occurs
     */
    public void serialize(String filename) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        }
    }

    /**
     * Deserializes a graph from a file.
     *
     * @param filename the name of the file to read from
     * @return the deserialized IncidenceArrayGraph
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class cannot be found during deserialization
     */
    public static IncidenceArrayGraph deserialize(String filename) 
            throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (IncidenceArrayGraph) ois.readObject();
        }
    }
}