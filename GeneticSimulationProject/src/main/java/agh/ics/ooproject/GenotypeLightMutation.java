package agh.ics.ooproject;

public class GenotypeLightMutation extends AbstractGenotype{
    public GenotypeLightMutation(ElementAnimal animal, int N){
        super(animal, N);
    }

    @Override
    void mutate() {
        int[] mutated = new int[this.N];
        for (int i = 0; i < this.N; i++) {
            mutated[i] = 0;
        }
        int toMutate = (int) Math.floor(Math.random()*this.N);
        for (int i = 0; i < this.noMutations; i++) {
            while (mutated[toMutate] != 0){
                toMutate = (int) Math.floor(Math.random()*this.N);
            }
            if (Math.random() < 0.5){
                this.genome[toMutate] = (this.genome[toMutate] + 1) % 8;
            }else{
                this.genome[toMutate] = (this.genome[toMutate] - 1) % 8;
            }
            mutated[toMutate] = 1;
        }
    }

}
