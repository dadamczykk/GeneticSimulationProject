package agh.ics.gui;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class intConversionTest {

    @Test
    public void integerTest(){
        Exception e = null;
        try {
            ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(1, 1, 1, 1,
                    Integer.parseInt("10"), Integer.parseInt("-30"),
                    Integer.parseInt("ala20"), Integer.parseInt("1203412903410923"),
                    Integer.parseInt("15"), Integer.parseInt("15"),
                    Integer.parseInt(""), Integer.parseInt("12"),
                    Integer.parseInt("11"), Integer.parseInt("12"),
                    Integer.parseInt("10"), true ? 1 : 0,
                    Integer.parseInt("11")));
        } catch (Exception ex){
            e = ex;
        }
        assertEquals(e.getClass(), NumberFormatException.class);
        e = null;
        try {
            ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(1, 1, 1, 1,
                    Integer.parseInt("10"), Integer.parseInt("-30"),
                    Integer.parseInt("20"), Integer.parseInt("1203412903410923"),
                    Integer.parseInt("15"), Integer.parseInt("15"),
                    Integer.parseInt(""), Integer.parseInt("12"),
                    Integer.parseInt("11"), Integer.parseInt("12"),
                    Integer.parseInt("10"), true ? 1 : 0,
                    Integer.parseInt("11")));
        } catch (Exception ex){
            e = ex;
        }
        assert e != null;
        assertEquals(e.getClass(), NumberFormatException.class);
        e = null;
        try {
            ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(1, 1, 1, 1,
                    Integer.parseInt("10"), Integer.parseInt("-30"),
                    Integer.parseInt("20"), Integer.parseInt("123"),
                    Integer.parseInt("015"), Integer.parseInt("15"),
                    Integer.parseInt("11"), Integer.parseInt("12"),
                    Integer.parseInt("1ss1"), Integer.parseInt("12"),
                    Integer.parseInt("10"), true ? 1 : 0,
                    Integer.parseInt("11")));
        } catch (Exception ex){
            e = ex;
        }
        assert e != null;
        assertEquals(e.getClass(), NumberFormatException.class);
        e = null;
        try {
            ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(1, 1, 1, 1,
                    Integer.parseInt("10"), Integer.parseInt("-30"),
                    Integer.parseInt("20"), Integer.parseInt("123"),
                    Integer.parseInt("15"), Integer.parseInt("15"),
                    Integer.parseInt(""), Integer.parseInt("12"),
                    Integer.parseInt("11"), Integer.parseInt("12"),
                    Integer.parseInt("10"), true ? 1 : 0,
                    Integer.parseInt("11")));
        } catch (Exception ex){
            e = ex;
        }
        assert e != null;
        assertEquals(e.getClass(), NumberFormatException.class);
        e = null;
        try {
            ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(1, 1, 1, 1,
                    Integer.parseInt("10"), Integer.parseInt("-30"),
                    Integer.parseInt("20"), Integer.parseInt("123"),
                    Integer.parseInt("15"), Integer.parseInt("15"),
                    Integer.parseInt("22"), Integer.parseInt("12"),
                    Integer.parseInt("11"), Integer.parseInt("12"),
                    Integer.parseInt("10"), true ? 1 : 0,
                    Integer.parseInt("11")));
        } catch (Exception ex){
            e = ex;
        }
        assertNull(e);
    }

}
