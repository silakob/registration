package com.registration.registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    public String helloString = "Hello World";
    // #region
    @RequestMapping("/hello")
    public String Hello() {
        return helloString;
    }

    @PostMapping("/hello")
    public String UpdateHello(){
        helloString = helloString.substring(helloString.length() -2, 1);
        return helloString;
    }
    // #endregion
}