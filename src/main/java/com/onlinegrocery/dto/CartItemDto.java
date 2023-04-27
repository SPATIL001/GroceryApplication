package com.onlinegrocery.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartItemDto {
	private long quantity;
	private double total;
	private List<ProductDto> products;
	
}
