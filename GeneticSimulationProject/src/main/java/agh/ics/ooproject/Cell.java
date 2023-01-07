package agh.ics.ooproject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cell {
    List<ElementAnimal> animals = new ArrayList<>();
    ElementGrass grass;
    GameMap map;
    int deaths;
    int births;
    boolean hasGrass;
    Position position;
    public Cell(GameMap map, Position position){
        this.map = map;
        this.position = position;
    }
    public void addElement(AbstractElement element){
        if (element instanceof ElementGrass){
            this.hasGrass = true;
            this.grass = (ElementGrass) element;
        }
        if (element instanceof ElementAnimal){
            this.animals.add((ElementAnimal) element);
        }

    }
    public void removeElement(AbstractElement element){
        if (element instanceof ElementGrass){
            grass.alive = false;
            this.hasGrass = false;
        }
        if (element instanceof ElementAnimal){
            this.animals.remove((ElementAnimal) element);
        }
    }
    public void moveElementTo(AbstractElement element, Position position){
        this.map.getCellAt(position).addElement(element);
        this.removeElement(element);
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
            grass.alive = false;
            this.hasGrass = false;
        }
    }
    public void procreate(){
        if (this.animals.size() > 2){
            Comparator<ElementAnimal> win = Comparator.comparingInt((ElementAnimal x) -> -x.energy).
                    thenComparingInt((ElementAnimal x) -> x.birthdate).
                    thenComparingInt((ElementAnimal x) -> x.noChildren);
            this.animals.sort(win);
            int i = 0;
            while (animals.get(i).energy >= map.sufficientEnergy){
                i++;
            }
            i--;
            i = i/2;
            ElementAnimal newAnimal;
            for (int j = 0; j < i; j++) {
                newAnimal = animals.get(i).procreateWith(animals.get(i+1));
                animals.add(newAnimal);
                this.map.animals.add(newAnimal);
            }
        }
    }

}
