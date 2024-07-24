package Simulation;


import Util.Pair;

import java.util.ArrayList;

import static Util.Calculator.coordFromIndex;
import static Util.Calculator.indexFromCoord;

/**
 * A tissue cell. It wants to grow, but not as much as cancer. Has a chance to turn a dead
 * cell into a live one every time step
 */

public class TissueCell extends Cell{

    /**
     * The logic object expects a constructor that takes a coordinate stored as a pair
     * See the Util folder and Pair.java to learn about the implementation of this
     * @param coords
     */

    public TissueCell (Pair coords){
        super (0, coords.getX(), coords.getY(), 1);
    }

    @Override
    public void interactNeighbors (ArrayList<Cell> cellList) {
        int x = this.getX();
        int y = this.getY();
        int id = this.getID();

        ArrayList <Cell> tissueList = validIndices (cellList);

        for (Cell c:tissueList){
            if (c.getID() == 0 ){
                int index = indexFromCoord(c.getX(), c.getY());
                cellList.set(index, new TissueCell(coordFromIndex(index)));
            }
        }
    }
}
