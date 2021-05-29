package org.clxmm.service.shiro.service;

import org.clxmm.service.shiro.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
public interface RoleService extends IService<Role> {

    List<Role> getRolesList(List<String> rolesIds);
}
