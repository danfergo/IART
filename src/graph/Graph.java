package graph;

import java.util.ArrayList;

/**
 * Created by danfergo on 03-05-2015.
 */
public class Graph<N extends Node,E extends Edge> {
    private ArrayList<N> startNodes = new ArrayList<N>();
    private ArrayList<N> endNodes = new ArrayList<N>();
    private ArrayList<N> nodes = new ArrayList<N>();


    public void addNode(N node){
        if(nodes.indexOf(node) == -1) {
            nodes.add(node);
        }
    }

    public void addEndNode(N node){
        if(nodes.indexOf(node) == -1) {
            endNodes.add(node);
            nodes.add(node);
        }
    }

    public void addStartNode(N node){
        if(nodes.indexOf(node) == -1) {
            startNodes.add(node);
            nodes.add(node);
        }
    }


    public ArrayList<N> getStartNodes(){
        return startNodes;
    }

    public ArrayList<N> getEndNodes(){
        return endNodes;
    }

    public ArrayList<N> getNodes(){
        return nodes;
    }
}
