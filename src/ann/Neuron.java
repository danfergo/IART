package ann;

import graph.DrawableNode;

import java.awt.*;

import static java.lang.Math.exp;

/**
 * Created by danfergo on 20-04-2015.
 */
public class Neuron extends DrawableNode<Axon> {

    Double outputValue = null;
    Double gradient = null;
    public static Double step = 0.15;
    public Neuron(double x, double y){
       super(x,y);
    }

    void setOutputValue(double value){
        this.outputValue = value ;
    }

    void setExpectedValue(double expectedValue){
        this.gradient = transferenceFunctionDerivative(outputValue)*(expectedValue - outputValue);
    }

    void backPropagateError(){
        this.gradient = transferenceFunctionDerivative(outputValue == null ? 0 : outputValue) * getErrorSum();
    }

    double getGradient(){
        if(this.gradient == null){
            backPropagateError();
        }
        return this.gradient;
    }


    public void updateWeights(){
        double deltaWeight;
        for(Axon axon : getArriving()){
            deltaWeight = step*axon.getSource().getOutputValue()*getGradient()
                   /* + 0.5*axon.getDeltaWeight()*/;
            axon.updateWeight(deltaWeight);
            axon.setDeltaWeight(deltaWeight);
        }
    }

    public double getOutputValue() {
        if(outputValue == null){
            outputValue = transferenceFunction(getInputSum());
        }
        return outputValue;
    }

    double getInputSum(){
        double sum = 0.0f;
        for(Axon axon : getArriving()){
            sum += axon.getWeightValue() * axon.getSource().getOutputValue();
        }
        return sum;
    }

    double getErrorSum(){
        double sum = 0;
        for(Axon axon : getLeaving()){
            sum += axon.getWeightValue() * axon.getDestination().getGradient();
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
        this.gradient = null;
        this.outputValue = null;
    }

    public Color getColor(){
        Color sColor = super.getColor();
        try {
            return new Color(sColor.getRed(),sColor.getGreen(),sColor.getBlue(),(int)Math.round(outputValue*255));
        }catch(NullPointerException e){
            return new Color(sColor.getRed(),sColor.getGreen(),sColor.getBlue(),9);
        }
    }
}
