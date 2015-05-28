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
    }

    public ANNTester(File file) throws IOException {
        testNewDataSet(file);
    }

    public void setANNLayout(int [] neuronsPerLayer){
        ann = NeuronalNetwork.FullInterConnectedNN(neuronsPerLayer);
        currentWindow = new MainWindow(this);
    }


    public void testNewDataSet(File file) throws IOException {
        //prepare data set
        DataSet dataSet = DataSet.DataSetFromFile(file, 1);
        setANNLayout(new int[]{41,10,20,1});

        //train ann

        //test ann

    }

    public void trainANN(DataSet set){

    }

    public void testANN(DataSet set){

    }


    public NeuronalNetwork getANN(){
        return ann;
    }

    public static void main(String args[]) throws IOException {
        if (args.length > 0){
            new ANNTester(new File(args[0]));
        }

       /* try {
            DataSet dataSet = DataSet.DataSetFromFile(new File(args[0]), 1);
            dataSet.generateRandomTrainingData(1.0/3);
            System.out.println(dataSet.getTrainingSample());
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
