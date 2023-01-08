package agh.ics.ooproject;

import java.util.Arrays;

abstract public class AbstractGenotype {
    int N;
    public int[] genome;
    int[] wasUsed;
    public int currentGenIdx;
    ElementAnimal animal;
    int startingIdx;
    int noMutations;
    public AbstractGenotype(ElementAnimal animal, int N){

        this.N = N;
        this.animal = animal;
        this.genome = new int[N];
        this.wasUsed = new int[N];
        for (int i = 0; i < N; i++) {
            this.genome[i] = (int) Math.floor(Math.random()*8);
            this.wasUsed[i] = 0;
        }
        this.currentGenIdx = (int) Math.floor(Math.random()*N);
        this.startingIdx = this.currentGenIdx;
        this.wasUsed[this.currentGenIdx] = 1;
        this.noMutations = (int) Math.floor(Math.random()*
                (this.animal.map.maximalMutations - this.animal.map.minimalMutations) +
                this.animal.map.minimalMutations);
        noMutations = Math.min(noMutations, N);
    }

    int nextMove() {
        switch (animal.behaviour){
            case PREDESTINATION -> {
                if (this.wasUsed[(this.currentGenIdx + 1 + this.N)%this.N] == 1){
                    for (int i = 0; i < N; i++) {
                        this.wasUsed[i] = 0;
                    }
                }
                this.currentGenIdx = (this.currentGenIdx + 1 + this.N)%this.N;
                this.wasUsed[this.currentGenIdx] = 1;
            }
            case MADNESS -> {
                if (Arrays.stream(this.wasUsed).sum() < this.N){
                    if (Math.random() < 0.8){
                        while (this.wasUsed[this.currentGenIdx] != 0){
                            this.currentGenIdx = (this.currentGenIdx + 1)%this.N;
                        }
                    }else{
                        while (this.wasUsed[this.currentGenIdx] != 0){
                            this.currentGenIdx = (int) Math.floor(Math.random()*N);
                        }
                    }
                    this.wasUsed[this.currentGenIdx] = 1;
                }else{
                    if (this.wasUsed[(this.currentGenIdx + 1)%this.N] == 1){
                        for (int i = 0; i < N; i++) {
                            this.wasUsed[i] = 0;
                        }
                    }
                    if (Math.random() < 0.8){
                        this.currentGenIdx = this.startingIdx;
                    }else{
                        this.currentGenIdx = (int) Math.floor(Math.random()*N);
                        this.startingIdx = this.currentGenIdx;
                    }
                    this.wasUsed[this.currentGenIdx] = 1;
                }
            }
        }
        return this.genome[currentGenIdx];
    }
    public void copyFrom(ElementAnimal strongerAnimal, ElementAnimal weakerAnimal){
        double ratio = ((double) strongerAnimal.energy) / ((double) (weakerAnimal.energy + strongerAnimal.energy));
        int[] newGenome = new int[N];
        if (Math.random() < 0.5){
            ratio = 1 - ratio;
            for (int i = 0; i < N; i++) {
                if (i < ratio*N){
                    newGenome[i] = weakerAnimal.genotype.genome[i];
                }else{
                    newGenome[i] = strongerAnimal.genotype.genome[i];
                }
            }
        }else{
            for (int i = 0; i < N; i++) {
                if (i < ratio*N){
                    newGenome[i] = strongerAnimal.genotype.genome[i];
                }else{
                    newGenome[i] = weakerAnimal.genotype.genome[i];
                }
            }
        }
        this.genome = newGenome;
    }
    abstract void mutate();
}
