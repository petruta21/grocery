package com.tatsiana.grocery.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_list")
@NoArgsConstructor
public class ProductInList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "amount")
    private String amount;
    @Column(name = "amount_size")
    private String amountSize;
    @Column(name = "bought")
    private boolean bought;
    @ManyToOne
    @JoinColumn(name = "list_id")
    private ProductsList productsList;
}
