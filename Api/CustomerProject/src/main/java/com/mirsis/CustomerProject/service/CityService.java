package com.mirsis.CustomerProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mirsis.CustomerProject.model.City;
import com.mirsis.CustomerProject.repository.ICityRepository;

@Service
public class CityService {

	ICityRepository cityRepository;

	public CityService(ICityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}
	
	public City getOneCityById(Long id) {
		return cityRepository.findById(id).orElse(null);
	}
	
	public List<City> getAllCity() {
		return cityRepository.findAll();
    }
}
