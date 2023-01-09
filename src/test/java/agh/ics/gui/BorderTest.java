package agh.ics.gui;

import agh.ics.ooproject.*;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorderTest {
    @Test
    public void inBorderTest(){
        GameMap map = new GameMap(MapType.VALHALLA, 10, 10, 10, 10, 10,
                PlantType.EQUATOR, 10, 10, 10, 10, 10,
                10, MutationType.RANDOM, 10, BehaviourType.MADNESS);

        assertEquals(Border.INSIDE, map.isValidPosition(new Position(1, 1)));
        assertEquals(Border.INSIDE, map.isValidPosition(new Position(1, 2)));
        assertEquals(Border.INSIDE, map.isValidPosition(new Position(8, 9)));
        assertEquals(Border.INSIDE, map.isValidPosition(new Position(9, 9)));
        assertEquals(Border.LEFT, map.isValidPosition(new Position(-1, 1)));
        assertEquals(Border.DOWN, map.isValidPosition(new Position(1, -1)));
        assertEquals(Border.RIGHT, map.isValidPosition(new Position(10, 1)));
        assertEquals(Border.UP, map.isValidPosition(new Position(1, 10)));
        assertEquals(Border.CORNER, map.isValidPosition(new Position(-1, -1)));
        assertEquals(Border.CORNER, map.isValidPosition(new Position(-1, 10)));
        assertEquals(Border.CORNER, map.isValidPosition(new Position(10, -1)));
        assertEquals(Border.CORNER, map.isValidPosition(new Position(10, 10)));
    }
}
