package com.kyasar.service;

import com.kyasar.model.Product;
import com.kyasar.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kadir on 28.09.2016.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOne(String _id) {
        return productRepository.findOne(_id);
    }

    @Override
    public Product create(Product p) {
        if (p.get_id() != null) {
            return null;
        }
        return productRepository.save(p);
    }

    @Override
    public Product update(Product p) {
        Product pPresent = productRepository.findOne(p.get_id());
        if (pPresent == null) {
            // Cannot update
            return null;
        }

        return productRepository.save(p);
    }

    @Override
    public void delete(String _id) {
        productRepository.delete(_id);
    }
}
