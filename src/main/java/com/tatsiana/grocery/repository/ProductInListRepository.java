package com.tatsiana.grocery.repository;

import com.tatsiana.grocery.model.ProductInList;
import com.tatsiana.grocery.model.ProductsList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductInListRepository extends CrudRepository<ProductInList, Long> {
    List<ProductInList> findByProductsListOrderByName(ProductsList list);

    List<ProductInList> findAll();
}
