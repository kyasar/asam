package com.kyasar.controller;

import com.kyasar.model.Product;
import com.kyasar.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by kadir on 25.09.2016.
 */
@RestController
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request; // for debug

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @RequestMapping(
            value = "/product/{_id}",
            method=GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Product> getProduct(
            @PathVariable(value="_id") String _id)
    {
        Product p = productServiceImpl.findOne(_id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/product",
            method=GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Collection<Product>> getProducts()
    {
        log.debug("GET " + request.getRequestURI());
        Collection<Product> products = productServiceImpl.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/product",
            method= POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Product> createProduct(@RequestBody Product p)
    {
        Product savedProduct = productServiceImpl.create(p);
        if (savedProduct != null)
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(
            value = "/product/{_id}",
            method= PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Product> updateProduct(
            @PathVariable(value="_id") String _id,
            @RequestBody Product p)
    {
        Product present = productServiceImpl.findOne(_id);
        if (present == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        p.set_id(present.get_id());

        Product savedProduct = productServiceImpl.update(p);
        if (savedProduct != null)
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(
            value = "/product/{_id}",
            method= DELETE)
    public ResponseEntity<Product> removeProduct(
            @PathVariable(value="_id") String _id)
    {
        Product present = productServiceImpl.findOne(_id);
        if (present == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        productServiceImpl.delete(_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
