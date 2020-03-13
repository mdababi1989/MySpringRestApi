package com.mdababi.service;

import java.util.List;
import java.util.stream.Collectors;

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
		return customerRepository.findAll().stream().map(customerMapper::CustomerToCustomerDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getByLastName(String name) {
		return customerMapper.CustomerToCustomerDTO(customerRepository.getByLastName(name));
	}

}
