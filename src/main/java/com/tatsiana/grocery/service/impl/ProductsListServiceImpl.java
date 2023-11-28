package com.tatsiana.grocery.service.impl;

import com.tatsiana.grocery.dto.ProductsListDTO;
import com.tatsiana.grocery.model.ProductInList;
import com.tatsiana.grocery.model.ProductsList;
import com.tatsiana.grocery.repository.ProductsListRepository;
import com.tatsiana.grocery.repository.UserRepository;
import com.tatsiana.grocery.service.ProductsListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductsListServiceImpl implements ProductsListService {
    private final ProductsListRepository productsListRepository;
    private final UserRepository userRepository;

    public ProductsListServiceImpl(ProductsListRepository productsListRepository, UserRepository userRepository) {
        this.productsListRepository = productsListRepository;
        this.userRepository = userRepository;
    }

    public List<ProductsListDTO> list() {
        return null;
    }

    public List<ProductsList> getAllByUserId(Long userId) {
        return productsListRepository.findAllByUserId(userId);
    }

    public ProductsListDTO get(Long listId) {
        return convertToProductsListDTO(productsListRepository.findById(listId).orElseThrow(() -> new RuntimeException("Cannot get list")));
    }

    @Transactional
    public ProductsListDTO save(ProductsListDTO productsListDto) {
        return convertToProductsListDTO(productsListRepository.save(convertToProductsList(productsListDto)));
    }

    @Transactional
    public void deleteById(Long id) {
        productsListRepository.deleteById(id);
    }

    @Transactional
    public ProductsListDTO copy(Long listId, String newName) {
        ProductsList oldList = productsListRepository.findById(listId).orElseThrow(() -> new RuntimeException("Unable to find list to copy from"));
        ProductsList newList = new ProductsList();
        newList.setListName(newName);
        newList.setUser(oldList.getUser());
        newList.setProducts(oldList.getProducts().stream().map(oldProduct -> {
            ProductInList newProduct = new ProductInList();
            newProduct.setName(oldProduct.getName());
            newProduct.setAmount(oldProduct.getAmount());
            newProduct.setAmountSize(oldProduct.getAmountSize());
            newProduct.setCategory(oldProduct.getCategory());
            newProduct.setBought(false);
            newProduct.setProductsList(newList);
            return newProduct;
        }).toList());
        return convertToProductsListDTO(productsListRepository.save(newList));
    }

    private ProductsList convertToProductsList(ProductsListDTO productsListDto) {
        ProductsList productsList = new ProductsList();
        productsList.setId(productsListDto.getId());
        productsList.setUser(userRepository.findById(productsListDto.getUserId()).orElseThrow(() -> new RuntimeException("Unable to find user")));
        productsList.setListName(productsListDto.getListName());
        return productsList;
    }

    private ProductsListDTO convertToProductsListDTO(ProductsList c) {
        ProductsListDTO result = new ProductsListDTO();
        result.setId(c.getId());
        result.setUserId(c.getUser().getId());
        result.setListName(c.getListName());
        return result;
    }
}
