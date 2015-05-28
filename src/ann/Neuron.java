package ann;

import graph.DrawableNode;

import static java.lang.Math.exp;

/**
 * Created by danfergo on 20-04-2015.
 */
public class Neuron extends DrawableNode<Axon> {

    Double outputValue = null;
    Double error = null;

    public Neuron(double x, double y){
       super(x,y);
    }

    void setOutputValue(double value){
        this.outputValue = value;
    }

    void setExpectedValue(double expectedValue){
        this.error = transferenceFunctionDerivative(outputValue)*(expectedValue - outputValue);
    }

    void backPropagateError(){
        this.error = transferenceFunctionDerivative(outputValue)*getErrorSum();
    }


    double getOutputValue(){
        if(outputValue == null){
            outputValue = transferenceFunction(getInputSum());
        }
        return outputValue;
    }

    double getInputSum(){
        double sum = 0;
        for(Axon axon : this.getArriving()){
            sum += axon.getWeightValue() * axon.getSource().getOutputValue();
        }
        return sum;
    }

    double getErrorSum(){
        double sum = 0;
        for(Axon axon : this.getLeaving()){
            sum += axon.getWeightValue() * axon.getDestination().error;
        }
        return sum;
    }

    static double transferenceFunction(double x){
        return 1/(1+exp(-1*x));
    }

    static double transferenceFunctionDerivative(double x){
        return x * (1 - x);
    }

    void clean() {
        this.error = null;
        this.outputValue = null;
    }
}
