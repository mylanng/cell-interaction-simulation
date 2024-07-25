package Simulation;


import Util.Pair;
import java.util.ArrayList;
import static Util.Calculator.coordFromIndex;


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
    public void interactNeighbors (ArrayList<Cell> neighbors) {
        int x = this.getX();
        int y = this.getY();

        ArrayList <Cell> cancerListNeighbors = validCell(neighbors, x, y);

        ArrayList <Integer> dead_indices = addCellID (cancerListNeighbors, 0);
        ArrayList <Integer> immune_indices = addCellID (cancerListNeighbors, 4);
        ArrayList <Integer> tissue_indices = addCellID (cancerListNeighbors, 1);

        if (dead_indices.size() > 0){
            // Cancer cell grows immediately on a DeadCell.
            neighbors.set (dead_indices.get(0), new CancerCell(coordFromIndex(dead_indices.get(0))));
        }

        else if (tissue_indices.size() > immune_indices.size() && tissue_indices.size() > 0){
            int replace2 = getRandomCellIndex(tissue_indices);
            neighbors.set (replace2, new DeadCell(coordFromIndex(replace2)));
        }

        else if (immune_indices.size() > 0){
            int replace3 = getRandomCellIndex(immune_indices);

            if(neighbors.get(replace3).getStrength() == 0){
                neighbors.set (replace3, new DeadCell(coordFromIndex(replace3)));
            }
            else{
                int currStrength = neighbors.get(replace3).getStrength();
                neighbors.get(replace3).setStrength(currStrength - 1);
            }
        }
    }
}
