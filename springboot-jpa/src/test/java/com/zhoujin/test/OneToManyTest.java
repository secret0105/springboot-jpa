package com.zhoujin.test;

import com.zhoujin.dao.UserRepository;
import com.zhoujin.pojo.Role;
import com.zhoujin.pojo.User;
import com.zhoujin.springbootjpa.SpringbootJpaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.Optional;

/**
 * 一对多关联测试类
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class OneToManyTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave(){
        //创建一个用户
        User user = new User();
        user.setName("奇异博士");
        user.setAge(200);

        //创建一个对象
        Role role = new Role();
        role.setRolename("时间宝石");

        //关联
        role.getUsers().add(user);
        user.setRole(role);

        //保存
        userRepository.save(user);


    }
    /**
     * 查询一对多关联
     * springboot 2.2 版本
     *findOne()方法已改变，使用getOne()代替
     * getOne()需要加上TranSactional注解
     */
    @Test
    @Transactional
    public void testFind(){
        //查询
        User u = userRepository.getOne(20L);
        System.out.println(u);

        User byId =userRepository.findById(20L).get();
        System.out.println(byId);
    }
}
