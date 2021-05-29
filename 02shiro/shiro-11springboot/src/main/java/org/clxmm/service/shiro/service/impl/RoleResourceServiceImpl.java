package org.clxmm.service.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.clxmm.service.shiro.entity.RoleResource;
import org.clxmm.service.shiro.mapper.RoleResourceMapper;
import org.clxmm.service.shiro.service.RoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色资源表 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    @Override
    public List<RoleResource> getResourceIdsByRolesIds(List<String> rolesIds) {
        LambdaQueryWrapper<RoleResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(RoleResource::getRoleId,rolesIds);
        return baseMapper.selectList(queryWrapper);
    }
}
