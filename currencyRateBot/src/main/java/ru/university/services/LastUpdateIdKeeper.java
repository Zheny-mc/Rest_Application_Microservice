package ru.university.services;

public interface LastUpdateIdKeeper {
    long get();

    void set(long lastUpdateId);
}
