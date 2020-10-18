package visualizations;

import StdDraw.StdDraw;
import colors.Colors;
import unitVisualizations.ArrayCellBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArrayVis<T> {

    private T[] array;
    private List<ArrayCellBlock> blockList;
    private int capacity;
    private double h;
    private int rank;

    public ArrayVis(T[] array, double height, int rank){
        this.array = array;
        this.capacity = array.length;
        this.blockList = new ArrayList<>();
        for(int i = 0;i<capacity;i++){
            blockList.add(new ArrayCellBlock<>());
            blockList.get(i).setColor("ADD");
        }
        this.h = height;
        this.rank = rank;
        this.draw();
    }

    public void add(T data, int index){
        array[index] = data;
        ArrayCellBlock toAdd = new ArrayCellBlock();
        blockList.add(index, toAdd);
        blockList.get(index).setColor("ADD");
        this.changeCellColor(index);
        blockList.get(index).setColor("FILLED");
        this.changeCellColor(index);
    }

    public T remove(int index){
        T oldData = array[index];
        array[index] = null;
        blockList.get(index).setColor("REMOVE");
        this.changeCellColor(index);
        blockList.get(index).setColor("EMPTY");
        this.changeCellColor(index);
        return oldData;
    }

    public T getElement(int index){
        return array[index];
    }

    public ArrayCellBlock getBlock(int index){
        return blockList.get(index);
    }

    public T replace(T data, int index){
        T oldData = remove(index);
        add(data, index);
        return oldData;
    }

    public void draw(){
        double indexW = 1.0/(capacity * 1.0);
        for(int i=0; i<capacity; i++){
            drawHelper(i, indexW);
            if(i>0){
                undoPrev(i-1, indexW);
            }
        }
        undoPrev(array.length-1, indexW);
    }

    private void changeCellColor(int i){
        double indexW = 1.0/(capacity * 1.0);
        drawHelper(i, indexW);
    }

    private void drawHelper(int i, double indexW){
        StdDraw.setFont(new Font("Sans Serif", Font.PLAIN, 16*10/capacity));
        double xStart = i*indexW;
        double xEnd = xStart+indexW;
        double[] xRay = {xStart, xEnd, xEnd, xStart};
        double yTop = 1 - (h*rank);
        double yBottom = (yTop - h);
        double[] yRay = {yTop, yTop, yBottom, yBottom};
        StdDraw.setPenColor(blockList.get(i).getCellColor());
        StdDraw.filledPolygon(xRay, yRay);
        StdDraw.setPenColor();
        StdDraw.setPenRadius(0.003);
        StdDraw.polygon(xRay, yRay);
        StdDraw.text((xStart+xEnd)/2, (yTop+yBottom)/2, ""+array[i]);
        StdDraw.pause(500);
    }

    private void undoPrev(int i, double indexW){
        StdDraw.setFont(new Font("Sans Serif", Font.PLAIN, 16*10/capacity));
        double xStart = i*indexW;
        double xEnd = xStart+indexW;
        double[] xRay = {xStart, xEnd, xEnd, xStart};
        double yTop = 1 - (h*rank);
        double yBottom = (yTop - h);
        double[] yRay = {yTop, yTop, yBottom, yBottom};
        blockList.get(i).setColor("FILLED");
        StdDraw.setPenColor(blockList.get(i).getCellColor());
        StdDraw.filledPolygon(xRay, yRay);
        StdDraw.setPenColor();
        StdDraw.setPenRadius(0.003);
        StdDraw.polygon(xRay, yRay);
        StdDraw.text((xStart+xEnd)/2, (yTop+yBottom)/2, ""+array[i]);
        StdDraw.pause(500);
    }
}
