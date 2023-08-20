package com.sunbeaminfo.controller;

import com.sunbeaminfo.DTO.CartDTO;
import com.sunbeaminfo.DTO.ProductDTO;
import com.sunbeaminfo.entities.Products;
import com.sunbeaminfo.service.CartService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<CartDTO> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
        CartDTO cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{cartId}/products")
    public ResponseEntity<List<Products>> getAllProductsInCart(@PathVariable Long cartId) {
        List<Products> productsInCart = cartService.getAllProductsInCart(cartId);
        return ResponseEntity.ok(productsInCart);
    }

    @PostMapping
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO) {
        CartDTO createdCart = cartService.createCart(cartDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO cartDTO) {
        CartDTO updatedCart = cartService.updateCart(id, cartDTO);
        if (updatedCart != null) {
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}/add-product")
    public ResponseEntity<String> addProductToCart(
            @PathVariable Long cartId,
            @RequestBody ProductDTO productDTO) {

        try {
            cartService.addProductToCart(cartId, productDTO);
            return ResponseEntity.ok("Product added to cart successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
