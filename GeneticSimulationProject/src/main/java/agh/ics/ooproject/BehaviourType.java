package agh.ics.ooproject;

public enum BehaviourType {
    PREDESTINATION,
    MADNESS;

    @Override
    public String toString(){
        return switch (this){
            case PREDESTINATION -> "Predestination";
            case MADNESS -> "Some madness";
        };
    }
}
