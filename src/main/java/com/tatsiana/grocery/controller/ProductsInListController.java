package com.tatsiana.grocery.controller;

import com.tatsiana.grocery.dto.ProductInListDTO;
import com.tatsiana.grocery.service.ProductInListService;
import com.tatsiana.grocery.service.ProductsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/grocery_list")
public class ProductsInListController {
    @Autowired
    private ProductInListService productInListService;
    @Autowired
    private ProductsListService productsListService;

    @GetMapping("/list/{userId}/{listId}")
    public String getUserListByListId(@PathVariable("userId") Long userId, @PathVariable("listId") Long listId, Model model) {
        List<ProductInListDTO> productInList = productInListService.getGroceryListByListId(listId);
        model.addAttribute("groceryLists", productInList);

        Map<Long, List<ProductInListDTO>> groceryListMaps = new LinkedHashMap<>();
        if (productInList != null) {
            for (ProductInListDTO item : productInList) {
                if (!groceryListMaps.containsKey(item.getListId())) {
                    groceryListMaps.put(item.getListId(), new ArrayList<>());
                }
                groceryListMaps.get(item.getListId()).add(item);
            }
        }
        model.addAttribute("groceryListMap", groceryListMaps);
        model.addAttribute("userId", userId);
        model.addAttribute("listId", listId);
        model.addAttribute("listName", productsListService.get(listId).getListName());
        return "productlist";
    }

    @PostMapping("/list/{userId}/{listId}")
    private String createProductInList(@PathVariable("userId") Long userId,
                                       @PathVariable("listId") Long listId,
                                       @Valid @RequestParam("name") String name,
                                       @Valid @RequestParam("amount") String amount,
                                       @Valid @RequestParam("amountSize") String amountSize,
                                       Model model) {
        ProductInListDTO newProduct = new ProductInListDTO();
        newProduct.setUserId(userId);
        newProduct.setListId(listId);
        newProduct.setName(name);
        newProduct.setCategory("");
        newProduct.setAmount(amount);
        newProduct.setAmountSize(amountSize);
        newProduct.setBought(false);

        productInListService.save(newProduct);
        return "productlist";
    }

    @PostMapping("/list/{userId}/{listId}/copy")
    private String copyProductInList(@PathVariable("userId") Long userId,
                                     @PathVariable("listId") Long listId,
                                     @Valid @RequestParam("name") String name,
                                     Model model) {
        productsListService.copy(listId, name);
        return "index";
    }

    @PutMapping("/list/{userId}/{listId}/{id}")
    private String updateProductInList(@PathVariable("userId") Long userId,
                                       @PathVariable("listId") Long listId,
                                       @PathVariable("id") Long id,
                                       @Valid @RequestParam("amount") String amount,
                                       @Valid @RequestParam("amountSize") String amountSize,
                                       Model model) {
        productInListService.updateProductInList(id, amount, amountSize);
        return "productlist";
    }

    @DeleteMapping("/list/{id}")
    public String deleteById(Long id) {
        productInListService.deleteById(id);
        return "productlist";
    }

    @DeleteMapping("/list/{userId}/{listId}/{id}")
    public String deleteProductInList(@PathVariable("userId") Long userId, @PathVariable("listId") Long listId, @PathVariable("id") Long id, Model model) {
        productInListService.deleteById(id);
        return "productlist";
    }
}
