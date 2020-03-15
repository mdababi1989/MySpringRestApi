package com.mdababi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mdababi.api.v1.mapper.CustomerMapper;
import com.mdababi.api.v1.model.CustomerDTO;
import com.mdababi.domain.Customer;
import com.mdababi.repositories.CustomerRepository;

class CustomerServiceTest {
	
	CustomerService customerService;
	@Mock
	CustomerRepository customerRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}
	
	@Test
	void testGetAllCustomers() {
		List<Customer> customers = Arrays.asList(new Customer(),new Customer(),new Customer());
		when(customerRepository.findAll()).thenReturn(customers);
		List<CustomerDTO> customerDTOs = customerService.getAllCustomers();
		assertEquals(3, customerDTOs.size());
		
	}

	@Test
	void testGetById() {
		Customer mohamed = Customer.builder().id(1L).firstName("Mohamed").lastName("Dababi").build();
		when(customerRepository.findById(any())).thenReturn(Optional.of(mohamed));
		CustomerDTO customerDTO = customerService.getById(1L);
		assertEquals("Mohamed", customerDTO.getFirstName());
		assertEquals("Dababi", customerDTO.getLastName());
	}

}
