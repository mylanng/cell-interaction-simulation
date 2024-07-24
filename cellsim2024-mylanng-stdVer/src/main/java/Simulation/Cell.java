package Simulation;


import java.util.ArrayList;

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

    public Cell (int strength, int x, int y, int id){
        if (strength > 0){
            this.strength = strength;
        }
        else {
            this.strength = 0;
        }

        if (x > 0){
            this.x = x;
        }
        else {
            this.x = 0;
        }

        if (y > 0){
            this.y = y;
        }
        else {
            this.y = 0;
        }

        if (id > 0){
            this.id = id;
        }
        else {
            this.id = 0;
        }
    }

    //Class constructor for Cell class
    public Cell (){
        this (0,0,0,0); // Initial values
    }

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

    public int changeID (int id){
        this.id = id;
        return this.id;
    }

    public int changeStrength (int strength){
        this.strength = strength;
        return this.strength;
    }

    //private HashMap <String, Double> ChemConcentrations;
    //private HashSet <String> signal;
    public ArrayList<Integer> adjacentCell (int xcoords, int ycoords){
        ArrayList<Integer> NeighborCells = new ArrayList<>();

        NeighborCells.add (indexFromCoord(xcoords+1,ycoords+1));
        NeighborCells.add (indexFromCoord(xcoords,ycoords+1));
        NeighborCells.add (indexFromCoord(xcoords-1,ycoords+1));
        NeighborCells.add (indexFromCoord(xcoords,ycoords-1));
        NeighborCells.add (indexFromCoord(xcoords+1,ycoords-1));
        NeighborCells.add (indexFromCoord(xcoords-1,ycoords-1));
        NeighborCells.add (indexFromCoord(xcoords+1,ycoords));
        NeighborCells.add (indexFromCoord(xcoords-1,ycoords));

        return NeighborCells;
    }

    public ArrayList <Cell> validIndices (ArrayList <Cell> CellList){
        ArrayList <Cell> neighbors = new ArrayList <> ();
        ArrayList <Integer> indices = adjacentCell(x,y) ;

        for (int i:indices){
            if (i>0){
                neighbors.add(CellList.get(i));
            }
        }
        return neighbors;
    }

    public void interactNeighbors(ArrayList<Cell> cellList) {
    }


}
