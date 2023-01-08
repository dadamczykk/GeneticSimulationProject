package agh.ics.ooproject;

public enum MapType {

    HEL,
    VALHALLA;

    @Override
    public String toString(){
        return switch (this){
            case VALHALLA -> "Globe";
            case HEL -> "Hellish portal";
        };
    }
}
