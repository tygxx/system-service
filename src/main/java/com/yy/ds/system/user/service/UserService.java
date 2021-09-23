package com.yy.ds.system.user.service;

import javax.transaction.Transactional;

import com.yy.ds.common.exception.Asserts;
import com.yy.ds.system.user.dao.UserDao;
import com.yy.ds.system.user.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.crypto.digest.BCrypt;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User save(User user) {
        // 将密码进行加密操作
        String encodePassword = BCrypt.hashpw(user.getPassword());
        user.setPassword(encodePassword);
        if (user.getId() == null) {
            Long usernameCount = userDao.countByUsername(user.getUsername());
            if (usernameCount > 0) {
                Asserts.fail("该用户:" + user.getUsername() + " ，已经存在");
            }
        } else if (user.getRoleList() == null) {
            // 修改时需要判断user中有没有role，如果没有role，直接save，它会把已有的中间表数据清理掉
            User oldUser = userDao.findByIdFetchRole(user.getId());
            user.setRoleList(oldUser.getRoleList());
            // 不让修改的参数
            user.setPassword(oldUser.getPassword());
        }

        // save方法：如果user有id，会先根据id去发一条查询sql，没有查询到数据则新增、查询到则修改
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