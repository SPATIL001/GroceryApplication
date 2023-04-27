
package com.onlinegrocery.serviceImpl;
 
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlinegrocery.dto.CartDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Cart;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.exceptionmessages.ExceptionMessages;
import com.onlinegrocery.exceptions.CartNotFoundException;
import com.onlinegrocery.exceptions.ProductNotFoundException;
import com.onlinegrocery.exceptions.UserNotFoundException;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.repo.CartRepo;
import com.onlinegrocery.repo.ProductRepo;
import com.onlinegrocery.service.CartService;
 
@Service
public class CartServiceImpl implements CartService {
 
    @Autowired
    private CartRepo cartRepo;
 
    @Autowired
    private ProductRepo productRepo;
 
    @Autowired
    private AppUserRepo userRepo;
 
    @Override
    public Cart addItemToCart(CartDto cartDTO) {
        Cart cart = new Cart();
        Product product = productRepo.findById(cartDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException());
        List<Product> listProducts = new ArrayList<>();
        cart.setProductCount(cartDTO.getProductCount());
        cart.setTotalPrice(cartDTO.getTotalPrice());
        cart.setUserName(cartDTO.getUserName());
        AppUser user = userRepo.findById(cartDTO.getUserid()).orElseThrow(() -> new UserNotFoundException());
        cart.setUser(user);
        listProducts.add(product);
        cart.setProducts(listProducts);
        return cartRepo.save(cart);
    }
 
    @Override
    public Cart updateCart(int cartId, CartDto cartDTO) {
        Product product = productRepo.findById(cartDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException());
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(ExceptionMessages.CART_NOT_FOUND_WITH_ID));
        cart.setProductCount(cartDTO.getProductCount());
        cart.setTotalPrice(cartDTO.getTotalPrice());
        if (!cart.getProducts().contains(product)) {
            cart.getProducts().add(product);
        }
        cartRepo.save(cart);
        return cart;
    }
 
    @Override
    public String deleteItemFromCart(int cartId) {
        if (!cartRepo.existsById(cartId)) {
            throw new CartNotFoundException();
        }
        cartRepo.deleteById(cartId);
        return "Deleted successfully";
    }
 
    @Override
    public Cart getCartById(int cartId) {
        return cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException());
    }
 
    @Override
    public List<Cart> getAllCartItems(String username) {
        try {
            AppUser user = userRepo.findByUserName(username);
            if (user == null) {
                throw new UserNotFoundException();
            }
            List<Cart> list = cartRepo.findAll()
                    .stream()
                    .filter(x -> x.getUser().getUserName().equals(user.getUserName()))
                    .collect(Collectors.toList());
            return list;
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }
 
    @Override
    public void deleteByUserName(String userName) {
         cartRepo.deleteByUserName(userName);
    }
 
}

