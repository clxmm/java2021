package service;

import java.util.List;
import java.util.Map;

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
    Map<String, String> findPasswordByLoginName(String name);


    /**
     * @param loginName 登录名称
     * @return
     * @Description 查找角色按用户登录名
     */
    List<String> findRoleByloginName(String loginName);

    /**
     * @param loginName 登录名称
     * @return
     * @Description 查找资源按用户登录名
     */
    List<String> findPermissionByloginName(String loginName);

}
