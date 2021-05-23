import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private List<Time> createList(Time... elems){
        return new ArrayList<>(Arrays.asList(elems));
    }
    List<Integer> emptyList = new ArrayList<>();
    List<Integer> hours = new ArrayList<Integer>();
    List<Integer> minutes = new ArrayList<Integer>();
    List<Integer> seconds = new ArrayList<Integer>();
    List<Integer> hours24 = new ArrayList<Integer>();

    RuntimeException ex;

    @Test
    void everyBranchTest(){
        assertEquals(emptyList,SILab2.function(Collections.emptyList()));
        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.function(createList(new Time(-1,1,1))));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));
        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.function(createList(new Time(25,1,1))));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.function(createList(new Time(1,-1,1))));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        ex = assertThrows(RuntimeException.class,
                () -> SILab2.function(createList(new Time(1,1,60))));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        hours.add(3600);
        hours.add(7355);
        assertEquals(hours, SILab2.function(createList(new Time(1,0,0), new Time(2,2,35))));

        hours24.add(86400);
        assertEquals(hours24, SILab2.function(createList(new Time(24,0,0))));

        ex = assertThrows(RuntimeException.class,
                () -> SILab2.function(createList(new Time(24,1, 1))));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

    }

    @Test
    void multipleConditionTest(){


        //if (hr < 0 || hr > 24){ //7
        //T || X
        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.function(createList(new Time(-1,1,1))));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));
        //F || T
        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.function(createList(new Time(25,1,1))));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));
        //F || F
        hours.add(3600);
        assertEquals(hours, SILab2.function(createList(new Time(1,0,0))));

        //if (min < 0 || min > 59) //12
        //T || X
        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.function(createList(new Time(1,-1,1))));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));
        //F || T
        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.function(createList(new Time(1,60,1))));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));
        //F || F
        minutes.add(60);
        assertEquals(minutes, SILab2.function(createList(new Time(0,1,0))));

        //if (sec >= 0 && sec <= 59) //15
        //T && T
        seconds.add(3667);
        assertEquals(seconds, SILab2.function(createList(new Time(1,1,7))));
        //T && F
        ex = assertThrows(RuntimeException.class,
                () -> SILab2.function(createList(new Time(1,1,60))));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));
        //F && X
        ex = assertThrows(RuntimeException.class,
                () -> SILab2.function(createList(new Time(1,1,-1))));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        //else if (hr == 24 && min == 0 && sec == 0) { //18
        //T && T && T
        hours24.add(86400);
        assertEquals(hours24, SILab2.function(createList(new Time(24,0,0))));
        //T && T && F
        ex = assertThrows(RuntimeException.class,
                () -> SILab2.function(createList(new Time(24,0, 3))));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));
        //T && F && X
        ex = assertThrows(RuntimeException.class,
                () -> SILab2.function(createList(new Time(24,3, 3))));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));
        //F && X && X   Ne e mozno


    }



}