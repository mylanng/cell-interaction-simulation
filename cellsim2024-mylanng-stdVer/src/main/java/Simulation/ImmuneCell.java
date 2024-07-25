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

    public void interactNeighbors (ArrayList<Cell> neighbors) {
        int x = this.getX();
        int y = this.getY();

        ArrayList <Cell> immuneList = validCell(neighbors, x, y);
        ArrayList<Integer> CancerCellIndices = addCellID (immuneList, 3);

        if (CancerCellIndices.size() > 0){
            int replace = getRandomCellIndex(CancerCellIndices);
            neighbors.set (replace, new DeadCell(coordFromIndex(replace)));
        }
    }
}