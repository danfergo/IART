package graph;

import java.awt.*;

/**
 * Created by danfergo on 04-05-2015.
 */
public abstract class DrawableEdge<N extends DrawableNode> extends  Edge<N>{


    public DrawableEdge(N source, N destination) {
        super(source, destination);
    }

    public Color getColor(){
        return new Color(0, 0, 0,180);
    }
    public abstract double  getThickness();


}
