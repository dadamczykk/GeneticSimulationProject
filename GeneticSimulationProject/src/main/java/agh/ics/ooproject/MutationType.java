package agh.ics.ooproject;

public enum MutationType {
    RANDOM,
    SLIGHT;

    @Override
    public String toString(){
        return switch (this){
            case RANDOM -> "Full randomness";
            case SLIGHT -> "Slight variation";
        };
    }
}
