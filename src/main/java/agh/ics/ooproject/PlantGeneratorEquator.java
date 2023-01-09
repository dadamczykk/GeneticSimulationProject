package agh.ics.ooproject;

import java.util.ArrayList;
import java.util.List;

public class PlantGeneratorEquator extends AbstractPlantGenerator{
    int[] equator;
    int noEq;
    public PlantGeneratorEquator(GameMap map){
        this.map = map;
        this.equator = new int[2];
        this.noEq = (int) (Math.floor((double) this.map.height / 5) +
                (Math.floor((double) this.map.height/5)%2)%2*((this.map.height%2)+1)%2 +
                (Math.floor((double) this.map.height/5)%2+1)%2*((this.map.height%2))%2);
        equator[0] = (this.map.height - noEq)/2;
        equator[1] = equator[0] + noEq;
    }


    public void addGrass(int n) {
        List<Cell> preferableCells = new ArrayList<>();
        List<Cell> notPreferableCells = new ArrayList<>();
        for (int i = 0; i < this.map.height; i++) {
            for (int j = 0; j < this.map.width; j++) {
                if (!this.map.grid[i][j].hasGrass){
                    if (i < equator[1] && i >= equator[0]){
                        preferableCells.add(this.map.grid[i][j]);
                    }else{
                        notPreferableCells.add(this.map.grid[i][j]);
                    }
                }
            }
        }
        int noFreeCells = notPreferableCells.size() + preferableCells.size();
        Pareto(n, noFreeCells, preferableCells, notPreferableCells);
    }

}
