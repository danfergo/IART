package ann;

import graph.DrawableEdge;
import graph.Edge;

import java.awt.*;

/**
 * Created by danfergo on 03-05-2015.
 */
public class Axon extends DrawableEdge<Neuron> {

    public Axon(Neuron source, Neuron destination) {
        super(source, destination);
    }

    @Override
    public double getThickness() {
        return 0;
    }
}
