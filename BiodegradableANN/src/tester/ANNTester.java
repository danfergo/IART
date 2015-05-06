package tester;

import ann.NeuronalNetwork;
import tester.ui.MainWindow;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by danfergo on 05-05-2015.
 */
public class ANNTester {
    NeuronalNetwork ann;
    MainWindow currentWindow = null;

    ANNTester(){
        setANNLayout(new int[]{41,5,10,10,5,1});
    }

    public void setANNLayout(int [] neuronsPerLayer){
        ann = NeuronalNetwork.FullInterConnectedNN(neuronsPerLayer);
        currentWindow = new MainWindow(this);
    }


    public void testNewDataSet(File file) throws IOException {
        //prepare data set
        DataSet dataSet = DataSet.DataSetFromFile(file);
        //train ann

        //test ann

    }

    public void trainANN(){

    }

    public void testANN(){

    }


    public NeuronalNetwork getANN(){
        return ann;
    }

    public static void main(String args[]){
        new ANNTester();

    }
}
