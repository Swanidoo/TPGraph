package graph;

/**
 * Exception thrown for general graph-related errors.
 */
public class GraphException extends Exception {

    /**
     * Constructs a GraphException with a message.
     *
     * @param message the exception message
     */
    public GraphException(String message) {
        super(message);
    }
}