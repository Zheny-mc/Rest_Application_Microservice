package ru.university.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "customer-client")
public class CustomerClientConfig {
    String url;
}
