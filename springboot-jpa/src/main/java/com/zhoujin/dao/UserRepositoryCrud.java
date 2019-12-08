package com.zhoujin.dao;

import com.zhoujin.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * 使用crud接口
 *
 */
@Repository
public interface UserRepositoryCrud extends CrudRepository<User,Long> {
}
