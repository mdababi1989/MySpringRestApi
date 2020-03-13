package com.mdababi.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mdababi.domain.Category;
import com.mdababi.repositories.CategoryRepository;


@Component
public class Bootstrap implements CommandLineRunner {

	private CategoryRepository categoryRepository;

	public Bootstrap(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {
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

		System.out.println("Data Loaded = " + categoryRepository.count());
	}

}
