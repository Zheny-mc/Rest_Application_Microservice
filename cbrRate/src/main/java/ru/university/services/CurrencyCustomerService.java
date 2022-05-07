package ru.university.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.springframework.stereotype.Service;
import ru.university.model.CachedCurrencyCustomer;
import ru.university.model.Customer;
import ru.university.repo.CustomerRepository;


@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyCustomerService {

	private final CustomerRepository repository;
    private final Cache<Integer, CachedCurrencyCustomer> currencyCustomerCache;

    public Customer getCustomer(Integer id){
	    log.info("getCurrencyCustomer. id:{}", id);
		getDelay(1000);
		Customer customer = null;

//	    var cachedCurrencyCustomers = currencyCustomerCache.get(id);
//	    if (cachedCurrencyCustomers == null) {
			var curCustomer = repository.findById(id);
			if (curCustomer.isPresent()) {
				customer = curCustomer.get();
				log.info("Customer. id:{}", customer);
			} else {
				throw new RuntimeException("Error get id: " + id);
			}

//		    currencyCustomerCache.put( id, new CachedCurrencyCustomer(customer) );
//	    } else {
//		    customer = cachedCurrencyCustomers.getCurrencyCustomer();
//		    log.info("Cache -> Customer. id:{}", customer);
//	    }

    	return customer;
	}

	public void getDelay(int delay) {
		try {Thread.sleep(delay);}
		catch (InterruptedException e) {e.printStackTrace();}
	}
}
