package com.tatsiana.grocery.repository;

import com.tatsiana.grocery.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();
}
