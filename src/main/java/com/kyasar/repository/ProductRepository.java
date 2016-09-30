package com.kyasar.repository;

import com.kyasar.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kadir on 29.09.2016.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
