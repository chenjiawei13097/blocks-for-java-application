package org.firefly.provider.springbootdevtools.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Welcome")
public class HelloWorldController {
    @GetMapping("/Greeting/{name}")
    public String greet(@PathVariable String name) {
        return "Welcome, " + name + "!";
    }
}
