package com.sunbeaminfo.DTO;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductResponse {
    private Long id;
    private Set<CartProductsDTO> productsList;
}
