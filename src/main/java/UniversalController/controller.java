package UniversalController;
import StdDraw.StdDraw;
import visualizations.ArrayVis;

public class controller {
    public controller(){
        //StdDraw.setCanvasSize();




    }
    public static void main(String args[]){
        StdDraw.setXscale();
        StdDraw.setYscale(-0.05, 1.05);
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLACK);

    }
    public <T> void makeArray(T[] ary, double height){
        ArrayVis vis = new ArrayVis (ary);

    }
    public void debug(){

    }
}
