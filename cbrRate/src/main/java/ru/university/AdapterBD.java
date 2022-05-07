package ru.university;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;



@SpringBootApplication
public class AdapterBD {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(AdapterBD.class).run(args);
    }
}
