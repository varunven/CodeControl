package UniversalController;
import StdDraw.StdDraw;
import visualizations.ArrayVis;

public class controller {
    public static void main(String args[]){
        Integer[] testArr = {1, 2, 3, 4, 5};
        ArrayVis av = new ArrayVis(testArr);
        av.draw(0, 0.1);
        StdDraw.show();
    }
}
