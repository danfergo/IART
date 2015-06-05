package ann;

import graph.DrawableEdge;
import graph.Edge;

import java.awt.*;
import java.util.Random;

/**
 * Created by danfergo on 03-05-2015.
 */
public class Axon extends DrawableEdge<Neuron> {

    double weight, deltaWeight;
    private static Random randomGenerator = new Random(System.nanoTime());

    public Axon(Neuron source, Neuron destination) {
        super(source, destination);
        weight = (randomGenerator.nextDouble()*2) -1;
    }

    public void updateWeight(double delta){
        weight += delta;
    }

    public Color getColor(){
        double w = Math.tanh(weight/15);
        return new Color(w >= 0 ? 0 : 225, 0, 0, (int)Math.abs(Math.round(w * 225)));
    }


    @Override
    public double  getWeightValue(){
        return weight;
    }



    void setDeltaWeight(double deltaWeight){
        this.deltaWeight = deltaWeight;
    }

    double getDeltaWeight(){
        return deltaWeight;
    }
}
