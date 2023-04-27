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
 
import com.onlinegrocery.controller.WishlistController;
import com.onlinegrocery.dto.WishlistDto;
import com.onlinegrocery.entity.Wishlist;
import com.onlinegrocery.service.WishlistService;
 

@ExtendWith(MockitoExtension.class)
public class WishlistControllerTest {

     @Mock
        private WishlistService wishlistService;
 
        @InjectMocks
        private WishlistController wishlistController;
 
        @Test
        public void addWishlistTest() {
            WishlistDto wishlistDTO = new WishlistDto();
            Wishlist wishlist = new Wishlist();
            when(wishlistService.addWishlist(wishlistDTO)).thenReturn(wishlist);
            ResponseEntity<Wishlist> response = wishlistController.addWishlist(wishlistDTO);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(wishlist, response.getBody());
        }
 
        @Test
        public void deleteItemFromWishlistTest() {
            int wishlistId = 1;
            ResponseEntity<String> response = wishlistController.deleteWishlist(wishlistId);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("deleted successfully", response.getBody());
        }
 
        @Test
        public void getCartByIdTest() {
            int wishlistId = 1;
            Wishlist wishlist = new Wishlist();
            when(wishlistService.getWishlistById(wishlistId)).thenReturn(wishlist);
            ResponseEntity<Wishlist> response = wishlistController.getWishlistById(wishlistId);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(wishlist, response.getBody());
        }
 
        @Test
        public void getAllCartItemsTest() {
            String username = "testuser";
            List<Wishlist> cartList = new ArrayList<>();
            when(wishlistService.getAllWishlist(username)).thenReturn(cartList);
            List<Wishlist> response = wishlistController.getAllWishlist(username);
            assertEquals(cartList, response);
        }
 

}


