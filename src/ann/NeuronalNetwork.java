package ann;

import graph.DrawableGraph;

import java.util.*;

import tester.DataSample;
import tester.DataSet;
import util.Arrays;

/**
 * Created by danfergo on 20-04-2015.
 */
public class NeuronalNetwork extends DrawableGraph<Neuron,Axon> {

    public static NeuronalNetwork FullInterConnectedNN(int[] neuronsPerLayer){
        NeuronalNetwork nn = new NeuronalNetwork();

        int nrOfLayers = neuronsPerLayer.length;
        double colSize = 1/(double)(nrOfLayers-1), halfColSize = colSize/2;
        ArrayList<Neuron> currentLayer, lastLayer = new ArrayList<Neuron>();
        Neuron neuron;
        BiasNeuron currentLayerBias;
        for(int j = 0 ; j < nrOfLayers; j++){
            currentLayer = new ArrayList<Neuron>();


            int nrOfNeuronsInThisLayer = neuronsPerLayer[j];
            double lineSize = 1/(double)(nrOfNeuronsInThisLayer+( j < nrOfLayers -1 ? 1 : 0) ), halfLineSize = lineSize/2;

            currentLayerBias = new BiasNeuron(nrOfNeuronsInThisLayer * lineSize + halfLineSize, j*colSize);

            for(int i = 0; i < nrOfNeuronsInThisLayer; i++){
                neuron = new Neuron(i*lineSize + halfLineSize,j*colSize);

                if(j > 0 || j < nrOfNeuronsInThisLayer-1){
                    for(int jj = 0 ; jj < lastLayer.size(); jj++){
                        lastLayer.get(jj).connectTo(neuron);
                    }
                }

                currentLayer.add(neuron);
                if(j == 0){
                    nn.addStartNode(neuron);
                }else if(j == nrOfLayers -1){
                    nn.addEndNode(neuron);
                }else{
                    nn.addNode(neuron);
                }
            }
            if(j < nrOfLayers-1){
                currentLayer.add(currentLayerBias);
                nn.addNode(currentLayerBias);
            }
            lastLayer = currentLayer;
        }



        return nn;
    }


    public static NeuronalNetwork FullInterConnectedWithDirectFirstLayerNN(int[] neuronsPerLayerPassed){
        int neuronsPerLayer[] = new int[neuronsPerLayerPassed.length+1];
        neuronsPerLayer[0] = neuronsPerLayerPassed[0];
        neuronsPerLayer[1] = neuronsPerLayerPassed[0];
        for(int i = 2; i < neuronsPerLayer.length;i++){
            neuronsPerLayer[i] = neuronsPerLayerPassed[i-1];
        }

        NeuronalNetwork nn = new NeuronalNetwork();

        int nrOfLayers = neuronsPerLayer.length;
        double colSize = 1/(double)(nrOfLayers-1), halfColSize = colSize/2;
        ArrayList<Neuron> currentLayer, lastLayer = new ArrayList<Neuron>();
        Neuron neuron;
        BiasNeuron currentLayerBias;
        for(int j = 0 ; j < nrOfLayers; j++){
            currentLayer = new ArrayList<Neuron>();


            int nrOfNeuronsInThisLayer = neuronsPerLayer[j];
            double lineSize = 1/(double)(nrOfNeuronsInThisLayer+( j < nrOfLayers -1 ? 1 : 0) ), halfLineSize = lineSize/2;

            currentLayerBias = new BiasNeuron(nrOfNeuronsInThisLayer * lineSize + halfLineSize, j*colSize);

            for(int i = 0; i < nrOfNeuronsInThisLayer; i++){
                neuron = new Neuron(i*lineSize + halfLineSize,j*colSize);

                if(j == 1){
                        lastLayer.get(i).connectTo(neuron);
                }else if(j > 1 || j < nrOfNeuronsInThisLayer-1){
                    for(int jj = 0 ; jj < lastLayer.size(); jj++){
                        lastLayer.get(jj).connectTo(neuron);
                    }
                }

                currentLayer.add(neuron);
                if(j == 0){
                    nn.addStartNode(neuron);
                }else if(j == nrOfLayers -1){
                    nn.addEndNode(neuron);
                }else{
                    nn.addNode(neuron);
                }
            }
            if(j < nrOfLayers-1){
                currentLayer.add(currentLayerBias);
                nn.addNode(currentLayerBias);
            }
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

        for(Neuron neuron : getNodes()){
            neuron.updateWeights();
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


    public ArrayList<Double> collectOutputValues(){
        ArrayList<Double> outputValues = new ArrayList<Double>();

        for(Neuron neuron: getEndNodes()){
            outputValues.add(neuron.getOutputValue());
        }
        return outputValues;
    }


    public void learn(DataSample sample){
        feedForward(sample);
        backPropagateError(sample);
    }

    public void learn(DataSet set){
        for(DataSample sample : set.getTrainingSample()){
            learn(sample);
        }
    }

    public ArrayList<Double> answer(DataSample sample){
        feedForward(sample);
        return collectOutputValues();
    }

    public static double performanceFunction(){
        return 0;
    }

}
