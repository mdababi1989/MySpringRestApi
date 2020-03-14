package com.mdababi.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mdababi.api.v1.model.CustomerDTO;
import com.mdababi.service.CustomerService;

class CustomerControllerTest {
	@InjectMocks
	private CustomerController customerController;
	@Mock
	private CustomerService customerService;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	void testListCategories() throws Exception {
		List<CustomerDTO> customers = Arrays.asList(new CustomerDTO(), new CustomerDTO(), new CustomerDTO());
		when(customerService.getAllCustomers()).thenReturn(customers);
		mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", hasSize(3)));
	}

	@Test
	void testGetByLastNameCategories() throws Exception {
		CustomerDTO customer = CustomerDTO.builder().id(1L).firstName("Mohamed").lastName("Dababi").build();
		when(customerService.getByLastName(anyString())).thenReturn(customer);
		mockMvc.perform(get("/api/v1/customers/Dababi").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", equalTo("Dababi")));

	}

}
