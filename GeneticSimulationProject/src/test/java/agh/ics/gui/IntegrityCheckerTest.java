package agh.ics.gui;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrityCheckerTest {

    @Test
    public void enumTest(){
        ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(5, 0, 1, 1, 15, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments1 = arguments;
        Exception thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments1));
        assertEquals(thrown.getMessage(), "Slider values must be 0 or 1");

        arguments = new ArrayList<>(Arrays.asList(-5, 0, 1, 1, 15, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments));
        assertEquals(thrown.getMessage(), "Slider values must be 0 or 1");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, -2, 15, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments2 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments2));
        assertEquals(thrown.getMessage(), "Slider values must be 0 or 1");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 15, 2, 15, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments3 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments3));
        assertEquals(thrown.getMessage(), "Slider values must be 0 or 1");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 15, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments4 = arguments;
        assertDoesNotThrow(() -> App.integrityChecker(finalArguments4));
    }

    @Test
    public void widthHeightTest(){
        ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 150, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments1 = arguments;
        Exception thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments1));
        assertEquals(thrown.getMessage(), "Map width and height must be from the interval [1, 100]");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 0, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments));
        assertEquals(thrown.getMessage(), "Map width and height must be from the interval [1, 100]");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 150, 106, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments2 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments2));
        assertEquals(thrown.getMessage(), "Map width and height must be from the interval [1, 100]");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, -150, -10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments3 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments3));
        assertEquals(thrown.getMessage(), "Map width and height must be from the interval [1, 100]");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 15, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments4 = arguments;
        assertDoesNotThrow(() -> App.integrityChecker(finalArguments4));
    }

    @Test
    public void plantsNoTest(){
        ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, -5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments1 = arguments;
        Exception thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments1));
        assertEquals(thrown.getMessage(), "Negative number of plants is incorrect");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, -5));
        ArrayList<Integer> finalArguments = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments));
        assertEquals(thrown.getMessage(), "Negative number of plants is incorrect");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 50000,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments2 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments2));
        assertEquals(thrown.getMessage(), "There cannot be more plants than tiles on the map");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 101));
        ArrayList<Integer> finalArguments3 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments3));
        assertEquals(thrown.getMessage(), "There cannot be more plants than tiles on the map");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 15, 10, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments4 = arguments;
        assertDoesNotThrow(() -> App.integrityChecker(finalArguments4));
    }

    @Test
    public void energyTest(){
        ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 5,
                5, -5, 5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments1 = arguments;
        Exception thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments1));
        assertEquals(thrown.getMessage(), "Negative energy from plants value is incorrect");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 5,
                5, 5, -5, 5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments));
        assertEquals(thrown.getMessage(), "Non positive animal energy value is incorrect");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 50,
                5, 5, 5, -5, 5, 5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments2 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments2));
        assertEquals(thrown.getMessage(), "Non positive animal energy value is incorrect");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 5,
                5, 5, 5, 5, -5, 5, 5, 5, 5, 11));
        ArrayList<Integer> finalArguments3 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments3));
        assertEquals(thrown.getMessage(), "Non positive animal energy value is incorrect");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 15, 10, 5,
                5, 5, 45, 5, 5, 5, 5, 5, 5, 51));
        ArrayList<Integer> finalArguments4 = arguments;
        assertDoesNotThrow(() -> App.integrityChecker(finalArguments4));

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 5,
                5, 5, 5, 5, 20, 5, 5, 5, 5, 11));
        ArrayList<Integer> finalArguments5 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments5));
        assertEquals(thrown.getMessage(), "Energy, that children consumes \n is bigger than satisfying energy");

    }

    @Test
    public void genomeTest(){
        ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 5,
                5, 5, 5, 5, 5, -5, -5, 5, 5, 5));
        ArrayList<Integer> finalArguments1 = arguments;
        Exception thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments1));
        assertEquals(thrown.getMessage(), "Children mutations cannot be negative");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 5,
                5, 5, 5, 5, 5, 5, -5, 5, 5, 5));
        ArrayList<Integer> finalArguments = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments));
        assertEquals(thrown.getMessage(), "Children mutations cannot be negative");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 50,
                5, 5, 5, 5, 5, -5, 5, 5, 5, 5));
        ArrayList<Integer> finalArguments2 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments2));
        assertEquals(thrown.getMessage(), "Children mutations cannot be negative");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 5,
                5, 5, 5, 5, 5, 10, 5, 5, 5, 11));
        ArrayList<Integer> finalArguments3 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments3));
        assertEquals(thrown.getMessage(), "Incorrect min and max value of children mutations");

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 15, 10, 5,
                5, 5, 5, 5, 5, 10, 10, 5, 5, 5));
        ArrayList<Integer> finalArguments4 = arguments;
        assertDoesNotThrow(() -> App.integrityChecker(finalArguments4));

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 15, 10, 5,
                5, 5, 5, 5, 5, 0, 0, 5, 5, 5));
        ArrayList<Integer> finalArguments5 = arguments;
        assertDoesNotThrow(() -> App.integrityChecker(finalArguments5));

        arguments = new ArrayList<>(Arrays.asList(1, 0, 1, 1, 10, 10, 5,
                5, 5, 5, 5, 5, 10, 10, -5, 5, 11));
        ArrayList<Integer> finalArguments6 = arguments;
        thrown = assertThrows(Exception.class, () -> App.integrityChecker(finalArguments6));
        assertEquals(thrown.getMessage(), "Genome length has to be positive");
    }


}
