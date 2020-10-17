package visualizations;

public class ArrayVis<T> {

    private T[] array;

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

    //shows if resize big, resize small, or don't resize
    private enum Resize{
        LARGE,
        SMALL,
        NO
    }
}
