package com.mdababi.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mdababi.domain.Category;
import com.mdababi.domain.Customer;
import com.mdababi.repositories.CategoryRepository;
import com.mdababi.repositories.CustomerRepository;


@Component
public class Bootstrap implements CommandLineRunner {

	private CategoryRepository categoryRepository;
	private CustomerRepository customerRepository;
	
	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		loadCustomers();
	}

	private void loadCustomers() {
		Customer mohamed = Customer.builder().firstName("Mohamed").lastName("Dababi").build();
		Customer wael = Customer.builder().firstName("Wael").lastName("Ayadi").build();
		Customer houcem = Customer.builder().firstName("Abdi").lastName("houcem").build();
		Customer brahim = Customer.builder().firstName("Cheyby").lastName("Brahim").build();
		Customer tarek = Customer.builder().firstName("Hammouda").lastName("Tarek").build();
		
		customerRepository.save(mohamed);
		customerRepository.save(wael);
		customerRepository.save(houcem);
		customerRepository.save(brahim);
		customerRepository.save(tarek);
		
		System.out.println("Customers Loaded = " + customerRepository.count());

	}

	private void loadCategories() {
		Category fruits = Category.builder().name("Fruits").build();
		Category dried = Category.builder().name("Dried").build();
		Category fresh = Category.builder().name("fresh").build();
		Category exotic = Category.builder().name("Exotic").build();
		Category nuts = Category.builder().name("nuts").build();

		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);

		System.out.println("Categories Loaded = " + categoryRepository.count());
	}

}
