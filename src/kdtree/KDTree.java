package kdtree;

import java.util.ArrayList;

/**
 * <h1>KD-Tree Implementation</h1>
 *
 * <p>
 *      Initial implementation only designed for two dimensions.
 *      Command line usage supported for higher dimensions, but
 *      drawing only supported for 2 dimensions.
 * </p>
 * <b>NOTE:</b> Duplicate nodes are discarded insertion.
 *
 * @author Don Robert Pornaras
 * @version 1.0
 * @since 2018-12-07
 */
public class KDTree {
    private KDNode root;
    private final int K; // number of dimensions
    private int size;
    private ArrayList<KDNode> insertionOrder;

    /**
     * Ctor only for defining dimensions of KDTree
     * @param K dimensions of KDTree
     * @throws InvalidInitializationException thrown when dimensions are less than 1
     */
    public KDTree(int K) throws InvalidInitializationException {
        if (K < 1)
            throw new InvalidInitializationException("Cannot initialize tree with dimension K = " + K + "."
                    + " Please initialize KD-Tree with dimension of at least 1");

        root = null;
        this.K = K;
        size = 0;

        insertionOrder = new ArrayList<>();
    }

    /**
     * Ctor for single root node
     * <b>NOTE:</b> root node decides dimension of the KDTree
     * @param root root initializer node
     * @throws InvalidInitializationException thrown when dimensions are less than 1
     */
    public KDTree(KDNode root) throws InvalidInitializationException {
        if (root.getCoordinates().size() < 1)
            throw new InvalidInitializationException("Cannot initialize tree with root dimensions = "
                    + root.getCoordinates().size() + "."
                    + " Please initialize with node of at least 1 dimension.");

        this.root = root;
        K = root.getCoordinates().size();
        size = 1;

        insertionOrder = new ArrayList<>();
        insertionOrder.add(root);
    }

    /**
     * Ctor for list of nodes to be inserted into tree in order
     * <b>NOTE:</b> first node decides dimension of the KDTree
     * @param nodes list of nodes to input into the tree
     * @throws InvalidInitializationException thrown when dimensions are less than 1
     * @throws NodeInsertionException when not inserting at least one node.
     */
    public KDTree(ArrayList<KDNode> nodes) throws InvalidInitializationException, NodeInsertionException {
        if (nodes.size() == 0)
            throw new NodeInsertionException("Failed attempt to insert " + nodes.size() + " nodes."
                    + " Must insert at least one node.");

        insertionOrder = new ArrayList<>();

        try {
            insert(nodes);
        } catch (NodeInsertionException x) {
            x.printStackTrace();
        }

        K = nodes.get(0).getCoordinates().size();
    }

    /**
     * Insert a list of nodes into the KDTree
     * @param nodes the list of nodes to insert
     * @return true if all nodes were inserted properly
     * @throws InvalidInitializationException thrown when dimensions are less than 1
     * @throws NodeInsertionException if attempted insertion node does not match dimensions of KDTree
     */
    public boolean insert(ArrayList<KDNode> nodes) throws InvalidInitializationException, NodeInsertionException {
        for (KDNode n : nodes) {
            boolean insertOk = insert(n);
            if (!insertOk)
                return false;
        }

        return true;
    }

    /**
     * Insert a single node into the KDTree
     * @param inNode node to insert
     * @return true on success
     * @throws InvalidInitializationException thrown when dimensions are less than 1
     * @throws NodeInsertionException thrown when node does not match dimensions of KDTree
     */
    public boolean insert(KDNode inNode) throws InvalidInitializationException, NodeInsertionException {
        if (inNode.getCoordinates().size() != K)
            throw new NodeInsertionException("Cannot insert node of " + inNode.getCoordinates().size() + " dimensions"
                    + " since it does not match the dimensions of the KD-tree!"
                    + "\nPlease insert node of exactly " + K + " dimensions.");

        if (root == null) {
            KDTree newTree = new KDTree(inNode);
            root = newTree.root;
            root.setDepth(0);
            size = 1;

            insertionOrder.add(inNode);
            return true;
        }

        return insert(root, inNode, 0) != null;
    }

    /**
     * Helper method to insert single node into the KDTree
     * @param current node currently examined
     * @param inNode node to be inserted
     * @param depth current depth
     * @return root of new tree with inNode inserted on success, root of same tree as input on failure
     */
    private KDNode insert(KDNode current, KDNode inNode, int depth) {
        if (current == null) {
            inNode.setDepth(depth);
            size++;
            insertionOrder.add(inNode);
            return inNode;
        }

        int currentDimension = depth % K;

        if (inNode.getCoordinates().get(currentDimension) < current.getCoordinates().get(currentDimension)) {
            current.setLeftChild(insert(current.getLeftChild(), inNode, depth + 1));
            current.getLeftChild().setParent(current);
        } else  if (inNode.getCoordinates().get(currentDimension) > current.getCoordinates().get(currentDimension)) {
            current.setRightChild(insert(current.getRightChild(), inNode, depth + 1));
            current.getRightChild().setParent(current);
        } else {
            return null;
        }

        return current;
    }

    /**
     * Find node with matching coordaintes
     * @param searchNodeData coordinates to match in node to find
     * @return true if node is found with coordinates matching input coordinates
     */
    public boolean find(ArrayList<Integer> searchNodeData) throws InvalidSearchException {
        if (searchNodeData.size() != K) {
            throw new InvalidSearchException("Search node's dimensions of " + searchNodeData.size()
                    + " do not match dimensions of the tree!"
                    + " Dimension of tree is K = " + K);
        }

        return find(root, searchNodeData, 0);
    }

    /**
     * Helper method to find a node in the KDTree
     * @param current node currently examined
     * @param searchNodeData coordinates to search for and match in the tree
     * @param depth current depth
     * @return true on sucess
     */
    private boolean find(KDNode current, ArrayList<Integer> searchNodeData, int depth) {
        if (current == null)
            return false;
        if (current.getCoordinates().equals(searchNodeData))
            return true;

        int currentDimension = depth % K;

        if (searchNodeData.get(currentDimension) < current.getCoordinates().get(currentDimension))
            return find(current.getLeftChild(), searchNodeData, depth +1);

        return find(current.getRightChild(), searchNodeData, depth +1);
    }

    /**
     * Find the minimum value in the KDTree
     * @param dimension chosen dimension to examine
     * @return the minimum value of chosen dimension
     * @throws InvalidSearchException if queried search dimension is less than 0 or greater than K
     */
    public int findMin(int dimension) throws InvalidSearchException {
        if (size == 0)
            throw new InvalidSearchException("Cannot perform search on KD-tree of size 0. Please insert at least one node.");
        if (dimension < 0 || dimension >= K )
            throw new InvalidSearchException("Queried search dimension of " + dimension
                                        + " must be at least 0 and less than the KD-tree's dimension of " + K);

        return findMin(root, dimension, 0);
    }

    /**
     * Helper method to find minimum value in the KDTree
     * @param current node currently examined
     * @param dimension chosen dimension to examine
     * @param depth current depth
     * @return the minimum value of chosen dimension
     */
    private int findMin(KDNode current, int dimension, int depth) {
        if (current == null)
            return Integer.MAX_VALUE;

        int currentDimension = depth % K;

        if (currentDimension == dimension) {
            if (current.getLeftChild() == null)
                return current.getCoordinates().get(dimension);
            return Math.min(current.getCoordinates().get(dimension),
                    findMin(current.getLeftChild(), dimension, depth +1));
        }

        return Math.min(current.getCoordinates().get(dimension),
                Math.min(findMin(current.getLeftChild(), dimension, depth +1),
                findMin(current.getRightChild(), dimension, depth +1)));
    }

    /**
     * Find the maximum value in the KDTree
     * @param dimension chosen dimension to examine
     * @return the maximum value of chosen dimension
     * @throws InvalidSearchException if queried search dimension is less than 0 or greater than K
     */
    public int findMax(int dimension) throws InvalidSearchException {
        if (size == 0)
            throw new InvalidSearchException("Cannot perform search on KD-tree of size 0. Please insert at least one node.");
        if (dimension < 0 || dimension > K )
            throw new InvalidSearchException("Queried search dimension of " + dimension
                    + " must be at least 0 and less than the KD-tree's dimension of " + K);

        return findMax(root, dimension, 0);
    }

    /**
     * Helper method to find maximum value in the KDTree
     * @param current node currently examined
     * @param dimension chosen dimension to examine
     * @param depth current depth
     * @return the maximum value of chosen dimension
     */
    private int findMax(KDNode current, int dimension, int depth) {
        if (current == null)
            return Integer.MIN_VALUE;

        int currentDimension = depth % K;

        if (currentDimension == dimension) {
            if (current.getRightChild() == null)
                return current.getCoordinates().get(dimension);
            return Math.max(current.getCoordinates().get(dimension),
                    findMax(current.getRightChild(), dimension, depth + 1));
        }

        return Math.max(current.getCoordinates().get(dimension),
                Math.max(findMax(current.getLeftChild(), dimension, depth + 1),
                        findMax(current.getRightChild(), dimension, depth + 1)));
    }

    /**
     * Find the height of the KDTree
     * @return the height of the KDTree
     */
    public int height() {
        return height(root);
    }

    /**
     * Helper method to find the height of the KDTree
     * @param current node currently examined
     * @return the height of the KDTree
     */
    private int height(KDNode current) {
        if (current == null)
            return 0;

        return Math.max(height(current.getLeftChild()), height(current.getRightChild()));
    }

    /**
     * Find the pre-order traversal of the KDTree
     * @return the pre-order traversal of the KDTree
     */
    public String preOrder() {
        StringBuilder out = new StringBuilder();
        preOrder(root, out);
        return out.toString();
    }

    /**
     * Helper method to find the pre-order traversal of the KDTree
     * @param current node currently examined
     * @param out placeholder for string representation of pre-order traversal
     * @return null on completion
     */
    private StringBuilder preOrder(KDNode current, StringBuilder out) {
        if (current == null)
            return out;

        out.append(current.getCoordinates().toString()).append(" ");
        preOrder(current.getLeftChild(), out);
        preOrder(current.getRightChild(), out);

        return null;
    }


    /**
     * Find the in-order traversal of the KDTree
     * @return the in-order traversal of the KDTree
     */
    public String inOrder() {
        StringBuilder out = new StringBuilder();
        inOrder(root, out);
        return out.toString();
    }

    /**
     * Helper method to find the in-order traversal of the KDTree
     * @param current node currently examined
     * @param out placeholder for string representation of in-order traversal
     * @return null on completion
     */
    private StringBuilder inOrder(KDNode current, StringBuilder out) {
        if (current == null)
            return out;

        inOrder(current.getLeftChild(), out);
        out.append(current.getCoordinates().toString()).append(" ");
        inOrder(current.getRightChild(), out);

        return null;
    }

    /**
     * Find the post-order traversal of the KDTree
     * @return the post-order traversal of the KDTree
     */
    public String postOrder() {
        StringBuilder out = new StringBuilder();
        postOrder(root, out);
        return out.toString();
    }

    /**
     * Helper method to find the post-order traversal of the KDTree
     * @param current node currently examined
     * @param out placeholder for string representation of post-order traversal
     * @return null on completion
     */
    private StringBuilder postOrder(KDNode current, StringBuilder out) {
        if (current == null)
            return out;

        postOrder(current.getLeftChild(), out);
        postOrder(current.getRightChild(), out);
        out.append(current.getCoordinates().toString()).append(" ");

        return null;
    }

    public int getK() { return K; }

    public int getSize() {
        return size;
    }

    public KDNode getRoot() {
        return root;
    }

    public ArrayList<KDNode> getInsertionOrder() {
        return insertionOrder;
    }

    @Override
    public String toString() {
        return "In order traversal: " + inOrder();
    }

    private static void sop(Object x) { System.out.println(x); }

}
