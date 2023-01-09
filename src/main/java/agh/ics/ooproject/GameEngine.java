package agh.ics.ooproject;

public class GameEngine {
    GameMap map;
    public GameEngine(GameMap map){
        this.map = map;
    }
    public void update(){
        map.deleteDead();
        map.updateAnimalPositions();
        map.consumption();
        map.procreation();
        map.plantGenerator.addGrass(map.noNewPlants);
        map.setStats();
        map.day++;
    }
}
