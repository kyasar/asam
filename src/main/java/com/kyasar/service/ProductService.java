package com.kyasar.service;

import com.kyasar.model.Product;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by kadir on 29.09.2016.
 */
public interface ProductService {

    List<Product> findAll();

    Product findOne(String _id);

    Product create(Product p);

    Product update(Product p);

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    void delete(String _id);
}
