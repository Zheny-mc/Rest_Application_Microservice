package ru.university.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class GetUpdatesRequest {

    @JsonProperty("offset")
    long offset;
}
