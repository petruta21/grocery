package com.tatsiana.grocery.service.impl;

import com.tatsiana.grocery.dto.ProductsListDTO;
import com.tatsiana.grocery.model.ProductInList;
import com.tatsiana.grocery.model.ProductsList;
import com.tatsiana.grocery.model.User;
import com.tatsiana.grocery.repository.ProductsListRepository;
import com.tatsiana.grocery.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsListServiceImplTest {
    public static final long EXISTING_LIST_ID = 1L;
    public static final long NEW_LIST_ID = 5L;

    public static final long USER_ID = 20L;
    public static final String BANANAS = "bananas";
    public static final String MILK = "milk";
    public static final String WATER = "water";

    public static final String CATEGORY_BANANAS = "fruits";
    public static final String CATEGORY_MILK = "drinks";
    public static final String CATEGORY_WATER = "drinks";

    public static final String AMOUNT_BANANAS = "5";
    public static final String AMOUNT_MILK = "7";
    public static final String AMOUNT_WATER = "1";

    public static final String AMOUNT_SIZE_BANANAS = "5";
    public static final String AMOUNT_SIZE_MILK = "7";
    public static final String AMOUNT_SIZE_WATER = "1";

    public static final Boolean BOUGHT_BANANAS = false;
    public static final Boolean BOUGHT_MILK = false;
    public static final Boolean BOUGHT_WATER = false;

    @Mock
    private ProductsListRepository productsListRepository;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<ProductsList> productsListArgumentCaptor;

    @InjectMocks
    private ProductsListServiceImpl productsListService;

    @Test
    void should_copy_list_with_products() {

        ProductsList existingList = new ProductsList();
        existingList.setId(EXISTING_LIST_ID);

        ProductInList product1 = new ProductInList();
        product1.setName(BANANAS);
        product1.setCategory(CATEGORY_BANANAS);
        product1.setAmount(AMOUNT_BANANAS);
        product1.setAmountSize(AMOUNT_SIZE_BANANAS);
        product1.setBought(BOUGHT_BANANAS);
        existingList.setProducts(List.of(product1));

        ProductInList product2 = new ProductInList();
        product2.setName(MILK);
        product2.setCategory(CATEGORY_MILK);
        product2.setAmount(AMOUNT_MILK);
        product2.setAmountSize(AMOUNT_SIZE_MILK);
        product2.setBought(BOUGHT_MILK);
        existingList.setProducts(List.of(product2));

        ProductInList product3 = new ProductInList();
        product3.setName(WATER);
        product3.setCategory(CATEGORY_WATER);
        product3.setAmount(AMOUNT_WATER);
        product3.setAmountSize(AMOUNT_SIZE_WATER);
        product3.setBought(BOUGHT_WATER);
        existingList.setProducts(List.of(product3));

        Optional<ProductsList> optional = Optional.of(existingList);
        when(productsListRepository.findById(EXISTING_LIST_ID)).thenReturn(optional);
        when(productsListRepository.save(any())).thenAnswer(invocation -> {
            ProductsList savedList = invocation.getArgument(0);
            savedList.setId(NEW_LIST_ID);
            User user = new User();
            user.setId(USER_ID);
            savedList.setUser(user);
            return savedList;
        });

        ProductsListDTO result = productsListService.copy(EXISTING_LIST_ID, "NEW_NAME");
        assertNotNull(result);

        verify(productsListRepository).findById(EXISTING_LIST_ID);
        verify(productsListRepository).save(productsListArgumentCaptor.capture());
        ProductsList savedList = productsListArgumentCaptor.getValue();
        assertEquals("NEW_NAME", savedList.getListName());
        for (int i = 0; i < savedList.getProducts().size(); i++) {
            ProductInList newProduct = savedList.getProducts().get(i);
            ProductInList oldProduct = existingList.getProducts().get(i);
            assertEquals(newProduct.getName(), oldProduct.getName());
            assertEquals(newProduct.getCategory(), oldProduct.getCategory());
            assertEquals(newProduct.getAmount(), oldProduct.getAmount());
            assertEquals(newProduct.getAmountSize(), oldProduct.getAmountSize());
            assertEquals(newProduct.isBought(), oldProduct.isBought());
        }
    }
}