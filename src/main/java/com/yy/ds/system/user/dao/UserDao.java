package com.yy.ds.system.user.dao;

import com.yy.ds.system.user.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Long countByUsername(String username);

    User findByUsername(String username);

    @Query("from User u left join fetch u.roleList r where u.username = ?1")
    User findByUsernameFetchRole(String username);

    @Query("from User u left join fetch u.roleList r where u.id = ?1")
    User findByIdFetchRole(Long userId);
}