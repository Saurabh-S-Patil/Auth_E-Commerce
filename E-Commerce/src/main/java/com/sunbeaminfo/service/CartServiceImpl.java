package com.sunbeaminfo.service;

import com.sunbeaminfo.DTO.CartDTO;
import com.sunbeaminfo.DTO.ProductDTO;
import com.sunbeaminfo.entities.Cart;
import com.sunbeaminfo.entities.Products;
import com.sunbeaminfo.dao.CartRepository;
import com.sunbeaminfo.dao.ProductsRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductsRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductsRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CartDTO getCartById(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        return optionalCart.map(this::convertToDTO).orElse(null);
    }

    @Override
    public CartDTO createCart(CartDTO cartDTO) {
        Cart cart = convertToEntity(cartDTO);
        Cart savedCart = cartRepository.save(cart);
        return convertToDTO(savedCart);
    }

    @Override
    public CartDTO updateCart(Long id, CartDTO cartDTO) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            BeanUtils.copyProperties(cartDTO, cart, "id");
            Cart updatedCart = cartRepository.save(cart);
            return convertToDTO(updatedCart);
        }
        return null;
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        BeanUtils.copyProperties(cart, cartDTO);
        cartDTO.setUserId(cart.getUser().getId()); // Assuming User has a 'getId' method
        return cartDTO;
    }

    private Cart convertToEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDTO, cart);
        return cart;
    }

    @Override
    public List<Products> getAllProductsInCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            return cart.getProductsList();
        }
        return Collections.emptyList();
    }

    // public void addProductToCart(Long cartId, ProductDTO productDTO) {
    //     Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    //     Product product = productRepository.findById(productDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

    //     // Create a new Products instance and add it to the cart
    //     Products products = new Products();
    //     products.setProduct(product);
    //     products.setQuantity(productDTO.getQuantity());
    //     products.setColor(productDTO.getColor());
    //     cart.addProduct(products);

    //     cartRepository.save(cart);
    // }

    // public void addProductToCart(Long cartId, ProductDTO productDTO) {
    //     Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    //     Products product = productRepository.findById(productDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

    //     // Create a new Products instance and add it to the cart
    //     Products products = new Products();
    //     products.setProduct(product);
    //     products.setQuantity(productDTO.getQuantity());
    //     products.setColor(productDTO.getColor());
    //     cart.addProduct(products);

    //     cartRepository.save(cart);
    // }

//     @Override
//     public void addProductToCart(Long cartId, ProductInCartDTO productDTO) {
//     Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
//     Products product = productRepository.findById(productDTO.getProduct_id()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

//     // Create a new Products instance and add it to the cart
//     Products products = new Products();
//     products.setProduct(product);
//     products.setQuantity(productDTO.getQuantity());
//     products.setColor(productDTO.getColor());
//     cart.addProduct(products);

//     cartRepository.save(cart);
// }

@Override
    public CartDTO addProductToCart(Long cartId, ProductDTO productDTO) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        Products product = productRepository.findById(productDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        // Create a new Products instance and add it to the cart
        Products products = new Products();
        // products.setProduct(product);
        // products.setQuantity(productDTO.getQuantity());
        // products.setColor(productDTO.getColor());
        products.setProductName(product.getProductName());
    products.setPrice(product.getPrice());
    products.setUnitStock(product.getUnitStock());
    products.setProductDescription(product.getProductDescription());
    products.setProductImage(product.getProductImage());
    products.setCategory(product.getCategory());
        cart.addProduct(products);

        // Update the total quantity in the cart
        cart.setQuantity(cart.getQuantity() + productDTO.getQuantity());

        // Update the cart
        cartRepository.save(cart);

        return convertToDTO(cart);
    }



}
