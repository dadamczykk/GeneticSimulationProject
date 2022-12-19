package agh.ics.ooproject;

public enum WorldTypes {
    HELL,
    EARTH;
    @Override
    public String toString(){
        return switch (this){
            case HELL -> "Hell, you'll burn";
            case EARTH -> "Earth - nothing interesting";
        };
    }
}

