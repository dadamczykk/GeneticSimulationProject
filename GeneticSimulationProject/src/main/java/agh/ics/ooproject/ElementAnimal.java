package agh.ics.ooproject;

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
    }
    public void move(){
        this.energy--;
        if (this.energy < 0){
            this.alive = false;
        }

        this.dir = (this.dir + this.genotype.nextMove())%8;
        Position nextPosition = this.position.addDir(this.dir);
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
//        switch (this.map.type){
//            case Valhalla -> {
//                System.out.println("Val");
//                switch (this.map.isValidPosition(nextPosition)){
//                    case Left, Right -> {
//                        nextPosition = new Position(nextPosition.x % this.map.width, nextPosition.y);
//                    }
//                    case Up, Down -> {
//                        nextPosition = this.position;
//                        this.dir = (this.dir + 4)%8;
//                    }
//                    case Corner -> {
//                        nextPosition = new Position(nextPosition.x % this.map.width, this.position.y);
//                        this.dir = (this.dir + 4)%8;
//                    }
//                    case Inside -> {}
//                }
//            }
//            case Hel -> {
//                switch (this.map.isValidPosition(nextPosition)){
//                    case Left, Right, Up, Down, Corner -> {
//                        nextPosition = new Position((int) Math.floor(Math.random()*this.map.width),
//                                (int) Math.floor(Math.random()*this.map.width));
//                        this.energy = this.energy - consumedEnergy;
//                    }
//                    case Inside -> {}
//                }
//            }
//        }
//        if (this.map.type == MapType.Valhalla){
//
//        }else{
//
//        }
        Cell oldCell = this.map.getCellAt(this.position);
        oldCell.moveElementTo(this, nextPosition);
    }
    public ElementAnimal procreateWith(ElementAnimal animal){
        ElementAnimal newAnimal = new ElementAnimal(this.map, this.position, this.map.day,
                this.consumedEnergy*2, this.sufficientEnergy, this.consumedEnergy, this.mutationType,
                this.genLength, this.behaviour);
        if (this.energy >= animal.energy){
            newAnimal.genotype.copyFrom(this, animal);
        }else{
            newAnimal.genotype.copyFrom(animal, this);
        }
        newAnimal.genotype.mutate();
        return newAnimal;
    }
}
