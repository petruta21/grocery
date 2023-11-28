package com.tatsiana.grocery.controller;

import com.tatsiana.grocery.dto.ProductsListDTO;
import com.tatsiana.grocery.model.ProductsList;
import com.tatsiana.grocery.security.CustomUserDetails;
import com.tatsiana.grocery.service.ProductsListService;
import com.tatsiana.grocery.service.impl.ProductsListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/lists")
public class ProductsListController {
    @Autowired
    private ProductsListService productsListService;

    public ProductsListController(ProductsListServiceImpl productsListService) {
        this.productsListService = productsListService;
    }

    @GetMapping("/")
    public String getLists(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails userDetails) {
            Long userId = userDetails.getUser().getId();
            List<ProductsList> productsLists = productsListService.getAllByUserId(userId);
            model.addAttribute("lists", productsLists);
            model.addAttribute("userId", userId);
            return "index";

        } else {
            return "error"; // TODO implement error page
        }
    }

    @PostMapping("/")
    private String createList(Model model, @Valid @RequestParam("name") String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            Long userId = userDetails.getUser().getId();

            ProductsListDTO newList = new ProductsListDTO();
            newList.setUserId(userId);
            newList.setListName(name);
            ProductsListDTO resultList = productsListService.save(newList);
            model.addAttribute("listName", name);
            model.addAttribute("listId", resultList.getUserId());
            model.addAttribute("id", resultList.getId());
            return "productlist";
        }

        return "error";//TODO
    }

    @DeleteMapping("/{id}")
    public String deleteListById(@PathVariable("id") Long id) {
        productsListService.deleteById(id);
        return "index";
    }
}