package ann;

import graph.Node;

import java.awt.*;
import java.lang.reflect.ParameterizedType;

/**
 * Created by danfergo on 31-05-2015.
 */
public class BiasNeuron extends Neuron {
    public BiasNeuron(double x, double y) {
        super(x, y);
    }

    public double getOutputValue(){
        return -1;
    }

    @Override
    public Color getColor(){
        return new Color(255, 0, 0);
    }

    public void connectTo(Node<Axon> destination){
        Axon axon = new Axon(this,(Neuron)destination);
        this.getLeaving().add(axon);
        destination.getArriving().add(axon);
    }
}
