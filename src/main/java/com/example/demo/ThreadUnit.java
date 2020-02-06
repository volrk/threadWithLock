package com.example.demo;

import java.util.concurrent.Callable;

public class ThreadUnit implements Callable<Boolean> {

    private double random;

    private int id;

    public ThreadUnit(double random, int id){
        this.id = id;
        this.random = random;
    }

    @Override
    public Boolean call() throws Exception {
        Thread.sleep((long) (5000*random));
        if(random > 0.5){
            System.out.println("ERROR" + " id: "+ this.id);
            throw new Exception();
        }
        System.out.println("call : " + (long)(5000*random) + " id: "+ this.id);
        return true;
    }
}
