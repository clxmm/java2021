package org.clxmm.service.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.clxmm.service.shiro.entity.Role;
import org.clxmm.service.shiro.mapper.RoleMapper;
import org.clxmm.service.shiro.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> getRolesList(List<String> rolesIds) {

        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Role::getId, rolesIds);

        return baseMapper.selectList(queryWrapper);
    }
}
