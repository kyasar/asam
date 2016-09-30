package com.kyasar.service;

import com.kyasar.model.Product;

import java.util.List;

/**
 * Created by kadir on 29.09.2016.
 */
public interface ProductService {

    List<Product> findAll();

    Product findOne(String _id);

    Product create(Product p);

    Product update(Product p);

    void delete(String _id);
}
