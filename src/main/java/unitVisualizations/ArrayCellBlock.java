package unitVisualizations;

import colors.Colors;

public class ArrayCellBlock <T> {

    //fixed width, height proportions- adjust with array size
    public static final int CELL_WIDTH = 2;
    public static final int CELL_HEIGHT = 2;
    //data text- adjust with cell width and height
    public static final int TEXT_SIZE = 1;

    private String cellColor;
    private T data;
    private Colors colors;

    public ArrayCellBlock(){
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

    public String getCellColor(){
        return this.cellColor;
    }

}