package com.tatsiana.grocery.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "list")
@Data
@NoArgsConstructor
public class ProductsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "list_name")
    private String listName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "productsList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductInList> products;
}

