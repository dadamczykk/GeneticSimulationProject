package agh.ics.ooproject;

public class ElementAnimal {
    Position position;
    int energy;
    int sufficientEnergy;
    int consumedEnergy;
    AbstractGenotype genotype;
    Behaviour behaviour;
    public ElementAnimal(Position position, int startingEnergy, int sufficientEnergy,
                         int consumedEnergy, AbstractGenotype genotype, Behaviour behaviour){
        this.position = position;
        this.energy = startingEnergy;
        this.sufficientEnergy = sufficientEnergy;
        this.consumedEnergy = consumedEnergy;
        this.genotype = genotype;
        this.behaviour = behaviour;
    }
}
