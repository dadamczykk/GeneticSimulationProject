package agh.ics.ooproject;

public enum MapType {
    Valhalla,
    Hel;

    @Override
    public String toString(){
        return switch (this){
            case Valhalla -> "Valhalla";
            case Hel -> "Hel";
        };
    }
}
