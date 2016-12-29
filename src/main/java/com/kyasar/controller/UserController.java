package com.kyasar.controller;

import com.kyasar.model.User;
import com.kyasar.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@Validated
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request; // for debug

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(
            value = "/user/{_id}",
            method=GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<User> getUser(
            @PathVariable(value="_id") String _id)
    {
        User user = userServiceImpl.findOne(_id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/user",
            method=GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Collection<User>> getUsers()
    {
        log.debug("GET " + request.getRequestURI());
        Collection<User> users = userServiceImpl.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/user",
            method= POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.ALL_VALUE)
    public @ResponseBody
    ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
        log.debug("POST " + request.getRequestURI());
        //if (bindingResult.hasErrors())
        //    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User savedUser = userServiceImpl.create(user);
        if (savedUser != null)
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(
            value = "/user/{_id}",
            method= DELETE)
    public ResponseEntity<User> removeProduct(
            @PathVariable(value="_id") String _id)
    {
        User present = userServiceImpl.findOne(_id);
        if (present == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userServiceImpl.delete(_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
