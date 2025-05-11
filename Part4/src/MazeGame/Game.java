package MazeGame;

import graph.GraphException; // Import the GraphException class

/**
 * Represents the game.
 */
public class Game {
    public static void main(String[] args) {
        try {
            // Create a maze with 10 cells, 50% density, and 20% dangerousness
            Maze maze = new Maze(10, 0.5, 0.2);

            // Print the maze
            System.out.println(maze);
        } catch (GraphException e) {
            System.err.println("An error occurred while creating the maze: " + e.getMessage());
        }
    }
}