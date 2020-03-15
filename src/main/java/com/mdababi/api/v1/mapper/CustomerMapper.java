package com.mdababi.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.mdababi.api.v1.model.CustomerDTO;
import com.mdababi.domain.Customer;

@Mapper
public interface CustomerMapper {
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	CustomerDTO CustomerToCustomerDTO(Customer customer);
	
}
