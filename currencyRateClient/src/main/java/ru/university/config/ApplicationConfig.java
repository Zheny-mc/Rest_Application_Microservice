package ru.university.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CbrCustomerClientConfig.class)
public class ApplicationConfig {

}
