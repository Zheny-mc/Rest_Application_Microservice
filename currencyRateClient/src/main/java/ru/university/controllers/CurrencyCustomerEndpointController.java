package ru.university.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.university.model.CurrencyCustomer;
import ru.university.services.CurrencyCustomerEndpointService;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "${app.rest.api.prefix}/customer")
public class CurrencyCustomerEndpointController {

    public final CurrencyCustomerEndpointService currencyRateEndpointService;

    @GetMapping("/{id}")
    public CurrencyCustomer getCurrencyCustomer(@PathVariable Integer id)  {
        log.info("getCurrencyCustomer in client, id:{}", id);
        //getDelay(1000);
        return currencyRateEndpointService.getCurrencyRate(id);
    }

    public void getDelay(int delay) {
        try {Thread.sleep(delay);}
        catch (InterruptedException e) {e.printStackTrace();}
    }
}