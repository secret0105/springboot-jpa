package com.zhoujin.dao;

import com.zhoujin.pojo.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 排序与分页接口
 *
 */
@Repository
public interface UserRepositoryPageAndSorting extends PagingAndSortingRepository<User,Long> {
}
