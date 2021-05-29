package org.clxmm.shiro.service;

/**
 * @author clxmm
 * @Description 模拟数据库操作接口
 * @create 2021-05-15 6:02 下午
 */
public interface SecurityService {


    /**
     * @param name 用户名称
     * @return 密码
     * @deprecated 查找用户密码
     */
    String findPasswordByLoginName(String name);


}
