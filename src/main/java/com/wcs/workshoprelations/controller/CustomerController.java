package com.wcs.workshoprelations.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wcs.workshoprelations.dto.CustomerDto;
import com.wcs.workshoprelations.entity.Customer;
import com.wcs.workshoprelations.repository.CustomerRepository;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	//Create
	@PostMapping
	public Customer create(@Valid @RequestBody CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setFirstname(customerDto.getFirstname());
		customer.setLastname(customerDto.getLastname());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
		customer.setAddress(customerDto.getAddress());
		customer.setBirthdate(customerDto.getBirthdate());
		customer.setPhone(customerDto.getPhone());

		return customerRepository.save(customer);
	}
	
	//Get all
	@GetMapping
	public List<Customer> getAll(){
		return customerRepository.findAll();
	}
	
	//Get one
	@GetMapping("/{id}")
	public Customer getById(@PathVariable(required = true) Long id) {
		Optional<Customer> optCustomer = customerRepository.findById(id);
		
		if(optCustomer.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return optCustomer.get();
	}
	
	//Update
	@PutMapping("/{id}")
	public Customer update(@PathVariable(required = true) Long id, @RequestBody CustomerDto customerDto) {
		Optional<Customer> optCustomer = customerRepository.findById(id);
		
		if(optCustomer.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Customer customerToUpdate = new Customer();
		customerToUpdate.setFirstname(customerDto.getFirstname());
		customerToUpdate.setLastname(customerDto.getLastname());
		customerToUpdate.setEmail(customerDto.getEmail());
		customerToUpdate.setPassword(customerDto.getPassword());
		customerToUpdate.setAddress(customerDto.getAddress());
		customerToUpdate.setBirthdate(customerDto.getBirthdate());
		customerToUpdate.setPhone(customerDto.getPhone());
		
		return customerRepository.save(customerToUpdate);	
	}

	//Delete
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(required = true) Long id) {
		Boolean exist = customerRepository.existsById(id);
		
		if(!exist) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		customerRepository.deleteById(id);
	}
}
