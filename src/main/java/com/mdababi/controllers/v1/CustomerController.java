package com.mdababi.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mdababi.api.v1.model.CustomerDTO;
import com.mdababi.api.v1.model.CustomerListDTO;
import com.mdababi.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@GetMapping
	public ResponseEntity<CustomerListDTO> listCustomers() {
		return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomers()),
				HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<CustomerDTO> getByIdCustomers(@PathVariable Long id) {
		return new ResponseEntity<CustomerDTO>(customerService.getById(id), HttpStatus.OK);
	}

}
