package Simulation;


import Util.Pair;

import java.util.ArrayList;
import java.util.Random;
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
    public void interactNeighbors (ArrayList<Cell> cellList) {
        int x = this.getX();
        int y = this.getY();
        int id = this.getID();


        ArrayList <Integer> dead_indices = new ArrayList<>();
        ArrayList <Integer> immune_indices = new ArrayList<>();
        ArrayList <Integer> tissue_indices = new ArrayList<>();

        ArrayList <Cell> dead_cell = new ArrayList<>();
        ArrayList <Cell> immune_cell = new ArrayList<>();
        ArrayList <Cell> tissue_cell = new ArrayList<>();

        ArrayList <Cell> cancerList = validIndices (cellList);

        for (Cell c :cancerList){
            if (c.getID() == 0 && c.getStrength() == 0){
                dead_indices.add(indexFromCoord(c.getX(), c.getY()));
                dead_cell.add(c);
            }
            else if (c.getID() == 4 && c.getStrength() == 3){
                immune_indices.add(indexFromCoord(c.getX(), c.getY()));
                immune_cell.add(c);
            }
            else if (c.getID() == 1 && c.getStrength() == 0) {
                tissue_indices.add(indexFromCoord(c.getX(), c.getY()));
                tissue_cell.add(c);
            }
            else {}

        }

        if (dead_cell.size() >0){
            Random rand_dead = new Random();
            Cell newCell = dead_cell.get (rand_dead.nextInt (dead_cell.size()));
            newCell.changeID(3);
            newCell.changeStrength(1);
        }

        if (tissue_cell.size() > immune_cell.size()){
            Random rand_tissue = new Random();
            Cell newTissue = tissue_cell.get (rand_tissue.nextInt (tissue_cell.size()));
            newTissue.changeID(0);
            newTissue.changeStrength(0);
        }

        if (immune_cell.size() > tissue_cell.size()){
            Random rand_immune = new Random();
            Cell newattack = immune_cell.get (rand_immune.nextInt (immune_cell.size()));
            int attackornot = rand_immune.nextInt (2);
            if (attackornot == 1){
                newattack.changeID(0);
                newattack.changeStrength(0);
            }
        }
    }
}
