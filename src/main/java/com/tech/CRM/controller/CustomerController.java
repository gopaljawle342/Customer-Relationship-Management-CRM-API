package com.tech.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tech.CRM.entity.Customer;
import com.tech.CRM.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("Customer creation failed due to validation errors: {}", result.getAllErrors());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        logger.info("Creating customer: {}", customer);
        Customer createdCustomer = customerService.createCustomer(customer);
        logger.info("Customer created successfully: {}", createdCustomer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        logger.info("Fetching customer with ID: {}", id);
        Customer customer = customerService.getCustomer(id);
        logger.info("Customer retrieved successfully: {}", customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer) {
        logger.info("Updating customer with ID: {}", id);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        logger.info("Customer updated successfully: {}", updatedCustomer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateCustomer(@PathVariable Long id) {
        logger.info("Deactivating customer with ID: {}", id);
        customerService.deactivateCustomer(id);
        logger.info("Customer with ID: {} has been deactivated", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestParam(required = false) String firstName,
                                                          @RequestParam(required = false) String lastName) {
        logger.info("Searching customers by firstName: '{}' and lastName: '{}'", firstName, lastName);
        List<Customer> customers = customerService.searchCustomers(firstName, lastName);
        logger.info("Found {} customers", customers.size());
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
