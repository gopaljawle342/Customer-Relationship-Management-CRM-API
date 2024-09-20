package com.tech.CRM.repository;

import org.springframework.data.repository.CrudRepository;

import com.tech.CRM.entity.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName);
}