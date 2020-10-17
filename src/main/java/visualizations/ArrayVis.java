package visualizations;

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

    public void resize(){

    }

    public void add(T element, int index){
        array[index] = element;
        size++;
        if(needsResize(capacity, size).equals(Resize.SMALL)){
            resize();
        }
    }
    public void Remove(T element, int index){
        array[index] = element;
        size++;
        if(needsResize(capacity, size).equals(Resize.SMALL)){
            resize();
        }
    }

    //shows if resize big, resize small, or don't resize
    private enum Resize{
        LARGE,
        SMALL,
        NO
    }
}
