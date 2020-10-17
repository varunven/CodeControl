package visualizations;

import StdDraw.StdDraw;

public class ArrayVis<T> {


    private T[] array;
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

    public void add(T element, int index){
        array[index] = element;
        size++;
        if(needsResize(capacity, size).equals(Resize.SMALL)){
            resize();
        }
    }
    public T remove(int index){
        T oldEl = array[index];
        array[index] = null;
        size++;
        if(needsResize(capacity, size).equals(Resize.SMALL)){
            resize();
        }
        return oldEl;
    }

    public T get(int index){
        return array[index];
    }

    public T replace(T element, int index){
        T oldEl = array[index];
        array[index] = element;
        return oldEl;
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
