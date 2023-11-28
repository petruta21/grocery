package com.tatsiana.grocery.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Long productId;
    @Column(name = "name")
    private String productName;
    @Column(name = "category")
    private String productCategory;

    @Override
    public Long getId() {
        return productId;
    }

    @Override
    public boolean isNew() {
        return productId == null;
    }

}
