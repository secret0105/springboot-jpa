package com.zhoujin.test;

import com.zhoujin.pojo.User;
import com.zhoujin.service.UserService;
import com.zhoujin.springbootcache.SpringbootCacheApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootCacheApplication.class)
public class CacheTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        User u = new User();
        u.setName("张三");
        u.setAge(15);
        userService.save(u);
    }

    /**
     * 缓存测试
     */
    @Test
    @Transactional
    public void testFind() {

        User user = userService.findById(1);

        System.out.println(user.getName());

        //第二次查询

        User u = userService.findById(1);
        System.out.println(u.getName());
    }
}
