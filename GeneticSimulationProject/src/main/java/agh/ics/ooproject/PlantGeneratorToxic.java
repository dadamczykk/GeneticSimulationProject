package agh.ics.ooproject;

import java.util.*;

public class PlantGeneratorToxic extends AbstractPlantGenerator{

    public PlantGeneratorToxic(GameMap map){
        this.map = map;
    }
    public void addGrass(int n){
        Cell[] cells = new Cell[this.map.height*this.map.width];
        for (int i = 0; i < this.map.height; i++){
            for (int j = 0; j < this.map.width; j++){
                cells[i*this.map.width + j] = this.map.grid[i][j];
            }
        }
        Comparator<Cell> byDeaths = Comparator.comparingInt((Cell x) -> x.deaths).thenComparingInt((Cell x) -> (int) (Math.random() * 100));
        Arrays.sort(cells, byDeaths);
        List<Cell> preferableCells = new ArrayList<>();
        List<Cell> notPreferableCells = new ArrayList<>();
        for (int i = 0; i < this.map.height*this.map.width; i++){
            if (!cells[i].hasGrass){
                if (preferableCells.size() < n){
                    preferableCells.add(cells[i]);
                }else{
                    notPreferableCells.add(cells[i]);
                }
            }
        }
        int noFreeCells = notPreferableCells.size() + preferableCells.size();
        Pareto(n, noFreeCells, preferableCells, notPreferableCells);
    }

}
