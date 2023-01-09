package agh.ics.ooproject;

import java.util.List;

abstract public class AbstractPlantGenerator {
    GameMap map;
    public void initialize(){
        addGrass(this.map.noPlants);
    }
    public void Pareto(int n, int noFreeCells, List<Cell> preferableCells, List<Cell> notPreferableCells){
        int randomP;
        int randomN;
        for (int i = 0; i < Math.min(n, noFreeCells); i++){
            Cell target;
            if (Math.random() < 0.8){
                if (preferableCells.size() > 0) {
                    randomP = (int) Math.floor(Math.random()*preferableCells.size());
                    target = preferableCells.get(randomP);
                    preferableCells.remove(target);
                }else{
                    randomN = (int) Math.floor(Math.random()*notPreferableCells.size());
                    target = notPreferableCells.get(randomN);
                    notPreferableCells.remove(target);
                }
                target.addElement(new ElementGrass(target.position, this.map.plantEnergy));

            }else{
                if (notPreferableCells.size() > 0) {
                    randomN = (int) Math.floor(Math.random()*notPreferableCells.size());
                    target = notPreferableCells.get(randomN);
                    notPreferableCells.remove(target);
                }else{
                    randomP = (int) Math.floor(Math.random()*preferableCells.size());
                    target = preferableCells.get(randomP);
                    preferableCells.remove(target);
                }
                target.addElement(new ElementGrass(target.position, this.map.plantEnergy));
            }
        }
    }

    abstract void addGrass(int n);

}
