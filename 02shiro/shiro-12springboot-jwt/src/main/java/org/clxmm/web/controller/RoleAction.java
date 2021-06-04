package org.clxmm.web.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @Description
 * @create 2021-06-01 8:44 下午
 */
@RestController
@RequestMapping("roles")
public class RoleAction {




    @RequestMapping("/initRoles")
    @RequiresRoles(value = {"dev","SuperAdmin"},logical = Logical.OR)
    public String initRoles() {


        return "hello";
    }

}
