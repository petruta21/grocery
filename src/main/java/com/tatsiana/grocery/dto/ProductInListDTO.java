package com.tatsiana.grocery.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductInListDTO {
    private Long id;
    private Long userId;
    private Long listId;
    @NotBlank(message = "Product name is mandatory")
    private String name;
    private String category;
    private String amount;
    private String amountSize;
    private boolean bought;
}
