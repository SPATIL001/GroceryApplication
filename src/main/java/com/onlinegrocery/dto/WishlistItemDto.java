package com.onlinegrocery.dto;

import java.util.List;

import lombok.Data;

@Data
public class WishlistItemDto {
	private double total;
	private List<ProductDto> products;

}
