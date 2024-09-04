package hu.besp.onetimetokenlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OneTimeTokenLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneTimeTokenLoginApplication.class, args);
    }

    // TODO change to mainline spring-security if in mainline (Spring Boot 3.4, November 2024)
}