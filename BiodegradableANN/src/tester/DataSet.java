package tester;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by danfergo on 20-04-2015.
 */
public class DataSet {
    ArrayList<String> characteristicsTitles;
    ArrayList<String> solutionsTitles;
    ArrayList<DataSet> dataSet;

    DataSet(ArrayList<Double> characteristicsTitles, ArrayList<Double> solutionsTitles){

    }


    DataSet(ArrayList<Double> caracteristics){

    }

    DataSet(ArrayList<Double> caracteristics, double solution){

    }

    public static DataSet DataSetFromFile(File file) throws IOException {

        CSVReader reader = new CSVReader(new FileReader(file),';');
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            for(String v : nextLine){
                System.out.print(v + " ");
            }
            System.out.println();
        }

        return new DataSet(null,null);

    }
}
 