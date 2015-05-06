package tester;

import java.util.ArrayList;

/**
 * Created by danfergo on 05-05-2015.
 */
public class DataSample {

    ArrayList<Double> characteristics = new ArrayList<Double>();
    ArrayList<Double> solutions = new ArrayList<Double>();
    int solutionSize;

    DataSample(ArrayList<Double> characteristics, ArrayList<Double> solutions){
        this.characteristics = characteristics;
        this.solutions = solutions;
        this.solutionSize = this.solutions.size();
    }


    DataSample(ArrayList<Double> characteristics){
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return characteristics.toString() + "\n";
    }
}
