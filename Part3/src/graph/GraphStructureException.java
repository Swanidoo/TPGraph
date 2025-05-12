package graph;

/**
 * Exception thrown when an operation cannot be performed due to the graph structure.
 * For example, finding a path between disconnected vertices.
 */
public class GraphStructureException extends Exception {

    /**
     * Constructs a GraphStructureException with a message.
     *
     * @param message the exception message
     */
    public GraphStructureException(String message) {
        super(message);
    }
}