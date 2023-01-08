package agh.ics.ooproject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cell {
    public List<ElementAnimal> animals = new ArrayList<>();
    ElementGrass grass;
    GameMap map;
    int deaths;
    int births;
    public boolean hasGrass;
    Position position;
    public Cell(GameMap map, Position position){
        this.map = map;
        this.position = position;
    }
    public void addElement(AbstractElement element){
        if (element instanceof ElementGrass){
            this.hasGrass = true;
            this.grass = (ElementGrass) element;
        }else{
            this.animals.add((ElementAnimal) element);
        }
    }
    public void removeElement(AbstractElement element){
        if (element instanceof ElementGrass){
            grass.alive = false;
            this.hasGrass = false;
        }else{
            this.animals.remove((ElementAnimal) element);
        }
    }
    public void moveAnimalTo(ElementAnimal element, Position position){
        if (animals.contains(element)){
            this.removeElement(element);
            this.map.getCellAt(position).addElement(element);
            element.position = position;
        }
    }
    public void eatGrass(){
        if (this.hasGrass && this.animals.size() > 0){
            if (this.animals.size() > 1){
                Comparator<ElementAnimal> win = Comparator.comparingInt((ElementAnimal x) -> -x.energy).
                        thenComparingInt((ElementAnimal x) -> x.birthdate).
                        thenComparingInt((ElementAnimal x) -> x.noChildren);
                this.animals.sort(win);
            }
            this.animals.get(0).energy = this.animals.get(0).energy + grass.energy;
            this.animals.get(0).plantsEaten++;
            grass.alive = false;
            this.hasGrass = false;
        }
    }
    public void procreate(){
        if (this.animals.size() >= 2){
            Comparator<ElementAnimal> win = Comparator.comparingInt((ElementAnimal x) -> -x.energy).
                    thenComparingInt((ElementAnimal x) -> x.birthdate).
                    thenComparingInt((ElementAnimal x) -> x.noChildren);
            this.animals.sort(win);
            int i = 0;
            while ( i < animals.size() && animals.get(i).energy >= map.sufficientEnergy){
                i++;
            }
            i--;

            i = i/2;
            i++;
            ElementAnimal newAnimal;
            for (int j = 0; j < i; j++) {
                if (animals.get(j).alive && animals.get(j+1).alive){
                    newAnimal = animals.get(j).procreateWith(animals.get(j+1));
                    animals.add(newAnimal);
                    this.map.aliveAnimals.add(newAnimal);
                }
            }
        }
    }

}
