package kdtree;

/**
 * Exception to handle invalid initial input into the KDTree
 *
 * <p>
 *     Thrown when client attempts to initialize tree with dimensions less than 0.
 * </p>
 * @author Don Robert Pornaras
 * @version 1.0
 * @since 2018-12-07
 */
public class InvalidInitializationException extends Exception {
    private static void sop(Object x) { System.out.println(x); }

    public InvalidInitializationException() { super(); }
    public InvalidInitializationException(String errMsg) { super(errMsg); }

    public void printMsg() { sop(getMessage()); }
}
