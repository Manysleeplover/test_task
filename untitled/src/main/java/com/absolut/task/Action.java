package com.absolut.task;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Action {
    private int timestamp;
    private AtomicInteger counter;

    public Action(int timestamp) {
        this.timestamp = timestamp;
        this.counter = new AtomicInteger(1);
    }

    public void inc(){
        counter.incrementAndGet();
    }

}
