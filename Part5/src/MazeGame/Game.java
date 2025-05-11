package MazeGame;

import graph.GraphException;
import graph.Vertex;
import graph.IncidenceArrayGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game.
 */
public class Game {
    public static void main(String[] args) {
        try {
            // Create a maze with 10 cells, 50% density, and 20% dangerousness
            Maze maze = new Maze(10, 0.5, 0.2);

            // Print the maze
            System.out.println("Generated Maze:");
            System.out.println(maze);

            // Find a path from departure to arrival
            List<Vertex> path = new ArrayList<>();
            boolean pathFound = maze.findPath(maze.getDeparture(), maze.getArrival(), path, maze.getGraph());
            System.out.println("\nPathfinding Result:");
            if (pathFound) {
                System.out.println("Path found: " + path);
            } else {
                System.out.println("No path found between the departure and arrival cells.");
            }

            // Find the optimal path with the smallest number of monsters
            List<Vertex> optimalPath = new ArrayList<>();
            int[] minMonsters = {Integer.MAX_VALUE};
            maze.findOptimalPath(maze.getDeparture(), maze.getArrival(), new ArrayList<>(), maze.getGraph(), 0, minMonsters, optimalPath);
            System.out.println("\nOptimal Pathfinding Result:");
            if (!optimalPath.isEmpty()) {
                System.out.println("Optimal path found: " + optimalPath);
                System.out.println("Number of monsters encountered: " + minMonsters[0]);
            } else {
                System.out.println("No optimal path found.");
            }

        } catch (GraphException e) {
            System.err.println("An error occurred while creating the maze: " + e.getMessage());
        }
    }
}