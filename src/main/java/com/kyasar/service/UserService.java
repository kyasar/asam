package com.kyasar.service;

import com.kyasar.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findOne(String _id);

    User create(User p);

    User update(User p);

    void delete(String _id);
}
