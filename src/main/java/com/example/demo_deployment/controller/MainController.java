package com.example.demo_deployment.controller;

import com.example.demo_deployment.domain.Model;
import com.example.demo_deployment.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MainRepository mainRepository;

    @GetMapping("/{searchRequest}")
    private List<Model> search(@PathVariable String searchRequest) {
        return mainRepository.search(searchRequest);
    }

    @GetMapping("/popular/wr")
    private List<Model> popularWr() {
        return mainRepository.popularWr();
    }

    @GetMapping("/popular/tr")
    private List<Model> popularTr() {
        return mainRepository.popularTr();
    }

    @GetMapping("/link/{request}")
    public String  giveMe(@PathVariable String request){
        return mainRepository.giveMe(request);
    }
}
