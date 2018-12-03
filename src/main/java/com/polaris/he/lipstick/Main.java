package com.polaris.he.lipstick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: hexie
 * Date: 2018-12-03 21:30
 * Description:
 */
@Controller
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @ResponseBody
    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }
}
