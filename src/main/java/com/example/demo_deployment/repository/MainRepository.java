package com.example.demo_deployment.repository;

import com.example.demo_deployment.domain.ApkControlModel;
import com.example.demo_deployment.domain.MusicModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainRepository {


    List<ApkControlModel> apkControl2();

    List<MusicModel> searchMerged(String searchQuery);

   List<MusicModel> searchSarkiyukle(String searchQuery);

     List<ApkControlModel> apkControl();

       List<MusicModel> searchTbzy(String searchQuery);

    List<MusicModel> searchTbdyhd(String searchQuery);

    List<MusicModel> searchTbdy(String searchQuery);

     List<MusicModel> popularTr();

     List<MusicModel> popularWr();

     String giveMe(String request);
}
