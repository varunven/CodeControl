package unitVisualizations;

import colors.Colors;

public class ArrayCellBlock <T> {

    //fixed width, height proportions- adjust with array size
    public static final int CELL_WIDTH = 2;
    public static final int CELL_HEIGHT = 2;
    //data text- adjust with cell width and height
    public static final int TEXT_SIZE = 1;
    //currently selected- boolean value with color representation. different color for different operation
    //different markers for null vs filled value

    private int width;
    private int height;
    private int textSize;
    private String cellColor;
    private T data;
    private Colors colors;

    public ArrayCellBlock(int capacity, int size, T data){
        width = CELL_WIDTH;
        height = CELL_HEIGHT;
        textSize = TEXT_SIZE;
        this.data = data;
        this.colors = new Colors();
        this.cellColor = colors.getColor("EMPTY");
    }

    public String changeColor(String colorName){
        this.cellColor = colors.getColor(colorName);
        return cellColor;
    }

    public void addToBlock(T data){
        this.cellColor = colors.getColor("ADD");
        this.data = data;
    }

    public T removeFromBlock(){
        this.cellColor = colors.getColor("REMOVE");
        T temp = this.data;
        this.data = null;
        return temp;
    }

    public void updateCellParameters(int newCapacity){
        //update width, height, textsize based on capacity
        //should zoom be a physical zoom or just change width height based on how many times they moved their hands
    }
}