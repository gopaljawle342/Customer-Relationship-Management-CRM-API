package com.tech.CRM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tech.CRM.entity.Customer;
import com.tech.CRM.exception.DuplicateEntryException;
import com.tech.CRM.exception.ResourceNotFoundException;
import com.tech.CRM.repository.CustomerRepository;

@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;

	public Customer createCustomer(Customer customer) {
		logger.info("Creating customer: {}", customer);
		try {
			Customer savedCustomer = customerRepository.save(customer);
			logger.info("Customer created successfully: {}", savedCustomer);
			return savedCustomer;
		} catch (DataIntegrityViolationException ex) {
			logger.error("Duplicate entry for email: {}", customer.getEmail(), ex);
			throw new DuplicateEntryException("Email '" + customer.getEmail() + "' already exists");
		}

	}

	public Customer getCustomer(Long id) {
		logger.info("Fetching customer with ID: {}", id);
		return customerRepository.findById(id).orElseThrow(() -> {
			logger.error("Customer not found with ID: {}", id);
			return new ResourceNotFoundException("Resource Not Found");
		});
	}

	public Customer updateCustomer(Long id, Customer updatedCustomer) {
		logger.info("Updating customer with ID: {}", id);
		Customer customer = customerRepository.findById(id).orElseThrow(() -> {
			logger.error("Customer not found with ID: {}", id);
			return new ResourceNotFoundException("Resource Not Found");
		});
		customer.setFirstName(updatedCustomer.getFirstName());
		customer.setLastName(updatedCustomer.getLastName());
		customer.setEmail(updatedCustomer.getEmail());
		customer.setDateOfBirth(updatedCustomer.getDateOfBirth());
		// return customerRepository.save(customer);
		try {
			Customer savedCustomer = customerRepository.save(customer);
			logger.info("Customer updated successfully: {}", savedCustomer);
			return savedCustomer;
		} catch (DataIntegrityViolationException ex) {
			logger.error("Duplicate entry for email: {}", customer.getEmail(), ex);
			throw new DuplicateEntryException("Email '" + customer.getEmail() + "' already exists");
		}
	}

	public Customer deactivateCustomer(Long id) {
		logger.info("Deactivating customer with ID: {}", id);
		Customer customer = customerRepository.findById(id).orElseThrow(() -> {
			logger.error("Customer not found with ID: {}", id);
			return new ResourceNotFoundException("Resource Not Found");
		});
		customer.setIsActive(false);
		Customer updatedCustomer = customerRepository.save(customer);
		logger.info("Customer deactivated successfully: {}", updatedCustomer);
		return updatedCustomer;

	}

	public List<Customer> searchCustomers(String firstName, String lastName) {
		logger.info("Searching customers by firstName: '{}' and lastName: '{}'", firstName, lastName);

		List<Customer> customers = customerRepository.findByFirstNameContainingAndLastNameContaining(firstName,
				lastName);
		logger.info("Found {} customers", customers.size());
		return customers;
	}
}
