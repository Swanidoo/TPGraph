package MazeGame;

import graph.*;
import java.util.*;

/**
 * Represents a maze using a graph.
 */
public class Maze {
    private List<CellDescription> cells;
    private List<Edge> links;
    private CellDescription departure;
    private CellDescription arrival;
    private Map<CellDescription, Vertex> cellToVertexMap;

    // Constructor
    public Maze(int size, double density, double dangerousness) throws GraphException {
        // Parameter validation
        if (size <= 0) {
            throw new IllegalArgumentException("Maze size must be positive.");
        }
        if (density < 0 || density > 1) {
            throw new IllegalArgumentException("Density must be between 0 and 1.");
        }
        if (dangerousness < 0 || dangerousness > 1) {
            throw new IllegalArgumentException("Dangerousness must be between 0 and 1.");
        }
        // Initialize the graph
        IncidenceArrayGraph graph = new IncidenceArrayGraph(size);

        // Initialize cells and map
        cells = new ArrayList<>();
        cellToVertexMap = new HashMap<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            boolean containsMonster = random.nextDouble() < dangerousness;
            CellDescription cell = new CellDescription(random.nextInt(10) + 1, random.nextInt(10) + 1, containsMonster, random.nextInt(5) + 1);
            cells.add(cell);

            // Create a corresponding Vertex and add it to the graph
            Vertex vertex = new Vertex(i, Color.RED);
            cellToVertexMap.put(cell, vertex);
            graph.addVertex(vertex);
        }

        // Initialize links
        links = new ArrayList<>();
        int maxLinks = (int) (size * (size - 1) / 2 * density);
        for (int i = 0; i < maxLinks; i++) {
            int v1 = random.nextInt(size);
            int v2 = random.nextInt(size);
            if (v1 != v2) {
                CellDescription cell1 = cells.get(v1);
                CellDescription cell2 = cells.get(v2);
                if (cellToVertexMap.get(cell1) != null && cellToVertexMap.get(cell2) != null) {
                    Edge edge = new UndirectedEdge(cellToVertexMap.get(cell1), cellToVertexMap.get(cell2));
                    links.add(edge);
                    try {
                        graph.addEdge(edge); // This may throw GraphException
                    } catch (GraphException e) {
                        System.err.println("Error adding edge: " + e.getMessage());
                    }
                }
            }
        }

        // Choose departure and arrival cells
        departure = cells.get(random.nextInt(size));
        arrival = cells.get(random.nextInt(size));

        // Ensure departure and arrival are connected
        if (!graph.isConnected(cellToVertexMap.get(departure), cellToVertexMap.get(arrival))) { // This may throw GraphException
            List<CellDescription> path = new ArrayList<>();
            path.add(departure);
            for (int i = 0; i < size / 2; i++) {
                path.add(cells.get(random.nextInt(size)));
            }
            path.add(arrival);

            for (int i = 0; i < path.size() - 1; i++) {
                Edge edge = new UndirectedEdge(cellToVertexMap.get(path.get(i)), cellToVertexMap.get(path.get(i + 1)));
                links.add(edge);
                graph.addEdge(edge); // This may throw GraphException
            }
        }
    }

    // toString() method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Maze:\n");
        sb.append("Cells:\n");
        for (CellDescription cell : cells) {
            sb.append(cell).append("\n");
        }
        sb.append("Links:\n");
        for (Edge link : links) {
            sb.append(link).append("\n");
        }
        sb.append("Departure: ").append(departure).append("\n");
        sb.append("Arrival: ").append(arrival).append("\n");
        return sb.toString();
    }
}