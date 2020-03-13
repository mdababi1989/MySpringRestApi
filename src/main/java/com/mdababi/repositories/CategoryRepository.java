package com.mdababi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mdababi.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
