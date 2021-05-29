package org.clxmm.service.shiro.service;

import org.clxmm.service.shiro.entity.UserRole;
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
public interface UserRoleService extends IService<UserRole> {

    List<UserRole> getRolesByUserId(String userId);
}
