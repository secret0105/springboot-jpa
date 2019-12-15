package com.zhoujin.dao;

import com.zhoujin.pojo.Role;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
