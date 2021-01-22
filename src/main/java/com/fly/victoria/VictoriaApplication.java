package com.fly.victoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VictoriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VictoriaApplication.class, args);
    }

}
