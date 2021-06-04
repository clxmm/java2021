package org.clxmm.shiro.core.base;

import lombok.Data;
import org.clxmm.shiro.utils.ToString;

/**
 * @author clxmm
 * @Description 基础返回封装
 * @create 2021-06-01 8:13 下午
 */
@Data
public class BaseResponse  extends ToString {


    private Integer code ;

    private String msg ;

    private String date;

    private static final long serialVersionUID = -1;

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(Integer code, String msg, String date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }
}
