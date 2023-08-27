package com.sunbeaminfo.service;

import com.sunbeaminfo.DTO.CartDTO;
import com.sunbeaminfo.DTO.CartProductResponse;
import com.sunbeaminfo.DTO.CartProductsDTO;
import com.sunbeaminfo.DTO.ProductDTO;
import com.sunbeaminfo.entities.Cart;
import com.sunbeaminfo.entities.CartProducts;
import com.sunbeaminfo.entities.Products;
import com.sunbeaminfo.entities.User;
import com.sunbeaminfo.dao.CartProductRepository;
import com.sunbeaminfo.dao.CartRepository;
import com.sunbeaminfo.dao.ProductsRepository;
import com.sunbeaminfo.dao.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductsRepository productRepository,UserRepository userRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository=userRepository;
        this.cartProductRepository=cartProductRepository;
    }

    @Override
    public CartDTO getCartById(Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        return optionalCart.map(this::convertToDTO).orElse(null);
    }

    @Override
    public Cart createCart(CartDTO cartDTO) {
        Cart cart = convertToEntity(cartDTO);
        User user = userRepository.findById(cartDTO.getUserId()).get();
        cart.setUser(user);
        Cart savedCart = cartRepository.save(cart);
        return savedCart;
    }

    @Override
    public CartDTO updateCart(Long userId, Long productId) {
    Products product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Cart cart = user.getCart();
       
        return  new CartDTO();
    }

    @Override
    public String deleteCart(Long cartId) {
    cartRepository.deleteById(cartId);
    return "success";
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUser().getId());
        cartDTO.setProductsList(cart.getProductsList());
        return cartDTO;
    }

    private Cart convertToEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDTO, cart);
        return cart;
    }

    @Transactional
    public CartDTO addProductToCart( Long userId,Long productId) {
     
        Products product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Cart cart = user.getCart();
        CartProducts cartProduct = new CartProducts();
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        // cartProduct.setColor(color);
        cartProduct.setPrice(product.getPrice());
        cartProduct.setQuantity(0);
        Set<CartProducts> productsList = cart.getProductsList();
        productsList.add(cartProduct);


        cartProductRepository.save(cartProduct);
        cartRepository.save(cart);        
         
        return convertToDTO(cart);
    }


@Transactional
@Override
public CartProductResponse getAllProductsInCart(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

    CartProductResponse cartProductResponse = new CartProductResponse();
    cartProductResponse.setId(cart.getId());

    Set<CartProductsDTO> cartProductDTOs = new HashSet<>();

    for (CartProducts cartProduct : cart.getProductsList()) {
        CartProductsDTO cartProductDTO = new CartProductsDTO();
        cartProductDTO.setId(cartProduct.getId()); // Assuming you want to set the cart product's ID
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(cartProduct.getProduct().getId());
        productDTO.setProductName(cartProduct.getProduct().getProductName());
        // Set other product properties here
        cartProductDTO.setProduct(productDTO);
        cartProductDTOs.add(cartProductDTO);
    }

    cartProductResponse.setProductsList(cartProductDTOs);

    return cartProductResponse;
}


@Transactional
public boolean removeProductFromCart(Long userId, Long productId) {
    Products product = productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));

    Cart cart = user.getCart();
    Set<CartProducts> productsList = cart.getProductsList();

    // Find the CartProducts entry associated with the given product and remove it
    CartProducts cartProductToRemove = null;
    for (CartProducts cartProduct : productsList) {
        if (cartProduct.getProduct().equals(product)) {
            cartProductToRemove = cartProduct;
            break;
        }
    }

    if (cartProductToRemove != null) {
        productsList.remove(cartProductToRemove);
        cartRepository.save(cart);
        return true;
    }

    cartRepository.save(cart);

    return false;
}


}