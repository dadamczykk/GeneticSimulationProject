package agh.ics.ooproject;

abstract public class AbstractMap {
    int width;
    int height;
    int noPlants;
    int plantEnergy;
    int noNewPlants;
    AbstractPlantGenerator plantGenerator;
    int noAnimals;
    int startingEnergy;
    int sufficientEnergy;
    int consumedEnergy;
    int minimalMutations;
    int maximalMutations;
    AbstractGenotype genotype;
    BehaviourType behaviour;

    Cell[][] grid;

}
