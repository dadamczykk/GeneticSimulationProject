package agh.ics.ooproject;

public enum PlantType {
    NONTOXIC,
    EQUATOR;

    @Override
    public String toString(){
        return switch (this){
            case NONTOXIC -> "Plants prefer places with less deaths";
            case EQUATOR -> "Plants prefer places near the equator";
        };
    }
}
