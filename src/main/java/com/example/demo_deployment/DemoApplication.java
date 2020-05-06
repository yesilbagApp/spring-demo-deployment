package com.example.demo_deployment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String helloOpenShift(){
		return "Burak Yeşilbağ dan selamlar :D :D :D ";
	}

	@GetMapping("/{data}")
	public String hello(@PathVariable String data){
		return "Merhaba" + data + "Open Shifte Hoşgeldin";
	}

}
