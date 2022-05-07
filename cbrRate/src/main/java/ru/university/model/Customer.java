package ru.university.model;

import lombok.Data;
import javax.persistence.*;

@Entity(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
