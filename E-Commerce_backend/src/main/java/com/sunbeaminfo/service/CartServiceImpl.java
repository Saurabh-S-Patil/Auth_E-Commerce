// package com.sunbeaminfo.service;

// import com.sunbeaminfo.DTO.CartDTO;
// import com.sunbeaminfo.DTO.ProductDTO;
// import com.sunbeaminfo.entities.Cart;
// import com.sunbeaminfo.entities.CartProducts;
// import com.sunbeaminfo.entities.Products;
// import com.sunbeaminfo.entities.User;
// import com.sunbeaminfo.dao.CartRepository;
// import com.sunbeaminfo.dao.ProductsRepository;
// import com.sunbeaminfo.dao.UserRepository;

// import org.springframework.beans.BeanUtils;
// import org.springframework.stereotype.Service;

// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import javax.persistence.EntityNotFoundException;

// @Service
// public class CartServiceImpl implements CartService {

//     private final CartRepository cartRepository;
//     private final UserRepository userRepository;
//     private final ProductsRepository productRepository;

//     public CartServiceImpl(CartRepository cartRepository, ProductsRepository productRepository,UserRepository userRepository) {
//         this.cartRepository = cartRepository;
//         this.productRepository = productRepository;
//         this.userRepository=userRepository;
//     }

//     @Override
//     public List<CartDTO> getAllCarts() {
//         List<Cart> carts = cartRepository.findAll();
//         return carts.stream().map(this::convertToDTO).collect(Collectors.toList());
//     }

//     @Override
//     public CartDTO getCartById(Long id) {
//         Optional<Cart> optionalCart = cartRepository.findById(id);
//         return optionalCart.map(this::convertToDTO).orElse(null);
//     }

//     @Override
//     public Cart createCart(CartDTO cartDTO) {
//         Cart cart = convertToEntity(cartDTO);
//         User user = userRepository.findById(cartDTO.getUserId()).get();
//         cart.setUser(user);
//         Cart savedCart = cartRepository.save(cart);
//         return savedCart;
//     }

//     @Override
//     public CartDTO updateCart(Long id, CartDTO cartDTO) {
//         Optional<Cart> optionalCart = cartRepository.findById(id);
//         if (optionalCart.isPresent()) {
//             Cart cart = optionalCart.get();
//             BeanUtils.copyProperties(cartDTO, cart, "id");
//             Cart updatedCart = cartRepository.save(cart);
//             return convertToDTO(updatedCart);
//         }
//         return null;
//     }

//     @Override
//     public void deleteCart(Long id) {
//         cartRepository.deleteById(id);
//     }

//     private CartDTO convertToDTO(Cart cart) {
//         CartDTO cartDTO = new CartDTO();
//         cartDTO.setId(cart.getId());
//         cartDTO.setUserId(cart.getUser().getId());
//         cartDTO.setProductsList(cart.getProductsList());
//         // BeanUtils.copyProperties(cart, cartDTO);
//         // cartDTO.setUserId(cart.getUser().getId()); 
//         return cartDTO;
//     }

//     private Cart convertToEntity(CartDTO cartDTO) {
//         Cart cart = new Cart();
//         BeanUtils.copyProperties(cartDTO, cart);
//         return cart;
//     }

//     @Override
//     public List<CartProducts> getAllProductsInCart(Long cartId) {
//         Cart cart = cartRepository.findById(cartId).orElse(null);
//         if (cart != null) {
//             return cart.getProductsList();
//         }
//         return Collections.emptyList();
//     }

//     // public void addProductToCart(Long cartId, ProductDTO productDTO) {
//     //     Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
//     //     Product product = productRepository.findById(productDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

//     //     // Create a new Products instance and add it to the cart
//     //     Products products = new Products();
//     //     products.setProduct(product);
//     //     products.setQuantity(productDTO.getQuantity());
//     //     products.setColor(productDTO.getColor());
//     //     cart.addProduct(products);

//     //     cartRepository.save(cart);
//     // }

//     // public void addProductToCart(Long cartId, ProductDTO productDTO) {
//     //     Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
//     //     Products product = productRepository.findById(productDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

//     //     // Create a new Products instance and add it to the cart
//     //     Products products = new Products();
//     //     products.setProduct(product);
//     //     products.setQuantity(productDTO.getQuantity());
//     //     products.setColor(productDTO.getColor());
//     //     cart.addProduct(products);

//     //     cartRepository.save(cart);
//     // }

// //     @Override
// //     public void addProductToCart(Long cartId, ProductInCartDTO productDTO) {
// //     Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
// //     Products product = productRepository.findById(productDTO.getProduct_id()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

// //     // Create a new Products instance and add it to the cart
// //     Products products = new Products();
// //     products.setProduct(product);
// //     products.setQuantity(productDTO.getQuantity());
// //     products.setColor(productDTO.getColor());
// //     cart.addProduct(products);

// //     cartRepository.save(cart);
// // }

// // @Override
// //     public CartDTO addProductToCart(Long cartId, ProductDTO productDTO) {
// //         Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
// //         Products product = productRepository.findById(productDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

// //         // Create a new Products instance and add it to the cart
// //         Products products = new Products();
// //         // products.setProduct(product);
// //         // products.setQuantity(productDTO.getQuantity());
// //         // products.setColor(productDTO.getColor());
// //         products.setProductName(product.getProductName());
// //     products.setPrice(product.getPrice());
// //     products.setUnitStock(product.getUnitStock());
// //     products.setProductDescription(product.getProductDescription());
// //     products.setProductImage(product.getProductImage());
// //     products.setCategory(product.getCategory());
// //         cart.addProduct(products);

// //         // Update the total quantity in the cart
// //         cart.setQuantity(cart.getQuantity() + productDTO.getQuantity());

// //         // Update the cart
// //         cartRepository.save(cart);

// //         return convertToDTO(cart);
// //     }



// }
package com.sunbeaminfo.service;

import com.sunbeaminfo.DTO.CartDTO;
import com.sunbeaminfo.DTO.ProductDTO;
import com.sunbeaminfo.entities.Cart;
import com.sunbeaminfo.entities.CartProducts;
import com.sunbeaminfo.entities.Products;
import com.sunbeaminfo.entities.User;
import com.sunbeaminfo.dao.CartRepository;
import com.sunbeaminfo.dao.ProductsRepository;
import com.sunbeaminfo.dao.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductsRepository productRepository,UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository=userRepository;
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
        cartProduct.setProductId(product);
        // cartProduct.setColor(color);
        cartProduct.setPrice(product.getPrice());
        cartProduct.setQuantity(0);
        Set<CartProducts> productsList = cart.getProductsList();
        productsList.add(cartProduct);
        cartRepository.save(cart);        
         
        return convertToDTO(cart);
    }

@Override
public Set<CartProducts> getAllProductsInCart(Long userId) {
User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    Cart cart = user.getCart();
    Set<CartProducts> productsList = cart.getProductsList();
return productsList;
}

@Override
@Transactional
public CartDTO removeProductFromCart(Long userId, Long productId) {
Products product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    Cart cart = user.getCart();
    Set<CartProducts> productsList = cart.getProductsList();
    productsList.remove(product);
    cartRepository.save(cart);
     
    return convertToDTO(cart);
}



// @Override
// @Transactional
// public CartDTO removeProductFromCart(Long userId, Long productId) {
//     Products product = productRepository.findById(productId)
//             .orElseThrow(() -> new EntityNotFoundException("Product not found"));

//     User user = userRepository.findById(userId)
//             .orElseThrow(() -> new EntityNotFoundException("User not found"));

//     Cart cart = user.getCart();

//     // Find the CartProducts object to remove
//     CartProducts productToRemove = null;
//     for (CartProducts cartProduct : cart.getProductsList()) {
//         if (cartProduct.getProductId().getId().equals(productId)) {
//             productToRemove = cartProduct;
//             break;
//         }
//     }

//     if (productToRemove != null) {
//         cart.removeProduct(productToRemove); // Utilize the removeProduct method in Cart class
//         cartRepository.save(cart);
//     } else {
//         throw new EntityNotFoundException("Product not found in user's cart");
//     }

//     return convertToDTO(cart);
// }




}