package com.mdababi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

	@Test
	void createNewCustomer() {
		CustomerDTO customerDTO = CustomerDTO.builder().firstName("mohamed").lastName("dababi").build();
		Customer customer = Customer.builder().firstName("mohamed").lastName("dababi").id(1L).build();
		when(customerRepository.save(any())).thenReturn(customer);
		CustomerDTO customerDTO1 = customerService.createNewCustomer(customerDTO);
		assertEquals("mohamed", customerDTO1.getFirstName());
		assertEquals("dababi", customerDTO1.getLastName());
		assertEquals("/api/v1/customers/1", customerDTO1.getCustomer_url());

	}

	@Test
	void saveCustomerByDTO() throws  Exception{
		CustomerDTO customerDTO = CustomerDTO.builder().firstName("mohamed").lastName("dababi").build();
		Customer customer = Customer.builder().id(1L).firstName("mohamed").lastName("dababi").id(1L).build();
		when(customerRepository.save(any())).thenReturn(customer);
		CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);
		assertEquals("mohamed", savedDto.getFirstName());
		assertEquals("dababi", savedDto.getLastName());
		assertEquals("/api/v1/customers/1", savedDto.getCustomer_url());
	}

	@Test
    void deleteCustomerByIdtest(){
	    Long id = 1L;
	    customerRepository.deleteById(id);
	    verify(customerRepository, times(1)).deleteById(anyLong());
    }



}
