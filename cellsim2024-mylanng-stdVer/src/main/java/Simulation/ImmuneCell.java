package Simulation;

import Util.Pair;

import java.util.ArrayList;
import static Util.Calculator.indexFromCoord;
import static Util.Calculator.coordFromIndex;

/**
 * The immune cell! It kills cancer, and has a chance to attack multiple cancer cells per turn!
 */

public class ImmuneCell extends Cell{
     /**
     * The logic object expects a constructor that takes a coordinate stored as a pair
     * See the Util folder and Pair.java to learn about the implementation of this
     * @param coords
     */
    public ImmuneCell(Pair coords){
        super (3, coords.getX(), coords.getY(), 4);
    }

    public void interactNeighbors (ArrayList<Cell> eachcell) {
        int x = getX();
        int y = getY();
        int id_cell = getID();

        ArrayList<Integer> indices = adjacentCell(x,y);
        ArrayList <Cell> neighbor_cells = new ArrayList<>();
        for (int i:indices){
            if (i>0){
                neighbor_cells.add(eachcell.get(i));
            }
        }

        for (Cell c:neighbor_cells){
            if (c.getID()==3 ){
                int index = indexFromCoord(c.getX(), c.getY());
                eachcell.set(index, new DeadCell(coordFromIndex(index)));
            }
        }

    }
}