package com.mirsis.CustomerProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirsis.CustomerProject.model.City;
import com.mirsis.CustomerProject.model.Customer;
import com.mirsis.CustomerProject.service.CityService;
import com.mirsis.CustomerProject.service.CustomerService;
import com.mirsis.CustomerProject.config.ResponseHandler;

@RestController
@RequestMapping("/api/musteri")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CityService cityService;

	@GetMapping("/hizmet-verilen-iller")
	public List<City> getHizmetVerilenIller() {
		return cityService.getAllCity();
	}

	@GetMapping("/musteri-listesi")
	public List<Customer> getMusteriListesi() {
		return customerService.getCustomerList();
	}

	@PostMapping("/musteri-dogrula")
	public ResponseEntity<Object> musteriDogrula(@RequestBody Customer customer) {
		if (customerService.customerDogrula(customer.getCustomerName(),customer.getCustomerSurname())) {
			return ResponseHandler.generateResponse(HttpStatus.OK, "Doğrulama başarılı.");
		} else {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Doğrulama başarısız.");
		}
	}

	@PostMapping("/musteri-ekle")
    public ResponseEntity<Customer> musteriEkle(@RequestBody Customer customer) {
        Customer newCustomer = customerService.addCustomer(customer);
        return ResponseEntity.ok(newCustomer);
    }
}
