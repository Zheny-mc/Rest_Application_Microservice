package ru.university.clients;

import ru.university.model.CurrencyCustomer;

public interface RateClient {

    CurrencyCustomer getCurrencyCustomer(Integer id);
}
