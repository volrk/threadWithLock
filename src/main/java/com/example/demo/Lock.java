package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lock {

    private static Map<Integer, Integer> map = new HashMap<>();

    public static Map<Integer, Integer> getMap(){
        return map;
    }

    public static void lock(List<Integer> elements) throws Exception {
        if(elements == null){
            return;
        }
        synchronized(map){
            Map<Integer, Integer> temp = new HashMap<>();
            for (Integer element : elements){
                if (element == null){
                    continue;
                }
                if(map.get(element) == null && temp.get(element) == null){
                    temp.put(element, element);
                }
                else {
                    System.out.println(element + " already lock");
                    throw new Exception();
                }
            }
            map.putAll(temp);
        }
    }

    public static void unlock(List<Integer> elements){
        if(elements == null){
            return;
        }
        synchronized(map){
            for (Integer element : elements){
                map.remove(element);
            }
        }
    }

}
