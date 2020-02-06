package com.example.demo;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LockTest {

    @Test
    void lock() throws Exception {
        Lock.lock(null);
        assertEquals(new HashMap<>(), Lock.getMap());

        Lock.lock(new ArrayList<>());
        assertEquals(new HashMap<>(), Lock.getMap());

        List<Integer> list = new ArrayList<>();
        list.add(null);
        Lock.lock(list);
        assertEquals(new HashMap<>(), Lock.getMap());

        Map<Integer, Integer> map = Maps.newHashMap(1,1);
        Lock.lock(Arrays.asList(1));
        assertEquals(map, Lock.getMap());

        map.put(2,2);
        map.put(3,3);
        Lock.lock(Arrays.asList(2,3));
        assertEquals(map, Lock.getMap());

        assertThrows(Exception.class, () -> {
            Lock.lock(Arrays.asList(2));
        });

        assertThrows(Exception.class, () -> {
            Lock.lock(Arrays.asList(4,4));
        });
    }

    @Test
    void unlock() throws Exception {
        Lock.unlock(null);
        assertEquals(new HashMap<>(), Lock.getMap());

        Lock.unlock(new ArrayList<>());
        assertEquals(new HashMap<>(), Lock.getMap());

        List<Integer> list = new ArrayList<>();
        list.add(null);
        Lock.unlock(list);
        assertEquals(new HashMap<>(), Lock.getMap());

        Map<Integer, Integer> map = Maps.newHashMap(1,1);
        map.put(2,2);
        map.put(3,3);
        Lock.lock(Arrays.asList(1,2,3));
        assertEquals(map, Lock.getMap());

        Lock.unlock(Arrays.asList(2,3));
        assertEquals(Maps.newHashMap(1,1), Lock.getMap());

        Lock.unlock(Arrays.asList(1));
        assertEquals(new HashMap<>(), Lock.getMap());
    }
}
