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
 * 资源表
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sh_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 父资源
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 资源名称
     */
    @TableField("RESOURCE_NAME")
    private String resourceName;

    /**
     * 资源路径
     */
    @TableField("REQUEST_PATH")
    private String requestPath;

    /**
     * 资源标签
     */
    @TableField("LABEL")
    private String label;

    /**
     * 图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * 是否叶子节点
     */
    @TableField("IS_LEAF")
    private String isLeaf;

    /**
     * 资源类型
     */
    @TableField("RESOURCE_TYPE")
    private String resourceType;

    /**
     * 排序
     */
    @TableField("SORT_NO")
    private Integer sortNo;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 系统code
     */
    @TableField("SYSTEM_CODE")
    private String systemCode;

    /**
     * 是否根节点
     */
    @TableField("IS_SYSTEM_ROOT")
    private String isSystemRoot;

    /**
     * 是否有效
     */
    @TableField("ENABLE_FLAG")
    private String enableFlag;


}
