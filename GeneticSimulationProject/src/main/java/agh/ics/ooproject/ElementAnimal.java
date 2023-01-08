package agh.ics.ooproject;

import java.util.Objects;

public class ElementAnimal extends AbstractElement{
    public int birthdate;
    public int energy;
    int sufficientEnergy;
    public int consumedEnergy;
    MutationType mutationType;
    BehaviourType behaviour;
    GameMap map;
    int dir;
    public AbstractGenotype genotype;
    int genLength;
    public int noChildren;
    public int plantsEaten;
    public int dayOfDeath;
    public ElementAnimal(GameMap map, Position position, int birthdate, int startingEnergy, int sufficientEnergy,
                         int consumedEnergy, MutationType mutationType, int genLength, BehaviourType behaviour){
        this.birthdate = birthdate;
        this.map = map;
        this.position = position;
        this.energy = startingEnergy;
        this.sufficientEnergy = sufficientEnergy;
        this.consumedEnergy = consumedEnergy;
        this.mutationType = mutationType;
        switch (this.mutationType){
            case RANDOM -> this.genotype = new GenotypeRandomMutation(this, genLength);
            case SLIGHT -> this.genotype = new GenotypeLightMutation(this, genLength);
        }
        this.behaviour = behaviour;
        this.dir = (int) Math.floor(Math.random()*8);
        this.alive = true;
        this.genLength = genLength;
        this.noChildren = 0;
        this.plantsEaten = 0;
        this.dayOfDeath = 0;
    }
    public void move(){
        if (this.energy <= 0){
            return;
        }

        this.dir = (this.dir + this.genotype.nextMove())%8;
        Position nextPosition = this.position.addDir(this.dir);
//        System.out.println(map.type);
        switch (this.map.type){
            case Valhalla -> {
//                System.out.println("Val");
                switch (this.map.isValidPosition(nextPosition)){
                    case Left, Right -> {
                        nextPosition = new Position((nextPosition.x + this.map.width) % this.map.width, nextPosition.y);
                    }
                    case Up, Down -> {
                        nextPosition = this.position;
                        this.dir = (this.dir + 4)%8;
                    }
                    case Corner -> {
                        nextPosition = new Position((nextPosition.x + this.map.width) % this.map.width, this.position.y);
                        this.dir = (this.dir + 4)%8;
                    }
                    case Inside -> {}
                }
            }
            case Hel -> {
                switch (this.map.isValidPosition(nextPosition)){
                    case Left, Right, Up, Down, Corner -> {
                        nextPosition = new Position((int) Math.floor(Math.random()*this.map.width),
                                (int) Math.floor(Math.random()*this.map.width));
                        this.energy = this.energy - consumedEnergy;
                    }
                    case Inside -> {}
                }
            }
        }
        Cell currentCell = this.map.getCellAt(this.position);
        currentCell.moveAnimalTo(this, nextPosition);
    }
    public ElementAnimal procreateWith(ElementAnimal animal){
        this.noChildren++;
        animal.noChildren++;
        ElementAnimal newAnimal = new ElementAnimal(this.map, this.position, this.map.day,
                this.consumedEnergy*2, this.sufficientEnergy, this.consumedEnergy, this.mutationType,
                this.genLength, this.behaviour);
        if (this.energy >= animal.energy){
            newAnimal.genotype.copyFrom(this, animal);
        }else{
            newAnimal.genotype.copyFrom(animal, this);
        }
        this.energy = this.energy - this.consumedEnergy;
        animal.energy = animal.energy - animal.consumedEnergy;
        newAnimal.genotype.mutate();
        return newAnimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementAnimal that = (ElementAnimal) o;
        return birthdate == that.birthdate && energy == that.energy && sufficientEnergy == that.sufficientEnergy && consumedEnergy == that.consumedEnergy && dir == that.dir && genLength == that.genLength && noChildren == that.noChildren && mutationType == that.mutationType && behaviour == that.behaviour && Objects.equals(map, that.map) && Objects.equals(genotype, that.genotype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthdate, energy, sufficientEnergy, consumedEnergy, mutationType, behaviour, map, dir, genotype, genLength, noChildren);
    }
}
