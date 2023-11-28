package com.tatsiana.grocery.controller;

import com.tatsiana.grocery.dto.ProductDTO;
import com.tatsiana.grocery.model.Product;
import com.tatsiana.grocery.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/list")
    public String getLists(Model model) {
        List<Product> products = productsService.getAll();
        model.addAttribute("products", products);

        Map<String, List<String>> productMaps = new HashMap<String, List<String>>();
        for (Product item : products) {
            if (!productMaps.containsKey(item.getProductCategory())) {
                productMaps.put(item.getProductCategory(), new ArrayList<String>());
            }
            productMaps.get(item.getProductCategory()).add(item.getProductName());

        }
        model.addAttribute("productMap", productMaps);
        return "list";
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        productsService.deleteById(id);
    }

    @PostMapping("/product")
    private Long create(@Valid @RequestBody ProductDTO product) {
        return productsService.saveOrUpdate(product).getId();
    }

    @PutMapping("/{id}")
    private ProductDTO update(@Valid @RequestBody ProductDTO product, @PathVariable("id") Long id) {
        return productsService.update(product, id);
    }
}