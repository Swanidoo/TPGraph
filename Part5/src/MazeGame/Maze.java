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
    private IncidenceArrayGraph graph; // Added this field to store the graph

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
        graph = new IncidenceArrayGraph(size); // Updated to assign the graph to the field

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

    // Recursive pathfinding algorithm
    public boolean findPath(CellDescription current, CellDescription target, List<Vertex> path, IncidenceArrayGraph graph) {
        // Mark the current cell as visited
        current.setVisited(true);
        path.add(cellToVertexMap.get(current));

        // Base case: if the target cell is reached
        if (current.equals(target)) {
            return true;
        }

        // Explore neighbors
        for (Edge edge : graph.getNeighborEdges(cellToVertexMap.get(current))) {
            Vertex neighborVertex = edge.getEnds()[0].equals(cellToVertexMap.get(current)) ? edge.getEnds()[1] : edge.getEnds()[0];
            CellDescription neighbor = getCellByVertex(neighborVertex);

            if (!neighbor.isVisited()) {
                if (findPath(neighbor, target, path, graph)) {
                    return true; // Path found
                }
            }
        }

        // Backtrack: unmark the cell and remove it from the path
        current.setVisited(false);
        path.remove(path.size() - 1);
        return false;
    }

    // Recursive optimal pathfinding algorithm
    public boolean findOptimalPath(CellDescription current, CellDescription target, List<Vertex> path, IncidenceArrayGraph graph, int monsters, int[] minMonsters, List<Vertex> bestPath) {
        // Mark the current cell as visited
        current.setVisited(true);
        path.add(cellToVertexMap.get(current));
        monsters += current.isContainsMonster() ? 1 : 0;

        // Base case: if the target cell is reached
        if (current.equals(target)) {
            if (monsters < minMonsters[0]) {
                minMonsters[0] = monsters;
                bestPath.clear();
                bestPath.addAll(path);
            }
            current.setVisited(false);
            path.remove(path.size() - 1);
            return true;
        }

        // Explore neighbors
        for (Edge edge : graph.getNeighborEdges(cellToVertexMap.get(current))) {
            Vertex neighborVertex = edge.getEnds()[0].equals(cellToVertexMap.get(current)) ? edge.getEnds()[1] : edge.getEnds()[0];
            CellDescription neighbor = getCellByVertex(neighborVertex);

            if (!neighbor.isVisited()) {
                findOptimalPath(neighbor, target, path, graph, monsters, minMonsters, bestPath);
            }
        }

        // Backtrack: unmark the cell and remove it from the path
        current.setVisited(false);
        path.remove(path.size() - 1);
        return false;
    }

    // Helper method to get a cell by its vertex
    private CellDescription getCellByVertex(Vertex vertex) {
        for (Map.Entry<CellDescription, Vertex> entry : cellToVertexMap.entrySet()) {
            if (entry.getValue().equals(vertex)) {
                return entry.getKey();
            }
        }
        return null;
    }

    // Getter for departure
    public CellDescription getDeparture() {
        return this.departure;
    }

    // Getter for arrival
    public CellDescription getArrival() {
        return this.arrival;
    }

    // Getter for graph
    public IncidenceArrayGraph getGraph() {
        return this.graph;
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