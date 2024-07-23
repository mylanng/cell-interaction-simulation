package Simulation;


import Util.Pair;

import java.util.ArrayList;

/**
 * This cell is dead and does nothing
 */
public class DeadCell extends Cell{

    /**
     * The logic object expects a constructor that takes a coordinate stored as a pair
     * See the Util folder and Pair.java to learn about the implementation of this
     * @param coords
     */

    private int x;
    private int y;

    public DeadCell(Pair coords){
        super (0, coords.getX(), coords.getY(), 0);
    }

}
