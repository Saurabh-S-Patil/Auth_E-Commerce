// package com.sunbeaminfo.controller;

// import com.sunbeaminfo.DTO.CartDTO;
// import com.sunbeaminfo.DTO.ProductDTO;
// import com.sunbeaminfo.entities.Cart;
// import com.sunbeaminfo.entities.CartProducts;
// import com.sunbeaminfo.entities.Products;
// import com.sunbeaminfo.service.CartService;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// import javax.persistence.EntityNotFoundException;

// @RestController
// @RequestMapping("/carts")
// public class CartController {

//     private final CartService cartService;

//     public CartController(CartService cartService) {
//         this.cartService = cartService;
//     }

//     @GetMapping
//     public ResponseEntity<List<CartDTO>> getAllCarts() {
//         List<CartDTO> carts = cartService.getAllCarts();
//         return ResponseEntity.ok(carts);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
//         CartDTO cart = cartService.getCartById(id);
//         return ResponseEntity.ok(cart);
//     }

//     @GetMapping("/{cartId}/products")
//     public ResponseEntity<List<CartProducts>> getAllProductsInCart(@PathVariable Long cartId) {
//         List<CartProducts> productsInCart = cartService.getAllProductsInCart(cartId);
//         return ResponseEntity.ok(productsInCart);
//     }

//     @PostMapping
//     public ResponseEntity<Cart> createCart(@RequestBody CartDTO cartDTO) {
//         Cart createdCart = cartService.createCart(cartDTO);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO cartDTO) {
//         CartDTO updatedCart = cartService.updateCart(id, cartDTO);
//         if (updatedCart != null) {
//             return ResponseEntity.ok(updatedCart);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
//         cartService.deleteCart(id);
//         return ResponseEntity.noContent().build();
//     }

//     // @PostMapping("/{cartId}/add-product")
//     // public ResponseEntity<String> addProductToCart(
//     //         @PathVariable Long cartId,
//     //         @RequestBody ProductDTO productDTO) {

//     //     try {
//     //         cartService.addProductToCart(cartId, productDTO);
//     //         return ResponseEntity.ok("Product added to cart successfully.");
//     //     } catch (EntityNotFoundException e) {
//     //         return ResponseEntity.notFound().build();
//     //     }
//     // }
// }

package com.sunbeaminfo.controller;

import com.sunbeaminfo.DTO.CartDTO;
import com.sunbeaminfo.DTO.CartProductResponse;
import com.sunbeaminfo.DTO.ProductDTO;
import com.sunbeaminfo.entities.Cart;
import com.sunbeaminfo.entities.CartProducts;
import com.sunbeaminfo.entities.Products;
import com.sunbeaminfo.service.CartService;
import com.sunbeaminfo.service.CartServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
        CartDTO cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{userId}/products")
    public ResponseEntity<CartProductResponse> getAllProductsInCart(@PathVariable Long userId) {
        CartProductResponse productsInCart = cartService.getAllProductsInCart(userId);
        return ResponseEntity.ok(productsInCart);
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody CartDTO cartDTO) {
        Cart createdCart = cartService.createCart(cartDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
    }

    @PutMapping("/{userId}/{productId}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long userId,@PathVariable Long productId) {
       
    //To be defined after product related APIs completed...
   
            return ResponseEntity.notFound().build();
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/addProduct/{productId}")
    public ResponseEntity<String> addProductToCart(
            @PathVariable Long userId,
            @PathVariable Long productId) {

        try {
            cartService.addProductToCart( userId,productId);
            return ResponseEntity.ok("Product added to cart successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
 
    @PostMapping("/{userId}/removeProduct/{productId}")
public ResponseEntity<String> removeProductToCart(
        @PathVariable Long userId,
        @PathVariable Long productId) {

    try {
        if (cartService.removeProductFromCart(userId, productId)) {
            return ResponseEntity.ok("Product removed from cart successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

   
}