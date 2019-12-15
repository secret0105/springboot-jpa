package com.zhoujin.test;

import com.zhoujin.dao.RoleRepository;
import com.zhoujin.pojo.Menu;
import com.zhoujin.pojo.Role;
import com.zhoujin.springbootjpa.SpringbootJpaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 多对多测试类
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class ManyToManyTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testSave(){
        //创建角色
        Role role = new Role();
        role.setRolename("项目经理");


        //创建菜单
        Menu menu = new Menu();
        menu.setMenuname("xxx管理系统");
        menu.setFatherid(0l);

        Menu menu1 = new Menu();
        menu1.setMenuname("项目管理");
        menu1.setFatherid(1l);

        //关联
        role.getMenus().add(menu);
        role.getMenus().add(menu1);

        menu.getRoles().add(role);
        menu1.getRoles().add(role);

        //保存

        roleRepository.save(role);
    }

    /**
     *
     * 查询
     */
    @Test
    @Transactional
    public void testFind(){

        Role role = roleRepository.getOne(1l);
        System.out.println(role);
    }
}
