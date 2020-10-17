package colors;
import StdDraw.StdDraw;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Colors {
    private Map<String, Color> colors;

    public Colors(){
        colors = new HashMap<>();
        colors.put("ADD", StdDraw.GREEN);
        colors.put("REMOVE", StdDraw.RED);
        colors.put("ITERATE", StdDraw.CYAN);
        colors.put("FILLED", StdDraw.WHITE);
        colors.put("EMPTY", StdDraw.GRAY);
    }

    public Color getColor(String color){
        return colors.get(color);
    }


}
