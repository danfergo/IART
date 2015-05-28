package util;

/**
 * Created by danfergo on 05-05-2015.
 */
public class Arrays {

   public static int  max(int[] array){
        int max = array[0];
        for(int i = 1 ; i < array.length; i++){
            if (array[i] > max)
                max = array[i];
            }
        return max;
   }

}

