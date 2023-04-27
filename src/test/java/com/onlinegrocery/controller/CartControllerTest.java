package com.onlinegrocery.controller;


 
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import com.onlinegrocery.controller.CartController;
import com.onlinegrocery.dto.CartDto;
import com.onlinegrocery.entity.Cart;
import com.onlinegrocery.service.CartService;
 
@ExtendWith(MockitoExtension.class)
public class CartControllerTest {
 
@Mock
private CartService cartService;
 
@InjectMocks
private CartController cartController;
 
@Test
public void addItemToCartTest() {
CartDto cartDTO = new CartDto();
Cart cart = new Cart();
when(cartService.addItemToCart(cartDTO)).thenReturn(cart);
ResponseEntity<Cart> response = cartController.addItemToCart(cartDTO);
assertEquals(HttpStatus.OK, response.getStatusCode());
assertEquals(cart, response.getBody());
}
 
@Test
public void updateCartTest() {
int cartId = 1;
CartDto cartDTO = new CartDto();
Cart cart = new Cart();
when(cartService.updateCart(cartId, cartDTO)).thenReturn(cart);
ResponseEntity<Cart> response = cartController.updateCart(cartId, cartDTO);
assertEquals(HttpStatus.OK, response.getStatusCode());
assertEquals(cart, response.getBody());
}
 
@Test
public void deleteItemFromCartTest() {
int cartId = 1;
ResponseEntity<String> response = cartController.deleteItemFromCart(cartId);
assertEquals(HttpStatus.OK, response.getStatusCode());
assertEquals("deleted", response.getBody());
}
 
@Test
public void getCartByIdTest() {
int cartId = 1;
Cart cart = new Cart();
when(cartService.getCartById(cartId)).thenReturn(cart);
ResponseEntity<Cart> response = cartController.getCartById(cartId);
assertEquals(HttpStatus.OK, response.getStatusCode());
assertEquals(cart, response.getBody());
}
 
@Test
public void getAllCartItemsTest() {
String username = "testuser";
List<Cart> cartList = new ArrayList<>();
when(cartService.getAllCartItems(username)).thenReturn(cartList);
List<Cart> response = cartController.getAllCartItems(username);
assertEquals(cartList, response);
}
 
@Test
public void deleteByUserNameTest() {
String username = "testuser";
cartController.deleteByUserName(username);
}
}
 


