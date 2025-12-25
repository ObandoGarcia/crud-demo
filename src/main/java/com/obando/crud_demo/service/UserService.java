package com.obando.crud_demo.service;

import com.obando.crud_demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);
}
