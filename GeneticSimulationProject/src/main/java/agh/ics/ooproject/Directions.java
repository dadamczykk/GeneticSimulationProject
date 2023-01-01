package agh.ics.ooproject;

public enum Directions {
    f,
    fr,
    r,
    br,
    b,
    bl,
    l,
    fl;

    @Override
    public String toString(){
        return switch (this){
            case f -> "Forward";
            case fr -> "ForwardRight";
            case r -> "Right";
            case br -> "BackwardRight";
            case b -> "Backward";
            case bl -> "BackwardLeft";
            case l -> "Left";
            case fl -> "ForwardLeft";
        };
    }
}
