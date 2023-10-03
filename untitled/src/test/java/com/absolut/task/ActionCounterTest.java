package com.absolut.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActionCounterTest {

    ActionCounter actionCounter;

    @BeforeEach
    void initActionCounter(){
        actionCounter = new ActionCounter();
    }

    @Test
    void defaultTest() throws Exception {
        actionCounter.call(1);
        actionCounter.call(2);
        actionCounter.call(2);
        Assertions.assertEquals(3,actionCounter.getActions(4));
        actionCounter.call(300);
        Assertions.assertEquals(4,actionCounter.getActions(300));
        Assertions.assertEquals(3,actionCounter.getActions(301));

    }

    @Test
    void customTests() throws Exception {
        actionCounter.call(100);
        Assertions.assertEquals(1,actionCounter.getActions(101));
        actionCounter.call(200);
        Assertions.assertEquals(2,actionCounter.getActions(201));
        actionCounter.call(300);
        Assertions.assertEquals(3,actionCounter.getActions(301));
    }

    @Test
    void emptyList(){
        Assertions.assertEquals(0,actionCounter.getActions(301));
    }

    @Test
    void bigDifferenceBetween() throws Exception {
        actionCounter.call(1);
        Assertions.assertEquals(0,actionCounter.getActions(1000));
        actionCounter.call(1);
        actionCounter.call(1);
        actionCounter.call(1);
        Assertions.assertEquals(4,actionCounter.getActions(2));
    }

    @Test
    void exceptionWhenTimestampSmallerThenLastActionTimestamp() throws Exception {
        actionCounter.call(1);
        actionCounter.call(2);
        assertThrows(Exception.class, () -> actionCounter.call(1));
    }
}
