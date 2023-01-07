package agh.ics.ooproject;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenotypeRandomMutation extends AbstractGenotype {
    public GenotypeRandomMutation(ElementAnimal animal, int N){
        super(animal, N);
    }

    @Override
    void mutate() {
        int noMutations = (int) Math.floor(Math.random()*
                (this.animal.map.maximalMutations - this.animal.map.minimalMutations) +
                this.animal.map.minimalMutations);
        for (int i = 0; i < noMutations; i++) {
            this.genome[i] = (int) Math.floor(Math.random()*8);
        }
    }
}
