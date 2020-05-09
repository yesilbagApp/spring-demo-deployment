package com.example.demo_deployment.repository;

import com.example.demo_deployment.domain.Model;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainRepository {

     List<Model> search(String searchQuery);

     List<Model> popularTr();

     List<Model> popularWr();

}
