package com.kyasar.controller;

import com.kyasar.model.Point;
import com.kyasar.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by kadir on 30.09.2016.
 */
@RestController
public class PointController {

    @Autowired
    PointRepository pointRepository;

    @RequestMapping(
            value = "/point/{_id}",
            method=GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Point> getPoint(
            @PathVariable(value="_id") String _id)
    {
        Point p = pointRepository.findOne(_id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/point",
            method= POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Point> createPoint(@RequestBody Point p)
    {
        System.out.println(p.toString());

        Point savedPoint = pointRepository.save(p);
        if (savedPoint != null)
            return new ResponseEntity<>(savedPoint, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
