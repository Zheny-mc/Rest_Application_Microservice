package ru.university.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.university.model.Customer;
import ru.university.repo.CustomerRepository;
import ru.university.services.CurrencyCustomerService;

import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CurrencyRateController {

	public final CurrencyCustomerService currencyCustomerService;
	private final CustomerRepository repository;

	@GetMapping
	public Collection<Customer> getCustomers(){
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable Integer id){
		return currencyCustomerService.getCustomer(id);
	}

	@PostMapping
	public Customer createCustomer(@RequestBody Customer customer){
		return repository.save(customer);
	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody String name, @PathVariable Integer id){
		var customer = repository.findById(id);
		if (customer.isPresent()) {
			var curCustomer = customer.get();
			curCustomer.setName(name);
			return repository.save(curCustomer);
		} else {
			throw new RuntimeException("Error get id: " + id);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Integer id){
		log.info("delete customer id: {}", repository.findById(id).get());
		repository.deleteById(id);
	}

}