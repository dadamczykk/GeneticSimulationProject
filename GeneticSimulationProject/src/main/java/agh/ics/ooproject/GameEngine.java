package agh.ics.ooproject;

public class GameEngine {
    GameMap map;
    public GameEngine(GameMap map){
        this.map = map;
    }
    public void run(){
        for (int i = 0; i < 1000000; i++) {
            map.deleteDead();
            map.updateAnimalPositions();
            map.consumption();
            map.procreation();
            map.plantGenerator.addGrass(map.noNewPlants);
            System.out.println(i);
        }
    }
    public void update(){
        map.deleteDead();
        map.updateAnimalPositions();
        map.consumption();
        map.procreation();
        map.plantGenerator.addGrass(map.noNewPlants);
        map.setMaxVals();
        map.day++;
    }
}
