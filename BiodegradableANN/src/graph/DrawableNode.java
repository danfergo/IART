package graph;


import java.awt.*;

/**
 * Created by danfergo on 04-05-2015.
 */
public abstract class DrawableNode<E extends DrawableEdge> extends Node<E>{
    private double x,y;

    public DrawableNode(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Color getColor(){
        if(getLeaving().isEmpty() && getArriving().isEmpty()){
            return new Color(255, 255, 0);
        }else if(getLeaving().isEmpty()){
            return new Color(0, 230, 90);
        }else if(getArriving().isEmpty()){
            return new Color(240, 0, 100);
        }else{
            return new Color(0, 50, 120);
        }
    }

    public double  getSize(){
        return 1;
    }


    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }


}
