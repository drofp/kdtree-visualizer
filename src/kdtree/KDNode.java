package kdtree;

import java.util.ArrayList;

/**
 * <h1>Node for KDTree to store integers</h1>
 *
 * @author Don Robert Pornaras
 * @version 1.0
 * @since 2018-12-07
 */
public class KDNode {
    private ArrayList<Integer> coordinates;
    private KDNode leftChild;
    private KDNode rightChild;
    private KDNode parent;
    private int depth; // count starting 0 from root

    /**
     * Ctor given list of coordinates for a single node
     * @param coordinates the list of coordinates for this node
     */
    KDNode(ArrayList<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Ctor for single node
     * @param coordinates values of each coordinate of this node
     * @param depth depth of this node
     */
    KDNode(ArrayList<Integer> coordinates, int depth) {
        this.coordinates = coordinates;
        this.depth = depth;
    }

    /**
     * Ctor generated from a given node
     * @param otherNode a given node to generate a new node
     */
    KDNode(KDNode otherNode) {
        this.coordinates = otherNode.coordinates;
        this.leftChild = otherNode.leftChild;
        this.rightChild = otherNode.rightChild;
        this.parent = otherNode.parent;
        this.depth = otherNode.depth;
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(ArrayList<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public KDNode getLeftChild() {
        return leftChild;
    }
    public void setLeftChild(KDNode leftChild) {
        this.leftChild = leftChild;
    }

    public KDNode getRightChild() {
        return rightChild;
    }
    public void setRightChild(KDNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }

    public KDNode getParent() { return parent; }
    public void setParent(KDNode parent) { this.parent = parent; }

    @Override
    public String toString() {
        return "Coordinates are: " + coordinates + " and Depth is: " + Integer.toString(depth);
    }
}
