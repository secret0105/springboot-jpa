package com.zhoujin.dao;

import com.zhoujin.pojo.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository接口
 *  方法名称命名查询
 *
 */

public interface UserRepositoryByName extends Repository<User,Long> {

    //方法的名称必须按照驼峰式规则命名

    /**
     * 按照名称查询
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 根据名称与年龄查询
     * @param name
     * @param age
     * @return
     */
    List<User> findByNameAndAge(String name, Integer age);

    /**
     * 模糊查询
     * @param name 需要查询的名称加上通配符 %
     * @return
     */
    List<User> findByNameLike(String name);

}
