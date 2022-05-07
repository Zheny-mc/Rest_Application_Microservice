package ru.university.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.university.clients.RateClient;
import ru.university.model.CurrencyCustomer;
import ru.university.model.CustomerType;

import java.util.Map;

@Service
@Slf4j
public class CurrencyCustomerEndpointService {

    private final Map<String, RateClient> clients;

    public CurrencyCustomerEndpointService(Map<String, RateClient> clients) {
        this.clients = clients;
    }

    public CurrencyCustomer getCurrencyRate(Integer id) {
        log.info("Query client id: {}", id);
        var client = clients.get(CustomerType.USER.getServiceName());
        return client.getCurrencyCustomer(id);
    }
}
