package ANN;

import java.util.ArrayList;

/**
 * Created by danfergo on 20-04-2015.
 */
public class Neuron {

    private ArrayList<Axon> leaving = new ArrayList<Axon>();
    private ArrayList<Axon> entering = new ArrayList<Axon>();

    private static class Axon {
        private Neuron from, to;
        int weight;


        Axon(Neuron from, Neuron to){
            this.from = from;
            this.to = to;

            from.leaving.add(this);
            to.entering.add(this);
        }
    }


    Neuron(){
    }

    void linkTo(Neuron neuronTo){
        new Axon(this,neuronTo);
    }


    ArrayList<Double> feedForward(DataSet set){
        return null;
    }
}
