package com.sunbeaminfo.service;

import com.sunbeaminfo.DTO.CartDTO;
import com.sunbeaminfo.DTO.ProductDTO;
import com.sunbeaminfo.entities.Products;

import java.util.List;

public interface CartService {

    List<CartDTO> getAllCarts();

    CartDTO getCartById(Long id);

    CartDTO createCart(CartDTO cartDTO);

    CartDTO updateCart(Long id, CartDTO cartDTO);

    void deleteCart(Long id);

    List<Products> getAllProductsInCart(Long cartId);

    // void addProductToCart(Long cartId, ProductInCartDTO productDTO);
     CartDTO addProductToCart(Long cartId, ProductDTO productDTO);
    
}
