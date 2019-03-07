package com.jongyeob.kim.cistudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class CiStudyApplication {

    @GetMapping("/good")
    public String good() {
        return "hello";
    }

    @GetMapping("/helloci")
    public String hello() {
        return "Hello CI";
    }

    public static void main(String[] args) {
        SpringApplication.run(CiStudyApplication.class, args);
    }

}
