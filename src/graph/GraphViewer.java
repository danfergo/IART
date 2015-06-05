package graph;

import ann.BiasNeuron;
import ann.Neuron;
import graph.DrawableGraph;

import javax.swing.*;
import java.awt.*;

/**
 * Created by danfergo on 05-05-2015.
 */
public class GraphViewer extends JPanel{
    DrawableGraph graph;


    public GraphViewer(DrawableGraph graph){
        this.graph = graph;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter  = (int)(long)(0.03*Math.round(Double.min(getWidth(),getHeight())));
        int radius = (int)(diameter/(double)2);

        //drawing edges
        for(Object dn : graph.getNodes()) {
            DrawableNode drawableNode = (DrawableNode)dn;

            for (Object tdn : drawableNode.getLeaving()) {
                DrawableEdge edgeTo = ((DrawableEdge) tdn);
                DrawableNode toDrawableNode = (DrawableNode) edgeTo.getDestination();


                g2.setColor(edgeTo.getColor());
                g2.setStroke(new BasicStroke(2f));
                g2.drawLine((int) (drawableNode.getX() * getWidth()), (int) ((drawableNode.getY()*0.95 + 0.025)*getHeight()),
                        (int) (toDrawableNode.getX() * getWidth()), (int) ((toDrawableNode.getY()*0.95 + 0.025)*getHeight()));
            }

        }

        // drawing nodes
        for(Object dn : graph.getNodes()){
            DrawableNode drawableNode = (DrawableNode)dn;

            g2.setColor(drawableNode.getColor());
            g2.setStroke(new BasicStroke(1f));
            g2.fillOval((int) (drawableNode.getX() * getWidth()) - radius, (int) ((drawableNode.getY()*0.95 + 0.025)*getHeight()) - radius, diameter, diameter);

            g2.setColor(new Color(0,0,0,250));
            g2.setStroke(new BasicStroke(0.1f*radius));
            g2.drawOval((int) (drawableNode.getX() * getWidth()) - radius, (int) ((drawableNode.getY()*0.95 + 0.025)*getHeight()) - radius , diameter, diameter);

/*
            if(dn instanceof Neuron || dn instanceof BiasNeuron){
                Double value = ((Neuron)dn).getOutputValue();
                try{
                    if(value != null){
                        g2.drawString(value+"", (int)(drawableNode.getX() * getWidth()), (int) ((drawableNode.getY()*0.95 + 0.025)*getHeight()));
                    }
                }catch(Exception e) {}
            }*/
        }

    }
}
