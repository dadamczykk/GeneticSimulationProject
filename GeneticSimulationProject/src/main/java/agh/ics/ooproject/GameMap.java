package agh.ics.ooproject;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameMap {
    MapType type;
    int width;
    int height;
    int noPlants;
    int plantEnergy;
    int noNewPlants;
    AbstractPlantGenerator plantGenerator;
    int noAnimals;
    int startingEnergy;
    public int sufficientEnergy;
    int consumedEnergy;
    int minimalMutations;
    int maximalMutations;
    int genLength;
    MutationType mutationType;
    BehaviourType behaviour;
    public Cell[][] grid;
    public List<ElementAnimal> aliveAnimals = new ArrayList<>();
    public List<ElementAnimal> deadAnimals = new ArrayList<>();
    int day;

    public int maxEnergy = 0;

    public GameMap(MapType type, int width, int height, int noPlants, int plantEnergy, int noNewPlants,
                   PlantType plantGenerator, int noAnimals, int startingEnergy,
                   int sufficientEnergy, int consumedEnergy, int minimalMutations, int maximalMutations,
                   MutationType mutationType, int genLength, BehaviourType behaviour){
        this.type = type;
        this.width = width;
        this.height = height;
        this.noPlants = noPlants;
        this.plantEnergy = plantEnergy;
        this.noNewPlants = noNewPlants;
        if (plantGenerator == PlantType.EQUATOR){this.plantGenerator = new PlantGeneratorEquator(this);}
        else {this.plantGenerator = new PlantGeneratorToxic(this);};
        this.noAnimals = noAnimals;
        this.startingEnergy = startingEnergy;
        this.sufficientEnergy = sufficientEnergy;
        this.consumedEnergy = consumedEnergy;
        this.minimalMutations = minimalMutations;
        this.maximalMutations = maximalMutations;
        this.mutationType = mutationType;
        this.behaviour = behaviour;
        this.genLength = genLength;
        this.day = 0;

        this.grid = new Cell[height][width];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                this.grid[i][j] = new Cell(this, new Position(j, i));
            }
        }
        this.initializeAnimals();
        this.initializeGrass();
    }

    public Cell getCellAt(Position position){
        return this.grid[position.y][position.x];
    }

    public void initializeAnimals(){
        int randomX;
        int randomY;
        ElementAnimal animal;
        for (int i = 0; i < noAnimals; i++){
            randomX = (int) Math.floor(Math.random()*this.width);
            randomY = (int) Math.floor(Math.random()*this.height);
            animal = new ElementAnimal(this, new Position(randomX, randomY), 0,
                    this.startingEnergy, this.sufficientEnergy, this.consumedEnergy, this.mutationType, this.genLength, this.behaviour);
            this.grid[randomY][randomX].addElement(animal);
            this.aliveAnimals.add(animal);
        }
    }
    public void initializeGrass(){
        this.plantGenerator.initialize();
    }

    public void deleteDead(){

        ElementAnimal animal;
        List<ElementAnimal> toDelete = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                for (int k = 0; k < this.grid[i][j].animals.size(); k++) {
                    animal = this.grid[i][j].animals.get(k);
                    animal.energy--;
                    if (animal.energy < 0){
                        animal.alive = false;
                    }
                    if(!animal.alive){

                        toDelete.add(animal);
                    }
                }
                for (ElementAnimal element : toDelete) {

                    element.dayOfDeath = day + 1;

                    this.deadAnimals.add(element);
                    this.aliveAnimals.remove(element);
                    this.grid[i][j].removeElement(element);
                }
            }
        }
    }
    public Border isValidPosition(Position position){
        if (position.x < this.width && position.x >= 0 && position.y < this.height && position.y >= 0){
            return Border.Inside;
        }
        if (position.x < 0 && position.y < 0 || position.x >= this.width && position.y < 0 ||
                position.x < 0 && position.y >= this.height || position.x >= this.width && position.y >= this.height){
            return Border.Corner;
        }
        if (position.x >= this.width){
            return Border.Right;
        }
        if (position.x < 0){
            return Border.Left;
        }
        if (position.y >= this.height){
            return Border.Up;
        }
        return Border.Down;
    }
    public void updateAnimalPositions(){
        List<ElementAnimal> toMove = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                toMove.addAll(this.grid[i][j].animals);
            }
        }
        for (ElementAnimal element : toMove) {
            element.move();
        }
//        ElementAnimal animal;
//
//        for (int i = 0; i < this.height; i++) {
//            for (int j = 0; j < this.width; j++) {
//                for (int k = 0; k < this.grid[i][j].animals.size(); k++) {
//                    animal = this.grid[i][j].animals.get(k);
//                    animal.move();
//                }
//            }
//        }
    }
    public void consumption(){
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.grid[i][j].eatGrass();
            }
        }
    }
    public void procreation(){
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.grid[i][j].procreate();
            }
        }
    }
    public void setMaxVals(){
        Comparator<ElementAnimal> win = Comparator.comparingInt((ElementAnimal x) -> -x.energy);
        if (this.aliveAnimals.size() > 0) {
            int maxEnergy = Collections.max(this.aliveAnimals, win).energy;
            if (this.deadAnimals.size() > 0) {
                maxEnergy = Math.max(maxEnergy, Collections.max(this.deadAnimals, win).energy);
            }
        }
    }
}
