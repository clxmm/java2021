package org.clxmm.service.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.clxmm.service.shiro.entity.Resource;
import org.clxmm.service.shiro.entity.Role;
import org.clxmm.service.shiro.entity.RoleResource;
import org.clxmm.service.shiro.entity.UserRole;
import org.clxmm.service.shiro.mapper.ResourceMapper;
import org.clxmm.service.shiro.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.clxmm.service.shiro.service.RoleResourceService;
import org.clxmm.service.shiro.service.RoleService;
import org.clxmm.service.shiro.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {


    @Autowired
    UserRoleService userRoleService;



    @Autowired
    RoleResourceService roleResourceService;

    /**
     * 根据用户id查询相关的资源
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> findResourcesIds(String userId) {


        // 根据用户查询到对应的角色
        List<UserRole> roles = userRoleService.getRolesByUserId(userId);


        List<String> rolesIds = roles.stream().map(UserRole::getRoleId).collect(Collectors.toList());


        // 根据 roles s 获取对应的资源id
        List<RoleResource> roleResourceList =  roleResourceService.getResourceIdsByRolesIds(rolesIds);


        List<String> resourceList = roleResourceList.stream().map(RoleResource::getResourceId).collect(Collectors.toList());


        return resourceList;
    }

    @Override
    public List<Resource> getListByIds(List<String> resourceIds) {

        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Resource::getId,resourceIds);
        return baseMapper.selectList(queryWrapper);
    }
}
