package unitVisualizations;

import colors.Colors;

import java.awt.*;

public class ArrayCellBlock <T> {

    //fixed width, height proportions- adjust with array size
    public static final int CELL_WIDTH = 2;
    public static final int CELL_HEIGHT = 2;
    //data text- adjust with cell width and height
    public static final int TEXT_SIZE = 1;

    private Color cellColor;
    private T data;
    private Colors colors;

    public ArrayCellBlock(){
        this.colors = new Colors();
        this.cellColor = colors.getColor("EMPTY");
    }

    public void setColor(String colorName){
        this.cellColor = colors.getColor(colorName);

    }

    public Color getCellColor(){
        return this.cellColor;
    }

}