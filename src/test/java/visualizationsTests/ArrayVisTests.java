package visualizationsTests;

import StdDraw.StdDraw;
import org.junit.Test;
import visualizations.ArrayVis;

import static org.junit.Assert.assertEquals;

public class ArrayVisTests{

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

    //constructor and add tests
    @Test
    public void emptyArrTest(){
        ArrayVis emptyVis = new ArrayVis(emptyArr, 0.1, 1);
        Integer[] newEmpty = new Integer[10];
        newEmpty[0] = 7;
        ArrayVis newVis = new ArrayVis(newEmpty, 0.1, 1);
        emptyVis.add(7, 0);
        System.out.println(emptyVis.equals(newVis));
        assertEquals(emptyVis, newVis);
    }

    @Test
    public void longArrTest(){
        ArrayVis longVis = new ArrayVis(longArr, 0.1, 1);
        longVis.draw();
        StdDraw.show();
    }
    @Test
    public void multipleArrTypesTest(){
        ArrayVis intVis = new ArrayVis(intArr, 0.1, 1);
        ArrayVis stringVis = new ArrayVis(stringArr, 0.1, 1);
        intVis.draw();
        intVis.draw();
        StdDraw.show();
    }

    @Test
    public void alternatingValuesTest(){
        ArrayVis alternativeValuesVis = new ArrayVis(alternatingValuesArray, 0.1, 1);
        alternativeValuesVis.draw();
        StdDraw.show();
    }

    @Test
    public void addOutOfBounds(){
        ArrayVis longVis = new ArrayVis(longArr, 0.1, 1);
        longVis.add("data", longArr.length+1);
        StdDraw.show();
    }
    public void addAlreadyTaken(){
        ArrayVis semiFullVis = new ArrayVis(semiFullArr, 0.1, 1);
        semiFullVis.add("data", 4);
        StdDraw.show();
    }
    @Test
    public void SemiFullAdd(){
        ArrayVis semiFullVis = new ArrayVis(semiFullArr, 0.1, 1);
        semiFullVis.add("data", 8);
        StdDraw.show();
    }
}