package UniversalController;
import StdDraw.StdDraw;
import visualizations.ArrayVis;

public class controller {

    static Integer[] longArr = new Integer[4];

    public static void longArrTest(){
        ArrayVis longVis = new ArrayVis(longArr, 0.1, 5);
        longVis.remove(0);
        longVis.add(5, 0);
        longVis.clearDataStructure();
        StdDraw.show();
    }

    public static void main(String args[]){
        StdDraw.disableDoubleBuffering();
        longArrTest();
    }
}
