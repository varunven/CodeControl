package colors;

import java.util.HashMap;
import java.util.Map;

public class Colors {
    private Map<String, String> colors;

    public Colors(){
        colors = new HashMap<>();
        colors.put("ADD", "#008000");
        colors.put("REMOVE", "#FF0000");
        colors.put("ITERATE", "#87CEEB");
        colors.put("FILLED", "#FFFFFF");
        colors.put("EMPTY", "#808080");
    }

    public String getColor(String color){
        return colors.get(color);
    }

}
