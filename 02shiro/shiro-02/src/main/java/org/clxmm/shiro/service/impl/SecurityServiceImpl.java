package org.clxmm.shiro.service.impl;

import org.clxmm.shiro.service.SecurityService;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-15 6:04 下午
 */
public class SecurityServiceImpl implements SecurityService {

    @Override
    public String findPasswordByLoginName(String name) {
        return "123";
    }
}
