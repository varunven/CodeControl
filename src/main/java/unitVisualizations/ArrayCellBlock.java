package unitVisualizations;

public class ArrayCellBlock <T> {

    //fixed width, height proportions- adjust with array size
    public static final int CELL_WIDTH = 2;
    public static final int CELL_HEIGHT = 2;
    //data text- adjust with cell width and height
    public static final int TEXT_SIZE = 1;
    //currently selected- boolean value with color representation. different color for different operation
    public static final String ADD = "#008000";
    public static final String REMOVE = "#FF0000";
    //different markers for null vs filled value
    public static final String FILLED = "white";
    public static final String EMPTY = "gray";

    private int width;
    private int height;
    private int textSize;
    private int capacity;
    private String cellColor;
    private T data;

    public ArrayCellBlock(int capacity, T data){
        this.capacity = capacity;
        width = CELL_WIDTH;
        height = CELL_HEIGHT;
        textSize = TEXT_SIZE;
        cellColor = EMPTY;
        this.data = data;
    }

    public <T> void addToBlock(T data){

    }

    public void updateSize(int newCapacity){
        capacity = newCapacity;
        //update width, height, textsize
        //should zoom be a physical zoom or just change width height based on how many times they moved their hands

    }

}
