package agh.ics.ooproject;

public enum Border {
    LEFT,
    RIGHT,
    UP,
    DOWN,
    CORNER,
    INSIDE;

    @Override
    public String toString(){
        return switch (this){
            case LEFT -> "Left";
            case RIGHT -> "Right";
            case UP -> "Up";
            case DOWN -> "Down";
            case CORNER -> "LeftUp";
            case INSIDE -> "Inside";
        };
    }
}
