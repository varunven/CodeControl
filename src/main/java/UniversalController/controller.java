package UniversalController;
import StdDraw.StdDraw;
import visualizations.ArrayVis;

import java.io.*;
import java.util.*;
import java.util.List;

public class controller {

    private static String code;

//    private static Object[] makeForLoop(String line){
//        //replace in the line any ++ with +=1, any -- with -=1
//        line = line.replace("++", "+=1");
//        line = line.replace("--", "-=1");
//        //between equals and ; is first value
//        int equals = line.indexOf("=");
//        int semi = line.indexOf(";");
//        int initVal = Integer.parseInt(line.substring(equals+1, semi));
//        //between < or > and ; is second value
//        int greater = line.indexOf(">");
//        int less = line.indexOf("<");
//        int greaterorless = Math.max(greater, less);
//        int semi2 = line.indexOf(";", semi+1);
//        int initMax = Integer.parseInt(line.substring(greaterorless+1, semi2));
//        //between + or - INCLUSIVE and ) is third value
//        int plus = line.indexOf("+");
//        int minus = line.indexOf("-");
//        int plusorminus = Math.max(plus, minus);
//        int paren = line.indexOf(")");
//        int interval = Integer.parseInt(line.substring(plusorminus+1, paren));
//        Object[] arr = new Object[4];
//        if(plusorminus == minus){
//            arr[0] = initVal;
//            arr[1] = initMax;
//            arr[2] = -interval;
//            arr[3] = "for";
//        }
//        else{
//            arr[0] = initVal;
//            arr[1] = initMax;
//            arr[2] = interval;
//            arr[3] = "for";
//        }
//        return arr;
//    }

    public static List<Object> readFile(String name, String line){
        List<Object> arrays = new ArrayList<>();
        try {
            Object[] x = null;
            if (line != null) {
                //constructor for array DONE
                //loop of some kind
                //accessor of array DONE
                //variable declaration IGNORE
                line = line.replaceAll("\\s", "");
                if (line.contains("=")) {
                    //constructors of array
                    if(line.contains("[]") && !line.contains("{")){
                        int first = line.indexOf("[");
                        int second = line.indexOf("[", first + 1);
                        String arrayName = line.substring(first+2, line.indexOf("="));
                        String length = line.substring(second+1, line.length()-2);
                        x = new Object[Integer.parseInt(length)];
                        arrays.add(arrayName);
                        arrays.add(x);
                    }
                    else if(line.contains("{")){
                        int start = line.indexOf("{");
                        int end = line.indexOf("}");
                        int first = line.indexOf("[");
                        String arrayName = line.substring(first+2, line.indexOf("="));
                        x = line.substring(start+1, end).split(",");
                        arrays.add(arrayName);
                        arrays.add(x);
                    }
                    //accessors of data
                    else if(line.contains("[")){
                        int first = line.indexOf("[");
                        int second = line.indexOf("]");
                        String arrayName = line.substring(0, first);
                        String index = line.substring(first+1, second);
                        int equals = line.indexOf("=");
                        int semi = line.indexOf(";");
                        String element = line.substring(equals+1, semi);
                        Object[] values = {element, index};
                        arrays.add(arrayName);
                        arrays.add(values);
                    }
                }
            }
            return arrays;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) throws IOException {
        String testFile = "C:\\Users\\dswhi\\IdeaProjects\\CodeControl\\src\\main\\java\\UniversalController\\line.txt";
        StdDraw.disableDoubleBuffering();
        int rank=0;
        Map<Object, ArrayVis> vises = new HashMap<>();
        File file = new File(testFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        code = "";
        while((line = reader.readLine()) != null){
            code += line;
            List<Object> arrs = readFile(testFile, line); //arr name to values
            if(arrs.size()!=0) {
                if (!vises.containsKey(arrs.get(0))) {
                    Object[] array = (Object[]) arrs.get(1);
                    ArrayVis vis = new ArrayVis(array, 0.1, rank);
                    vises.put(arrs.get(0), vis);
                } else {
                    ArrayVis vis = vises.get(arrs.get(0));
                    Object[] arrs1 = (Object[]) arrs.get(1);
                    vis.replace(arrs1[0], Integer.parseInt((String)arrs1[1]));
                }
            }
            rank += 1;
        }
    }
}
