package com.tatsiana.grocery.service.impl;

import com.tatsiana.grocery.dto.ProductDTO;
import com.tatsiana.grocery.model.Product;
import com.tatsiana.grocery.repository.ProductRepository;
import com.tatsiana.grocery.service.ProductsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {
    private ProductRepository productRepository;

    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> list() {
        return productRepository.findAll().stream().map(ProductsServiceImpl::convertToProductDTO).collect(Collectors.toList());
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO saveOrUpdate(ProductDTO productDto) {
        return convertToProductDTO(productRepository.save(convertToProduct(productDto)));
    }

    public ProductDTO update(ProductDTO productDto, Long id) {
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(productDto.getName());
        product.setProductCategory(productDto.getCategory());
        return convertToProductDTO(productRepository.save(product));
    }

    private Product convertToProduct(ProductDTO productDto) {
        Product product = new Product();
        product.setProductId(productDto.getId());
        product.setProductName(productDto.getName());
        product.setProductCategory(productDto.getCategory());
        return product;
    }

    private static ProductDTO convertToProductDTO(Product c) {
        ProductDTO result = new ProductDTO();
        result.setId(c.getId());
        result.setName(c.getProductName());
        result.setCategory(c.getProductCategory());
        return result;
    }
}
