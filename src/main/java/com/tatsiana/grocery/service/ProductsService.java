package com.tatsiana.grocery.service;

import com.tatsiana.grocery.dto.ProductDTO;
import com.tatsiana.grocery.model.Product;

import java.util.List;

public interface ProductsService {
    List<ProductDTO> list();

    List<Product> getAll();

    void deleteById(Long id);

    ProductDTO saveOrUpdate(ProductDTO productDto);

    ProductDTO update(ProductDTO productDto, Long id);
}
