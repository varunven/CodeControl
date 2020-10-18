package UniversalController;
import StdDraw.StdDraw;
import visualizations.ArrayVis;

public class controller {
    static Integer[] intArr = {1, 2, 3};
    static String[] stringArr = {"1", "234", "56789"};
    static Integer[] longArr = {1, 2, 3, 4, 5, 6, 7};
    static Integer[] semiFullArr = semiFullArray();
    private static Integer[] semiFullArray(){
        Integer[] arr = new Integer[10];
        for(int i=0; i<6; i++){
            if(i%2==0){
                arr[i] =i;
            }
        }
        return arr;
    }
    static Integer[] alternatingValuesArray = setAlternatingArray();
    static Integer[] emptyArr = new Integer[10];

    static private Integer[] setAlternatingArray(){
        Integer[] arr = new Integer[6];
        for(int i=0; i<6; i++){
            if(i%2==0){
                arr[i] =i;
            }
        }
        return arr;
    }

    //construction and drawing tests

    public static void longArrTest(){
        ArrayVis longVis = new ArrayVis(longArr, 0.1, 5);
        StdDraw.pause(1000);
        longVis.remove(0);
        StdDraw.pause(1000);
        longVis.add(5, 0);
        StdDraw.show();
    }

    public static void emptyArrTest(){
        ArrayVis emptyVis = new ArrayVis(emptyArr, 0.1, 1);
        Integer[] newEmpty = new Integer[10];
        newEmpty[0] = 7;
        ArrayVis newVis = new ArrayVis(newEmpty, 0.1, 1);
        emptyVis.add(7, 0);
        System.out.println(emptyVis.equals(newVis));
    }

    public static void main(String args[]){
        StdDraw.disableDoubleBuffering();
        longArrTest();
        emptyArrTest();
    }
}
