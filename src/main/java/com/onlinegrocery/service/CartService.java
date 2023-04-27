package com.onlinegrocery.service;

import java.util.List;
import com.onlinegrocery.dto.CartDto;
import com.onlinegrocery.entity.Cart;
import com.onlinegrocery.entity.Order;

public interface CartService {
	String deleteItemFromCart(int cartId);
	Cart getCartById(int cartId);
	List<Cart> getAllCartItems(String username);
	Cart updateCart(int cartId, CartDto cartDTO);
	Cart addItemToCart(CartDto cartDTO);
	void deleteByUserName(String username);
}
