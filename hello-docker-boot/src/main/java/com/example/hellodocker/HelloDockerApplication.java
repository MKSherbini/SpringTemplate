package com.example.hellodocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelloDockerApplication {

    public static void main(String[] args) {
        System.out.println("args = " + Arrays.toString(args));
        SpringApplication.run(HelloDockerApplication.class, args);
    }
// mvn spring-boot:run -Dspring-boot.run.arguments=spongebob
// java -jar target\hello-docker-0.0.1.jar spongebob
}
