package org.clxmm.shiro.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author clxmm
 * @Description 序列化工具
 * @create 2021-05-20 8:50 下午
 */
public class ToString  implements Serializable {

    public ToString() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
