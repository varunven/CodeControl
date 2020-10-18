package visualizationsTests;

import visualizations.ArrayVis;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import junit.framework.*;

import java.util.ArrayList;

public class ArrayVisTests<T> extends TestCase{

    Integer[] intArr = {1, 2, 3};
    String[] stringArr = {"1", "234", "56789"};
    Integer[] emptyArr = {};
    Integer[] longArr = {1, 2, 3, 4, 5, 6, 7};
    Integer[] alternatingValuesArray = setAlternatingArray();

    private Integer[] setAlternatingArray(){
        Integer[] arr = new Integer[6];
        for(int i=0; i<6; i++){
            if(i%2==0){
                arr[i] =i;
            }
        }
        return arr;
    }

    private ArrayList<String> emptyArrList = new ArrayList();

    @Test
    public void multipleArrTypes(){
        ArrayVis vis = new ArrayVis(intArr);
    }

}
