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

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@GetMapping
	public ResponseEntity<CustomerListDTO> listCategories() {
		return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomers()),
				HttpStatus.OK);
	}

	@GetMapping("{name}")
	public ResponseEntity<CustomerDTO> getByNameCategories(@PathVariable String name) {
		return new ResponseEntity<CustomerDTO>(customerService.getByLastName(name), HttpStatus.OK);
	}

}
