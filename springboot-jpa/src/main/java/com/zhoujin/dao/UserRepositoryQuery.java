package com.zhoujin.dao;

import com.zhoujin.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * 通过@Query注解来查询
 *
 */
public interface UserRepositoryQuery extends Repository<User,Long> {

    //使用HQL语句查询
    @Query("from User where name= ?1")
    List<User> queryByNameUseHQL(String name);

    //使用原生sql语句查询
    @Query(value = "select * from user where name = ?",nativeQuery = true)
    List<User> queryByNameUseSQL(String name);

    //更新
    @Query("update User set name= ?1 where id = ?2")
    @Modifying  //需要执行更新操作
    int updateNameById(String name, Long id);
}
