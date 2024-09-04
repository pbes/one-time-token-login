package hu.besp.onetimetokenlogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class SecureController {

    @GetMapping("/")
    Map<String, String> hello(Principal principal) {
        return Map.of("message", "hello, " + principal.getName());
    }

}
