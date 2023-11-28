package com.tatsiana.grocery.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductsListDTO {
    private Long id;
    private Long userId;
    @NotBlank(message = "ListName is mandatory")
    private String listName;
}
