package com.mdababi.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.mdababi.api.v1.model.CustomerDTO;
import com.mdababi.api.v1.model.CustomerListDTO;
import com.mdababi.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
	public static final String BASE_URL = "/api/v1/customers/";

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListDTO listCustomers() {
		return new CustomerListDTO(customerService.getAllCustomers());
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO getByIdCustomers(@PathVariable Long id) {
		return customerService.getById(id);
	}

	@PostMapping("create")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createNewCustomer(@RequestBody  CustomerDTO customerDTO){
		return customerService.createNewCustomer(customerDTO);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO updateCustomer(@RequestBody  CustomerDTO customerDTO, @PathVariable Long id){
		return customerService.saveCustomerByDTO(id, customerDTO);
	}

	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO patchCustomer(@RequestBody  CustomerDTO customerDTO, @PathVariable Long id){
		return customerService.patchCustomer(id, customerDTO);
	}

    @DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
    public void deleteCustomerById( @PathVariable Long id){
		customerService.deleteCustomerById(id);
    }

}
