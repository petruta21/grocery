package com.tatsiana.grocery.service;

import com.tatsiana.grocery.dto.ProductInListDTO;

import java.util.List;

public interface ProductInListService {

    List<ProductInListDTO> list();

    List<ProductInListDTO> getGroceryListByListId(Long listId);

    void deleteById(Long id);

    ProductInListDTO save(ProductInListDTO productInListDto);

    void updateProductInList(Long id, String amount, String amountSize);


}
