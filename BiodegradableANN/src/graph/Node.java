package graph;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * Created by danfergo on 03-05-2015.
 */
public class Node<E extends Edge> {
    private ArrayList<E> edgesLeaving = new ArrayList<E>();
    private ArrayList<E> edgesArriving = new ArrayList<E>();

    public Node(){

    }



    public void connectTo(Node<E> destination){
        try {
            Class classEdge = ((Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            E edge = (E)classEdge.getDeclaredConstructors()[0].newInstance(this,destination);
            this.getLeaving().add(edge);
            destination.getArriving().add(edge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<E> getLeaving(){
        return  edgesLeaving;
    }


    public ArrayList<E> getArriving(){
        return  edgesArriving;
    }

}
