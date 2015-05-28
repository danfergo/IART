package graph;

import ann.Neuron;

/**
 * Created by danfergo on 03-05-2015.
 */
public class Edge<N extends Node> {
    private N source, destination;


    public Edge(N source, N destination){
        this.source = source;
        this.destination = destination;
    }



    public N getSource(){
        return source;
    }

    public N getDestination(){
        return destination;
    }


}
