package service.impl;

import service.SecurityService;
import tools.DigestsUtil;

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
}
