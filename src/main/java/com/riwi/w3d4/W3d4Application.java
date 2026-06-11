package com.riwi.w3d4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class W3d4Application {
    public static void main(String[] args) {
        SpringApplication.run(W3d4Application.class, args);
    }
}

@Controller
class HomeController {
    @GetMapping("/")
    public String home() {
        return "redirect:/admin/libros";
    }
}
