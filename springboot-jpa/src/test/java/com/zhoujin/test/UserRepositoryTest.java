package com.zhoujin.test;

import com.zhoujin.dao.UserRepository;
import com.zhoujin.dao.UserRepositoryByName;
import com.zhoujin.pojo.User;
import com.zhoujin.springbootjpa.SpringbootJpaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 测试类
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryByName userRepositoryByName;

    /**
     * 简单的插入操作
     *
     */
//    @Test
//    public void saveOne(){
//        User u = new User();
//        u.setName("zz");
//        u.setAge(12);
//        System.out.println(u);
//        userRepository.save(u);
//    }


    /**
     * 通过名称查询
     */
    @Test
    public void findByName(){
        List<User> list = userRepositoryByName.findByName("张三");
        for (User user:list
             ) {
            System.out.println(user);
        }
    }

    /**
     * 模糊查询
     *
     */
    @Test
    public void testFindByNameLike(){
        List<User> list = userRepositoryByName.findByNameLike("王%");
        for (User user:list
                ) {
            System.out.println(user);
        }
    }

}
