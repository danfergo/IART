package tester;

import ann.Neuron;
import ann.NeuronalNetwork;
import com.sun.org.apache.xpath.internal.SourceTree;
import tester.ui.MainWindow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by danfergo on 05-05-2015.
 */
public class ANNTester {
    NeuronalNetwork ann;
    MainWindow currentWindow = null;
    int[] topology;
    double ratio;
    ANNTester(){
    }

    public ANNTester(File file, int [] topology, double ratio) throws IOException {
        this.topology = Arrays.copyOf(topology,topology.length);
        this.ratio = ratio;

        testNewDataSet(file);
    }

    public void setANN(int [] neuronsPerLayer){
        ann = NeuronalNetwork.FullInterConnectedNN(neuronsPerLayer);
        currentWindow = new MainWindow(this);
    }


    public void testNewDataSet(File file) throws IOException {
        //prepare data set
        DataSet dataSet = DataSet.DataSetFromFile(file, topology[0]);
        setANN(topology);

        dataSet.normalize();

        dataSet.generateRandomTrainingData(ratio);

        System.out.println("==========================================================");

        System.out.println("DATA SET SIZE: \t" + dataSet.size());
        System.out.println("TRAINING SET SIZE: \t" + dataSet.getTrainingSample().size());
        System.out.println("TEST SET SIZE: \t" + dataSet.getTestSample().size());
        System.out.println("TRAINING/TEST RATIO: \t" + (float)Math.round(ratio*1000)/1000);

        System.out.println("");
        //train ann
        System.out.println("Start learning...");
        trainANN(dataSet);
        System.out.println("Ended learning.");

        //test ann
        System.out.println("Start testing...");
        testANN(dataSet);
        System.out.println("Ended test.");


    }

    public void trainANN(DataSet set) {
        double error = 0;
        int wrong = 0;
        long t = System.currentTimeMillis();
        //Neuron.step = 0.2;
        for (int i = 1; i <= 20000; i++) {
            wrong = 0;
            for (DataSample sample : set.getTrainingSample()) {
                ann.learn(sample);
                currentWindow.repaint();
                if (Math.round(ann.answer(sample).get(0)) != sample.getSolutions().get(0)) {
                    wrong++;
                }
                //error+= performanceInternalSum(ann.collectOutputValues(), sample.getSolutions());
            }
            //System.out.println(error/2*(i*set.size()));
           // System.out.println("TRAINING ERR: " + ((double) wrong) / set.size());
            /*if (((double) wrong) / set.size() < 0.03 && Neuron.step > 0.1) {
                Neuron.step = 0.1;
                System.out.println("SETTING STEP");
                System.out.println("SETTING STEP");
                System.out.println("SETTING STEP");
                System.out.println("SETTING STEP");
                System.out.println("SETTING STEP");
                System.out.println("SETTING STEP");
                System.out.println("SETTING STEP");
            }

            else if (((double) wrong) / set.size() < 0.025 && Neuron.step > 0.05) {
                Neuron.step = 0.05;
                System.out.println("SETTING STEP 2");
                System.out.println("SETTING STEP 2");
                System.out.println("SETTING STEP 2");
                System.out.println("SETTING STEP 2");
                System.out.println("SETTING STEP 2");
                System.out.println("SETTING STEP 2");
                System.out.println("SETTING STEP 2");
            }*/
            set.reShuffleTrainingData();
        }
        System.out.println("Last iteration error: " + ((double) wrong) / set.size());
        System.out.println("Duration (s): " + (System.currentTimeMillis() - t)/(double)1000);
    }
    public void testANN(DataSet dataSet){
        int wrong2 = 0;
        for(DataSample sample : dataSet.getTestSample()){
            if (Math.round(ann.answer(sample).get(0)) != sample.getSolutions().get(0)){
                wrong2++;
            }
        }
        System.out.println("ERR: " + wrong2 / (double) dataSet.getTestSample().size());
    }



    private double performanceInternalSum(ArrayList<Double> predicted, ArrayList<Double> solutions){
        double sum = 0;
        Iterator<Double> predictedIterator =  predicted.iterator();
        Iterator<Double> solutionsIterator =  solutions.iterator();

        while (predictedIterator.hasNext() && solutionsIterator.hasNext()){
            sum += Math.pow(predictedIterator.next() - solutionsIterator.next(), 2);
        }

        return sum;
    }

    public NeuronalNetwork getANN(){
        return ann;
    }

    public static void main(String args[]) throws IOException {
        if (args.length == 4){

            String [] sTopology = args[1].split("-");
            int topology[] = new int[sTopology.length];
            for(int i = 0; i < topology.length; i++){
                topology[i] = Integer.parseInt(sTopology[i]);
            }
            Neuron.step = Double.parseDouble(args[2]);

            new ANNTester(new File(args[0]),topology,Double.parseDouble(args[3]));
        }else {
            System.out.println("Bad usage. Usage\n" +
                    "\tjava ANNTester <test file> <topology> <step> <ratio>");
        }
   }
}
