package MazeGame;

/**
 * Represents a cell in the maze.
 */
public class CellDescription {
    private int width;
    private int length;
    private boolean containsMonster;
    private int nbPerson;
    private boolean visited; // New field to track if the cell has been visited

    // Constructor
    public CellDescription(int width, int length, boolean containsMonster, int nbPerson) {
        this.width = width;
        this.length = length;
        this.containsMonster = containsMonster;
        this.nbPerson = nbPerson;
        this.visited = false; // Initialize as not visited
    }

    // Getters and Setters
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isContainsMonster() {
        return containsMonster;
    }

    public void setContainsMonster(boolean containsMonster) {
        this.containsMonster = containsMonster;
    }

    public int getNbPerson() {
        return nbPerson;
    }

    public void setNbPerson(int nbPerson) {
        this.nbPerson = nbPerson;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // toString() method
    @Override
    public String toString() {
        return "CellDescription{" +
                "width=" + width +
                ", length=" + length +
                ", containsMonster=" + containsMonster +
                ", nbPerson=" + nbPerson +
                ", visited=" + visited +
                '}';
    }
}