package Simulation;


import java.util.ArrayList;
import java.util.Random;

import static Util.Calculator.indexFromCoord;

/**
 * The default, boring cell.
 */

public class Cell {
    /**
     * the simulation expects a getter that return the ID that is specifically called getID()
     * any other method call will break it. So if you name this "getCellID()" or "getId()" it won't work
     * This is why interfaces are useful
     * @return
     */

    private int x;
    private int y;
    private int strength;
    private int id;

    public Cell (){
        // Initial values
        strength = 0;
        x = 0;
        y = 0;
        id = 0;
    }

    // Setters
    public Cell (int strength, int x, int y, int id){
        setStrength(strength);
        setX(x);
        setY(y);
        setID(id);
    }

    public void setStrength(int strength) {
        if (strength > 0) {
            this.strength = strength;
        } else {
            this.strength = 0;
        }

    }

    public void setX(int x) {
        if (x > 0) {
            this.x = x;
        } else {
            this.x = 0;
        }

    }

    public void setY(int y) {
        if (y > 0) {
            this.y = y;
        } else {
            this.y = 0;
        }

    }

    public void setID(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            this.id = 0;
        }

    }

    //Class constructor for Cell class
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getStrength(){
        return this.strength;
    }

    public int getID() {
        return this.id;
    }

    public ArrayList<Integer> NeighborIndices(int xcoords, int ycoords){
        ArrayList<Integer> IndicesList = new ArrayList<>();
        int[][] relativePosition = {
                {-1, 1} , {0, 1} , {1, 1},
                {-1, 0} ,          {1, 0},
                {-1, -1}, {0, -1}, {1, -1},
        };

        for (int[] relPos : relativePosition) {
            if ((xcoords+relPos[0] >=0 && xcoords+relPos[0] < 100) && (ycoords+relPos[1] >= 0 && ycoords+relPos[1] < 100)) {
                IndicesList.add(indexFromCoord(xcoords+relPos[0], ycoords+relPos[1]));
            }
        }
        return IndicesList;
    }

    public ArrayList <Cell> validCell(ArrayList <Cell> CellList, int x, int y){
        ArrayList <Cell> neighborCells = new ArrayList <> ();
        ArrayList <Integer> indices = NeighborIndices(x,y) ;

        for (int i:indices){
            if (i>=0){
                neighborCells.add(CellList.get(i));
            }
        }
        return neighborCells;
    }

    public ArrayList<Integer> addCellID (ArrayList <Cell> CellList, int id){
        ArrayList<Integer> IDcells = new ArrayList<>();

        for (Cell c:CellList){
            if (c.getID() == id){
                int index = indexFromCoord(c.getX(), c.getY());
                IDcells.add (index);
            }
        }
        return IDcells;
    }

    public void interactNeighbors(ArrayList<Cell> neighbors) {
    }

    public int getRandomCellIndex(ArrayList<Integer> cellList){
        Random randInt = new Random();
        int index = randInt.nextInt(cellList.size());
        return cellList.get(index);
    }
}
