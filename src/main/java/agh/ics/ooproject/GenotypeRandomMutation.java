package agh.ics.ooproject;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenotypeRandomMutation extends AbstractGenotype {
    public GenotypeRandomMutation(ElementAnimal animal, int N){
        super(animal, N);
    }

    @Override
    void mutate() {
        int[] mutated = new int[this.N];
        int toMutate = (int) Math.floor(Math.random()*this.N);
        for (int i = 0; i < this.noMutations; i++) {
            while (mutated[toMutate] != 0){
                toMutate = (int) Math.floor(Math.random()*this.N);
            }
            this.genome[toMutate] = (int) Math.floor(Math.random()*8);
            mutated[toMutate] = 1;
        }
    }
}
