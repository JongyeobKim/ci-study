package com.jongyeob.kim.cistudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CiStudyApplication {

    @GetMapping("/helloci")
    public String hello() {
        return "Hello CI";
    }

    public static void main(String[] args) {
        SpringApplication.run(CiStudyApplication.class, args);
    }

}
