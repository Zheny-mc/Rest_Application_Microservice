package ru.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.university.model.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
