package com.kyasar.repository;

import com.kyasar.model.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kadir on 30.09.2016.
 */
@Repository
public interface PointRepository extends MongoRepository<Point, String> {
}
