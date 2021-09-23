package com.yy.ds.system.user.controller;

import javax.validation.Valid;

import com.yy.ds.common.api.CommonResult;
import com.yy.ds.system.user.domain.User;
import com.yy.ds.system.user.dto.UserReq;
import com.yy.ds.system.user.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册与修改")
    @PostMapping("/add")
    public CommonResult<User> add(@Valid @RequestBody UserReq userReq) {
        User user = new User();
        BeanUtils.copyProperties(userReq, user);
        return CommonResult.success(userService.save(user));
    }

    @ApiOperation(value = "注销用户")
    @PostMapping("delete/{id}")
    public CommonResult<Boolean> del(@PathVariable Long id) {
        return CommonResult.success(userService.deleteById(id));
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public CommonResult<User> findOne(@PathVariable Long id) {
        User user = userService.getById(id);
        user.setRoleList(null);
        return CommonResult.success(user);
    }
}