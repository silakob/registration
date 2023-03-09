package com.registration.registration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController{
    @RequestMapping("/hello")
    public String Hello(){
        return "Hello World";
    }
}