package agh.ics.ooproject;

public enum Border {
    Left,
    Right,
    Up,
    Down,
    Corner,
    Inside;

    @Override
    public String toString(){
        return switch (this){
            case Left -> "Left";
            case Right -> "Right";
            case Up -> "Up";
            case Down -> "Down";
            case Corner -> "LeftUp";
            case Inside -> "Inside";
        };
    }
}
