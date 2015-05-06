package tester;

import java.util.ArrayList;

/**
 * Created by danfergo on 05-05-2015.
 */
public class DataSample {

    ArrayList<Double> characteristics = new ArrayList<Double>();
    ArrayList<Double> solutions = new ArrayList<Double>();

    DataSample(ArrayList<Double> characteristics, ArrayList<Double> solutions){
           this.characteristics = characteristics;
           this.solutions = solutions;
    }


    DataSample(ArrayList<Double> characteristics){
        this.characteristics = characteristics;
    }


}
