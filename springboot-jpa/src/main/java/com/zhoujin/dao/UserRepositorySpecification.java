package com.zhoujin.dao;

import com.zhoujin.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 使用JpaSpecificationExecutor接口
 *
 *
 */
@Repository
public interface UserRepositorySpecification extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {
}
