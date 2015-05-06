package ann;

import graph.DrawableNode;
import graph.Node;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by danfergo on 20-04-2015.
 */
public class Neuron extends DrawableNode<Axon> {

    Double value = null;

    public Neuron(double x, double y){
       super(x,y);
    }

    void setValue(double value){
        this.value = value;
    }


    double getOutputValue(){
        return getArriving().size() == 0  ? transferenceFunction(value)  : transferenceFunction(sumInputValues());
    }

    double sumInputValues(){
        double sum = 0;
        /**for(Axon axon : this.getArriving()){
            sum += axon.output();
        }**/
        return sum;
    }

    static double transferenceFunction(double x){
        return x;
    }

    static double transferenceFunctionDerivative(double x){
        return 1;
    }

    @Override
    public double getSize() {
        return 0;
    }
}
