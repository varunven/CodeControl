package colors;

import java.util.HashMap;
import java.util.Map;

public class Colors {
    private Map<String, String> colors;

    public Colors(){
        colors = new HashMap<>();
        colors.put("Green", "#008000");
        colors.put("Red", "#FF0000");
        colors.put("White", "#FFFFFF");
        colors.put("Gray", "#808080");
        colors.put("Grey", "#808080");
    }

    public String getColor(String color){
        return colors.get(color);
    }

}
