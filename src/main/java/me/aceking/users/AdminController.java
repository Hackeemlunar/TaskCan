package me.aceking.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admins")
public class AdminController {

    @GetMapping("/")
    public String getTasks() {
        return "Hello World";
    }
}
