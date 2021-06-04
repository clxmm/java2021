package org.clxmm.shiro.core.base;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-19 9:08 下午
 */
public class SimpleToken extends UsernamePasswordToken {




    private String tokenType;

    private String quickPassword;

    /**
     * Constructor for SimpleToken
     * @param tokenType
     */
    public SimpleToken(String tokenType, String username,String password) {
        super(username,password);
        this.tokenType = tokenType;
    }

    public SimpleToken(String tokenType, String username,String password,String quickPassword) {
        super(username,password);
        this.tokenType = tokenType;
        this.quickPassword = quickPassword;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getQuickPassword() {
        return quickPassword;
    }

    public void setQuickPassword(String quickPassword) {
        this.quickPassword = quickPassword;
    }
}
