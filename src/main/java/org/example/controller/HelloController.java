package org.example.controller;

import org.example.security.Roles;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    @Secured({Roles.Customer,Roles.User})
    public String sayHello(){
        return "Hello from helloController in NetflixProject!";
    }
}
