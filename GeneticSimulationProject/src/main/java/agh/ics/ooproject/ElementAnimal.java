package agh.ics.ooproject;

public class ElementAnimal extends AbstractElement{
    int energy;
    int sufficientEnergy;
    int consumedEnergy;
    AbstractGenotype genotype;
    BehaviourType behaviour;
    public ElementAnimal(Position position, int startingEnergy, int sufficientEnergy,
                         int consumedEnergy, AbstractGenotype genotype, BehaviourType behaviour){
        this.position = position;
        this.energy = startingEnergy;
        this.sufficientEnergy = sufficientEnergy;
        this.consumedEnergy = consumedEnergy;
        this.genotype = genotype;
        this.behaviour = behaviour;
    }
}
