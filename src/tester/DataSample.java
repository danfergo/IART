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


    @Override
    public String toString() {
        return characteristics.toString() + "\n";
    }

    public ArrayList<Double> getCharacteristics() {
        return characteristics;
    }
    public ArrayList<Double> getSolutions() {
        return solutions;
    }

    @Override
    public DataSample clone(){
        return new DataSample((ArrayList<Double>)characteristics.clone(), new ArrayList<Double>());
    }
}
