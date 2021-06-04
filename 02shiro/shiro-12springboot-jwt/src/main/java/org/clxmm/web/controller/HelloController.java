package org.clxmm.web.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-18 9:21 下午
 */
@RestController
public class HelloController {


    @GetMapping("hello")
    public String hello() {
        return "hello";
    }




    @GetMapping("testRole")
    @RequiresRoles(value = {"dev","SuperAdmin"},logical = Logical.AND)
    public String testRole() {


        return "test role";
    }

}
