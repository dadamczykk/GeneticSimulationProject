package agh.ics.ooproject;

import java.util.Objects;

public class Position {
    public final int x;
    public final int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;

    }
    public Position addDir(int dir){
        return switch (dir){
            case 0 -> new Position(this.x, this.y+1);
            case 1 -> new Position(this.x+1, this.y+1);
            case 2 -> new Position(this.x+1, this.y);
            case 3 -> new Position(this.x+1, this.y-1);
            case 4 -> new Position(this.x, this.y-1);
            case 5 -> new Position(this.x-1, this.y-1);
            case 6 -> new Position(this.x-1, this.y);
            case 7 -> new Position(this.x-1, this.y+1);
            default -> new Position(this.x, this.y);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
