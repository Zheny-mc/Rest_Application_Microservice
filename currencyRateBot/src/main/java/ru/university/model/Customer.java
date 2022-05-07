package ru.university.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Customer {
    Integer id;
    String name;

    @JsonCreator
    public Customer(@JsonProperty("id") Integer id,
                    @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
