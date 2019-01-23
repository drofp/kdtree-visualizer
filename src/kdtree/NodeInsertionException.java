package kdtree;

/**
 * Exception to handle invalid node input into the KDTree
 * <p>
 *      Thrown when dimensions of node do not match dimensions
 *      of KDTree, or there is not at least one node being
 *      inserted in the insert operation.
 * </p>
 * @author Don Robert Pornaras
 * @version 1.0
 * @since 2018-12-07
 */
public class NodeInsertionException extends Exception {
    private static void sop(Object x) { System.out.println(x); }

    public NodeInsertionException() { super(); }
    public NodeInsertionException(String errMsg) { super(errMsg); }

    public void printMsg() { sop(getMessage()); }
}
