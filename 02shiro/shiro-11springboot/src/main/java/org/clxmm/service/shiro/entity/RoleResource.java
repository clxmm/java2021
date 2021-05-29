package org.clxmm.service.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sh_role_resource")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("ENABLE_FLAG")
    private String enableFlag;

    @TableField("ROLE_ID")
    private String roleId;

    @TableField("RESOURCE_ID")
    private String resourceId;


}
