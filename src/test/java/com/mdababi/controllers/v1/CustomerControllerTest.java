package com.mdababi.controllers.v1;

import static com.mdababi.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testListCustomers() throws Exception {
        CustomerDTO customerDTO1 = CustomerDTO.builder().firstName("test1").lastName("test1").build();
        CustomerDTO customerDTO2 = CustomerDTO.builder().firstName("test2").lastName("test2").build();
        CustomerDTO customerDTO3 = CustomerDTO.builder().firstName("test3").lastName("test3").build();

        List<CustomerDTO> customers = Arrays.asList(customerDTO1, customerDTO2, customerDTO3);
        when(customerService.getAllCustomers()).thenReturn(customers);
        mockMvc.perform(get(CustomerController.BASE_URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(3)));
    }

    @Test
    void testGetByIdCustomers() throws Exception {
        CustomerDTO customer = CustomerDTO.builder().firstName("Mohamed").lastName("Dababi").customer_url("/api/v1/customers/1").build();
        when(customerService.getById(any())).thenReturn(customer);
        mockMvc.perform(get(CustomerController.BASE_URL +"1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.lastName", equalTo("Dababi")));
    }


    @Test
    void createNewCustomerTest() throws Exception {
        CustomerDTO customer = CustomerDTO.builder().firstName("Mohamed").lastName("Dababi").build();
        CustomerDTO returnCustomer = CustomerDTO.builder().firstName("Mohamed").lastName("Dababi").customer_url("/api/v1/customers/1").build();
        when(customerService.createNewCustomer(any())).thenReturn(returnCustomer);
        mockMvc.perform(post(CustomerController.BASE_URL +"create").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(customer))).andExpect(status().isCreated()).
                andExpect(jsonPath("$.firstName", equalTo("Mohamed")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }

    @Test
    void updateCustomerTest() throws Exception {
        CustomerDTO customer = CustomerDTO.builder().firstName("Mohamed").lastName("Dababi").build();
        CustomerDTO returnCustomer = CustomerDTO.builder().firstName("Mohamed").lastName("Dababi").customer_url("/api/v1/customers/1").build();
        when(customerService.saveCustomerByDTO(anyLong(),any())).thenReturn(returnCustomer);
        mockMvc.perform(put(CustomerController.BASE_URL + "1").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(customer))).andExpect(status().isOk() ).
                andExpect(jsonPath("$.firstName", equalTo("Mohamed")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }

    @Test
    void patchCustomerTest() throws Exception {
        CustomerDTO customer = CustomerDTO.builder().firstName("Mohamed").build();
        CustomerDTO returnCustomer = CustomerDTO.builder().firstName("Mohamed").lastName("Dababi").customer_url("/api/v1/customers/1").build();
        when(customerService.patchCustomer(anyLong(),any())).thenReturn(returnCustomer);
        mockMvc.perform(patch(CustomerController.BASE_URL +"1").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(customer))).andExpect(status().isOk() ).
                andExpect(jsonPath("$.firstName", equalTo("Mohamed")))
                .andExpect(jsonPath("$.lastName", equalTo("Dababi")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }


}
