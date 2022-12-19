package agh.ics.ooproject;

public enum PlantType {
    NONTOXIC,
    EQUATOR;

    @Override
    public String toString(){
        return switch (this){
            case NONTOXIC -> "Plants prefere places with less deaths";
            case EQUATOR -> "Plants prefere places near equator";
        };
    }
}
