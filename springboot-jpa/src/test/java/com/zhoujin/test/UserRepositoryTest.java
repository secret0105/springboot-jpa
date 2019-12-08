package com.zhoujin.test;

import com.zhoujin.dao.*;
import com.zhoujin.pojo.User;
import com.zhoujin.springbootjpa.SpringbootJpaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.by;
import static org.springframework.data.domain.Sort.unsorted;

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

    @Autowired
    private UserRepositoryQuery userRepositoryQuery;

    @Autowired
    private UserRepositoryCrud repositoryCrud;

    @Autowired
    private UserRepositoryPageAndSorting pageAndSorting;

    @Autowired
    private UserRepositorySpecification specification;

    /**
     * 简单的插入操作
     *
     */
    @Test
    public void saveOne(){
        User u = new User();
        u.setName("zz");
        u.setAge(12);
        System.out.println(u);
        userRepository.save(u);
    }


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


    /**
     *
     * 使用注解查询+HQL语句
     */
    @Test
    public void testQueryByName(){
        List<User> list = userRepositoryQuery.queryByNameUseHQL("王五");
        for (User user:list
             ) {
            System.out.println(user);
        }
    }

    /**
     *
     * 使用注解查询+原生SQL
     */
    @Test
    public void testQueryByNameSQL(){
        List<User> list = userRepositoryQuery.queryByNameUseSQL("张三");
        for (User user:list
             ) {
            System.out.println(user);
        }
    }

    /**
     *
     * 更新操作
     */
    @Test
    @Transactional   //开启事务
    @Rollback(value = false)
    public void updateQueryById(){
        userRepositoryQuery.updateNameById("李四",8L);
    }


    /**
     *
     * crud接口操作
     *
     */
    @Test
    public void saveCrud(){
        User u = new User();
        u.setAge(13);
        u.setName("钢铁侠");
        repositoryCrud.save(u);
    }

    @Test
    public void testCrudFindOne(){
        Object u = repositoryCrud.findById(4L);
        System.out.println(u);

    }

    /**
     * count()方法统计表中对象的数目
     *
     */
    @Test
    public void testCrudCount(){
        long count = repositoryCrud.count();
        System.out.println(count);
    }


    /**
     *
     * 排序
     */
    @Test
    public void testSort(){
        //定义排序规则
        Order order = new Order(Direction.ASC,"age");

        //排序
        Sort sort = Sort.by(order);

        List<User> list = (List<User>) pageAndSorting.findAll(sort);

        for (User u:list
             ) {
            System.out.println(u);
        }
    }

    /**
     * 分页
     *
     */
    @Test
    public void testPage(){
        //获取分页对象并封装
        Pageable page = PageRequest.of(1, 2);

        //开始分页查询
        Page<User> pages = pageAndSorting.findAll(page);

        //获取总页数
        int totalPages = pages.getTotalPages();
        System.out.println("总页数"+totalPages);

        //获取总条数
        long totalElements = pages.getTotalElements();
        System.out.println("总条数"+totalElements);
        //获取分页信息
        List<User> list = pages.getContent();
        for (User user:list
             ) {

            System.out.println(user);
        }

    }
    /**
     *
     * 排序加分页
     */

    @Test
    public void testSortAndPage(){
        //定义排序规则
        Order order = new Order(Direction.ASC, "age");
        //封装排序

        Sort sort = Sort.by(order);

        //定义分页排序规则
        Pageable pageable = PageRequest.of(0, 3, sort);

        //获取分页对象
        Page<User> page = pageAndSorting.findAll(pageable);

        List<User> list = page.getContent();
        for (User u:list
             ) {
            System.out.println(u);
        }

    }

    /**
     *
     * 使用JAPRepository接口排序
     *
     */
    @Test
    public void testJpaSort(){
        Order order = new Order(Direction.DESC, "age");

        Sort sort = Sort.by(order);

        List<User> list = userRepository.findAll(sort);

        for (User u:list
             ) {
            System.out.println(u);
        }
    }

    /**
     *
     * 单条件查询
     */
    @Test
    public void testSpe(){

        //Specification封装查询条件
        Specification<User> spec = new Specification<User>() {

            /**
             *
             *
             * @param root 查询对象属性的封装
             * @param query 封装了我们要执行的查询中各个部分的信息
             * @param criteriaBuilder 查询条件的构造器，定义不同的查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("name"), "王五");

                return predicate;
            }
        };

        List<User> list = specification.findAll(spec);
        for (User u: list
             ) {
            System.out.println(u);
        }

    }

    /**
     *
     * 多条件查询
     */
    @Test
    public void testSpecMuilty(){


        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list = new ArrayList();
                Predicate predicate = criteriaBuilder.equal(root.get("name"), "李四");
                Predicate predicate1 = criteriaBuilder.equal(root.get("age"), 16);
                list.add(predicate);
                list.add(predicate1);

                Predicate[] pre = new Predicate[list.size()];
                //toArray()可返回泛型的类型
                //and()方法里面时可变参数，类型是Predicate，使用数组
                return criteriaBuilder.and(list.toArray(pre));


            }
        };
        List<User> list = specification.findAll(spec);
        for (User u:list
             ) {
            System.out.println(u);
        }
    }

    /**
     *
     * 多条件查询 简写
     */
    @Test
    public void testSpecSimple(){


        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //假设条件为where name = "lsi" and age =16 or id =2

                //and()方法里面时可变参数，类型是Predicate
                //先写and 在写or

                return criteriaBuilder.or(criteriaBuilder.and(criteriaBuilder.equal(root.get("name"),"李四"),
                        criteriaBuilder.equal(root.get("age"),16)),criteriaBuilder.equal(root.get("id"),3));


            }
        };
        List<User> list = specification.findAll(spec);
        for (User u:list
                ) {
            System.out.println(u);
        }
    }
}
