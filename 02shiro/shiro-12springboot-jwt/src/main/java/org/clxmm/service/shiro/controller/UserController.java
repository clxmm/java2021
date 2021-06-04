package org.clxmm.service.shiro.controller;


import org.clxmm.service.shiro.entity.User;
import org.clxmm.service.shiro.service.UserService;
import org.clxmm.shiro.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
@RestController
@RequestMapping("/shiro/user")
public class UserController {



    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String save(UserVo userVo) {


        userService.saveOrUpdateUser(userVo);



        return "成功";
    }





}
