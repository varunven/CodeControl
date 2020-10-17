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
        this.blockList = new ArrayList<>();
        for(int i = 0;i<array.length;i++){
            blockList.add(new ArrayCellBlock<>());
        }
        this.colors = new Colors();
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
        ArrayCellBlock toAdd = new ArrayCellBlock();
        blockList.add(index, toAdd);
        blockList.get(index).setColor(colors.getColor("ADD"));
        //draw the block
        size++;
        if(needsResize(capacity, size).equals(Resize.SMALL)){
            resize();
        }
    }

    public T remove(int index){
        T oldData = array[index];
        array[index] = null;
        blockList.get(index).setColor(colors.getColor("REMOVE"));
        blockList.set(index, null);
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


}
