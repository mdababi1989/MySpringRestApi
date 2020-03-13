package com.mdababi.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CustomerDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String customer_url;
	
	
}
