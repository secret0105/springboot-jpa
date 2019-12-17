package com.zhoujin.service;

import com.zhoujin.pojo.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    User save(User user);


}
