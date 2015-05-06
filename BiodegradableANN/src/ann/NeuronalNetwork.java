package ann;

import graph.DrawableGraph;

import java.util.*;

import tester.DataSet;
import util.Arrays;

/**
 * Created by danfergo on 20-04-2015.
 */
public class NeuronalNetwork extends DrawableGraph<Neuron,Axon> {

    public static NeuronalNetwork FullInterConnectedNN(int[] neuronsPerLayer){
        NeuronalNetwork nn = new NeuronalNetwork();

        int neuronSize = Arrays.max(neuronsPerLayer);

        ArrayList<ArrayList<Neuron>> neurons = new ArrayList<ArrayList<Neuron>>();

        int nrOfLayers = neuronsPerLayer.length;
        double colSize = 1/(double)nrOfLayers, halfColSize = colSize/2;
        ArrayList<Neuron> currentLayer, lastLayer = new ArrayList<Neuron>();
        Neuron neuron;

        for(int j = 0 ; j < nrOfLayers; j++){
            currentLayer = new ArrayList<Neuron>();

            int nrOfNeuronsInThisLayer = neuronsPerLayer[j];
            double lineSize = 1/(double)nrOfNeuronsInThisLayer, halfLineSize = lineSize/2;
            for(int i = 0; i < nrOfNeuronsInThisLayer; i++){
                neuron = new Neuron(i*lineSize + halfLineSize,j*colSize + halfColSize);

                if(j > 0 || j < nrOfNeuronsInThisLayer-1){
                    for(int jj = 0 ; jj < lastLayer.size(); jj++){
                        lastLayer.get(jj).connectTo(neuron);
                    }
                }


                currentLayer.add(neuron);
                nn.addNode(neuron);
            }

            neurons.add(currentLayer);
            lastLayer = currentLayer;
        }



        return nn;
    }


    void feedForward(DataSet set){
       /* Iterator<Double> setCharacteristicsIterator =  set.getCharacteristics().iterator();
        Iterator<Neuron> inputNeuronsIterator =  getEndNodes().iterator();

        while (inputNeuronsIterator.hasNext() && setCharacteristicsIterator.hasNext()){
            inputNeuronsIterator.next().setValue(setCharacteristicsIterator.next());
        }*/
    }

    ArrayList<Double> collectResults(){
        ArrayList<Double> outputValues = new ArrayList<Double>();
        Double output;

        for(Neuron neuron: getEndNodes()){
            output = neuron.getOutputValue();
            if(output != null){
                outputValues.add(neuron.getOutputValue());
            }
        }

        return outputValues;
    }

    double performanceFunction(){
        return 0;
    }
}
