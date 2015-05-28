package graph;

import ann.Neuron;
import graph.DrawableGraph;

import javax.swing.*;
import java.awt.*;

/**
 * Created by danfergo on 05-05-2015.
 */
public class GraphViewer extends JPanel{
    DrawableGraph graph;

    private static final int MAX_SCORE = 20;
    private static final int PREF_W = 800;
    private static final int PREF_H = 650;
    private static final int BORDER_GAP = 30;
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 12;
    private static final int Y_HATCH_CNT = 10;

    public GraphViewer(DrawableGraph graph){
        this.graph = graph;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



        //drawing edges
        for(Object dn : graph.getNodes()) {
            DrawableNode drawableNode = (DrawableNode)dn;

            for (Object tdn : drawableNode.getLeaving()) {
                DrawableEdge edgeTo = ((DrawableEdge) tdn);
                DrawableNode toDrawableNode = (DrawableNode) edgeTo.getDestination();


                g2.setColor(edgeTo.getColor());
                g2.setStroke(new BasicStroke(2f));
                g2.drawLine((int) (drawableNode.getX() * getWidth()), (int) (drawableNode.getY() * getHeight()),
                        (int) (toDrawableNode.getX() * getWidth()), (int) (toDrawableNode.getY() * getHeight()));
            }

        }

        // drawing nodes
        for(Object dn : graph.getNodes()){
            DrawableNode drawableNode = (DrawableNode)dn;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;

            g2.setColor(drawableNode.getColor());
            g2.setStroke(new BasicStroke(1f));
            g2.fillOval((int) (drawableNode.getX() * getWidth()), (int) (drawableNode.getY() * getHeight()), ovalW, ovalH);

        }

    }
}
