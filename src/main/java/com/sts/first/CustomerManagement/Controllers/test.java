package com.sts.first.CustomerManagement.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class test {
    @GetMapping
    public String testing(){
        return "Welcome to SeerTech Systems";
    }
}
