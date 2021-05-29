package service.impl;

import service.SecurityService;
import tools.DigestsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-15 6:04 下午
 */
public class SecurityServiceImpl implements SecurityService {

    @Override
    public Map<String, String> findPasswordByLoginName(String name) {


        return DigestsUtil.entryptPassword("123");
    }

    @Override
    public List<String> findRoleByloginName(String loginName) {
        List<String> list = new ArrayList<>();
        list.add("admin");
        list.add("dev");
        return list;
    }

    @Override
    public List<String> findPermissionByloginName(String loginName) {
        List<String> list = new ArrayList<>();
        list.add("order:add");
        list.add("order:list");
        list.add("order:del");
        return list;
    }
}
