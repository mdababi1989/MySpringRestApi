package com.mdababi.bootstrap;

import com.mdababi.domain.Vendor;
import com.mdababi.repositories.VendorRepository;
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
	private VendorRepository vendorRepository;

	
	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		loadCustomers();
		loadVendors();
	}

	private void loadVendors() {
		Vendor western = Vendor.builder().name("Western Tasty Fruits Ltd.").build();
		Vendor exotic = Vendor.builder().name("Exotic Fruits Company").build();
		Vendor home = Vendor.builder().name("Home Fruits").build();
		Vendor fun = Vendor.builder().name("Fun Fresh Fruits Ltd.").build();
		Vendor nuts = Vendor.builder().name("Nuts for Nuts Company").build();

		vendorRepository.save(western);
		vendorRepository.save(exotic);
		vendorRepository.save(home);
		vendorRepository.save(fun);
		vendorRepository.save(nuts);
		System.out.println("Vendor Loaded = " + vendorRepository.count());
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
