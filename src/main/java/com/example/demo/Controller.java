package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "/rest")
public class Controller {

    @GetMapping("/test/{nombre}")
    public boolean test (@PathVariable int nombre){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Integer> list = new ArrayList<>();
        for (int i =0; i<nombre; i++){
            list.add(1);
        }
        ThreadList thread = new ThreadList(list);
        executorService.submit(thread);
        executorService.shutdown();
        return true;
    }

}
