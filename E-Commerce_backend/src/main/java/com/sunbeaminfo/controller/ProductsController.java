package com.sunbeaminfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sunbeaminfo.DTO.FileResponseDTO;
import com.sunbeaminfo.entities.Products;
import com.sunbeaminfo.service.FileService;
import com.sunbeaminfo.service.ProductsService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Optional<Products> product = productsService.getProductById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // @PostMapping
    // public ResponseEntity<Products> createProduct(@RequestBody Products product) {
    //     Products createdProduct = productsService.createProduct(product);
    //     return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    // }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestPart("data") Products product,@RequestPart("image") MultipartFile image) {

        String fileName =null;
        try {
            fileName = this.fileService.uploadImage(path,image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        product.setProductImage(fileName);        
        Products createdProduct = productsService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products updatedProduct) {
        Products product = productsService.updateProduct(id, updatedProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productsService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Products>> getProductsByCategory(@PathVariable String category) {
        List<Products> products = productsService.getProductsByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
