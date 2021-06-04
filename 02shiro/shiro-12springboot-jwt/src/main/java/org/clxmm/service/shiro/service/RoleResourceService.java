package org.clxmm.service.shiro.service;

import org.clxmm.service.shiro.entity.RoleResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色资源表 服务类
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
public interface RoleResourceService extends IService<RoleResource> {

    List<RoleResource> getResourceIdsByRolesIds(List<String> rolesIds);
}
