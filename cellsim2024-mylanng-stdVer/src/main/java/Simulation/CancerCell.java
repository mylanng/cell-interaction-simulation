package Simulation;


import Util.Pair;

import java.util.ArrayList;
import java.util.Random;

import static Util.Calculator.coordFromIndex;
import static Util.Calculator.indexFromCoord;


/**
 *This is a cancer cell. It is the most complex cell as it can attack tissue or immune cells, or grow into a dead cell.
 * For attacking tissue, it is a 1 hit replace it with a dead cell.
 * Immune cells are cooler. Each hit from a cancer cell lowers its strength by 1. When an immune cell reaches 0 strength
 * it dies!
 *
 * It has a priority of action. If it can grow, it will grow. If it can kill a tissue cell, it will do that. Why?
 * Easiest way to grow is to kill a week tissue cell. If no other option, will attack immune cells. Path of
 * least resistance to growing basically.
 *
 * Growing means turning a dead cell into a CancerCell.
 */

public class CancerCell extends Cell{
    public CancerCell (Pair coords){
        super (1, coords.getX(), coords.getY(), 3);
    }

    @Override
    public void interactNeighbors (ArrayList<Cell> cellList) {
        int x = this.getX();
        int y = this.getY();

        ArrayList <Cell> cancerList = validIndices (cellList, x, y);

        ArrayList <Integer> dead_indices = addCellID (cancerList, 0);
        ArrayList <Integer> immune_indices = addCellID (cancerList, 4);
        ArrayList <Integer> tissue_indices = addCellID (cancerList, 1);

        if (dead_indices.size() > 0){
            int replace1 = getRandomCell(dead_indices);
            cellList.set (replace1, new CancerCell(coordFromIndex(replace1)));
        }

        else if (tissue_indices.size() > immune_indices.size() && tissue_indices.size() > 0){
            int replace2 = getRandomCell(tissue_indices);
            cellList.set (replace2, new DeadCell(coordFromIndex(replace2)));
        }

        else if (immune_indices.size() > 0){
            int replace3 = getRandomCell(immune_indices);
            cellList.set (replace3, new DeadCell(coordFromIndex(replace3)));

            if(cellList.get(replace3).getStrength() == 0){
                cellList.set (replace3, new DeadCell(coordFromIndex(replace3)));
            }
            else{
                int currentStr = cellList.get(replace3).getStrength();
                cellList.get(replace3).setStrength(currentStr - 1);
            }
        }
    }
}
