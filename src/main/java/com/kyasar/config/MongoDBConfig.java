package com.kyasar.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

/**
 * Created by kadir on 28.09.2016.
 */
@Configuration
public class MongoDBConfig {

    @Autowired
    private Environment env;

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClient(), env.getProperty("spring.data.mongodb.database"));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException{
        return new MongoTemplate(mongoDbFactory());
    }

}