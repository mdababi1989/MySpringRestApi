package com.mdababi.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.mdababi.api.v1.model.CustomerDTO;
import com.mdababi.domain.Customer;

class CustomerMapperTest {
	private CustomerMapper customerMapper;
	
	@BeforeEach
	void setUp() throws Exception {
		customerMapper = CustomerMapper.INSTANCE;
	}

	@Test
	void testCustomerToCustomerDTO() {
		Customer mohamed = Customer.builder().id(1L).firstName("Mohamed").lastName("Dababi").build();
		CustomerDTO customerDTO = customerMapper.CustomerToCustomerDTO(mohamed);
		assertEquals(Long.valueOf(1L), customerDTO.getId());
		assertEquals("Mohamed", customerDTO.getFirstName());
		assertEquals("Dababi", customerDTO.getLastName());
	}

}
