package com.mdababi.service;

import com.mdababi.api.v1.mapper.CustomerMapper;
import com.mdababi.api.v1.model.CustomerDTO;
import com.mdababi.bootstrap.Bootstrap;
import com.mdababi.domain.Customer;
import com.mdababi.repositories.CategoryRepository;
import com.mdababi.repositories.CustomerRepository;
import com.mdababi.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
class CustomerServiceImplTest {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;


    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Loading customer Data");
        System.out.println(customerRepository.findAll().size());
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }


    @Test
    void patchCustomerUpdateFirstName() {
        String updatedName= "UpdatedName";
        long id = getCustomerIdValue();
        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();
        CustomerDTO customerDTO = CustomerDTO.builder().firstName(updatedName).build();
        customerService.patchCustomer(id, customerDTO);
        Customer updatedCustomer  =customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
       assertEquals(updatedName, updatedCustomer.getFirstName());
        assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));

    }

    @Test
    void patchCustomerUpdateLastName() {
        String updatedName= "UpdatedName";
        long id = getCustomerIdValue();
        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();
        CustomerDTO customerDTO = CustomerDTO.builder().lastName(updatedName).build();
        customerService.patchCustomer(id, customerDTO);
        Customer updatedCustomer  =customerRepository.findById(id).get();
        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getLastName());
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
    }

    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();
        return customers.get(0).getId();

    }

}