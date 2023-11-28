package com.tatsiana.grocery.service.impl;

import com.tatsiana.grocery.dto.ProductInListDTO;
import com.tatsiana.grocery.model.ProductInList;
import com.tatsiana.grocery.model.ProductsList;
import com.tatsiana.grocery.repository.ProductInListRepository;
import com.tatsiana.grocery.service.ProductInListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductInListServiceImpl implements ProductInListService {

    private final ProductInListRepository productInListRepository;

    public ProductInListServiceImpl(ProductInListRepository productInListRepository) {
        this.productInListRepository = productInListRepository;
    }

    public List<ProductInListDTO> list() {
        return productInListRepository
                .findAll()
                .stream()
                .map(ProductInListServiceImpl::convertToProductInListDTO)
                .collect(Collectors.toList());
    }

    public List<ProductInListDTO> getGroceryListByListId(Long listId) {
        ProductsList list = new ProductsList();
        list.setId(listId);
        List<ProductInList> res = productInListRepository.findByProductsListOrderByName(list);
        return res.stream().map(ProductInListServiceImpl::convertToProductInListDTO).collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        productInListRepository.deleteById(id);
    }

    @Transactional
    public ProductInListDTO save(ProductInListDTO productInListDto) {
        return convertToProductInListDTO(productInListRepository.save(convertToProductInList(productInListDto)));
    }

    @Transactional
    public void updateProductInList(Long id, String amount, String amountSize) {
        ProductInList productInList = productInListRepository.findById(id).orElseThrow(() -> new RuntimeException("unable to find product"));
        productInList.setAmount(amount);
        productInList.setAmountSize(amountSize);
        productInListRepository.save(productInList);
    }

    private ProductInList convertToProductInList(ProductInListDTO productInListDto) {
        ProductInList productInList = new ProductInList();
        ProductsList productsList = new ProductsList();
        productsList.setId(productInListDto.getListId());
        productInList.setProductsList(productsList);
        productInList.setName(productInListDto.getName());
        productInList.setAmount(productInListDto.getAmount());
        productInList.setCategory(productInListDto.getCategory());
        productInList.setAmountSize(productInListDto.getAmountSize());
        productInList.setBought(productInListDto.isBought());
        return productInList;
    }

    private static ProductInListDTO convertToProductInListDTO(ProductInList c) {
        ProductInListDTO result = new ProductInListDTO();
        result.setId(c.getId());
        result.setListId(c.getProductsList().getId());
        result.setName(c.getName());
        result.setAmount(c.getAmount());
        result.setCategory(c.getCategory());
        result.setAmountSize(c.getAmountSize());
        result.setBought(c.isBought());
        return result;
    }
}
