package MazeGame;

/**
 * Represents a cell in the maze.
 */
public class CellDescription {
    private int width;
    private int length;
    private boolean containsMonster;
    private int nbPerson;

    // Constructor
    public CellDescription(int width, int length, boolean containsMonster, int nbPerson) {
        this.width = width;
        this.length = length;
        this.containsMonster = containsMonster;
        this.nbPerson = nbPerson;
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

    // toString() method
    @Override
    public String toString() {
        return "CellDescription{" +
                "width=" + width +
                ", length=" + length +
                ", containsMonster=" + containsMonster +
                ", nbPerson=" + nbPerson +
                '}';
    }
}