package org.clxmm.shiro.vo;

import lombok.Data;
import org.clxmm.shiro.utils.ToString;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-23 11:33 上午
 */
@Data
public class LoginVo extends ToString {



    /**登录名**/
    private String loginName;

    /**登录密码**/
    private String passWord;

    /**图片验证码**/
    private String radompicture;

    /**图片验证码key**/
    private String key;

    /**终端信息*/
    private String blackBox;

    /**终端类型(ios、android、h5)*/
    private String terminalType;

    /**登陆方式**/
    private String loginType;

    /**微信openId**/
    private String openId;

    /**系统**/
    private String systemCode;

    /**短信验证码**/
    private String checkCode;

    /**应用下载渠道code**/
    private String channelNo;

    /**推荐码**/
    private String recommendId;

    /**产品类型**/
    private String productType;

}
