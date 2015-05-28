package ann;

import graph.DrawableGraph;

import java.util.*;

import tester.DataSample;
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

    void clean(){
        for(Neuron neuron: getNodes()){
            neuron.clean();
        }
    }


    private void backPropagateError(DataSample sample){

        Iterator<Double> sampleSolutionsIterator =  sample.getSolutions().iterator();
        Iterator<Neuron> outputNeuronsIterator =  getEndNodes().iterator();

        while (outputNeuronsIterator.hasNext() && sampleSolutionsIterator.hasNext()){
            outputNeuronsIterator.next().setExpectedValue(sampleSolutionsIterator.next());
        }


        for (Neuron neuron : getStartNodes()){
            neuron.backPropagateError();
        }

    }


    void feedForward(DataSample sample){

        clean();

        Iterator<Double> sampleCharacteristicsIterator =  sample.getCharacteristics().iterator();
        Iterator<Neuron> inputNeuronsIterator =  getStartNodes().iterator();

        while (inputNeuronsIterator.hasNext() && sampleCharacteristicsIterator.hasNext()){
            inputNeuronsIterator.next().setOutputValue(sampleCharacteristicsIterator.next());
        }

        for(Neuron neuron: getEndNodes()){
            neuron.getOutputValue();
        }
    }


    ArrayList<Double> collectOutputValues(DataSample sample){
        ArrayList<Double> outputValues = new ArrayList<Double>();

        for(Neuron neuron: getEndNodes()){
            outputValues.add(neuron.getOutputValue());
        }

        return outputValues;
    }


    void learn(DataSample sample){
        feedForward(sample);
        backPropagateError(sample);
    }

    DataSample answer(DataSample sample){
        feedForward(sample);
        DataSample ret = sample.clone();
        collectOutputValues(ret);
        return ret;
    }

    double performanceFunction(){
        return 0;
    }
}
