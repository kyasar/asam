package com.kyasar.repository;

import com.kyasar.model.Product;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kadir on 28.09.2016.
 */
@Repository
public class ProductRepositoryTemp {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Product addProduct(Product p) {
        mongoTemplate.save(p);
        return p;
    }

    public Product findById(String id) {
        Product p = mongoTemplate.findById(new ObjectId(id), Product.class);
        return p;
    }

    public List<Product> findAll() {
        List<Product> products = mongoTemplate.findAll(Product.class);
        return products;
    }
}
