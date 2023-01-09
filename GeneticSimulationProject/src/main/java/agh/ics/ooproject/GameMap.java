package agh.ics.ooproject;


import java.util.*;

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

    public float avgTol = 0;

    public String topGenome = "";

    public float allEnergy = 0;


    public ArrayList<Position> topPositions = new ArrayList<>();

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
        else {this.plantGenerator = new PlantGeneratorToxic(this);}
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
        List<ElementAnimal> toDelete = new ArrayList<>();
        for (ElementAnimal aliveAnimal : aliveAnimals) {
            aliveAnimal.energy--;
            if (aliveAnimal.energy < 0){
                aliveAnimal.alive = false;
            }
            if(!aliveAnimal.alive){
                toDelete.add(aliveAnimal);
            }
        }
        for (ElementAnimal element : toDelete) {
            element.dayOfDeath = day + 1;
            this.deadAnimals.add(element);
            this.aliveAnimals.remove(element);
            this.getCellAt(element.position).removeElement(element);
        }
    }
    public Border isValidPosition(Position position){
        if (position.x < this.width && position.x >= 0 && position.y < this.height && position.y >= 0){
            return Border.INSIDE;
        }
        if (position.x < 0 && position.y < 0 || position.x >= this.width && position.y < 0 ||
                position.x < 0 && position.y >= this.height || position.x >= this.width && position.y >= this.height){
            return Border.CORNER;
        }
        if (position.x >= this.width){
            return Border.RIGHT;
        }
        if (position.x < 0){
            return Border.LEFT;
        }
        if (position.y >= this.height){
            return Border.UP;
        }
        return Border.DOWN;
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
    public void setStats(){
        avgTol = 0;
        topGenome = "None";
        allEnergy = 0;
        topPositions = new ArrayList<>();
        maxEnergy = 0;

        Comparator<ElementAnimal> win = Comparator.comparingInt((ElementAnimal x) -> -x.energy);
        if (this.aliveAnimals.size() > 0) {
            this.maxEnergy = Collections.max(this.aliveAnimals, win).energy;
            if (this.deadAnimals.size() > 0) {
                maxEnergy = Math.max(maxEnergy, Collections.max(this.deadAnimals, win).energy);
            }
        }
        if (deadAnimals.size() != 0) {
            for (ElementAnimal animal : deadAnimals) {
                avgTol += animal.dayOfDeath - animal.birthdate;
            }
            avgTol /= (float) deadAnimals.size();
        }
        HashMap<String, Integer> genomes = new HashMap<>();
        if (aliveAnimals.size() != 0) {
            for (ElementAnimal animal : aliveAnimals) {
                String key = Arrays.toString(animal.genotype.genome);
                if (genomes.containsKey(key)) {
                    genomes.put(key, genomes.get(key) + 1);
                } else {
                    genomes.put(key, 1);
                }
            }
            topGenome = Collections.max(genomes.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        }

        for (int x = 0; x < this.width; x++){
            for (int y = 0; y < this.height; y++){
                if (grid[y][x].animals.size() > 0) {
                    for (ElementAnimal animal : grid[y][x].animals) {
                        allEnergy += animal.energy;
                        if (Arrays.toString(animal.genotype.genome).equals(topGenome)){
                            topPositions.add(animal.position);
                        }
                    }
                }
            }
        }

    }
}
