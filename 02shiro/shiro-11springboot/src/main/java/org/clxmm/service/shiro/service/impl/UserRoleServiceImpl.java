package org.clxmm.service.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.clxmm.service.shiro.entity.UserRole;
import org.clxmm.service.shiro.mapper.UserRoleMapper;
import org.clxmm.service.shiro.service.UserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<UserRole> getRolesByUserId(String userId) {

        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId,userId);

        return baseMapper.selectList(queryWrapper);
    }
}
