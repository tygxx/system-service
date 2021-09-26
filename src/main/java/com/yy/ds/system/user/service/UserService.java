package com.yy.ds.system.user.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.yy.ds.common.dto.UserDto;
import com.yy.ds.common.exception.Asserts;
import com.yy.ds.system.user.dao.UserDao;
import com.yy.ds.system.user.domain.Role;
import com.yy.ds.system.user.domain.User;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        User oldUserByUsername = userDao.findByUsername(user.getUsername());
        // 不能添加相同用户名
        if (user.getId() == null && oldUserByUsername != null) {
            Asserts.fail("该用户:" + user.getUsername() + " ，已经存在");
        } else {
            // 修改时如果没有role，直接save，它会把已有的中间表数据清理掉
            User oldUserById = userDao.findByIdFetchRole(user.getId());
            user.setRoleList(oldUserById.getRoleList());
            // 新修改的用户名不能和老用户名重复
            if (oldUserByUsername != null && oldUserByUsername.getId() != user.getId()) {
                Asserts.fail("该用户:" + user.getUsername() + " ，已经存在");
            }
        }

        // save方法：如果user有id，会先根据id去发一条查询sql，没有查询到数据则新增、查询到则修改
        return userDao.save(user);
    }

    public User getById(Long id) {
        return userDao.findById(id).get();
    }

    public UserDto findByUserName(String username) {
        User user = userDao.findByUsernameFetchRole(username);
        if (user != null) {
            List<Role> roleList = user.getRoleList();
            UserDto userDTO = new UserDto();
            BeanUtils.copyProperties(user, userDTO);
            if (!CollectionUtils.isEmpty(roleList)) {
                List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName())
                        .collect(Collectors.toList());
                userDTO.setRoles(roleStrList);
            }
            return userDTO;
        }
        return null;
    }

    @Transactional
    public Boolean deleteById(Long id) {
        userDao.deleteById(id);
        return true;
    }

}