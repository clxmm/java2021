package org.clxmm.shiro.client;

import org.clxmm.shiro.tools.DigestsUtil;
import org.clxmm.shiro.tools.EncodesUtil;
import org.junit.Test;

import java.util.Map;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-15 7:26 下午
 */
public class ClientTest {

    /**
     * @Description 测试16进制编码
     */
    @Test
    public void testHex() {
        String val = "holle";
        String flag = EncodesUtil.encodeHex(val.getBytes());
        String valHandler = new String(EncodesUtil.decodeHex(flag));
        System.out.println("比较结果：" + val.equals(valHandler));
    }


    /**
     * @Description 测试base64编码
     */
    @Test
    public void testBase64() {
        String val = "holle";
        String flag = EncodesUtil.encodeBase64(val.getBytes());
        String valHandler = new String(EncodesUtil.decodeBase64(flag));
        System.out.println("比较结果：" + val.equals(valHandler));
    }


    @Test
    public void testDigestsUtil() {
        Map<String, String> map = DigestsUtil.entryptPassword("123");
        System.out.println("获得结果：" + map.toString());
    }
}
