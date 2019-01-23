package kdtree;

/**
 * Exception to handle invalid initial search query for the KDTree
 *
 * <p>
 *     Thrown when client attempts to search tree for node with dimensions less than 1
 *     or when client attempts to search using queried dimension of less than 1
 * </p>
 * @author Don Robert Pornaras
 * @version 1.0
 * @since 2018-12-07
 */
public class InvalidSearchException extends Exception {
    private static void sop(Object x) { System.out.println(x); }

    public InvalidSearchException() { super(); }
    public InvalidSearchException(String errMsg) { super(errMsg); }

    public void printMsg() { sop(getMessage()); }
}
