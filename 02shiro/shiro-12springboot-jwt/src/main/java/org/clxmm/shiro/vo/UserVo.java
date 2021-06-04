package org.clxmm.shiro.vo;

import org.clxmm.service.shiro.entity.User;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-23 11:59 上午
 */
public class UserVo extends User {




    /**
     * 当前拥有的角色Ids
     **/
    private String hasRoleIds;

    /**
     * 零时密码
     **/
    private String plainPassword;

    public String getHasRoleIds() {
        return hasRoleIds;
    }

    public void setHasRoleIds(String hasRoleIds) {
        this.hasRoleIds = hasRoleIds == null ? null : hasRoleIds.trim();
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword == null ? null : plainPassword.trim();
    }
}
