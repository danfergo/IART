package tester;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by danfergo on 20-04-2015.
 */
public class DataSet {
    private ArrayList<String> characteristicsTitles;
    private ArrayList<String> solutionsTitles;
    private ArrayList<DataSample> dataSample;
    private List<DataSample> trainingSample;
    private List<DataSample> testSample;
    private int columns = 0;


    DataSet(ArrayList<Double> characteristicsTitles, ArrayList<Double> solutionsTitles){
        dataSample = new ArrayList<DataSample>();
    }


    DataSet(ArrayList<Double> caracteristics){

    }

    DataSet(ArrayList<Double> caracteristics, double solution){

    }

    private void addDataSample(DataSample dataSample){
        this.dataSample.add(dataSample);
    }

    public static DataSet DataSetFromFile(File file, int columns) throws IOException {

        CSVReader reader = new CSVReader(new FileReader(file),';');
        String [] nextLine;
        DataSet dataSet = new DataSet(null,null);
        dataSet.columns = columns;
        while ((nextLine = reader.readNext()) != null) {
            ArrayList<Double> characteristics = new ArrayList<Double>();
            // nextLine[] is an array of values from the line
            for(String v : nextLine){
                try {
                    characteristics.add(Double.parseDouble(v));
                } catch (NumberFormatException e) {
                }
            }
            dataSet.addDataSample(new DataSample(characteristics));
        }

        return dataSet;

    }

    public void generateRandomTrainingData(double ratio){
        Collections.shuffle(dataSample, new Random(System.nanoTime()));
        trainingSample = dataSample.subList(0, (int) (ratio * dataSample.size()));
        testSample = dataSample.subList((int)(ratio * dataSample.size()), dataSample.size());
    }

    public List<DataSample> getTrainingSample() {
        return trainingSample;
    }

    public List<DataSample> getTestSample() {
        return testSample;
    }
}
 