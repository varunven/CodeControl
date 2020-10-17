package visualizations;

import StdDraw.StdDraw;
import unitVisualizations.ArrayCellBlock;
import java.util.List;

public class ArrayVis<T> {

    private T[] array;
    private List<ArrayCellBlock> blockList;
    private int size;
    private int capacity;

    public ArrayVis(T[] array){
        this.array = array;
    }

    private Resize needsResize(int capacity, int size){
        if(size==capacity && capacity>10){
            return ArrayVis.Resize.LARGE;
        }
        if(size <= capacity/4 && capacity>10){
            return ArrayVis.Resize.SMALL;
        }
        return ArrayVis.Resize.NO;
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
        ArrayCellBlock toAdd = new ArrayCellBlock(capacity, size);
        toAdd.addToBlock(data);
        blockList.add(index, toAdd);
        //draw the block
        size++;
        if(needsResize(capacity, size).equals(Resize.SMALL)){
            resize();
        }
    }

    public T remove(int index){
        T oldData = array[index];
        array[index] = null;
        ArrayCellBlock toRemove = blockList.get(index);
        toRemove.removeFromBlock();
        blockList.remove(index);
        size--;
        if(needsResize(capacity, size).equals(Resize.SMALL)){
            resize();
        }
        return oldData;
    }

    public T getElement(int index){
        return array[index];
    }

    public ArrayCellBlock getBlock(int index){ return blockList.get(index); }

    public T replace(T data, int index){
        T oldData = remove(index);
        add(data, index);
        return oldData;
    }

    private void resize(){

    }

    //shows if resize big, resize small, or don't resize
    private enum Resize{
        LARGE,
        SMALL,
        NO
    }

    public void drawCanvas(double x, double y){
    }

}
