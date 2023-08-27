package com.sunbeaminfo.DTO;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductsDTO {
    private Long id;
    private ProductDTO product;

}
