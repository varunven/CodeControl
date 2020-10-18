package unitVisualizations;

import colors.Colors;
import java.awt.*;

public class ArrayCellBlock <T> {

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