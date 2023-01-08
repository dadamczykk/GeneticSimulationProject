package agh.ics.ooproject;

public enum MapType {

    Hel,
    Valhalla;

    @Override
    public String toString(){
        return switch (this){
            case Valhalla -> "Valhalla";
            case Hel -> "Hel";
        };
    }
}
