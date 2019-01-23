package kdtree;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    /////////////////////////////////////
    ///// Poor Initialization Tests /////
    /////////////////////////////////////

    /**
     * Run all tests on initialization of KD-Tree.
     */
    private static void testInitialization() {
        testPoorInitializationInvalidDimensionsZero();
        testPoorInitializationInvalidDimensionsNegative();
        testPoorInitializationWithNodeWithData();
        testPoorInitializationWithEmptyListOfNodes();
    }

    private static void testPoorInitializationInvalidDimensionsZero() {
        String testName = "===== Test Initialization of Tree with Invalid (Zero) Dimensions =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to 0");
            KDTree testTree = new KDTree(0);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

        } catch (InvalidInitializationException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testPoorInitializationInvalidDimensionsNegative() {
        String testName = "===== Test Initialization of Tree with Invalid (Negative) Dimensions =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to -7");
            KDTree testTree = new KDTree(-7);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

        } catch (InvalidInitializationException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testPoorInitializationWithNodeWithData() {
        String testName = "===== Test Initialization of Tree with Node with Invalid (Zero) Dimensions =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to size of input initial root node");
            KDTree testTree = new KDTree(new KDNode(new ArrayList<Integer>()));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

        } catch (InvalidInitializationException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testPoorInitializationWithEmptyListOfNodes() {
        String testName = "===== Test Initialization of Tree with Node with Invalid (Zero) Dimensions =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to size of input initial root node");
            KDTree testTree = new KDTree(new ArrayList<KDNode>());
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        }

        sop("");
    }

    ////////////////////////////////
    ///// Poor Insertion Tests /////
    ////////////////////////////////

    /**
     * Run all tests of insertion into KD-Tree.
     */
    private static void testInsertion() {
        testPoorInsertionWrongDimensions();
    }

    private static void testPoorInsertionWrongDimensions() {
        String testName = "===== Test Invalid Insertion Into of Tree with Empty List of Nodes =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (50, 40, 30) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40, 30))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            // This code should not run
            sop("Inserting (50, 50, 50) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 50, 50))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        }

        sop("");
    }

    /////////////////////////////
    ///// Find Method Tests /////
    /////////////////////////////
    private static void testFindMethod() {
        testFindNodeDataInvalidDataDimensions();
        testFindNodeDataDoesNotExist();
        testFindNodeDataDoesExist();
    }

    private static void testFindNodeDataInvalidDataDimensions() {
        String testName = "===== Test Invalid Search For Node With Dimensions Not Matching Tree Dimensions =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (40, 50) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 50))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (70, 60) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(70, 60))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Searching for (10, 20, 30)");
            testTree.find(new ArrayList<>(Arrays.asList(10, 20, 30)));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testFindNodeDataDoesNotExist() {
        String testName = "===== Test Search For Node that Does Not Exist In Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (40, 50) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 50))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (70, 60) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(70, 60))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Searching for (10, 20)");
            sop("Search query returned: " + testTree.find(new ArrayList<>(Arrays.asList(10, 20))));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testFindNodeDataDoesExist() {
        String testName = "===== Test Search For Node that Does Exist In Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (40, 50) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 50))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (70, 60) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(70, 60))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Searching for (40, 50)");
            sop("Search query returned: " + testTree.find(new ArrayList<>(Arrays.asList(40, 50))));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    ////////////////////////////////
    ///// FindMin Method Tests /////
    ////////////////////////////////

    private static void testFindMinMethod() {
        testFindMinInvalidDataDimensions();
        testFindMinEmptyTreeDimension0();
        testFindMinNonEmptyTreeDimension0();
        testFindMinEmptyTreeDimension1();
        testFindMinNonEmptyTreeDimension1();
    }

    private static void testFindMinInvalidDataDimensions() {
        String testName = "===== Test Invalid Find Minimum For Queried Dimension Not Matching Tree Dimensions =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            int chosenDimension = 3;
            sop("Minimum of dimension " + chosenDimension + " is: " + testTree.findMin(chosenDimension));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testFindMinEmptyTreeDimension0() {
        String testName = "===== Test Invalid Find Minimum For Queried Dimension 0 On Empty Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            int chosenDimension = 0;
            sop("Minimum of dimension " + chosenDimension + " is: " + testTree.findMin(chosenDimension));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testFindMinNonEmptyTreeDimension0() {
        String testName = "===== Test Valid Find Minimum For Queried Dimension 0 On Empty Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (40, 50) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 50))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (70, 60) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(70, 60))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            int chosenDimension = 0;
            sop("Minimum of dimension " + chosenDimension + " is: " + testTree.findMin(chosenDimension));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testFindMinEmptyTreeDimension1() {
        String testName = "===== Test Invalid Find Minimum For Queried Dimension 1 On Empty Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            int chosenDimension = 1;
            sop("Minimum of dimension " + chosenDimension + " is: " + testTree.findMin(chosenDimension));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testFindMinNonEmptyTreeDimension1() {
        String testName = "===== Test Valid Find Minimum For Queried Dimension 1 On Empty Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (40, 50) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 50))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (70, 60) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(70, 60))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            int chosenDimension = 1;
            sop("Minimum of dimension " + chosenDimension + " is: " + testTree.findMin(chosenDimension));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    ////////////////////////////////
    ///// FindMax Method Tests /////
    ////////////////////////////////

    private static void testFindMaxMethod() {
        testFindMaxInvalidDataDimensions();
        testFindMaxEmptyTreeDimension0();
        testFindMaxNonEmptyTreeDimension0();
        testFindMaxEmptyTreeDimension1();
        testFindMaxNonEmptyTreeDimension1();
    }

    private static void testFindMaxInvalidDataDimensions() {
        String testName = "===== Test Invalid Find Maximum For Queried Dimension Not Matching Tree Dimensions =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            int chosenDimension = 3;
            sop("Maximum of dimension " + chosenDimension + " is: " + testTree.findMax(chosenDimension));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testFindMaxEmptyTreeDimension0() {
        String testName = "===== Test Invalid Find Maximum For Queried Dimension 0 On Empty Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            int chosenDimension = 0;
            sop("Maximum of dimension " + chosenDimension + " is: " + testTree.findMax(chosenDimension));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testFindMaxNonEmptyTreeDimension0() {
        String testName = "===== Test Valid Find Maximum For Queried Dimension 0 On Empty Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (40, 50) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 50))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (70, 60) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(70, 60))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            int chosenDimension = 0;
            sop("Maximum of dimension " + chosenDimension + " is: " + testTree.findMax(chosenDimension));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testFindMaxEmptyTreeDimension1() {
        String testName = "===== Test Invalid Find Maximum For Queried Dimension 1 On Empty Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            int chosenDimension = 1;
            sop("Maximum of dimension " + chosenDimension + " is: " + testTree.findMax(chosenDimension));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testFindMaxNonEmptyTreeDimension1() {
        String testName = "===== Test Valid Find Maximum For Queried Dimension 1 On Empty Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with 2 dimensions");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (40, 50) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 50))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            sop("Inserting (70, 60) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(70, 60))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree);

            int chosenDimension = 1;
            sop("Maximum of dimension " + chosenDimension + " is: " + testTree.findMax(chosenDimension));

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        sop("");
    }

    ////////////////////////////////////////
    ///// Test Integration and Drawing /////
    ////////////////////////////////////////
    private static void testIntegrationAndDrawing() {
        testDrawingEmptyTree();
        testDrawingTreeOneNode();
        testDrawingTreeTwoNodes();
        testDrawingTreeThreeNodes();

        testExampleTree();
    }

    /**
     * Test the visualizer on every insertion into a KDTree
     */
    private static void testDrawingEmptyTree() {
        String testName = "===== Test Drawing Empty Tree =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to 2");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree + "\n");

            sop("Drawing tree...");
            KDTreeVisualizer.visualizeTree(testTree, testName);
        } catch (InvalidInitializationException e) {
            e.printMsg();
        }

        sop("");
    }

    /**
     * Test the visualizer on a KDTree with one node.
     */
    private static void testDrawingTreeOneNode() {
        String testName = "===== Test Drawing Tree With 1 Node =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to 2");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree + "\n");

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree + "\n");

            sop("Drawing tree...");
            KDTreeVisualizer.visualizeTree(testTree, testName);
        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        }

        sop("");
    }

    /**
     * Test the visualizer a KDTree with two nodes
     */
    private static void testDrawingTreeTwoNodes() {
        String testName = "===== Test Drawing Tree With 2 Nodes =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to 2");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree + "\n");

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree + "\n");

            sop("Inserting (40, 70) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 70))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree + "\n");

            sop("Drawing tree...");
            KDTreeVisualizer.visualizeTree(testTree, testName);
        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        }

        sop("");
    }

    /**
     * Test the visualizer a KDTree with three nodes
     */
    private static void testDrawingTreeThreeNodes() {
        String testName = "===== Test Drawing Tree With 3 Nodes =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to 2");
            KDTree testTree = new KDTree(2);
            sop("tree size is now: " + testTree.getSize());
            sop(testTree + "\n");

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree + "\n");

            sop("Inserting (40, 70) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 70))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree + "\n");

            sop("Inserting (80, 20) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(80, 20))));
            sop("tree size is now: " + testTree.getSize());
            sop(testTree + "\n");

            sop("Drawing tree...");
            KDTreeVisualizer.visualizeTree(testTree, testName);
        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        }

        sop("");
    }

    /**
     * Test a valid KDTree from example input in project instructions
     */
    private static void testExampleTree() {
        String testName = "===== Test Example Tree From Project Instructions =====";
        sop(testName);

        try {
            //////////////////////////////////////////////////////////////////////////
            sop("===== Instantiation =====");
            sop("Instantiating new tree with dimensions initialized to 2");
            KDTree testTree = new KDTree(2);
            sop("Instantiation Test: COMPLETE\n");

            //////////////////////////////////////////////////////////////////////////
            sop("==== Insertion ====");
            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));
            sop(testTree + "\n");

            sop("Inserting (40, 70) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 70))));
            sop(testTree + "\n");

            sop("Inserting (80, 20) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(80, 20))));
            sop(testTree + "\n");

            sop("Inserting (90, 10) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(90, 10))));
            sop(testTree + "\n");
            sop("Insertion Test: COMPLETE\n");

            //////////////////////////////////////////////////////////////////////////
            sop("===== Print Representation =====");
            sop(testTree + "\n");
            sop("Print Representation Test: COMPLETE\n");

            //////////////////////////////////////////////////////////////////////////
            sop("===== Size on Non-Empty Tree =====");
            sop("Size before insertion: " + testTree.getSize());

            sop("Inserting (60, 30) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(60, 30))));

            sop("Size after insertion: " + testTree.getSize());
            sop("Tree after insertion: " + testTree);
            sop("Size Test on Non-Empty Tree: COMPLETE\n");

            //////////////////////////////////////////////////////////////////////////
            sop("===== Min Finder =====");
            try {
                sop("Min in x-dimension is: " + testTree.findMin(0));
                sop("Min in y-dimension is: " + testTree.findMin(1));
            } catch (InvalidSearchException e) {
                e.printMsg();
            }

            sop("Min Finder Test: COMPLETE\n");

            //////////////////////////////////////////////////////////////////////////
            sop("===== Max Finder =====");
            try {
                sop("Max in x-dimension is: " + testTree.findMax(0));
                sop("Max in y-dimension is: " + testTree.findMax(1));
            } catch (InvalidSearchException e) {
                e.printMsg();
            }

            sop("Max Finder Test: COMPLETE\n");

            //////////////////////////////////////////////////////////////////////////
            sop("===== Insertion Order =====");
            sop("Insertion order is: " + testTree.getInsertionOrder());
            sop("Insertion Order Test: COMPLETE\n");

            //////////////////////////////////////////////////////////////////////////
            sop("===== Drawing Representation =====");
            KDTreeVisualizer testVisualizer = new KDTreeVisualizer(testTree);
            testVisualizer.visualizeTree(testName);
            sop("Drawing Representation Test: COMPLETE\n");

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        }

        sop("");
    }

    /////////////////////////////////
    ///// Print Traversal Tests /////
    /////////////////////////////////

    /**
     * Run all tests of various traversal orders.
     */
    private static void testTraversals() {
        testPreOrderTraversal();
        testInOrderTraversal();
        testPostOrderTraversal();
    }

    private static void testPreOrderTraversal() {
        String testName = "===== Test Example Tree From Project Instructions' Pre-Order Traversal =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to 2");
            KDTree testTree = new KDTree(2);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));

            sop("Inserting (40, 70) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 70))));

            sop("Inserting (80, 20) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(80, 20))));

            sop("Inserting (90, 10) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(90, 10))));

            sop("Inserting (60, 30) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(60, 30))));

            sop("The KD-tree's pre-order traversal is: " + testTree.preOrder());

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testInOrderTraversal() {
        String testName = "===== Test Example Tree From Project Instructions' In-Order Traversal =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to 2");
            KDTree testTree = new KDTree(2);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));

            sop("Inserting (40, 70) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 70))));

            sop("Inserting (80, 20) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(80, 20))));

            sop("Inserting (90, 10) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(90, 10))));

            sop("Inserting (60, 30) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(60, 30))));

            sop("The KD-tree's in-order traversal is: " + testTree.inOrder());

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        }

        sop("");
    }

    private static void testPostOrderTraversal() {
        String testName = "===== Test Example Tree From Project Instructions' Post-Order Traversal =====";
        sop(testName);

        try {
            sop("Instantiating new tree with dimensions initialized to 2");
            KDTree testTree = new KDTree(2);

            sop("Inserting (50, 40) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(50, 40))));

            sop("Inserting (40, 70) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(40, 70))));

            sop("Inserting (80, 20) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(80, 20))));

            sop("Inserting (90, 10) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(90, 10))));

            sop("Inserting (60, 30) into tree");
            testTree.insert(new KDNode(new ArrayList<>(Arrays.asList(60, 30))));

            sop("The KD-tree's post-order traversal is: " + testTree.postOrder());

        } catch (InvalidInitializationException e) {
            e.printMsg();
        } catch (NodeInsertionException e) {
            e.printMsg();
        }

        sop("");
    }

    public static void runAllTests() {
        // NOTE: Closing one window will close all windows when drawing with visualizer

        testInitialization();

        testInsertion();

        testFindMethod();

        testFindMinMethod();

        testFindMaxMethod();

        testIntegrationAndDrawing();

        testTraversals();
    }

    public static void main(String[] args) {
        runAllTests();
    }

    private static void sop(Object x) { System.out.println(x); }
}
