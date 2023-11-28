package com.tatsiana.grocery.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductDTO {
    private Long id;
    @NotBlank(message = "Product name is mandatory")
    private String name;
    @NotBlank(message = "Product category is mandatory")
    private String category;
}
