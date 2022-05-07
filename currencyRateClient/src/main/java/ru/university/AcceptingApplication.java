package ru.university;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;



@SpringBootApplication
public class AcceptingApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(AcceptingApplication.class).run(args);
    }
}
