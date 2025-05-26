package graph;

/**
 * Exception thrown when the graph exceeds its maximum capacity.
 */
public class GraphOverflowException extends RuntimeException {

    /**
     * Constructs a GraphOverflowException with a message.
     *
     * @param message the exception message
     */
    public GraphOverflowException(String message) {
        super(message);
    }
}