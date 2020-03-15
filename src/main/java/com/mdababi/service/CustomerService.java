package com.mdababi.service;

import java.util.List;

import com.mdababi.api.v1.model.CustomerDTO;

public interface CustomerService {
	List<CustomerDTO> getAllCustomers();
	CustomerDTO getById(Long id);
}
