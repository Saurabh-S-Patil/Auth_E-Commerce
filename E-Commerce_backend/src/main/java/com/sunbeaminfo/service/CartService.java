// package com.sunbeaminfo.service;

// import com.sunbeaminfo.DTO.CartDTO;
// import com.sunbeaminfo.DTO.ProductDTO;
// import com.sunbeaminfo.entities.Cart;
// import com.sunbeaminfo.entities.CartProducts;
// import com.sunbeaminfo.entities.Products;

// import java.util.List;

// public interface CartService {

//     List<CartDTO> getAllCarts();

//     CartDTO getCartById(Long id);

//     Cart createCart(CartDTO cartDTO);

//     CartDTO updateCart(Long id, CartDTO cartDTO);

//     void deleteCart(Long id);

//     List<CartProducts> getAllProductsInCart(Long cartId);

//     // void addProductToCart(Long cartId, ProductInCartDTO productDTO);
//     // CartDTO addProductToCart(Long cartId, ProductDTO productDTO);
    
// }

package com.sunbeaminfo.service;

import com.sunbeaminfo.DTO.CartDTO;
import com.sunbeaminfo.DTO.CartProductResponse;
import com.sunbeaminfo.DTO.ProductDTO;
import com.sunbeaminfo.entities.Cart;
import com.sunbeaminfo.entities.CartProducts;
import com.sunbeaminfo.entities.Products;

import java.util.List;
import java.util.Set;

public interface CartService {

   // List<CartDTO> getAllCarts();

    CartDTO getCartById(Long cartId);

    Cart createCart(CartDTO cartDTO);

    CartDTO updateCart(Long userId, Long productId);

    String deleteCart(Long cartId);

    CartProductResponse getAllProductsInCart(Long userId);

    // CartDTO addProductToCart(Long userId ,Long productId);
   
    boolean removeProductFromCart(Long userId ,Long productId);
   
}