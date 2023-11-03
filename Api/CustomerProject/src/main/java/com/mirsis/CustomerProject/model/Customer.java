package com.mirsis.CustomerProject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;


import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "customer_name")
	@Length(min = 3, max = 50)
	private String customerName;

	@Column(name = "customer_surname")
	@Length(min = 2, max = 50)
	private String customerSurname;

	@Column(name = "gender")
	@Length(max = 1)
	private String gender;

	@Column(name = "birthdate")
	private Date birthdate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="city_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	City city;

	@Column(name = "address")
	@Length(min = 10, max = 100 )
	private String address;

	@Column(name = "is_disabled")
	@Length(max = 1)
	private String isDisabled;

	@Column(name = "phone_number")
	//@Pattern(regexp ="[0-9\\s]{12}")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSurname() {
		return customerSurname;
	}

	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		if(birthdate == null){
			this.birthdate = new Date();
		}else{			
			this.birthdate = birthdate;
		}
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer(int id, @Length(min = 3, max = 50) String customerName,
			@Length(min = 2, max = 50) String customerSurname, @Length(max = 1) String gender, Date birthdate,
			City city, @Length(min = 10, max = 100) String address, @Length(max = 1) String isDisabled,
			@Pattern(regexp = "[0-9\\s]{12}") String phoneNumber, String email) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerSurname = customerSurname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.city = city;
		this.address = address;
		this.isDisabled = isDisabled;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Customer() {
		
	}

	

}
