package com.polaris.he.lipstick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: hexie
 * Date: 2018-12-08 11:12
 * Description:
 */
@Controller
public class HelloController {

    @ResponseBody
    @GetMapping
    public String hello() {
        return "hello world";
    }

    @GetMapping("/hello")
    public String hello0() {
        return "hello";
    }
}