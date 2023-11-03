package com.mirsis.CustomerProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirsis.CustomerProject.model.City;
import com.mirsis.CustomerProject.service.CityService;


@RestController
@RequestMapping("/city")
public class CityController {
	
	@Autowired
    private CityService cityService;
	
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping("/get")
    public List<City> getAllPosts(){
        return cityService.getAllCity();
    }
}
