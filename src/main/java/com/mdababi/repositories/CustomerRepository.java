package com.mdababi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdababi.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
