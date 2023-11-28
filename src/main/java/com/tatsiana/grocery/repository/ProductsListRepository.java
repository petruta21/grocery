package com.tatsiana.grocery.repository;

import com.tatsiana.grocery.model.ProductsList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsListRepository extends CrudRepository<ProductsList, Long> {

    List<ProductsList> findAll();

    List<ProductsList> findAllByUserId(Long userId);
}
