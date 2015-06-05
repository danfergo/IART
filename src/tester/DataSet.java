package tester;

import com.opencsv.CSVReader;
import com.sun.org.apache.xpath.internal.SourceTree;

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


    DataSet(ArrayList<String> characteristicsTitles, ArrayList<Double> solutionsTitles){
        dataSample = new ArrayList<DataSample>();
    }


    DataSet(ArrayList<String> caracteristics){

    }

    DataSet(ArrayList<String> caracteristics, double solution){

    }

    public int size(){
        return dataSample.size();
    }

    private void addDataSample(DataSample dataSample){
        this.dataSample.add(dataSample);
    }

    public static DataSet DataSetFromFile(File file, int columns) throws IOException {

        CSVReader reader = new CSVReader(new FileReader(file),';');
        String [] nextLine;
        DataSet dataSet = new DataSet(null,null);
        dataSet.columns = columns;
        int j;
        while ((nextLine = reader.readNext()) != null) {
            ArrayList<Double> characteristics = new ArrayList<Double>();
            ArrayList<Double> solutions = new ArrayList<Double>();
            // nextLine[] is an array of values from the line
            j = 0;
            for(String v : nextLine){
                try {
                    if( j < columns){
                        characteristics.add(Double.parseDouble(v));
                    }else {
                        solutions.add(Double.parseDouble(v));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                j++;
            }
            dataSet.addDataSample(new DataSample(characteristics,solutions));
        }


        return dataSet;

    }

    public void normalize() {
        double min,max,c;
        for(DataSample s: dataSample){
            min = s.getCharacteristics().get(0);
            max = s.getCharacteristics().get(0);
            for(int i = 0; i < s.getCharacteristics().size();i++){
                c = s.getCharacteristics().get(i);
                if(c < min){
                    min = c;
                }
                if( c > max){
                    max = c;
                }
            }
            if (max == min )continue;
            for(int i = 0; i < s.getCharacteristics().size();i++) {
                c = s.getCharacteristics().get(i);
                s.getCharacteristics().set(i, (c - min) / (max - min));

            }
        }
    }

    public void generateRandomTrainingData(double ratio){
        Collections.shuffle(dataSample, new Random(System.nanoTime()));
        trainingSample = dataSample.subList(0, (int) (ratio * dataSample.size()));
        testSample = dataSample.subList((int)(ratio * dataSample.size()), dataSample.size());
    }

    public void reShuffleTrainingData(){
        Collections.shuffle(trainingSample, new Random());
    }

    public List<DataSample> getTrainingSample() {
        return trainingSample;
    }

    public List<DataSample> getTestSample() {
        return testSample;
    }
}
 