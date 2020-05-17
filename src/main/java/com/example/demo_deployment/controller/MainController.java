package com.example.demo_deployment.controller;

import com.example.demo_deployment.domain.ApkControlModel;
import com.example.demo_deployment.domain.MusicModel;
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

    @GetMapping("searchmerged/{searchRequest}")
    private List<MusicModel> searchMerged(@PathVariable String searchRequest) {
        return mainRepository.searchMerged(searchRequest);
    }

    @GetMapping("tbdy/{searchRequest}")
    private List<MusicModel> searchTbdy(@PathVariable String searchRequest) {
        return mainRepository.searchTbdy(searchRequest);
    }

    @GetMapping("apkcontrol")
    private List<ApkControlModel> apkControl() {
        return mainRepository.apkControl();
    }

    @GetMapping("tbzy/{searchRequest}")
    private List<MusicModel> searchTbzy(@PathVariable String searchRequest) {
        return mainRepository.searchTbzy(searchRequest);
    }

    @GetMapping("tbzy2/{searchRequest}")
    private List<MusicModel> searchTbzy2(@PathVariable String searchRequest) {
        return mainRepository.searchTbzy2(searchRequest);
    }

    @GetMapping("sarkiyukle/{searchRequest}")
    private List<MusicModel> searchSarkiyukle(@PathVariable String searchRequest) {
        return mainRepository.searchSarkiyukle(searchRequest);
    }

    @GetMapping("sarkiyukle2/{searchRequest}")
    private List<MusicModel> searchSarkiyukle2(@PathVariable String searchRequest) {
        return mainRepository.searchSarkiyukle2(searchRequest);
    }

    @GetMapping("/popular/wrr")
    private List<MusicModel> popularWr() {
        return mainRepository.popularWr();
    }

    @GetMapping("/popular/tr")
    private List<MusicModel> popularTr() {
        return mainRepository.popularTr();
    }

    @GetMapping("/link/{request}")
    public String  giveMe(@PathVariable String request){
        return mainRepository.giveMe(request);
    }


}
