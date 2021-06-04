package org.clxmm.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.clxmm.shiro.constant.ShiroConstant;
import org.clxmm.shiro.core.base.BaseResponse;
import org.clxmm.shiro.core.impl.JwtTokenManager;
import org.clxmm.shiro.utils.EmptyUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author clxmm
 * @Description 自定义登录验证过滤器
 * @create 2021-06-01 8:14 下午
 */
public class JwtAuthcFilter extends FormAuthenticationFilter {


    private JwtTokenManager jwtTokenManager;

    public JwtAuthcFilter(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }


    /**
     * @Description 是否允许访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断当前请求头中是否带有jwtToken的字符串
        String jwtToken = WebUtils.toHttp(request).getHeader("jwtToken");

        if (StringUtils.isBlank(jwtToken)) {
            //没有没有：走原始校验
            return super.isAccessAllowed(request, response, mappedValue);
        }

        //如果有：走jwt校验
        boolean verifyToken = jwtTokenManager.isVerifyToken(jwtToken);
        if (verifyToken) {
            return super.isAccessAllowed(request, response, mappedValue);
        } else {
            return false;
        }
    }


    /**
     * @Description 访问拒绝时调用
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //判断当前请求头中是否带有jwtToken的字符串
        String jwtToken = WebUtils.toHttp(request).getHeader("jwtToken");
        //如果有：返回json的应答
        if (!EmptyUtil.isNullOrEmpty(jwtToken)){
            BaseResponse baseResponse = new BaseResponse(ShiroConstant.NO_LOGIN_CODE,ShiroConstant.NO_LOGIN_MESSAGE);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(baseResponse));
            return false;
        }
        //如果没有：走原始方式
        return super.onAccessDenied(request, response);
    }


}
