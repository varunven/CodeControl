package visualizationsTests;

import StdDraw.StdDraw;
import org.junit.Before;
import visualizations.ArrayVis;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import junit.framework.*;

import java.util.ArrayList;

public class ArrayVisTests<T> extends TestCase{

    Integer[] intArr = {1, 2, 3};
    String[] stringArr = {"1", "234", "56789"};
    Integer[] longArr = {1, 2, 3, 4, 5, 6, 7};
    Integer[] semiFullArr = semiFullArray();
    private Integer[] semiFullArray(){
        Integer[] arr = new Integer[10];
        for(int i=0; i<6; i++){
            if(i%2==0){
                arr[i] =i;
            }
        }
        return arr;
    }
    Integer[] alternatingValuesArray = setAlternatingArray();
    Integer[] emptyArr = new Integer[10];

    private Integer[] setAlternatingArray(){
        Integer[] arr = new Integer[6];
        for(int i=0; i<6; i++){
            if(i%2==0){
                arr[i] =i;
            }
        }
        return arr;
    }

    @Before
    public void setup(){
        //StdDraw.enableDoubleBuffering();
    }

    //construction and drawing tests

    @Test
    public void emptyArrTest(){
        ArrayVis emptyVis = new ArrayVis(emptyArr);
        emptyVis.draw(1, 1);
        StdDraw.show();
    }
    @Test
    public void longArrTest(){
        ArrayVis longVis = new ArrayVis(longArr);
        longVis.draw(1, 1);
        StdDraw.show();
    }
    @Test
    public void multipleArrTypesTest(){
        ArrayVis intVis = new ArrayVis(intArr);
        ArrayVis stringVis = new ArrayVis(stringArr);
        intVis.draw(1, .5);
        intVis.draw(2, .5);
        StdDraw.show();
    }
    @Test
    public void alternatingValuesTest(){
        ArrayVis alternativeValuesVis = new ArrayVis(alternatingValuesArray);
        alternativeValuesVis.draw(1, 1);
        StdDraw.show();
    }

    //adding and drawing tests
    @Test
    public void emptyAdd(){
        ArrayVis emptyVis = new ArrayVis(emptyArr);
        emptyVis.add("data", 0);
        StdDraw.show();
    }

    @Test
    public void addOutOfBounds(){
        ArrayVis longVis = new ArrayVis(longArr);
        longVis.add("data", longArr.length+1);
        StdDraw.show();
    }

    public void addAlreadyTaken(){
        ArrayVis semiFullVis = new ArrayVis(semiFullArr);
        semiFullVis.add("data", 4);
        StdDraw.show();
    }

    @Test
    public void SemiFullAdd(){
        ArrayVis semiFullVis = new ArrayVis(semiFullArr);
        semiFullVis.add("data", 8);
        StdDraw.show();
    }

}
