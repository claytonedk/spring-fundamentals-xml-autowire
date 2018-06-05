package com.kuhn.service;

import java.util.List;
import java.util.Map;

import com.kuhn.entity.Customer;
import com.kuhn.enums.CustomerType;
import com.kuhn.log.Log;
import com.kuhn.repository.CustomerRepository;
import com.kuhn.strategy.discount.DiscountCalc;

public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	private Log mainLog;
	private List<Log> logs;
	private Map<CustomerType, DiscountCalc> mapDiscount;
	
	public void setMainLog(Log mainLog) {
		this.mainLog = mainLog;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	public void setMapDiscount(Map<CustomerType, DiscountCalc> mapDiscount) {
		this.mapDiscount = mapDiscount;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAll() {
		mainLog.log("CustomerService.getAll()");
		
		logs.forEach((v) -> v.log("CustomerService.getAll()"));
		
		return customerRepository.getAll();
	}

	@Override
	public double calculerRabais(CustomerType customerType, double valeurTotale) {
		return mapDiscount.get(customerType).calculateDiscount(valeurTotale);
	}
}
