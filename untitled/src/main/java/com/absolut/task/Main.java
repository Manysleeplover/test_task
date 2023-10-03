package com.absolut.task;

public class Main {
    public static void main(String[] args) throws Exception {
        var actionCounter = new ActionCounter();
        actionCounter.call(1);
        actionCounter.call(2);
        actionCounter.call(2);
        System.out.println(actionCounter.getActions(4));
        actionCounter.call(300);
        System.out.println(actionCounter.getActions(300));
        System.out.println(actionCounter.getActions(301));


    }
}