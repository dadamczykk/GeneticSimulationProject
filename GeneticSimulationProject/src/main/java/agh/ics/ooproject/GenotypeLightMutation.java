package agh.ics.ooproject;

public class GenotypeLightMutation extends AbstractGenotype{
    public GenotypeLightMutation(ElementAnimal animal, int N){
        super(animal, N);
    }

    @Override
    void mutate() {
        int noMutations = (int) Math.floor(Math.random()*
                (this.animal.map.maximalMutations - this.animal.map.minimalMutations) +
                this.animal.map.minimalMutations);
        for (int i = 0; i < noMutations; i++) {
            if (Math.random() < 0.5){
                this.genome[i] = (this.genome[i] + 1) % 8;
            }else{
                this.genome[i] = (this.genome[i] - 1) % 8;
            }
        }
    }

}
