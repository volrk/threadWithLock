package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ThreadList implements Callable<Boolean> {

    private List<Integer> list = new ArrayList<>();

    public ThreadList(List<Integer> list){
        this.list = list;
    }

    @Override
    public Boolean call() throws Exception {
        List<Integer> listObjet = this.list.stream().map(element -> (int) (Math.random()*20)).collect(Collectors.toList());

        Lock.lock(listObjet);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<ThreadUnit> listThread = listObjet.stream().map(element -> new ThreadUnit(Math.random(), element)).collect(Collectors.toList());
        executorService.invokeAll(listThread);

        Lock.unlock(listObjet);

        System.out.println("alles ist klar");
        return true;
    }
}
