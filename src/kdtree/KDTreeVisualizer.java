package kdtree;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.*;

/**
 * <h1>KD-Tree Visualization</h1>
 *
 * <ul>
 *     <li>Currently only works for 2-dimensional KD-Trees.</li>
 *     <li>Will automatically resize depending on input values of evaluated KD-Tree.</li>
 * </ul>
 *
 * @author Don Robert Pornaras
 * @version 1.0
 * @since 2018-12-07
 */
public class KDTreeVisualizer extends JPanel {
    private KDTree kdTree;
    private double plottingMinXValueRaw;
    private double plottingMinYValueRaw;
    private double plottingMaxXValueRaw;
    private double plottingMaxYValueRaw;
    private double internalBufferRange;

    private int width;
    private int height;
    private int padding;
    private int labelPadding;
    private int totalPadding;

    private Color backgroundColor;
    private Color gridColor;
    private Color axisLabelColor;
    private Color titleColor;
    private Color axesLinesColor;
    private Color lineColor;
    private Color pointColor;

    private Font titleFont;
    private Font axesTitleFont;

    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth;
    private int cntYDivisions;
    private int cntXDivisions;

    /**
     * Ctor to initialize visualizer for a given KDTree
     * @param kdTree the KDTree to visualize
     */
    public KDTreeVisualizer(KDTree kdTree) {
        this.kdTree = kdTree;

        internalBufferRange = 0.2; // percentage of original range of KDTree

        try {
            int rawTreeRangeX = kdTree.findMax(0) - kdTree.findMin(0);
            int rawTreeRangeY = kdTree.findMax(1) - kdTree.findMin(1);

            if (kdTree.getSize() >= 2) {
                plottingMinXValueRaw = (double) kdTree.findMin(0)
                        - (internalBufferRange) * ((double) (rawTreeRangeX));
                plottingMinYValueRaw = (double) kdTree.findMin(1)
                        - (internalBufferRange) * ((double) (rawTreeRangeY));
                plottingMaxXValueRaw = (double) kdTree.findMax(0)
                        + (internalBufferRange) * ((double) (rawTreeRangeX));
                plottingMaxYValueRaw = (double) kdTree.findMax(1)
                        + (internalBufferRange) * ((double) (rawTreeRangeY));
            } else if (kdTree.getSize() == 1) {
                plottingMinXValueRaw = (double) kdTree.findMin(0)
                        - (internalBufferRange) * ((double) kdTree.findMin(0));
                plottingMinYValueRaw = (double) kdTree.findMin(1)
                        - (internalBufferRange) * ((double) kdTree.findMin(1));
                plottingMaxXValueRaw = (double) kdTree.findMax(0)
                        + (internalBufferRange) * ((double) kdTree.findMax(0));
                plottingMaxYValueRaw = (double) kdTree.findMax(1)
                        + (internalBufferRange) * ((double) kdTree.findMax(1));
            } else {
                // default range if no elements present in graph
                plottingMinXValueRaw = 0;
                plottingMinYValueRaw = 0;
                plottingMaxXValueRaw = 100;
                plottingMaxYValueRaw = 100;
            }
        } catch (InvalidSearchException e) {
            e.printMsg();
        }

        this.width = 1080;
        this.height = 720;
        this.padding = 40;
        this.labelPadding = 40;
        this.totalPadding = 2 * padding + labelPadding;

        this.backgroundColor = Color.WHITE;
        this.gridColor = new Color(204, 204, 255);
        this.titleColor = Color.BLACK;
        this.axesLinesColor = Color.BLACK;
        this.axisLabelColor = Color.BLACK;
        this.pointColor = Color.RED;
        this.lineColor = Color.GREEN;

        this.titleFont = new Font("TimesNewRoman", Font.BOLD, 18);
        this.axesTitleFont = new Font("TimesNewRoman", Font.PLAIN, 14);

        this.pointWidth = 10;
        this.cntYDivisions = 10;
        this.cntXDivisions = 10;
    }

    /**
     * Paint new graph
     * @param g Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xRange = plottingMaxXValueRaw - plottingMinXValueRaw;
        double xScale = ((double) getWidth() - 2.0 * (double) padding - (double) labelPadding) / (xRange);
        double yRange = plottingMaxYValueRaw - plottingMinYValueRaw;
        double yScale = ((double) getHeight() - 2.0 * (double) padding - (double) labelPadding) / (yRange);

        drawEmptyGraph(g2);

        // Get list of coordinates to graph
        ArrayList<Point> graphPoints = collectPoints(xScale, yScale);

        // Draw Hyperplanes (as line for 2D representation
        drawHyperplanes(g2, graphPoints, xScale, yScale);

        // Draw Points
        drawPoints(g2, graphPoints);
    }

    ///////////////////////////////////////////////////
    ///// Utility Methods for Drawing Empty Graph /////
    ///////////////////////////////////////////////////

    /**
     * Draw an empty graph with title and labeled axes
     * @param g2 Graphics2D object
     */
    private void drawEmptyGraph(Graphics2D g2) {
        // Draw background
        drawBackground(g2);

        // Draw title
        drawTitle(g2);

        // Axes titles
        drawAxesTitles(g2);

        // Draw the Axes and Grid
        drawYAxisGridAndMarkers(g2);
        drawXAxisGridAndMarkers(g2);

        // Solid axes lines
        drawSolidAxesLines(g2);
    }

    /**
     * Draw background of the graph
     * @param g2 Graphics2D object
     */
    private void drawBackground(Graphics2D g2) {
        g2.setColor(backgroundColor);
        g2.fillRect(padding + labelPadding, padding,
                getWidth() - totalPadding,
                getHeight() - totalPadding);
    }

    /**
     * Draw the graph title
     * @param g2 Graphics2D object
     */
    private void drawTitle(Graphics2D g2) {
        g2.setColor(titleColor);
        FontMetrics titleFontMetrics = g2.getFontMetrics(titleFont);
        g2.setFont(titleFont);
        String title = "Visualized KDTree of Size " + kdTree.getSize();
        g2.drawString(title,
                (getWidth()/ 2) - (titleFontMetrics.stringWidth(title) / 2) + (labelPadding / 2),
                padding / 2);
    }

    /**
     * Draw axes titles
     * @param g2 Graphics2D object
     */
    private void drawAxesTitles(Graphics2D g2) {
        FontMetrics axisFontMetrics = g2.getFontMetrics(axesTitleFont);
        g2.setFont(axesTitleFont);

        AffineTransform originalState = g2.getTransform();
        g2.rotate((-1)*Math.PI / 2);
        String yAxisLabel = "Y-Coordinates [KDTree Units]";
        g2.drawString(yAxisLabel,
                (-getHeight() / 2) - (axisFontMetrics.stringWidth(yAxisLabel) / 2) + (labelPadding / 2),
                padding / 2);
        g2.setTransform(originalState);

        String xAxisLabel = "X-Coordinates [KDTree Units]";
        g2.drawString(xAxisLabel,
                (getWidth() / 2) - (axisFontMetrics.stringWidth(xAxisLabel) / 2) + (labelPadding / 2),
                getHeight() - padding / 2);
    }

    /**
     * Draw y-axis grid and y-axis markers
     *
     * <b>NOTE:</b> Will default to range noted in ctor if no nodes are present in the KDTree
     *
     * @param g2 Graphics2D object
     */
    private void drawYAxisGridAndMarkers(Graphics2D g2) {
        for (int i = 0; i < cntYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - totalPadding))
                    / cntYDivisions + padding + labelPadding);
            int x1 = pointWidth + padding + labelPadding;
            int y1 = y0;

            g2.setColor(gridColor);
            g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);

            g2.setColor(axisLabelColor);
            String yMarker = ((int) ((plottingMinYValueRaw + (plottingMaxYValueRaw
                    - plottingMinYValueRaw) * ((i * 1.0)
                    / cntYDivisions)) * 100)) / 100.0 + "";
            FontMetrics yMetrics = g2.getFontMetrics();
            int labelWidth = yMetrics.stringWidth(yMarker);
            g2.drawString(yMarker, x0 - labelWidth - 5, y0 + (yMetrics.getHeight() / 2) - 3);
            g2.drawLine(x0, y0, x1, y1);
        }
    }

    /**
     * Draw x-axis grid and x-axis markers
     *
     * <b>NOTE:</b> Will default to range noted in ctor if no nodes are present in the KDTree
     *
     * @param g2 Graphics2D object
     */
    private void drawXAxisGridAndMarkers(Graphics2D g2) {
        for (int i = 0; i < cntXDivisions + 1; i++) {
            int x0 = ((i * (getWidth() - totalPadding))
                    / cntXDivisions + padding + labelPadding);
            int y0 = getHeight() - padding - labelPadding;
            int x1 = x0;
            int y1 = y0 - pointWidth;

            g2.setColor(gridColor);
            g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);

            g2.setColor(axisLabelColor);
            String xMarker = ((int) ((plottingMinXValueRaw + (plottingMaxXValueRaw
                    - plottingMinXValueRaw) * ((i * 1.0)
                    / cntXDivisions)) * 100)) / 100.0 + "";
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(xMarker);
            g2.drawString(xMarker, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
            g2.drawLine(x0, y0, x1, y1);
        }
    }

    /**
     * Draw solid lines to represent axes
     * @param g2 Graphics2D object
     */
    private void drawSolidAxesLines(Graphics2D g2) {
        g2.setColor(axesLinesColor);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding,
                padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding,
                getWidth() - padding, getHeight() - padding - labelPadding);
    }

    /**
     * Gather points in graph in order, in drawable coordinates
     * @param xScale conversion from raw x-coordinates to drawable x-coordinates
     * @param yScale conversion from raw y-coordinates to drawable y-coordinates
     * @return ArrayList of graph points to be drawn
     */
    private ArrayList<Point> collectPoints(double xScale, double yScale) {
        ArrayList<Point> graphPoints = new ArrayList<>();

        for (int i = 0; i < kdTree.getSize(); i++) {
            int currentXCoordinate = kdTree.getInsertionOrder().get(i).getCoordinates().get(0);
            int currentYCoordinate = kdTree.getInsertionOrder().get(i).getCoordinates().get(1);

            int x = transformXCoordinate(currentXCoordinate, xScale);
            int y = transformYCoordinate(currentYCoordinate, yScale);
            graphPoints.add(new Point(x, y));
        }

        return graphPoints;
    }

    /**
     * Draw hyperplanes based on order of insertion into KDTree
     * @param g2 Graphics2D object
     * @param graphPoints ArrayList of graph points in insertion order into KDTree
     * @param xScale conversion from raw x-coordinates to drawable x-coordinates
     * @param yScale conversion from raw y-coordinates to drawable y-coordinates
     */
    private void drawHyperplanes(Graphics2D g2, ArrayList<Point> graphPoints, double xScale, double yScale) {
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size(); i++) {
            KDNode currentNode = kdTree.getInsertionOrder().get(i);

            // values directly from KDTree
            double x1Raw = findClosestLeftX(currentNode);
            double x2Raw = findClosestRightX(currentNode);
            double y1Raw = findClosestAboveY(currentNode);
            double y2Raw = findClosestBelowY(currentNode);

            // transformation onto drawing plane
            int x1 = transformXCoordinate(x1Raw, xScale);
            int y1 = transformYCoordinate(y1Raw, yScale);
            int x2 = transformXCoordinate(x2Raw, xScale);
            int y2 = transformYCoordinate(y2Raw, yScale);

            g2.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * Draw points from KDTree
     * @param g2 Graphics2D object
     * @param graphPoints points from the KDTree
     */
    private void drawPoints(Graphics2D g2, ArrayList<Point> graphPoints) {
        g2.setColor(pointColor);
        for (Point point : graphPoints) {
            int x = point.x - pointWidth / 2;
            int y = point.y - pointWidth / 2;
            int ovalWidth = pointWidth;
            int ovalHeight = pointWidth;

            g2.fillOval(x, y, ovalWidth, ovalHeight);
        }
    }

    /**
     * Convert raw unit x-coordinate in KDTree to graphical x-position
     * @param xCoordinate raw unit x-coordinate from KDTree
     * @param xScale proportional x-direction scale factor from raw unit to graphical representation
     * @return graphical x-position coordinate
     */
    private int transformXCoordinate(double xCoordinate, double xScale) {
        double adjustedXCoord = xCoordinate - plottingMinXValueRaw;
        return (int) (padding + labelPadding + (adjustedXCoord) * xScale);
    }

    /**
     * Convert raw unit y-coordinate in KDTree to graphical y-position
     * @param yCoordinate raw unit y-coordinate from KDTree
     * @param yScale proportional y-direction scale factor from raw unit to graphical representation
     * @return graphical y-position coordinate
     */
    private int transformYCoordinate(double yCoordinate, double yScale) {
        double adjustedYCoord = yCoordinate - plottingMinYValueRaw;
        return (int) ((getHeight() - padding - labelPadding) - yScale * adjustedYCoord);
    }
    ////////////////////////////////////////////////////////////////////
    ///// Utility Methods for Drawing Lines in the Insertion Order /////
    ////////////////////////////////////////////////////////////////////

    /**
     * Find the largest x-coordinate to the left of a node
     * @param node given node to examine
     * @return the largest x-coordinate to the left of the input node
     */
    private double findClosestLeftX(KDNode node) {
        if (node.getDepth() % kdTree.getK() == 0)
            return node.getCoordinates().get(0);

        // intialized to leftmost coordinate
        double currentLargestX = plottingMinXValueRaw;

        KDNode current = new KDNode(node);

        while (current != null) {
            if (current.getCoordinates().get(0) < node.getCoordinates().get(0))
                if (current.getDepth() % kdTree.getK() == 0
                        && current.getCoordinates().get(0) > currentLargestX)
                    currentLargestX = current.getCoordinates().get(0);

            current = current.getParent();
        }

        return currentLargestX;
    }

    /**
     * Find the smallest x-coordinate to the right of a node
     * @param node given node to examine
     * @return the smallest x-coordinate to the right of the input node
     */
    private double findClosestRightX(KDNode node) {
        if (node.getDepth() % kdTree.getK() == 0)
            return node.getCoordinates().get(0);

        // initialized to rightmost coordinate
        double currentSmallestX = plottingMaxXValueRaw;

        KDNode current = new KDNode(node);

        while (current != null) {
            if (current.getCoordinates().get(0) > node.getCoordinates().get(0))
                if (current.getDepth() % kdTree.getK() == 0
                        && current.getCoordinates().get(0) < currentSmallestX)
                    currentSmallestX = current.getCoordinates().get(0);

            current = current.getParent();
        }

        return currentSmallestX;
    }

    /**
     * Find the smallest y-coordinate above a node
     * @param node given node to examine
     * @return the smallest y-coordinate above the input node
     */
    private double findClosestAboveY(KDNode node) {
        if (node.getDepth() % kdTree.getK() == 1)
            return node.getCoordinates().get(1);

        // initialized to topmost coordinate
        double currentSmallestY = plottingMaxYValueRaw;

        KDNode current = new KDNode(node);

        while (current != null) {
            if (current.getCoordinates().get(1) > node.getCoordinates().get(1))
                if (current.getDepth() % kdTree.getK() == 1
                        && current.getCoordinates().get(1) < currentSmallestY)
                    currentSmallestY = current.getCoordinates().get(1);

            current = current.getParent();
        }

        return currentSmallestY;
    }

    /**
     * Find the largest y-coordinate to the below of a node
     * @param node given node to examine
     * @return the largest y-coordinate below the input node
     */
    private double findClosestBelowY(KDNode node) {
        if (node.getDepth() % kdTree.getK() == 1)
            return node.getCoordinates().get(1);

        // initialized to bottommost coordinate
        double currentLargestY = plottingMinYValueRaw;

        KDNode current = new KDNode(node);

        while (current != null) {
            if (current.getCoordinates().get(1) < node.getCoordinates().get(1))
                if (current.getDepth() % kdTree.getK() == 1
                        && current.getCoordinates().get(1) > currentLargestY)
                    currentLargestY = current.getCoordinates().get(1);

            current = current.getParent();
        }

        return currentLargestY;
    }


    ///////////////////////
    ///// Entry Point /////
    ///////////////////////

    /**
     * Perform visualization of the KDTree
     */
    public void visualizeTree(String frameTitle) {
        JFrame frame = new JFrame(frameTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(width, height));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        frame.add(this);
        frame.pack();

        // open window in center of screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    /**
     * Perform visualization of the KDTree
     * @param kdtree the input tree to visualize
     */
    public static void visualizeTree(KDTree kdtree, String frameTitle) {
        JFrame frame = new JFrame(frameTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        KDTreeVisualizer newPanel = new KDTreeVisualizer(kdtree);
        newPanel.setPreferredSize(new Dimension(newPanel.width, newPanel.height));
        newPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        frame.add(newPanel);
        frame.pack();

        // open window in center of screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private static void sop(Object x) { System.out.println(x); }
}
