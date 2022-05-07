package ru.university.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CurrencyCustomer {
	private Integer id;
	private String name;

    @JsonCreator
    public CurrencyCustomer(@JsonProperty("id") Integer id,
                            @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
