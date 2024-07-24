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

    public void interactNeighbors (ArrayList<Cell> cellList) {
        int x = getX();
        int y = getY();

        ArrayList <Cell> immuneList = validIndices (cellList);

        for (Cell c:immuneList){
            if (c.getID()==3 ){
                int index = indexFromCoord(c.getX(), c.getY());
                cellList.set(index, new DeadCell(coordFromIndex(index)));
            }
        }

    }
}