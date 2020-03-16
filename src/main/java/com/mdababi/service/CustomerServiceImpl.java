package com.mdababi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mdababi.domain.Customer;
import org.springframework.stereotype.Service;

import com.mdababi.api.v1.mapper.CustomerMapper;
import com.mdababi.api.v1.model.CustomerDTO;
import com.mdababi.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        super();
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customer -> {
            CustomerDTO customerDTO = customerMapper.CustomerToCustomerDTO(customer);
            customerDTO.setCustomer_url("/api/v1/customers/" + customer.getId());
            return customerDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getById(Long id) {
        return customerRepository.findById(id).map(customerMapper::CustomerToCustomerDTO).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.CustomerDTOToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.CustomerDTOToCustomer(customerDTO);
        customer.setId(id);
		return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
           if(customerDTO.getFirstName() !=null)
               customer.setFirstName(customerDTO.getFirstName());
           if(customerDTO.getLastName()!=null)
               customer.setLastName(customerDTO.getLastName());
            CustomerDTO returnDto = customerMapper.CustomerToCustomerDTO(customerRepository.save(customer));
            returnDto.setCustomer_url("/api/v1/customers/" + id);
           return returnDto;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDto = customerMapper.CustomerToCustomerDTO(savedCustomer);
        returnDto.setCustomer_url("/api/v1/customers/" + savedCustomer.getId());
        return returnDto;

    }


}
