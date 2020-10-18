package visualizations;

import StdDraw.StdDraw;
import colors.Colors;
import unitVisualizations.ArrayCellBlock;

import java.util.ArrayList;
import java.util.List;

public class ArrayVis<T> {

    private T[] array;
    private List<ArrayCellBlock> blockList;
    private int size;
    private int capacity;
    private Colors colors;

    public ArrayVis(T[] array){
        this.array = array;
        this.capacity = array.length;
        this.blockList = new ArrayList<>();
        for(int i = 0;i<capacity;i++){
            blockList.add(new ArrayCellBlock<>());
            blockList.get(i).setColor("ADD");
        }
        this.colors = new Colors();
    }

    public void stepInto() {
        /*
        add
        remove
        resize
        get
        replace
        iterate through array (uses get?)
         */
    }

    public void add(T data, int index){
        array[index] = data;
        ArrayCellBlock toAdd = new ArrayCellBlock();
        blockList.add(index, toAdd);
        blockList.get(index).setColor("ADD");
        //draw the block
        size++;
    }

    public T remove(int index){
        T oldData = array[index];
        array[index] = null;
        blockList.get(index).setColor("REMOVE");
        blockList.set(index, null);
        size--;
        return oldData;
    }

    public T getElement(int index){
        return array[index];
    }

    public ArrayCellBlock getBlock(int index){
        return blockList.get(index);
    }

    public T replace(T data, int index){
        T oldData = remove(index);
        add(data, index);
        return oldData;
    }

    public void draw(int rank, double h){
        double indexW = 1.0/(capacity * 1.0);
        for(int i=0; i<capacity; i++){
            double xStart = i*indexW;
            double xEnd = xStart+indexW;
            double[] xRay = {xStart, xEnd, xEnd, xStart};
            double yTop = 1 - (h*rank);
            double yBottom = (yTop - h);
            double[] yRay = {yTop, yTop, yBottom, yBottom};
            StdDraw.setPenColor(blockList.get(i).getCellColor());
            StdDraw.filledPolygon(xRay, yRay);
            StdDraw.setPenColor();
            StdDraw.setPenRadius(0.003);
            StdDraw.polygon(xRay, yRay);
            StdDraw.text((xStart+xEnd)/2, (yTop+yBottom)/2, ""+array[i]);
        }
    }
}
