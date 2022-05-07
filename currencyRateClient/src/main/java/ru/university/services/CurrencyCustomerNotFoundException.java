package ru.university.services;

public class CurrencyCustomerNotFoundException extends RuntimeException {
    public CurrencyCustomerNotFoundException(String message) {
        super(message);
    }
}
