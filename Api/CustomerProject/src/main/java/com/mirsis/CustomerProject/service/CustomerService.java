package com.mirsis.CustomerProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mirsis.CustomerProject.model.City;
import com.mirsis.CustomerProject.model.Customer;
import com.mirsis.CustomerProject.repository.ICustomerRepository;

@Service
public class CustomerService {

	private ICustomerRepository customerRepository;
	private CityService cityService;

	public CustomerService(ICustomerRepository customerRepository,CityService cityService) {
		this.customerRepository = customerRepository;
		this.cityService = cityService;
	}

	public List<Customer> getCustomerList() {
		return customerRepository.findAll();
	}

	public boolean customerDogrula(String customerName, String customerSurname) {
		Customer customer = customerRepository.findByCustomerNameAndCustomerSurname(customerName, customerSurname);
			if (customer == null) {
				return false;
			}	
		return true;
	}
	
	public Customer addCustomer(Customer customer) {
		
		City city = cityService.getOneCityById(customer.getCity().getId());
		
		Customer toSave = new Customer();
		toSave.setId(customer.getId());
		toSave.setCustomerName(customer.getCustomerName());
		toSave.setCustomerSurname(customer.getCustomerSurname());
		toSave.setGender(customer.getGender());
		toSave.setBirthdate(customer.getBirthdate());
		toSave.setCity(city);
		toSave.setAddress(customer.getAddress());
		toSave.setIsDisabled(customer.getIsDisabled());
		toSave.setPhoneNumber(customer.getPhoneNumber());
		toSave.setEmail(customer.getEmail());
			
        return customerRepository.save(toSave);
    }
}
