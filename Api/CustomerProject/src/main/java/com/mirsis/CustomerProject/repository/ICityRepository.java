package com.mirsis.CustomerProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mirsis.CustomerProject.model.City;

@Repository
public interface ICityRepository extends JpaRepository <City,Long> {

}
