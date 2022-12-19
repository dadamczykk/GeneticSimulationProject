package agh.ics.ooproject;

public enum MutationType {
    RANDOM,
    SLIGHT;

    @Override
    public String toString(){
        return switch (this){
            case RANDOM -> "Genotype will mutate randomly";
            case SLIGHT -> "Genotype will mutate slightly";
        };
    }
}
