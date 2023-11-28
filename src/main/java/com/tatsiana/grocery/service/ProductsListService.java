package com.tatsiana.grocery.service;

import com.tatsiana.grocery.dto.ProductsListDTO;
import com.tatsiana.grocery.model.ProductsList;

import java.util.List;

public interface ProductsListService {

    List<ProductsListDTO> list();

    List<ProductsList> getAllByUserId(Long userId);

    ProductsListDTO get(Long listId);

    ProductsListDTO save(ProductsListDTO productsListDto);

    void deleteById(Long id);

    ProductsListDTO copy(Long listId, String newName);
}
