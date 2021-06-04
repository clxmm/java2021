package org.clxmm.service.shiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.clxmm.service.shiro.service.UserService;
import org.clxmm.shiro.constant.ShiroConstant;
import org.clxmm.shiro.core.base.BaseResponse;
import org.clxmm.shiro.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author clxmm
 * @Description 登陆
 * @create 2021-05-23 11:27 上午
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {


    @Autowired
    UserService userService;


    @GetMapping("/usersLongin")
    public Object usersLongin(LoginVo loginVo) {

        loginVo.setSystemCode(ShiroConstant.PLATFORM_MGT);

        try {
            Map<String, String> map = userService.login(loginVo);

            return map;

        } catch (Exception e) {
            log.error("登陆异常:{}", e);
        }
        return null;
    }


    @GetMapping("usersLongout")
    public Object usersLongout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "退出成功";
    }


    /**
     * 保存新密码
     */
    @PostMapping("saveNewPassword")
    public String saveNewPassword(String oldPassword,String newPassword) {



        boolean b = userService.saveNewPassword(oldPassword,newPassword);


        return "成功";
    }



    /**
     * @Description jwt的json登录方式
     * @param loginVo
     * @return
     */
    @RequestMapping("login-jwt")
    public BaseResponse LoginForJwt(@RequestBody LoginVo loginVo){
        return userService.routeForJwt(loginVo);
    }





}
