package com.yy.ds.system.user.service;

import javax.transaction.Transactional;

import com.yy.ds.system.user.dao.UserDao;
import com.yy.ds.system.user.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    public User getById(Long id) {
        return userDao.findById(id).get();
    }

    @Transactional
    public Boolean deleteById(Long id) {
        userDao.deleteById(id);
        return true;
    }
}