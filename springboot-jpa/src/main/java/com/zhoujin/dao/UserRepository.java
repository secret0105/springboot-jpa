package com.zhoujin.dao;

import com.zhoujin.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zj
 *
 * 直接继承JpaRepository  该接口已经实现了基础crud操作
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
