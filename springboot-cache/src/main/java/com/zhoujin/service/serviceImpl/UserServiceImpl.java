package com.zhoujin.service.serviceImpl;

import com.zhoujin.dao.UserRepository;
import com.zhoujin.pojo.User;
import com.zhoujin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> list = userRepository.findAll();

        return list;


    }

    @Override
    @Cacheable(value = "user") //对当前查询的对象做缓存,value与ehcache中配置的名称一致
    public User findById(Integer id) {

        User user = userRepository.getOne(id);
        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);

    }
}
